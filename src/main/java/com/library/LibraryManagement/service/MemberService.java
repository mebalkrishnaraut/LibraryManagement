package com.library.LibraryManagement.service;

import com.library.LibraryManagement.dto.MemberRequest;
import com.library.api.model.MemberResponse;

import java.util.List;

public interface MemberService {

    void createMember(MemberRequest request);
    List<MemberResponse> getAllMembers();
}
