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
import com.ll.medium.global.rq.Rq.Rq;
import com.ll.medium.global.rsData.RsData.RsData;

import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

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
        RsData<Member> joinRs = memberService.join(joinForm.getUsername(),joinForm.getPassword());

        if (joinRs.isFail()){
            return rq.historyBack(joinRs.getMsg());
        }
        return rq.redirect(
                "/",
                joinRs.getMsg()
        );
    }
}
