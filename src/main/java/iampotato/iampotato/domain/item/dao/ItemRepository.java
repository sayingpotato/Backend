package iampotato.iampotato.domain.item.dao;

import iampotato.iampotato.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public List<Item> findByIds(List<Long> ids) {

        return em.createQuery("select i from Item i where i.id in :ids", Item.class)
                .setParameter("ids", ids)
                .getResultList();
    }

}
