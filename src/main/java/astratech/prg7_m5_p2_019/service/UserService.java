package astratech.prg7_m5_p2_019.service;

import astratech.prg7_m5_p2_019.model.DTO.UserRegistrationDto;
import astratech.prg7_m5_p2_019.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users save(UserRegistrationDto registrationDto);

}
