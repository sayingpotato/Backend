package iampotato.iampotato.domain.customer.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String customerImage;

    private String customerNumber;

    private String customerDept;

    private String customerCollege;

    private String customerGrade;

}
