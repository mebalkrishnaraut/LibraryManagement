package com.library.LibraryManagement.dataaceess;

import com.library.LibraryManagement.model.BorrowTransaction;
import com.library.LibraryManagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<BorrowTransaction, Long> {
    List<BorrowTransaction> findByMemberIdAndStatus(Long memberId, String status);
    List<BorrowTransaction> findByMemberAndReturnedAtIsNull(Member member);
}
