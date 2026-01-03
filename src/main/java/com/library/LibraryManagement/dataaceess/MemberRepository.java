package com.library.LibraryManagement.dataaceess;

import com.library.LibraryManagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
