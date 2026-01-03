package com.library.LibraryManagement.service.impl;

import com.library.LibraryManagement.dataaceess.MemberRepository;
import com.library.LibraryManagement.dto.MemberRequest;
import com.library.LibraryManagement.model.Member;
import com.library.LibraryManagement.service.MemberService;
import com.library.api.model.MemberResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public void createMember(MemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        memberRepository.save(member);
    }

    @Override
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MemberResponse toResponse(Member member) {
        MemberResponse res = new MemberResponse();
        res.setId(member.getId());
        res.setName(member.getName());
        res.setEmail(member.getEmail());
        res.setPhone(member.getPhone());
        return res;
    }
}
