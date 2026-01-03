package com.library.LibraryManagement.controller;

import com.library.LibraryManagement.mapper.LibraryMapper;
import com.library.LibraryManagement.service.MemberService;
import com.library.api.MembersApi;
import com.library.api.model.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MembersController implements MembersApi {
    @Autowired
    MemberService memberService;

    @Override
    public ResponseEntity<Void> createMember(com.library.api.model.MemberRequest memberRequest) {
        memberService.createMember(LibraryMapper.toDto(memberRequest));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<MemberResponse>> getMembersList() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }
}
