package hyundaimeta.com.comingmapi.apis.auth.service;


import hyundaimeta.com.comingmapi.apis.auth.dto.LoginDto;
import hyundaimeta.com.comingmapi.apis.auth.dto.UserCreateDto;


public interface AuthService {

	boolean insertUser(UserCreateDto userCreateDto) throws Exception;

	boolean login(LoginDto loginDto);
    

}
