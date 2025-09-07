package com.turkcell.Library.repository;

import com.turkcell.Library.entity.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PenaltyRepository extends JpaRepository<Penalty, Integer> {
    @Query("SELECT p FROM Penalty p WHERE p.loan.loan_id = :loanId")
    List<Penalty> findByLoanId(@Param("loanId") int loanId);

    @Query("SELECT p FROM Penalty p WHERE p.staff.staff_Id = :staffId")
    List<Penalty> findByStaffId(@Param("staffId") int staffId);

    List<Penalty> findByPaidFalse();
    List<Penalty> findByPaidTrue();

    @Query("SELECT SUM(p.amount) FROM Penalty p WHERE p.loan.member.id = :memberId AND p.paid = false")
    Integer getTotalUnpaidPenaltiesByMember(int memberId);
}