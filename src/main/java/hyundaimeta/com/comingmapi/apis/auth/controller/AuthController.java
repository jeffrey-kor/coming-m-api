package hyundaimeta.com.comingmapi.apis.auth.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hyundaimeta.com.comingmapi.apis.auth.service.impl.AuthServiceImpl;
import hyundaimeta.com.comingmapi.configs.ResponseConfig;
import hyundaimeta.com.comingmapi.apis.auth.dto.LoginDto;
import hyundaimeta.com.comingmapi.apis.auth.dto.MemberCreateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
@RequestMapping("auth")
@Api(tags = {"회원인증 및 회원관리 Api"})
public class AuthController {

    @Autowired 
    AuthServiceImpl authService;

    @Autowired
    ResponseConfig responseConfig;
    
    
    @PostMapping(value="/signIn")
    @ApiOperation(value = "유저 로그인", notes = "로그인을 한다.")
    public void login(@Valid LoginDto loginDto,@ApiIgnore HttpSession session) throws Exception{	
        //spring security 사용 - (WebSecurityConfig,AuthServiceImpl(loadUserByUsername) 참고 )
    }
    
    @DeleteMapping(value="/logout")
    @ApiOperation(value = "유저 로그아웃", notes = "로그아웃을 한다.")
    public ResponseEntity<Object> logout(@ApiIgnore HttpSession session) throws Exception{ 
        session.invalidate();
        return responseConfig.OK();
    }
    
    @PostMapping(value="/signUp")
    @ApiOperation(value = "유저 회원가입", notes = "유저 회원가입을 한다.")
    public ResponseEntity<Object> insertUser(@Valid MemberCreateDto memberCreateDto) throws Exception{ 
        boolean result = authService.insertUser(memberCreateDto);
        
        if(result) {
        	return responseConfig.OK();
        }else {
        	return responseConfig.ERROR();
        }  
    }

    
    @GetMapping(value="/admintest")
    public ResponseEntity<Object> admintest(@ApiIgnore HttpSession session){
    	System.out.println(session.getAttribute("role"));
    	System.out.println(session.getAttribute("connectedUser"));
    	return responseConfig.OK();
    }
    
    @GetMapping(value="/membertest")
    public ResponseEntity<Object> membertest(@ApiIgnore HttpSession session){
    	System.out.println(session.getAttribute("role"));
    	System.out.println(session.getAttribute("connectedUser"));
    	return responseConfig.OK();
    }
    
}
