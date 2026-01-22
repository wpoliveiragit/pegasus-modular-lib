# Pegasus Security

- Este projeto foi criado para fins de estudo
- O projeto esta no formato de biblioteca, por conta de fins praticos, mas o ideal era que ele fosse uma API REST
- Regras de negocio:
    - se for adicionado `api.security.expires-at`, o token passa a ter validade
    - se for adicionado `api.security.valid-after-seconds`, o token só pode ser usado apos a data da solicitação
      adicionado com o calor da propriedades (em segundo).
    -

## Adicionando a biblioteca

**Adicione a dependencia**

```xml

<dependency>
    <groupId>br.com.pegasus.modules</groupId>
    <artifactId>pegasus-security-lib</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Application.yml**

```yaml
api.security.proj-name: pegasus-api-rest-commerce
api.security.audience: security-gateway
api.security.expires-at: 15
api.security.valid-after-seconds: 5
api.security.version: v1
api.security.enable-h2-console: true
api.security.enable-log: true
api.security.open-routes:
  - /oauth/token

api.security.rsa.public-key: | # Obrigatorio, mas a chave pode ser alterada
  -----BEGIN PUBLIC KEY-----
  MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAomggTzv6/KXVmenuLKuZ
  gZlB7vmETmWi3fQoMzLOXkbSix0krE2ysOfaxa7NuIgKHwGFGfAweXRws4Qhdr+C
  mafBmGze1pZqtIIzzWvbgONan85W8i0nQs+BtQ+fY0PoUmfcYmnGDE+ma2xNRVOI
  ARVvAfh/AXM+p0iQ6cf/wrz2Idx7woEzaOlOIaEq4QFRoAtQYGNCFXucG68vGXov
  OSgJq9nbhabrbpQgHTn2OqlH8KIymYATo5G25Q9MGpEvSkgDYsCLdOxkgmZlUUuZ
  Zubbdo29vCJr9KG8Mr6FTuMQ5fFpydDHdAvLbffMWNzQcbWZPsbq6x58gvMAkXuP
  qQIDAQAB
  -----END PUBLIC KEY-----
