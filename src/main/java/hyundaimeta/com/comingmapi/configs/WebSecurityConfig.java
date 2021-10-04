package hyundaimeta.com.comingmapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hyundaimeta.com.comingmapi.apis.auth.service.AuthService;
import hyundaimeta.com.comingmapi.filters.CustomUsernamePasswordAuthenticationFilter;
import hyundaimeta.com.comingmapi.handlers.CustomAuthenticationFailureHandler;
import hyundaimeta.com.comingmapi.handlers.CustomAuthenticationSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthService authService;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	public WebSecurityConfig(
			AuthService authService,
			CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
			CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
		
		this.authService = authService;
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
		this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
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
                		"/auth/signIn"
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
        
        
        http.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        
        
    }

    protected CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter() {
    	CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
		
    	try {
    		authFilter.setFilterProcessesUrl("/auth/signIn");
    		authFilter.setAuthenticationManager(this.authenticationManagerBean());
    		authFilter.setUsernameParameter("account");
    		authFilter.setPasswordParameter("password");
    		authFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
    		authFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return authFilter;
    	
    }
    
}
