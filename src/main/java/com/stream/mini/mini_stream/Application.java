package com.stream.mini.mini_stream;

import com.stream.mini.mini_stream.database.DatabaseController;
import com.stream.mini.mini_stream.requests.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class Application {

    private final char[] hexArray = "0123456789ABCDEF".toCharArray();

    @Autowired
    DatabaseController db;

    @RequestMapping(value={"/index", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(Form form, Model model) {
        if(!validateLogin(form)) {
            model.addAttribute("message","Invalid Login");
            return "login";
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpUser(Form form, Model model) {
        if (signup(form)) {
            return "redirect:/index";
        }
        model.addAttribute("message","User already exists");
        return "signup";

    }

    private boolean validateLogin(Form form) {
        return db.validateLogin(form);
    }

    private boolean signup(Form form) {
        return db.signupUser(form);
    }
}
