package iampotato.iampotato.domain.customer.dto;

import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.domain.CustomerImage;
import iampotato.iampotato.domain.customer.domain.CustomerStatus;
import lombok.Data;

@Data
public class MyPageResponse {

    private String loginId;

    private String nickname;

    private CustomerStatus customerStatus;  //회원 가입 상태 [COMPLETE, UNAUTHORIZED]

    private String customerNumber;

    private String customerDept;

    private String customerCollege;

    private String customerGrade;

    private CustomerImage customerImage;

    public MyPageResponse(Customer customer) {
        this.loginId = customer.getLoginId();
        this.nickname = customer.getNickname();
        this.customerStatus = customer.getCustomerStatus();
        this.customerNumber = customer.getCustomerNumber();
        this.customerDept = customer.getCustomerDept();
        this.customerCollege = customer.getCustomerCollege();
        this.customerGrade = customer.getCustomerGrade();
        this.customerImage = customer.getCustomerImage();
    }
}
