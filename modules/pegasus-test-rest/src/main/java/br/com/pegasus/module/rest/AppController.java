package br.com.pegasus.module.rest;

import br.com.pegasus.module.security.JwtTokenSecurity;
import br.com.pegasus.module.security.report.SecurityReport;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AppController {

  private final JwtTokenSecurity jwtSecurity;
  private final JwtDecoder jwtDecoder;
  private final SecurityReport report;

  @GetMapping("/app/test")
  public ResponseEntity<String> appTest() {
    return ResponseEntity.ok("ok");
  }

  @PostMapping("/oauth/token")
  public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest request) {
    return ResponseEntity.ok(new TokenResponse(jwtSecurity.createToken(request.getUsername())));
  }

  // DTO de entrada
  @Setter
  @Getter
  public static class TokenRequest {
    private String username;
  }

  // DTO de saída
  public record TokenResponse(String token) {
  }
}

