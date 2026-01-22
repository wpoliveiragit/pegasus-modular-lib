package br.com.pegasus.module.security.props;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@ConfigurationProperties(prefix = "api.security")
@Validated
@Getter
@Setter
public class SecurityProps {
  // obrigatorios
  private @jakarta.validation.Valid RsaProps rsa = new RsaProps();
  private @NotBlank String projName;
  private @NotBlank String audience;
  private @NotBlank String version;
  // não obrigatorios
  private boolean enableH2Console = false;
  private boolean enableLog = true;
  private @Min(0) @Max(60)int validAfterSeconds = 0;
  private @Min(1) Integer expiresAt;
  private List<String> openRoutes = List.of("/**");

}
