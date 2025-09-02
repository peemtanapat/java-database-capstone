
package com.project.back_end.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.project.back_end.DTO.Login;
import com.project.back_end.DTO.response.LoginResponseDTO;
import com.project.back_end.services.LoginService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDTO postMethodName(@RequestBody Login request) {
        String token = loginService.validateAdmin(request.getUsername(), request.getPassword());

        return new LoginResponseDTO(token);
    }

}
