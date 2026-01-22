package br.com.pegasus.module.security.report;

import br.com.pegasus.module.security.props.SecurityProps;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SecurityReport {
  private SecurityProps prop;
  private List<Jwt> requestTokenList = new ArrayList<>();
}
