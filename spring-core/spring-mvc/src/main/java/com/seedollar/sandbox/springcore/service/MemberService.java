package com.seedollar.sandbox.springcore.service;

import com.seedollar.sandbox.springcore.domain.Member;

public interface MemberService {

    Long addMember(Member member);

    boolean deleteMember(Long memberId);


}
