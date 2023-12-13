package com.ll.medium.domain.member.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    @GetMapping("/join")
    public String showJoin(){
        return "domain/member/member/join";
    }
    @Setter
    @Getter
    public static class JoinForm{
        private String username;
        private String password;
    }
    @PostMapping("/join")
    public String signup(@Valid JoinForm joinForm){
        return "redirect:/";
    }

}
