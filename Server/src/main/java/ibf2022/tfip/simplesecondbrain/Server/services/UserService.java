package ibf2022.tfip.simplesecondbrain.Server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.tfip.simplesecondbrain.Server.models.User;
import ibf2022.tfip.simplesecondbrain.Server.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

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

}

