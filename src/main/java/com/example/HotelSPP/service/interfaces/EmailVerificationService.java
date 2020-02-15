package com.example.HotelSPP.service.interfaces;

public interface EmailVerificationService {
    void sendVerificationMail(String email, String contextPath);

    void validateVerificationToken(String token);

    void activateUser(long userid);
}
