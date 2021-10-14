package hyundaimeta.com.comingmapi.apis.auth.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import hyundaimeta.com.comingmapi.apis.auth.dto.MemberCreateDto;
import hyundaimeta.com.comingmapi.apis.auth.service.AuthService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import hyundaimeta.com.comingmapi.repositories.MemberRepository;
import hyundaimeta.com.comingmapi.entities.Member;


@Service
public class AuthServiceImpl implements AuthService {
    
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Override
    public boolean insertUser(MemberCreateDto memberCreateDto) {
    	
    	try {
//    		 Optional<User> user = userRepository.findById(7L);
//    		
//    		 user.ifPresent(selectUser->{
//    			 selectUser.setName("tes333");
//    			 userRepository.save(selectUser);
//    		 });
    		 
 
    		Member member = modelMapper.map(memberCreateDto, Member.class);
    		
    		String encodedPassword = passwordEncoder.encode(member.getPassword());
    		member.setPassword(encodedPassword);
    		member.setSecsnYn("N");
    		memberRepository.save(member);
    	} catch (DataIntegrityViolationException e) {
    		return false;
		} catch (Exception e) {
			return false;
		}   	    
    	return true;
    }

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		
		Member memberEntity = memberRepository.findByLoginId(loginId);
		if (memberEntity == null) { 
			throw new UsernameNotFoundException("UsernameNotFoundException"); 
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+memberEntity.getRole()));
		
		return new User(memberEntity.getLoginId(), memberEntity.getPassword(), authorities);
	}

}
