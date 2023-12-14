package com.ll.medium.global.init;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ll.medium.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;

    @Bean
    public ApplicationRunner initNotProd(){
        return args -> {
            if (memberService.count() > 0) return;

            memberService.join("system", "system");
            memberService.join("admin", "admin");
            memberService.join("user1", "1234");
            memberService.join("user2", "1234");
        };
    }
}
