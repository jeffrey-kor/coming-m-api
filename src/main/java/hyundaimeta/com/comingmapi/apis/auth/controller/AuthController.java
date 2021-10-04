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
import hyundaimeta.com.comingmapi.apis.auth.dto.MemberCreateDto;
import hyundaimeta.com.comingmapi.apis.auth.service.AuthService;
import hyundaimeta.com.comingmapi.entities.Member;
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
    public void login(@Valid LoginDto loginDto,@ApiIgnore HttpSession session) throws Exception{ 
        //spring security 사용 - (WebSecurityConfig,AuthServiceImpl(loadUserByUsername) 참고 )
    }
    
    @DeleteMapping(value="/logout")
    @ApiOperation(value = "유저 로그아웃", notes = "로그아웃을 한다.")
    public ResponseEntity<String> logout(@ApiIgnore HttpSession session) throws Exception{ 
        session.invalidate();
        return new ResponseEntity<String>("OK", HttpStatus.OK);  
    }
    
    @PostMapping(value="/signUp")
    @ApiOperation(value = "유저 회원가입", notes = "유저 회원가입을 한다.")
    public ResponseEntity<String> insertUser(@Valid MemberCreateDto memberCreateDto) throws Exception{ 
        boolean result = authService.insertUser(memberCreateDto);
        
        if(result) {
        	return new ResponseEntity<String>("OK", HttpStatus.OK); 
        }else {
        	return new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR); 
        }  
    }

    
    @GetMapping(value="/test")
    public ResponseEntity<String> test(@ApiIgnore HttpSession session){
    	System.out.println(session.getAttribute("role"));
    	System.out.println(session.getAttribute("connectedUser"));
    	return new ResponseEntity<String>("valid", HttpStatus.OK); 
    }
    
}
