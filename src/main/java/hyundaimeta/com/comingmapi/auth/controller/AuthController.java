package hyundaimeta.com.comingmapi.auth.controller;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.MediaType; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import hyundaimeta.com.comingmapi.auth.service.AuthService;
import hyundaimeta.com.comingmapi.entities.User;
import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest; 
import java.util.List; 
import java.util.Optional;

@RestController
@RequestMapping("auth")
@Api(tags = {"회원 인증(로그인,회원가입 등) 관련"})
public class AuthController {
    // 기본형 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired 
    AuthService authService;

    // 회원 입력 
    @PostMapping(value="/signUp") 
    public ResponseEntity<User> insertUser(HttpServletRequest req, User user){ 
        User result = authService.insertUser(user);
        return new ResponseEntity<User>(result, HttpStatus.OK); 
    }

}
