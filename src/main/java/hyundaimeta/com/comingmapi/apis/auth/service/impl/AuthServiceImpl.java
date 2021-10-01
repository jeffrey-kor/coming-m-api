package hyundaimeta.com.comingmapi.apis.auth.service.impl;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import hyundaimeta.com.comingmapi.apis.auth.dto.LoginDto;
import hyundaimeta.com.comingmapi.apis.auth.dto.UserCreateDto;
import hyundaimeta.com.comingmapi.apis.auth.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import hyundaimeta.com.comingmapi.repositories.UserRepository;
import hyundaimeta.com.comingmapi.entities.User;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
	ModelMapper modelMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean insertUser(UserCreateDto userCreateDto) {
    	
    	try {
//    		 Optional<User> user = userRepository.findById(7L);
//    		
//    		 user.ifPresent(selectUser->{
//    			 selectUser.setName("tes333");
//    			 userRepository.save(selectUser);
//    		 });
    		 
 
    		User user = modelMapper.map(userCreateDto, User.class);
    		
    		String encodedPassword = passwordEncoder.encode(user.getPassword());
    		user.setPassword(encodedPassword);
    		user.setSecsnYn("N");
    		userRepository.save(user);
    	} catch (DataIntegrityViolationException e) {
    		return false;
		} catch (Exception e) {
			return false;
		}   	    
    	return true;
    }

	@Override
	public boolean login(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return true;
	}

}
