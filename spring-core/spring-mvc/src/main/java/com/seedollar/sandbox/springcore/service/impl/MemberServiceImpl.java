package com.seedollar.sandbox.springcore.service.impl;

import com.seedollar.sandbox.springcore.domain.Member;
import com.seedollar.sandbox.springcore.repository.MemberRepository;
import com.seedollar.sandbox.springcore.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Long addMember(Member member) {
        return memberRepository.add(member);
    }

    @Override
    public boolean deleteMember(Long memberId) {
        return memberRepository.remove(memberId);
    }
}
