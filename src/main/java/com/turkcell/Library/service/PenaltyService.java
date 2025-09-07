package com.turkcell.Library.service;

import com.turkcell.Library.entity.Loan;
import com.turkcell.Library.entity.Penalty;
import com.turkcell.Library.entity.Staff;
import com.turkcell.Library.repository.PenaltyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaltyService {
    private final PenaltyRepository penaltyRepository;
    private final LoanService loanService;
    private final StaffService staffService;

    public PenaltyService(PenaltyRepository penaltyRepository, LoanService loanService, StaffService staffService) {
        this.penaltyRepository = penaltyRepository;
        this.loanService = loanService;
        this.staffService = staffService;
    }

    public List<Penalty> getAllPenalties() {
        return penaltyRepository.findAll();
    }

    public Optional<Penalty> getPenaltyById(int id) {
        return penaltyRepository.findById(id);
    }

    public Penalty createPenalty(Penalty penalty) {
        // Validate loan and staff exist
        if (penalty.getLoan() != null) {
            loanService.getLoanById(penalty.getLoan().getLoan_id())
                    .orElseThrow(() -> new RuntimeException("Loan not found"));
        }

        if (penalty.getStaff() != null) {
            staffService.getStaffById(penalty.getStaff().getStaff_Id())
                    .orElseThrow(() -> new RuntimeException("Staff not found"));
        }

        // Set paid to false by default
        if (penalty.getPenalty_id() == 0) { // new penalty
            penalty.setPaid(false);
        }

        return penaltyRepository.save(penalty);
    }

    public Penalty updatePenalty(int id, Penalty penaltyDetails) {
        Penalty penalty = penaltyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Penalty not found with id: " + id));

        penalty.setAmount(penaltyDetails.getAmount());
        penalty.setPaid(penaltyDetails.isPaid());

        if (penaltyDetails.getLoan() != null) {
            Loan loan = loanService.getLoanById(penaltyDetails.getLoan().getLoan_id())
                    .orElseThrow(() -> new RuntimeException("Loan not found"));
            penalty.setLoan(loan);
        }

        if (penaltyDetails.getStaff() != null) {
            Staff staff = staffService.getStaffById(penaltyDetails.getStaff().getStaff_Id())
                    .orElseThrow(() -> new RuntimeException("Staff not found"));
            penalty.setStaff(staff);
        }

        return penaltyRepository.save(penalty);
    }

    public void deletePenalty(int id) {
        if (!penaltyRepository.existsById(id)) {
            throw new RuntimeException("Penalty not found with id: " + id);
        }
        penaltyRepository.deleteById(id);
    }

    public List<Penalty> getPenaltiesByLoan(int loanId) {
        return penaltyRepository.findByLoanId(loanId);
    }

    public List<Penalty> getPenaltiesByStaff(int staffId) {
        return penaltyRepository.findByStaffId(staffId);
    }

    public List<Penalty> getUnpaidPenalties() {
        return penaltyRepository.findByPaidFalse();
    }

    public List<Penalty> getPaidPenalties() {
        return penaltyRepository.findByPaidTrue();
    }

    public Integer getTotalUnpaidPenaltiesByMember(int memberId) {
        Integer total = penaltyRepository.getTotalUnpaidPenaltiesByMember(memberId);
        return total != null ? total : 0;
    }

    public Penalty markPenaltyAsPaid(int penaltyId) {
        Penalty penalty = penaltyRepository.findById(penaltyId)
                .orElseThrow(() -> new RuntimeException("Penalty not found with id: " + penaltyId));

        if (penalty.isPaid()) {
            throw new RuntimeException("Penalty has already been paid");
        }

        penalty.setPaid(true);
        return penaltyRepository.save(penalty);
    }
}
