package de.neuefische.backend.controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    @GetMapping("me")
    public String getMe(){
      return  SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
