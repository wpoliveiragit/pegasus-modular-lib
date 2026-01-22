package br.com.pegasus.module.security.props;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsaProps {
  private @NotBlank String publicKey;
  private @NotBlank String privateKey;
}
