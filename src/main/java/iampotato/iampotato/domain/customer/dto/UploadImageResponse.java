package iampotato.iampotato.domain.customer.dto;

import iampotato.iampotato.domain.customer.domain.CustomerImage;
import lombok.Data;

@Data
public class UploadImageResponse {  //이미지 업로드시 ResponseBody
    private Long customerId;
    private CustomerImage customerImage;

    public UploadImageResponse(Long customerId, CustomerImage customerImage) {
        this.customerId = customerId;
        this.customerImage = customerImage;
    }
}
