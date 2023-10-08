package iampotato.iampotato.domain.review.dao;

import iampotato.iampotato.domain.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public Review findById(Long id) {
        return em.find(Review.class, id);
    }
}
