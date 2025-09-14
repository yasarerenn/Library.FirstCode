package com.turkcell.Library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int staff_Id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<Loan> loans;

    @JsonIgnore
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<Penalty> penalties;

    // -- getters/setters --
    public int getStaff_Id() { return staff_Id; }
    public void setStaff_Id(int staff_Id) { this.staff_Id = staff_Id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public List<Loan> getLoans() { return loans; }
    public void setLoans(List<Loan> loans) { this.loans = loans; }

    public List<Penalty> getPenalties() { return penalties; }
    public void setPenalties(List<Penalty> penalties) { this.penalties = penalties; }
}
