package iampotato.iampotato.domain.item.dao;

import iampotato.iampotato.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public List<Item> findByIds(List<Long> ids) {

        return em.createQuery("select i from Item i where i.id in :ids", Item.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    public Optional<Item> findById(Long id) {

        return em.createQuery("select i from Item i " +
                        "left join fetch i.itemOptions.itemOptions io " +
                        "where i.id = :id", Item.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findAny();
    }

}
