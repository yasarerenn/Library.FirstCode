package com.turkcell.Library.service;

import com.turkcell.Library.entity.Book;
import com.turkcell.Library.entity.Loan;
import com.turkcell.Library.entity.Member;
import com.turkcell.Library.entity.Staff;
import com.turkcell.Library.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final MemberService memberService;
    private final StaffService staffService;

    public LoanService(LoanRepository loanRepository, BookService bookService,
                       MemberService memberService, StaffService staffService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.memberService = memberService;
        this.staffService = staffService;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(int id) {
        return loanRepository.findById(id);
    }

    public Loan createLoan(Loan loan) {
        // Validate member, book, and staff exist
        if (loan.getMember() != null) {
            memberService.getMemberById(loan.getMember().getId())
                    .orElseThrow(() -> new RuntimeException("Member not found"));
        }

        if (loan.getBook() != null) {
            Book book = bookService.getBookById(loan.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            // Check if book is available (you might want to check status)
            // For now, assume status id 1 = available, 2 = borrowed
        }

        if (loan.getStaff() != null) {
            staffService.getStaffById(loan.getStaff().getStaff_Id())
                    .orElseThrow(() -> new RuntimeException("Staff not found"));
        }

        // Set loan date if not provided
        if (loan.getLoanDate() == null) {
            loan.setLoanDate(new Date());
        }

        // Set default return date (e.g., 14 days from loan date) if not provided
        if (loan.getReturnDate() == null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(loan.getLoanDate());
            cal.add(Calendar.DAY_OF_MONTH, 14); // 14 days loan period
            loan.setReturnDate(cal.getTime());
        }

        // Set returned to false by default
        if (loan.getReturned() == null) {
            loan.setReturned(false);
        }

        return loanRepository.save(loan);
    }

    public Loan updateLoan(int id, Loan loanDetails) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        loan.setLoanDate(loanDetails.getLoanDate());
        loan.setReturnDate(loanDetails.getReturnDate());
        loan.setReturned(loanDetails.getReturned());

        if (loanDetails.getMember() != null) {
            Member member = memberService.getMemberById(loanDetails.getMember().getId())
                    .orElseThrow(() -> new RuntimeException("Member not found"));
            loan.setMember(member);
        }

        if (loanDetails.getBook() != null) {
            Book book = bookService.getBookById(loanDetails.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            loan.setBook(book);
        }

        if (loanDetails.getStaff() != null) {
            Staff staff = staffService.getStaffById(loanDetails.getStaff().getStaff_Id())
                    .orElseThrow(() -> new RuntimeException("Staff not found"));
            loan.setStaff(staff);
        }

        return loanRepository.save(loan);
    }

    public void deleteLoan(int id) {
        if (!loanRepository.existsById(id)) {
            throw new RuntimeException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }

    public List<Loan> getLoansByMember(int memberId) {
        return loanRepository.findByMember_Id(memberId);
    }

    public List<Loan> getLoansByBook(int bookId) {
        return loanRepository.findByBook_Id(bookId);
    }

    public List<Loan> getLoansByStaff(int staffId) {
        return loanRepository.findByStaffId(staffId);
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnedFalse();
    }

    public List<Loan> getReturnedLoans() {
        return loanRepository.findByReturnedTrue();
    }

    public List<Loan> getOverdueLoans() {
        return loanRepository.findOverdueLoans(new Date());
    }

    public List<Loan> getLoansBetweenDates(Date startDate, Date endDate) {
        return loanRepository.findLoansBetweenDates(startDate, endDate);
    }

    public Loan returnBook(int loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + loanId));

        if (loan.getReturned()) {
            throw new RuntimeException("Book has already been returned");
        }

        loan.setReturned(true);
        return loanRepository.save(loan);
    }
}
