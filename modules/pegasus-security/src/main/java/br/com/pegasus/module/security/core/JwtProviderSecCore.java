package br.com.pegasus.module.security.core;

import br.com.pegasus.module.security.JwtTokenSecurity;
import br.com.pegasus.module.security.props.SecurityProps;
import br.com.pegasus.module.security.report.SecurityReport;
import br.com.pegasus.module.security.util.MethodSecurityUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Log4j2
public class JwtProviderSecCore {

  private static final String VERSION = "version";

  private final String audience;
  private final int validAfterSeconds;
  private final Integer expiresAt;
  private final boolean enabledLog;
  private final String version;

  private final OAuth2TokenValidatorResult success = OAuth2TokenValidatorResult.success();
  private final OAuth2TokenValidatorResult nbfFail = createError("Token ainda não é válido (nbf)");
  private final OAuth2TokenValidatorResult expFail = createError("Token expirado");
  private final OAuth2TokenValidatorResult iatFail = createError("Token emitido no futuro (iat)");
  private final OAuth2TokenValidatorResult algFail = createError("Algoritmo JWT inválido");
  private final OAuth2TokenValidatorResult audFail = createError("Audience inválida");
  private final OAuth2TokenValidatorResult verFail = createError("Version inválida");

  private final Duration tolerance = Duration.ofSeconds(60); // clock skew
  private final NimbusJwtDecoder decoder;
  private final JwtEncoder encoderGen;
  private final JwtClaimsSet.Builder builder;
  private final SecurityReport report;

  public JwtProviderSecCore(SecurityReport report) {
    this.report = report;
    SecurityProps props = report.getProp();
    audience = props.getAudience();
    validAfterSeconds = props.getValidAfterSeconds();
    expiresAt = props.getExpiresAt();
    enabledLog = props.isEnableLog();
    version = props.getVersion();
    decoder = MethodSecurityUtil.createNimbusJwtDecoder(props.getRsa().getPublicKey());
    encoderGen = MethodSecurityUtil.createJwtEncoder(props.getRsa().getPublicKey(), props.getRsa().getPrivateKey());
    builder = JwtClaimsSet.builder().issuer(props.getProjName()).audience(List.of(audience)).claim(VERSION, version);
  }

  public JwtTokenSecurity createTokenGenerator() {
    final String createTokenGeneratorTokenMsg = "JWT Token Generator initialized using RSA keys and audience '{}'";
    final String createTokenLogMsg = "New token created with ID: {}";

    JwtTokenSecurity jwtTokenSecurity = subject -> {
      Instant now = Instant.now();
      if (expiresAt != null) {// expires at check
        builder.expiresAt(now.plusSeconds(expiresAt));
      }
      JwtClaimsSet claims = builder//
          .subject(subject)//
          .id(UUID.randomUUID().toString()) //
          .issuedAt(now)//
          .notBefore(now.plusSeconds(validAfterSeconds))//
          .build();

      String tokenValue = encoderGen.encode(JwtEncoderParameters.from(claims)).getTokenValue();
      MethodSecurityUtil.logInfo(log, enabledLog, createTokenLogMsg, claims.getId());
      return tokenValue;
    };

    MethodSecurityUtil.logInfo(log, enabledLog, createTokenGeneratorTokenMsg, audience);
    return jwtTokenSecurity;
  }

  public JwtDecoder createDecoder() { // valida
    List<Jwt> requestTokenList = report.getRequestTokenList();
    decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(//
        jwt -> {
          requestTokenList.add(jwt);
          return validToken(jwt);
        }
    ));
    MethodSecurityUtil.logInfo(log, enabledLog, "JWT Decoder initialized with audience validation: '{}'", audience);
    return decoder;
  }

  private OAuth2TokenValidatorResult validToken(Jwt jwt) {
    Instant now = Instant.now();
    Instant iat = jwt.getIssuedAt();
    Instant nbf = jwt.getNotBefore();

    /* Verifica:
     * - igualdade de algoritmo
     * - quando foi criado (com distorção do relogio)
     * - se o token já esta disponível pra uso (tempo de carencia)
     * - se o token esta expirado */
    if (!"RS256".equals(jwt.getHeaders().get("alg").toString())) return algFail;
    if (iat != null && iat.isAfter(now.plus(tolerance))) return iatFail;
    if (nbf != null && now.isBefore(nbf)) return nbfFail;
    if (jwt.getExpiresAt() != null && now.isAfter(jwt.getExpiresAt())) return expFail;

    // custom
    if (!jwt.getClaim(VERSION).equals(version)) return verFail;
    if (!jwt.getAudience().contains(audience)) return audFail;
    return success;
  }

  private static OAuth2TokenValidatorResult createError(String msg) {
    return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token", msg, null));
  }

}