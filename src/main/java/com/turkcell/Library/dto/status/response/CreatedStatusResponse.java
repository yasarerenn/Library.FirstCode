package com.turkcell.Library.dto.status.response;

public class CreatedStatusResponse {
    private Integer statusId;
    private String statusName;

    public CreatedStatusResponse() {}

    public CreatedStatusResponse(Integer statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
