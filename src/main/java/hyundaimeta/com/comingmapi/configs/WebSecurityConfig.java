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
        //security 예외 url
        http.authorizeRequests()
                .antMatchers("/api/v1/health").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        //BCryptPasswordEncoder 관련
        http.cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions()
                .disable();
    }

}
