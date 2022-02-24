/*
 * Copyright 2022 Thorsten Ludewig (t.ludewig@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
