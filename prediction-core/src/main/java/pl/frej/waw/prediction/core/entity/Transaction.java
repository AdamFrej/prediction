package pl.frej.waw.prediction.core.entity;

public interface Transaction {
    Long getId();
    User getUser();
    void setUser(User user);
}
