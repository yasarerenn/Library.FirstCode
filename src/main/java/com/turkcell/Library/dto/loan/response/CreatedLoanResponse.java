package com.turkcell.Library.dto.loan.response;

import java.util.Date;

public class CreatedLoanResponse {
    private int loanId;
    private int memberId;
    private String memberName;
    private int bookId;
    private String bookTitle;
    private int staffId;
    private String staffName;
    private Date loanDate;
    private Date returnDate;
    private Boolean returned;

    public CreatedLoanResponse() {}

    public CreatedLoanResponse(int loanId, int memberId, String memberName, int bookId, String bookTitle,
                        int staffId, String staffName, Date loanDate, Date returnDate, Boolean returned) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.staffId = staffId;
        this.staffName = staffName;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}
