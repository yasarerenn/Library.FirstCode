package com.turkcell.Library.dto.status.request;

public class UpdateStatusRequest {
    private String statusName;

    public UpdateStatusRequest() {}

    public UpdateStatusRequest(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
