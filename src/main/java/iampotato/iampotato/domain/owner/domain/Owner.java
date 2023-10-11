package iampotato.iampotato.domain.owner.domain;

import iampotato.iampotato.domain.ownerstore.domain.OwnerStore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "owner_id")
    private String id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @Builder.Default
    private List<OwnerStore> ownerStores = new ArrayList<>();

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

    public void updateOwnerStores(List<OwnerStore> ownerStores) {
        this.ownerStores = ownerStores;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
