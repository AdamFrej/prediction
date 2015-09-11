package pl.waw.frej.prediction.persistence.database.entity;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AnswerEntity implements Answer {

    @Id
    @GeneratedValue
    @Column(name = "ANSWER_ID")
    private Long id;

    
    private String name;
    private String description;
    private LocalDateTime liquidationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="ANSWER_USER",
            joinColumns={@JoinColumn(name="ANSWER_ID", referencedColumnName="ANSWER_ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")})
    private List<UserEntity> owners;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public LocalDateTime getLiquidationDate() {
        return liquidationDate;
    }

    @Override
    public void setLiquidationDate(LocalDateTime liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    @Override
    public List<User> getOwners() {
        return new ArrayList<>(owners);
    }

    @Override
    public void addOwner(User user) {
        owners.add((UserEntity) user);
    }

    @Override
    public void removeOwner(User owner) {
        owners.remove(owner);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AnswerEntity ? this.getId().equals(((AnswerEntity) obj).getId()) : super.equals(obj);
    }
}
