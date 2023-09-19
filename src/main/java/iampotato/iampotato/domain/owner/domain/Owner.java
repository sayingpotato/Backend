package iampotato.iampotato.domain.owner.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "owner_id")
    private String id;

    private String loginId;

    private String password;

    private String ssn;

    private String nickname;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OwnerStatus ownerStatus = OwnerStatus.UNAUTHORIZED;    //회원 가입 상태 [COMPLETE, UNAUTHORIZED]

    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime modifiedDate;

    private String ownerBusinessNumber;

    public void authorizeOwner() {
        this.ownerStatus = OwnerStatus.COMPLETE;
    }
}
