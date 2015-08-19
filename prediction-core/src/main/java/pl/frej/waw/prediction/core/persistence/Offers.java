package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.Offer;

import java.util.List;

public interface Offers {
    boolean add(Offer offer);

    boolean remove(String id);

    Offer find(String Id);

    List<Offer> findByUser(String userId);
}
