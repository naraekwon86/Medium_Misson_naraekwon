package com.ll.medium.domain.home.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdmHomeController {
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public String showMain(){
        return "domain/home/home/adm/main";
    }

}
