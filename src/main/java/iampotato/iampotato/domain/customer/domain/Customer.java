package iampotato.iampotato.domain.customer.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String login_id;

    private String password;

    private String ssn;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;  //회원 가입 상태 [COMPLETE, UNAUTHORIZED]

}
