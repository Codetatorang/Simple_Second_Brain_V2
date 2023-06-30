package ibf2022.tfip.simplesecondbrain.Server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ibf2022.tfip.simplesecondbrain.Server.models.User;
// import ibf2022.tfip.simplesecondbrain.Server.repositories.UserRepository;
import ibf2022.tfip.simplesecondbrain.Server.repositories.UserRepository1;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService  implements UserDetailsService{
    @Autowired
    private UserRepository1 userRepo;
    // @Autowired
    // private UserRepository user1Repo;

    private final static String USER_NOT_FOUND_MSG = "user name: %s not found";

    public Optional<User> getUserByUserNameandPassword(String userName, String userPassword) {
        return userRepo.getUserByUserNameandPassword(userName, userPassword);
    }

    public Optional<User> getUserByUserName(String userName) {
        return userRepo.getUserByUserName(userName);
    }

    public Optional<User> getUserByPassword(String userPassword) {
        return userRepo.getUserByPassword(userPassword);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    public int saveUser(String userName, String userPassword, String accName, String email) {
        // check if user exists
        if (userRepo.getUserByEmail(email).isPresent() && userRepo.getUserByUserName(userName).isPresent()) {
            return -2;
        } else if (userRepo.getUserByUserName(userName).isPresent()) {
            return -1;
        } else if (userRepo.getUserByEmail(email).isPresent()) {
            return 0;
        }

        // else save user into mysql
        return userRepo.saveUser(userName, userPassword, accName, email);
    }

    public int deleteUser(String userName) {
        return userRepo.deleteUser(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // return user1Repo.findByUserName(username)
        //     .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
        return null;
    }

}

