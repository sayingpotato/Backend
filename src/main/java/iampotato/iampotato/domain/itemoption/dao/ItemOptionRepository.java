package iampotato.iampotato.domain.itemoption.dao;

import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemOptionRepository {

    private final EntityManager em;

    public List<ItemOption> findByIds(List<Long> ids) {

        return em.createQuery("select io from ItemOption io where io.id in :ids", ItemOption.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}
