package com.stream.mini.mini_stream;

import com.stream.mini.mini_stream.dto.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public String signup(SignUpForm signUpForm, RedirectAttributes re) {
        try {
            userManager.createUser(signUpForm);
            System.out.println("---user doesn't exists---");
            return "redirect:/stream";
        } catch (UserExistsException ex) {
            re.addFlashAttribute("user_exists", "true");
            return "redirect:/index";
        }
    }

    @RequestMapping(value={"/", "/index"})
    public String index() {
        return "index";
    }
}
