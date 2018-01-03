package com.stream.mini.mini_stream;

import com.stream.mini.mini_stream.dto.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Application {

    @Autowired
    UserAccountsManager userManager;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping("/stream")
    public String stream() {
        return "stream";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(SignUpForm signUpForm) {
        userManager.createUser(signUpForm);
        return "redirect:/stream";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {

        if(error != null) {
            System.out.println("--- error ---");
            return "redirect:/index?error";
        }
        if(logout != null) {
            System.out.println("--- login ---");
            return "redirect:/index?logout";
        }
        return "login";
    }

    @RequestMapping(value={"/", "/index"})
    public String index() {
        return "index";
    }
}


//    private void createUserAccount(SignUpForm form) {
//         encoder = new BCryptPasswordEncoder();
//        User user BCryptPasswordEncoder= new User(form.getUname(), encoder.encode(form.getPassword()), "user");
//        JdbcUserDetailsManager userDetails = new JdbcUserDetailsManager();
//        userDetails.setJdbcTemplate(jdbcTemplate);
//        userDetails.createUser(user);
//        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//    }
