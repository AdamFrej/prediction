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
            joinColumns={@JoinColumn(name="T_ANSWER_ID", referencedColumnName="ANSWER_ID")},
            inverseJoinColumns={@JoinColumn(name="T_USER_ID", referencedColumnName="USER_ID")})
    private List<UserEntity> owners;
    private boolean liquidated;


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
    public boolean isLiquidated() {
        return liquidated;
    }

    @Override
    public void setLiquidated(boolean liquidated) {
        this.liquidated = liquidated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return !(getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
