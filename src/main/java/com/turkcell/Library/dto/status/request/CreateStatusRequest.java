package com.turkcell.Library.dto.status.request;

public class CreateStatusRequest {
    private String statusName;

    public CreateStatusRequest() {}

    public CreateStatusRequest(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
