package com.turkcell.Library.dto.loan.request;

import java.util.Date;

public class CreateLoanRequest {
    private int memberId;
    private int bookId;
    private int staffId;
    private Date returnDate; // Optional, will default to 14 days from loan date

    public CreateLoanRequest() {}

    public CreateLoanRequest(int memberId, int bookId, int staffId, Date returnDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.staffId = staffId;
        this.returnDate = returnDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
