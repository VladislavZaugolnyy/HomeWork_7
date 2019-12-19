package ua.epam.task_7.model;

public enum AccountStatus {
    ACTIVE("ACTIVE"),
    NOT_ACTIVE("NOT ACTIVE");

    private  String status;

    AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
