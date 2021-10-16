package hyundaimeta.com.comingmapi.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import hyundaimeta.com.comingmapi.repositories.MemberRepository;

//로그인 성공했을때
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
		
		Map<String,Object> map = new HashMap<>();
		map.put("status","200");
		map.put("message","OK");

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, map);
        out.flush();
		
	}

}
