package com.ll.medium.domain.member.member.service;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.ll.medium.global.rsData.RsData.RsData;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<Member> join(String username,String password){
        if (List.of("admin","system").contains(username)){
            return RsData.of("400-1" , "%s(은)는 사용할 수 없는 아이디 입니다.".formatted(username));
        }
        if (findByUsername(username).isPresent()){
            return RsData.of("400-2","이미 존재하는 회원입니다.");
        }
        Member member = Member.builder()
                        .username(username)
                                .password(passwordEncoder.encode(password))
                                        .build();
        memberRepository.save(member);
        return RsData.of("200","/%s님 환영합니다.회원가입이 완료되었습니다.로그인 후 이용해 주세요".formatted(member.getUsername()),member);
    }
    public Optional<Member> findByUsername(String username){
        return memberRepository.findByUsername(username);
    }
    public long count(){
        return memberRepository.count();
    }

}
