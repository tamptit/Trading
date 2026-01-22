package storm.server.gateway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import storm.server.gateway.model.UserDTO;

@RestController
@RequestMapping("/api")
public class RegisController {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/public/regis")
    public String registerPage() {
        return ("Register Page");
    }

    @PostMapping("/public/regis")
    public String registry (@RequestBody UserDTO dto) {
        return ("Register Post");
//        return userService.register(dto);
    }

    @PutMapping("/public/regis")
    public String updateUserInform (@RequestBody UserDTO dto) {
        return ("Register Putting");
    }

    @GetMapping("/public/{username}")
    public String encodePasswordTest (@PathVariable String username, @RequestParam String pwdTest) {
        UserDetails ud = userDetailsService.loadUserByUsername(username);
        System.out.println("pwdEncode= " + passwordEncoder.encode(pwdTest));
        String ta = "ta";
        String s1 = passwordEncoder.encode(ta);
        String s2 = passwordEncoder.encode(ta);
        String s3 = passwordEncoder.encode(ta);
        System.out.println("L1: " + s1);
        System.out.println("L2: " + s2);
        System.out.println("L3: " + s3);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("L1: " + encoder.matches(s1, "ta"));
        System.out.println("L2: " + encoder.matches(s2, "ta"));
        System.out.println("L3: " + encoder.matches(s3, "ta"));
        System.out.println("l1: " + passwordEncoder.matches(ta, s1));
        System.out.println("l2: " + passwordEncoder.matches(ta, s2));
        System.out.println("l3: " + passwordEncoder.matches(ta, s3));
        return "ok";
    }

}
