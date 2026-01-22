package br.com.pegasus.module.security.config;

import br.com.pegasus.module.security.JwtTokenSecurity;
import br.com.pegasus.module.security.core.JwtProviderSecCore;
import br.com.pegasus.module.security.core.OAuthWebSecCore;
import br.com.pegasus.module.security.props.SecurityProps;
import br.com.pegasus.module.security.report.SecurityReport;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableConfigurationProperties(SecurityProps.class)
public class BeanSecConfig {

  @Bean
  public SecurityReport createSecurityReport(SecurityProps props){
    SecurityReport securityReport = new SecurityReport();
    securityReport.setProp(props);
    return securityReport;
  }

  @Bean
  public SecurityFilterChain createRequestFilterConfig(HttpSecurity http, JwtDecoder jwtDecoder, SecurityProps props) throws Exception {
    return new OAuthWebSecCore().createRequestFilterConfig(http, jwtDecoder, props);
  }

  @Bean
  public JwtTokenSecurity createTokenGenerator(SecurityReport report) {
    return new JwtProviderSecCore(report).createTokenGenerator();
  }

  @Bean
  public JwtDecoder createDecoderGenerator(SecurityReport report) {
    return new JwtProviderSecCore(report).createDecoder();
  }

}