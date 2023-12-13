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
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.domain.member.member.entity.Member;
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

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
    public String join(@Valid JoinForm joinForm){
        Member member = memberService.join(joinForm.getUsername(), joinForm.getPassword());
        long id = member.getId();
        return "redirect:/?msg=No %d member joined.".formatted(id);
    }

}
