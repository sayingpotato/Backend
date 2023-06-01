package iampotato.iampotato.domain.customer.dao;

import iampotato.iampotato.domain.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final EntityManager em;

    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer findOne(String id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    public Optional<Customer> findByLoginId(String loginId) {   //LoginId를 통해 모든 Customer 조회
        List<Customer> result = em.createQuery("select c from Customer c where c.loginId = :loginId", Customer.class)    //:loginId라고 하여 밑에서 setParameter를 통해 "loginId"의 value와 바인딩
                .setParameter("loginId", loginId)   //위에 있는 :loginId가 여기 파리미터의 Key값과 바인딩 되어서 value가 위로 넘어가게 됌
                .getResultList();
        return result.stream().findAny();
    }

    public List<Customer> findByNickname(String nickname) { //Nickname을 통해 모든 Customer 조회
        return em.createQuery("select c from Customer c where c.nickname = :nickname", Customer.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    public Optional<Customer> findById(String username) {
        List<Customer> result = em.createQuery("select c from Customer c" +
                        " where c.id = :username", Customer.class)
                .setParameter("username", username)
                .getResultList();
        return result.stream().findAny();
    }

    public void delete(Customer customer) {
        em.remove(customer);
    }

}
