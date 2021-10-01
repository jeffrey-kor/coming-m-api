package hyundaimeta.com.comingmapi.apis.auth.controller;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.MediaType; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import hyundaimeta.com.comingmapi.apis.auth.service.impl.AuthServiceImpl;
import hyundaimeta.com.comingmapi.apis.auth.dto.LoginDto;
import hyundaimeta.com.comingmapi.apis.auth.dto.UserCreateDto;
import hyundaimeta.com.comingmapi.apis.auth.service.AuthService;
import hyundaimeta.com.comingmapi.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.List; 
import java.util.Optional;

@RestController
@RequestMapping("auth")
@Api(tags = {"회원인증 및 회원관리 Api"})
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired 
    AuthServiceImpl authService;
    
    @PostMapping(value="/signIn")
    @ApiOperation(value = "유저 로그인", notes = "로그인을 한다.")
    public ResponseEntity<String> login(@Valid LoginDto loginDto,@ApiIgnore HttpSession session) throws Exception{ 
        boolean result = authService.login(loginDto);
        
        if(result) {
        	session.setAttribute("name", loginDto.getId());
        	System.out.println(session.getAttribute("name"));
        	return new ResponseEntity<String>( session.getId() + "\nHello " + session.getAttribute("name"), HttpStatus.OK); 
        }else {
        	return new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR); 
        }  
    }
    
    @DeleteMapping(value="/logout")
    @ApiOperation(value = "유저 로그아웃", notes = "로그아웃을 한다.")
    public ResponseEntity<String> logout(@ApiIgnore HttpSession session) throws Exception{ 
        
    	System.out.println("111111111111111111111111111");
        System.out.println(session.getAttribute("name"));
        session.removeAttribute("name");
        System.out.println("222222222222222222222222");
        System.out.println(session.getAttribute("name"));
        
        return new ResponseEntity<String>("OK", HttpStatus.OK);  
    }
    
    @PostMapping(value="/signUp")
    @ApiOperation(value = "유저 회원가입", notes = "유저 회원가입을 한다.")
    public ResponseEntity<String> insertUser(@Valid UserCreateDto userCreateDto) throws Exception{ 
        boolean result = authService.insertUser(userCreateDto);
        
        if(result) {
        	return new ResponseEntity<String>("OK", HttpStatus.OK); 
        }else {
        	return new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR); 
        }  
    }

    
    @GetMapping(value="/test")
    public ResponseEntity<String> test(){
    	return new ResponseEntity<String>("valid", HttpStatus.OK); 
    }
    
}
