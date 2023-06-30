package ibf2022.tfip.simplesecondbrain.Server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.tfip.simplesecondbrain.Server.repositories.UserDao;
import ibf2022.tfip.simplesecondbrain.Server.requests.AuthenticationRequest;
import ibf2022.tfip.simplesecondbrain.Server.ultils.JwtUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    
    private final AuthenticationManager authenticationManager;
    private final UserDao userDaoRepo;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println("user authenticated");
        final UserDetails user = userDaoRepo.findUserbyUserName(request.getUsername());
        if(user != null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("some error has occured.");
    };


}
