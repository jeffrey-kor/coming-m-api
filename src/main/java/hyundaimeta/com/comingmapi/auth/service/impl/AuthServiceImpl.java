package hyundaimeta.com.comingmapi.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import hyundaimeta.com.comingmapi.auth.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
}
