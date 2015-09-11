package pl.waw.frej.prediction.web.model;


import java.io.Serializable;

public class UserForm implements Serializable{
    private String name;
    private Long funds;

    public Long getFunds() {
        return funds;
    }

    public void setFunds(Long funds) {
        this.funds = funds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
