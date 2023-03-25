package iampotato.iampotato.domain.owner.domain;

import lombok.Getter;

import javax.persistence.*;

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
    private OwnerStatus ownerStatus;


}
