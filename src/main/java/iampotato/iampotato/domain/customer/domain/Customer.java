package iampotato.iampotato.domain.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    //==정적 팩토리 메서드==//
    public static Customer createCustomer(String loginId, String password, String nickname) {
        Customer customer = new Customer();
        customer.loginId = loginId;
        customer.password = password;
        customer.nickname = nickname;
        return customer;
    }
    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String loginId;

    private String password;

    private String ssn;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;  //회원 가입 상태 [COMPLETE, UNAUTHORIZED]

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String customerImage;

    private String customerNumber;

    private String customerDept;

    private String customerCollege;

    private String customerGrade;

}
