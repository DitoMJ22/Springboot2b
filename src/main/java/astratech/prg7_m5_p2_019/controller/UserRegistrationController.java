package astratech.prg7_m5_p2_019.controller;

import astratech.prg7_m5_p2_019.model.DTO.UserRegistrationDto;
import astratech.prg7_m5_p2_019.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("users")
    public UserRegistrationDto userRegistrationDto() { return new UserRegistrationDto();}

    @GetMapping
    public String showRegistrationForm() { return "registration";}

    @PostMapping
    public String registerUserAccount(@ModelAttribute("users") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
