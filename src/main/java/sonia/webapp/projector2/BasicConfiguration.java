package sonia.webapp.projector2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import sonia.webapp.projector2.configuration.Credentials;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter
{

  private final static Logger LOGGER = LoggerFactory.getLogger(
    BasicConfiguration.class.getName());

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {

    Credentials admin
      = sonia.webapp.projector2.configuration.Configuration.
        getActiveConfiguration().getAdmin();

    LOGGER.trace("admin user = {}, admin password = {}", admin.getUser(), admin.
      getPassword());

    PasswordEncoder encoder
      = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    auth
      .inMemoryAuthentication()
      .withUser(admin.getUser())
      .password(encoder.encode(admin.getPassword()))
      .roles("USER", "ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http
      .authorizeRequests()
      .antMatchers(
        "/api/**",
        "/done",
        "/cancel",
        "/service.xml"
      ).permitAll()
      .antMatchers("/admin/**").hasAnyRole("ADMIN")
      .anyRequest()
      .authenticated()
      .and()
      .httpBasic();
  }
}
