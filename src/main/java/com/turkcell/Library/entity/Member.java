
package com.turkcell.Library.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {

    // Birincil anahtar (otomatik artan)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Üyenin adı
    @Column(name = "first_name")
    private String firstName;

    // Üyenin soyadı
    @Column(name = "last_name")
    private String lastName;

    // E-posta (benzersizlik kontrolü serviste yapılıyor)
    @Column(name = "email")
    private String email;

    // NOT: Telefonu int olarak tutmak baştaki sıfırları ve +90 gibi kodları düşürür.
    // İleride String'e çevirmek daha sağlıklıdır. (Şimdilik mevcut şemaya dokunmuyoruz.)
    @Column(name = "phone")
    private int phone;

    // Kayıt tarihi
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date")
    private Date registrationDate;

    // Üyenin ödünç aldığı kitaplar (Loan tarafında 'member' alanı ile eşleşir)
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Loan> loans;

    // --- Getter / Setter ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
