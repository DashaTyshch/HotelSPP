package com.example.HotelSPP.service.implementation;

import com.example.HotelSPP.entity.request.LoginRequest;
import com.example.HotelSPP.exceptions.LoginException;
import com.example.HotelSPP.repository.interfaces.UserRepository;
import com.example.HotelSPP.security.JwtTokenProvider;
import com.example.HotelSPP.service.interfaces.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
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
    /*@Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;*/
    @Autowired
    private JwtTokenProvider tokenProvider;
    /*@Autowired
    private EmailVerificationService verificationService;
*/

    @Override
    public String authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.generateToken(authentication);
        }// catch (LockedException ex) {
           // throw new LockedException("Account is not verified by email ! Please check your mail !");
        //}
        catch (AuthenticationException ex) {
            log.error(ex.getMessage());
            throw new LoginException("Login or password was incorrect !");
        }
    }
}
