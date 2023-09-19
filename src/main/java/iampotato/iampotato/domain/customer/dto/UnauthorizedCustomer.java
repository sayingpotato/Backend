package iampotato.iampotato.domain.customer.dto;

import iampotato.iampotato.domain.customer.domain.CustomerImage;
import lombok.Data;

@Data
public class UnauthorizedCustomer {
    private String id;
    private String customerNumber;
    private String customerDept;
    private String customerCollege;
    private CustomerImage customerImage;

    public UnauthorizedCustomer(String id, String customerNumber, String customerDept, String customerCollege, CustomerImage customerImage) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.customerDept = customerDept;
        this.customerCollege = customerCollege;
        this.customerImage = customerImage;
    }
}
