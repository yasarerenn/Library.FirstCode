package com.turkcell.Library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "penalty")
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalty_id")
    private int penalty_id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "loan") // FK: penalty.loan -> loan.loan_id
    private Loan loan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff") // cezayı oluşturan/onaylayan personel
    private Staff staff;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "paid", nullable = false)
    private boolean paid;

    // -- getters/setters --
    public int getPenalty_id() { return penalty_id; }
    public void setPenalty_id(int penalty_id) { this.penalty_id = penalty_id; }

    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }

    public Staff getStaff() { return staff; }
    public void setStaff(Staff staff) { this.staff = staff; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
}
