package com.example.HotelSPP.service.implementation;

import com.example.HotelSPP.entity.User;
import com.example.HotelSPP.entity.UserRole;
import com.example.HotelSPP.entity.request.LoginRequest;
import com.example.HotelSPP.entity.request.RegisterRequest;
import com.example.HotelSPP.exceptions.LoginException;
import com.example.HotelSPP.repository.interfaces.UserRepository;
import com.example.HotelSPP.security.JwtTokenProvider;
import com.example.HotelSPP.service.interfaces.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;
    /*@Autowired
    private EmailVerificationService verificationService;
*/

    @Override
    public Boolean isUserPhoneUnique(String phone) {
        return !userRepository.findUserByPhone(phone).isPresent();
    }

    @Override
    public String authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.generateToken(authentication);
        }
        catch (AuthenticationException ex) {
            log.error(ex.getMessage());
            throw new LoginException("Логін або пароль неправильні. Перевірте дані, будь ласка.");
        }
    }

    @Override
    public boolean registerUser(RegisterRequest registerRequest) {
        if (userRepository.isSignedUp(registerRequest.getEmail())
                || userRepository.isSignedUp(registerRequest.getPhone())) {
            return false;
        }
        User toRegister = User.builder()
                .phone(registerRequest.getPhone())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .role(UserRole.USER)
                .build();

        userRepository.addUser(toRegister);
        return true;
    }
}
