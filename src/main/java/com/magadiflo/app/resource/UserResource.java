package com.magadiflo.app.resource;

import com.magadiflo.app.domain.User;
import com.magadiflo.app.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

    private final IUserService userService;

    public UserResource(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> listAll() {
        return this.userService.listAll();
    }

    @PostMapping
    public User addOne(@RequestBody @Valid User user) {
        return this.userService.save(user);
    }

}
