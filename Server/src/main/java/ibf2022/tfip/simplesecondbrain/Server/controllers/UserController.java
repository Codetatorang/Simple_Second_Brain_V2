package ibf2022.tfip.simplesecondbrain.Server.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.tfip.simplesecondbrain.Server.models.User;
import ibf2022.tfip.simplesecondbrain.Server.requests.RegistrationRequest;
import ibf2022.tfip.simplesecondbrain.Server.services.UserService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userSvc;

    @PostMapping(path="/login" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserByName(@RequestBody User user) {
        System.out.println("user: " + user);
        Optional<User> userName = (userSvc.getUserByUserName(user.getUsername()));
        if (userName.isPresent()) { //username present
            JsonObject obj;
            obj = Json.createObjectBuilder()
                        .add("userName",  userName.get().getUsername())
                        .add("userPassword",  userName.get().getUserPassword())
                        .add("msg",  "User: " + user.getUsername() + " found")
                        .build();
            return ResponseEntity.ok(obj.toString());
        } else { //username not present
            JsonObject obj;
            obj = Json.createObjectBuilder()
                        .add("userName",  "null")
                        .add("msg",  "User: " + user.getUsername() + " not found")
                        .build();
            return ResponseEntity.ok(obj.toString());
        }
    }

    @PostMapping(path = "/createuser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        int result = userSvc.saveUser(user.getUsername(), user.getUserPassword(), user.getAccName(), user.getEmail());
        JsonObject obj;
        User userEmail, userUsername;

        switch(result){
            case -2:{ //email and username exists
                userEmail = userSvc.getUserByEmail(user.getEmail()).get();
                userUsername = userSvc.getUserByUserName(user.getUsername()).get();

                obj = Json.createObjectBuilder()
                        .add("userName", userUsername.getUsername())
                        .add("email", userEmail.getEmail())
                        .add("result", "User: " + user.getAccName() +  " or Email: "+ user.getEmail() +" already exists")
                        .build();
                return ResponseEntity.ok(obj.toString());
            }

            case -1:{ //username exists
                userUsername = userSvc.getUserByUserName(user.getUsername()).get();

                 obj = Json.createObjectBuilder()
                        .add("userName", userUsername.getUsername())
                        .add("result", "User: " + user.getUsername() +" already exists")
                        .build();
                return ResponseEntity.ok(obj.toString());
            }
            
            case 0:{ //email exists
                userEmail = userSvc.getUserByEmail(user.getEmail()).get();

                obj = Json.createObjectBuilder()
                        .add("email", userEmail.getEmail())
                        .add("result", "Email: " + user.getEmail() +" already exists")
                        .build();
                return ResponseEntity.ok(obj.toString());
            }

            default:{ //user created
                obj = Json.createObjectBuilder()
                        .add("userName", user.getAccName())
                        .add("result", "User " + user.getAccName() + " Created")
                        .build();
                return ResponseEntity.ok(obj.toString());
            }
        }
    }


    @GetMapping("/register")
    public String register(RegistrationRequest request){
        return "works";
    }
}