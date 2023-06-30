package ibf2022.tfip.simplesecondbrain.Server.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {
    
    private final BCryptPasswordEncoder passwordEncoder;

    
    public PasswordService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public PasswordService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
