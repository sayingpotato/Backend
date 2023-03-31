package iampotato.iampotato.domain.store.dao;

import iampotato.iampotato.domain.store.domain.Location;
import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.dto.StoreMapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StoreRepository {

    private final EntityManager em;

    public void save(Store store) {
        em.persist(store);
    }

    public Store findById(Long id) {
        return em.find(Store.class, id);
    }

    public List<Store> findAll() {
        return em.createQuery("select s from Store s", Store.class)
                .getResultList();
    }

    /**
     * MySQL 의 MBR 기능을 사용하기위해 nativeQuery 로 작성
     */
    public List<Store> findByLocation(Location northeast, Location southwest) {

        // NativeQuery 로 보내기위해 북동쪽, 남서쪽 거리를 String 으로 반환 (x y, x y) 좌표로 보내며 x 가 Longitude, y 가 Latitude 입니다.
        String pointFormat = String.format(
                "'LINESTRING(%f %f, %f %f)'",
                northeast.getLongitude(), northeast.getLatitude(), southwest.getLongitude(), southwest.getLatitude()
        );

        Query query = em.createNativeQuery("SELECT * "
                        + "FROM store AS s "
                        + "WHERE MBRCONTAINS(ST_LINESTRINGFROMTEXT(" + pointFormat + "), s.location)", Store.class)
                .setMaxResults(10);


        // 비어있으면?
        return query.getResultList();
    }
}