api.security.rsa.private-key: | # Obrigatorio, mas a chave pode ser alterada
  -----BEGIN PRIVATE KEY-----
  MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQCiaCBPO/r8pdWZ
  6e4sq5mBmUHu+YROZaLd9CgzMs5eRtKLHSSsTbKw59rFrs24iAofAYUZ8DB5dHCz
  hCF2v4KZp8GYbN7Wlmq0gjPNa9uA41qfzlbyLSdCz4G1D59jQ+hSZ9xiacYMT6Zr
  bE1FU4gBFW8B+H8Bcz6nSJDpx//CvPYh3HvCgTNo6U4hoSrhAVGgC1BgY0IVe5wb
  ry8Zei85KAmr2duFputulCAdOfY6qUfwojKZgBOjkbblD0wakS9KSANiwIt07GSC
  ZmVRS5lm5tt2jb28Imv0obwyvoVO4xDl8WnJ0Md0C8tt98xY3NBxtZk+xurrHnyC
  8wCRe4+pAgMBAAECgf8g/QWTEpvotcatfNbkO/byXC8IW6aERiDO9Mk1nRxOChS6
  LTFFzoJ9bJsDjH48/WUQ5z4iBqfSA9TgofYqKLxeebTZQUQCUotaHxhmoYH0rDm8
  PC1tA7710zCFlqbJgwGtu2sIknOoDIXBTD5l1pHJppDl1ZP2t3ICiLrmwbkx1N1J
  pPxaJQmmBtN59UxpFQv06XTsur9l5yeKiSg3NhUZlzIsQaIyGTHSOi5eWm90gNNi
  LV6ptvcXOGP2EO9DzK9UH48kXajL/7EO3hAAbR0z0nkB7+SGBFrB4ldUtLG430o/
  IdhKuc96a+dGI+NvUuaIQ+ylDvT0SilzPO6p7UECgYEA0m6JN7fwv8CM+xQbXzOS
  +NBwwoNYl2evend3sqS2/+5lnrqPMGw8pLtBBOmmcYYJi2xtVXEGXY7n/z5MRSm1
  sKHIqPoOrIPiJQMm9zyBurzdslu01vmwO0uZDkqncnFAY+kwlLSx4w6lnh9T2CPh
  WZqaXlFfPw/xycm5V+r2ew0CgYEAxZNIEtPEr8Cowc6VrFLxBO1YhNJHW0giBtb4
  +MG33v2Zk33Vd6SUqY7JP6cZaBxPlbB/7LbUKET7CnNIdorIDMvnSlVfJ/yRsHKm
  OJJh6laoTGeZZH33trG0gIpSBumdsVNKOmEUisa3/ZNZTuoXWgKY+164Zt7iR7JV
  UITAkA0CgYBlZ/scLLoJzeKihDgEkrf6y4frVJx2qfK4U6YNIq9Fej7iVQj3ztES
  mWJl2arp/ivPUnsFt6fXoixOplje45C5A93NAGPd4tgx+ejnL/NcZ/N2JhdpmX3B
  tVaWuoci8UyUWSWeI82tm7tYDcS73d6U7ZoCZbg+jZJ5KYr1wTbyaQKBgAkooKiu
  xGlZRvlk+C860Q1nazkM/l3O5DTo9jWh1UIzA1GA/cpECNgrVEztFBJtbYsE2YlB
  mGgden2rhmpoWImvUhNyDa0u2hoR1n682mkgh0CzdLrh0//WEQX8Y30Ki1LTB5fZ
  NDiSqajJkdREqbx0bl8IrwelwVuNesL2xG5pAoGAUQh8OwHNTbAEr8vXHZXowE4D
  eZ8vXcmsRtETLQKWGeKFK+Ojm5JrHnYnGUaa1P5LjmHrbjOe32pDa8xyjcZ12Y/7
  UfnkcVhntvj1xY7l1cGfghjDsxzNYJ2C6DsbD4p4km+WHpdQu5+koQ82VJeESQZy
  ZfYfSpkgoD1441uXFdc=
  -----END PRIVATE KEY-----
```

**Explicando cada propriedade**

- `api.security.proj-name`
    - Obrigatório.
    - Usado para auditoria
    - Exemplo: pegasus-api-rest-commerce
- `api.security.audience`
    - Obrigatório.
    - Usado para alditoria
    - Exemplo: security-gateway
- `api.security.expires-at`
    - Opcional
    - exemplo: 60
- `api.security.valid-after-seconds`
    - opcional
    - Quando o token for criado, ele só será valido após este tempo 
    - Valor deve ser inteiro, positivo e no maximo de 60 segundo. 
    - Exemplo: 5




- `api.security.claim.name` version # Obrigatório para minha auditoria
- `api.security.claim.value` v1 # Obrigatório para minha auditoria
- `api.security.enable-h2-console` true # Opcional: permite o acesso ao console web do h2
- `api.security.enable-log` true # Opcional: habilita logs do security
- `api.security.open-routes` # Opcional: endpoints q são liberados do security
- `api.security.rsa.public-key` # Obrigatorio, mas a chave pode ser alterada
- `api.security.rsa.privare-key` # Obrigatorio, mas a chave pode ser alterada

**Adicione a anotação `@EnabledOAuthWebSecurity` em algum Bean do projeto**

## Uma sugestaode uso

- Crie um controller
    - Crie o endpoint para obter o token
    - opcional: crie um segundo endpoint para testar
- libere apenas o endpoint do token pela propriedade `api.security.open-routes` (lembrando q ela é uma lista)
- faça uma request
  **exemplo**

```java
import br.com.pegasus.module.security.JwtTokenSecurity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
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
```

**Onde adicionar o token?**
Por padrão OAuth2/JWT, o token vai no header Authorization.

**formato**

```
Authorization: Bearer <seu_token_aqui>
```