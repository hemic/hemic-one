package com.hemic.one.web.rest;

import com.hemic.one.service.UserService;
import com.hemic.one.web.rest.vm.UserVm;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jor
 * @create 2021/11/15 15:29
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final UserService userService;

    public AccountResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerAccount(@Valid @RequestBody UserVm userVm) {
        return userService.create(userVm);
    }

    @PutMapping("/account/{id}")
    public void update(@PathVariable("id") String id, @Valid @RequestBody UserVm userVm) {
        userService.update(id, userVm);
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }


}
