package userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import userservice.entity.User;

@RestController
@RequestMapping("/v1/user")
public class UserController {

	//private final UserService userService;
 
       
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    
    

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {
       // userService.registerNewUser(user);
        return "redirect:/signup?success";
    }

	@GetMapping("/")
    public String greeting() {
        return "Welcome to User service";
    }
	

       
    
}
