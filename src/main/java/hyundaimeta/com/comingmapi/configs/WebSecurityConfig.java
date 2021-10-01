	package hyundaimeta.com.comingmapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Bean
   public PasswordEncoder getPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //security �삁�쇅 url
        http.authorizeRequests()
                .antMatchers(
                		"/health",
                		"/swagger*/**",
                		"/v3/api-docs",
                		"/auth/signUp",
                		"/auth/signIn",
                		"/auth/logout"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        //BCryptPasswordEncoder 愿��젴
        http.cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions()
                .disable();
    }

}
