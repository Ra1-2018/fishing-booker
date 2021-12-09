package com.isa.project.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {
    @Autowired VerificationTokenRepository tokenRepository;

    public VerificationToken save(VerificationToken token) { return tokenRepository.save(token); }

    public VerificationToken findByToken(String token) { return tokenRepository.findByToken(token); }
}
