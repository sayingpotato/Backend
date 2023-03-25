package iampotato.iampotato.domain.sideowner.domain;

import iampotato.iampotato.domain.owner.domain.Owner;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class SideOwner {

    @Id @GeneratedValue
    @Column(name = "side_owner_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private String password;

    private String nickname;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String grade;
}
