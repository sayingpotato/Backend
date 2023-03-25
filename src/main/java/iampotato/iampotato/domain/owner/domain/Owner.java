package iampotato.iampotato.domain.owner.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Owner {

    @Id @GeneratedValue
    @Column(name = "owner_id")
    private Long id;

    private String loginId;

    private String password;

    private String ssn;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private OwnerStatus ownerStatus;    //회원 가입 상태 [COMPLETE, UNAUTHORIZED]

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String ownerBusinessNumber;

}
