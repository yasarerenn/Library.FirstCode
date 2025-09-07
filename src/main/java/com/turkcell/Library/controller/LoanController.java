package com.turkcell.Library.controller;

import com.turkcell.Library.dto.loan.request.CreateLoanRequest;
import com.turkcell.Library.dto.loan.response.CreatedLoanResponse;
import com.turkcell.Library.entity.Book;
import com.turkcell.Library.entity.Loan;
import com.turkcell.Library.entity.Member;
import com.turkcell.Library.entity.Staff;
import com.turkcell.Library.service.LoanService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<CreatedLoanResponse>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        List<CreatedLoanResponse> response = loans.stream()
                .map(this::convertToLoanResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedLoanResponse> getLoanById(@PathVariable int id) {
        return loanService.getLoanById(id)
                .map(loan -> ResponseEntity.ok(convertToLoanResponse(loan)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreatedLoanResponse> createLoan(@RequestBody CreateLoanRequest request) {
        try {
            Loan loan = convertToLoan(request);
            Loan savedLoan = loanService.createLoan(loan);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToLoanResponse(savedLoan));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<CreatedLoanResponse> returnBook(@PathVariable int id) {
        try {
            Loan returnedLoan = loanService.returnBook(id);
            return ResponseEntity.ok(convertToLoanResponse(returnedLoan));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable int id) {
        try {
            loanService.deleteLoan(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<CreatedLoanResponse>> getLoansByMember(@PathVariable int memberId) {
        List<Loan> loans = loanService.getLoansByMember(memberId);
        List<CreatedLoanResponse> response = loans.stream()
                .map(this::convertToLoanResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CreatedLoanResponse>> getActiveLoans() {
        List<Loan> loans = loanService.getActiveLoans();
        List<CreatedLoanResponse> response = loans.stream()
                .map(this::convertToLoanResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<CreatedLoanResponse>> getOverdueLoans() {
        List<Loan> loans = loanService.getOverdueLoans();
        List<CreatedLoanResponse> response = loans.stream()
                .map(this::convertToLoanResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<CreatedLoanResponse>> getLoansBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Loan> loans = loanService.getLoansBetweenDates(startDate, endDate);
        List<CreatedLoanResponse> response = loans.stream()
                .map(this::convertToLoanResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private CreatedLoanResponse convertToLoanResponse(Loan loan) {
        return new CreatedLoanResponse(
                loan.getLoan_id(),
                loan.getMember().getId(),
                loan.getMember().getFirstName() + " " + loan.getMember().getLastName(),
                loan.getBook().getId(),
                loan.getBook().getTitle(),
                loan.getStaff().getStaff_Id(),
                loan.getStaff().getFirstName() + " " + loan.getStaff().getLastName(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getReturned()
        );
    }

    private Loan convertToLoan(CreateLoanRequest request) {
        Loan loan = new Loan();

        Member member = new Member();
        member.setId(request.getMemberId());
        loan.setMember(member);

        Book book = new Book();
        book.setId(request.getBookId());
        loan.setBook(book);

        Staff staff = new Staff();
        staff.setStaff_Id(request.getStaffId());
        loan.setStaff(staff);

        loan.setReturnDate(request.getReturnDate());

        return loan;
    }
}
