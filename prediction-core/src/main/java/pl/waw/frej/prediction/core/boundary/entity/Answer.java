package pl.waw.frej.prediction.core.boundary.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface Answer {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    LocalDateTime getLiquidationDate();

    void setLiquidationDate(LocalDateTime completionTime);

    List<User> getOwners();
    void addOwner(User user);

    void removeOwner(User owner);
}
