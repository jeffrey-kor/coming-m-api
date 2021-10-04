package hyundaimeta.com.comingmapi.apis.auth.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import hyundaimeta.com.comingmapi.apis.auth.dto.MemberCreateDto;


public interface AuthService extends UserDetailsService {

	boolean insertUser(MemberCreateDto memberCreateDto) throws Exception;

}
