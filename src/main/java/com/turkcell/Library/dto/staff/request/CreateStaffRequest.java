package com.turkcell.Library.dto.staff.request;

public class CreateStaffRequest {
    private String firstName;
    private String lastName;

    public CreateStaffRequest() {}

    public CreateStaffRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
