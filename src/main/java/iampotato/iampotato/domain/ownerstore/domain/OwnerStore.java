package iampotato.iampotato.domain.ownerstore.domain;

import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.store.domain.Store;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerStore {

    @Id
    @GeneratedValue
    @Column(name = "owner_store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
