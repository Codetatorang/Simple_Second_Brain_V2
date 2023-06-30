package ibf2022.tfip.simplesecondbrain.Server.repositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import ibf2022.tfip.simplesecondbrain.Server.services.PasswordService;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class UserDao {
        
        private static PasswordService passwordService = new PasswordService();
        private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
                new User(
                        "test",
                        passwordService.encodePassword("111"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );

        public UserDetails findUserbyUserName(String userName) {
                return APPLICATION_USERS.stream()
                        .filter(user -> user.getUsername().equals(userName))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("User not found..."));
        }
    
}
