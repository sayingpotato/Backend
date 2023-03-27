package iampotato.iampotato.domain.owner.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
