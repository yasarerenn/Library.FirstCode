package com.turkcell.Library.repository;

import com.turkcell.Library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByMember_Id(int memberId);
    List<Loan> findByBook_Id(int bookId);
    @Query("SELECT l FROM Loan l WHERE l.staff.staff_Id = :staffId")
    List<Loan> findByStaffId(@Param("staffId") int staffId);
    List<Loan> findByReturnedFalse();
    List<Loan> findByReturnedTrue();

    @Query("SELECT l FROM Loan l WHERE l.returnDate < :currentDate AND l.returned = false")
    List<Loan> findOverdueLoans(Date currentDate);

    @Query("SELECT l FROM Loan l WHERE l.loanDate BETWEEN :startDate AND :endDate")
    List<Loan> findLoansBetweenDates(Date startDate, Date endDate);
}