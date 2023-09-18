package iampotato.iampotato.domain.owner.application;

import iampotato.iampotato.domain.owner.dao.OwnerRepository;
import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.owner.exception.OwnerException;
import iampotato.iampotato.domain.owner.exception.OwnerExceptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional
    public Owner signUp(Owner owner) {
        validateDuplicatedOwnerByLoginId(owner);
        ownerRepository.save(owner);
        return owner;
    }

    private void validateDuplicatedOwnerByLoginId(Owner owner) {
        Optional<Owner> id = ownerRepository.findByLoginId(owner.getLoginId());
        if (id.isPresent())
            throw new OwnerException(OwnerExceptionGroup.OWNER_DUPLICATION);
    }

    public List<Owner> getUnauthorizedOwners() {
        List<Owner> owners = ownerRepository.findUnauthorizedOwners();
        if (owners == null || owners.isEmpty()) {
            throw new OwnerException(OwnerExceptionGroup.OWNER_NULL);
        }
        return owners;
    }

    @Transactional
    public Owner authorizeOwner(String id) {
        Owner owner = ownerRepository.findById(id);
        owner.authorizeOwner();
        return owner;
    }
}
