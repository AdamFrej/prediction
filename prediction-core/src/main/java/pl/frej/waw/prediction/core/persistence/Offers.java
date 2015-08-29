package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.Offer;

import java.util.List;

public interface Offers {
    boolean add(Offer offer);

    boolean remove(Long id);

    Offer find(Long Id);

    List<Offer> findByUser(Long userId);

    List<Offer> findByAnswer(Long answerId);
}
