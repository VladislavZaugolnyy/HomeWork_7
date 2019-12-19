package ua.epam.task_7.model;

public class Account extends BaseModel {
    private String email;
    private AccountStatus status;

    public Account(Long id, String email, AccountStatus status) {
        super(id);
        this.email = email;
        this.status = status;
    }

    public Long getId() {
        return super.getId();
    }

    public String getEmail() {
        return email;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "info='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
