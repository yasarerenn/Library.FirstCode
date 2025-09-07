package com.turkcell.Library.controller;

import com.turkcell.Library.entity.Penalty;
import com.turkcell.Library.service.PenaltyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/penalties")
public class PenaltyController {
    private final PenaltyService penaltyService;

    public PenaltyController(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    @GetMapping
    public ResponseEntity<List<Penalty>> getAllPenalties() {
        List<Penalty> penalties = penaltyService.getAllPenalties();
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Penalty> getPenaltyById(@PathVariable int id) {
        return penaltyService.getPenaltyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Penalty> createPenalty(@RequestBody Penalty penalty) {
        try {
            Penalty savedPenalty = penaltyService.createPenalty(penalty);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPenalty);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Penalty> updatePenalty(@PathVariable int id, @RequestBody Penalty penaltyDetails) {
        try {
            Penalty updatedPenalty = penaltyService.updatePenalty(id, penaltyDetails);
            return ResponseEntity.ok(updatedPenalty);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePenalty(@PathVariable int id) {
        try {
            penaltyService.deletePenalty(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<Penalty>> getPenaltiesByLoan(@PathVariable int loanId) {
        List<Penalty> penalties = penaltyService.getPenaltiesByLoan(loanId);
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<Penalty>> getPenaltiesByStaff(@PathVariable int staffId) {
        List<Penalty> penalties = penaltyService.getPenaltiesByStaff(staffId);
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/unpaid")
    public ResponseEntity<List<Penalty>> getUnpaidPenalties() {
        List<Penalty> penalties = penaltyService.getUnpaidPenalties();
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/paid")
    public ResponseEntity<List<Penalty>> getPaidPenalties() {
        List<Penalty> penalties = penaltyService.getPaidPenalties();
        return ResponseEntity.ok(penalties);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<Penalty> payPenalty(@PathVariable int id) {
        try {
            Penalty paidPenalty = penaltyService.markPenaltyAsPaid(id);
            return ResponseEntity.ok(paidPenalty);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/member/{memberId}/total-unpaid")
    public ResponseEntity<Integer> getTotalUnpaidPenaltiesByMember(@PathVariable int memberId) {
        Integer total = penaltyService.getTotalUnpaidPenaltiesByMember(memberId);
        return ResponseEntity.ok(total);
    }
}
