package hyundaimeta.com.comingmapi.handlers;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import hyundaimeta.com.comingmapi.repositories.MemberRepository;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
    private MemberRepository memberRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
		String userName = "";
		HttpSession session = request.getSession();
		Collection< GrantedAuthority > authorities = null;
		if(authentication.getPrincipal() instanceof Principal ) {
            userName = ((Principal)authentication.getPrincipal()).getName();
            session.setAttribute("role", "none");
        }else {
            User userSpringSecu = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            session.setAttribute("role", String.valueOf( userSpringSecu.getAuthorities()));
            session.setAttribute("connectedUser" , memberRepository.findByAccount( userSpringSecu.getUsername() ) );
        }
	}

}
