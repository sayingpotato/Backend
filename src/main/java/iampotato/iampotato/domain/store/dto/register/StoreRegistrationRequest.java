package iampotato.iampotato.domain.store.dto.register;

import iampotato.iampotato.domain.store.domain.Address;
import iampotato.iampotato.domain.store.domain.StoreCategory;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StoreRegistrationRequest {

    @NotBlank
    private StoreCategory category;

    @NotBlank
    private String name;

    @NotBlank
    private Address address;

    @NotBlank
    private String phone;
}
