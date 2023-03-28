package iampotato.iampotato.domain.customer.dao;

import iampotato.iampotato.domain.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final EntityManager em;

    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer findOne(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    public List<Customer> findByLoginId(String loginId) {
        return em.createQuery("select c from Customer c where c.loginId = :loginId", Customer.class)    //:loginId라고 하여 밑에서 setParameter를 통해 "loginId"의 value와 바인딩
                .setParameter("loginId", loginId)   //위에 있는 :loginId가 여기 파리미터의 Key값과 바인딩 되어서 value가 위로 넘어가게 됌
                .getResultList();
    }

}
