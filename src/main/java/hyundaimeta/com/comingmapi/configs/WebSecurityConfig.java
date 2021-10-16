package hyundaimeta.com.comingmapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hyundaimeta.com.comingmapi.apis.auth.service.AuthService;
import hyundaimeta.com.comingmapi.filters.CustomUsernamePasswordAuthenticationFilter;
import hyundaimeta.com.comingmapi.handlers.CustomAuthenticationEntryPoint;
import hyundaimeta.com.comingmapi.handlers.CustomAuthenticationFailureHandler;
import hyundaimeta.com.comingmapi.handlers.CustomAuthenticationSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthService authService;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	public WebSecurityConfig(
			AuthService authService,
			CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
			CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
			CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
		
		this.authService = authService;
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
		this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
		this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
	}
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

	 @Bean
     public SessionRegistry sessionRegistry() {
         return new SessionRegistryImpl();
     }
	
	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
	 }
   
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 
	     http.sessionManagement()
	        .maximumSessions(2000)
	        .maxSessionsPreventsLogin(true)
	        .sessionRegistry(sessionRegistry())
	        .expiredUrl("/");
		 
		//entrypoint 클래스를 활용한 exception 처리
		http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
		 
        //security 접근 url
        http.authorizeRequests()
        		//ADMIN만 접근 가능
        		.antMatchers("/auth/admintest").hasAnyRole("ADMIN")
        		
        		//ADMIN,MEMBER만 접근 가능
        		.antMatchers("/auth/membertest").hasAnyRole("ADMIN,MEMBER")
        		
        		//모두 접근 가능
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
    		authFilter.setUsernameParameter("loginId");
    		authFilter.setPasswordParameter("password");
    		authFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
    		authFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return authFilter;
    	
    }
    
}
