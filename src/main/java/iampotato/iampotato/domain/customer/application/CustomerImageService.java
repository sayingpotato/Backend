package iampotato.iampotato.domain.customer.application;

import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.domain.CustomerImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerImageService {

    private final CustomerRepository customerRepository;

    @Transactional  //CustomerImage(학생증 이미지)를 업로드해주는 서비스 로직이다.
    public Customer uploadImage(Long customerId, MultipartFile multipartFile) throws Exception {
        //parseImageInfo() 과정에서 Exception이 발생할 수 있는데 이러면 상단 호출 메소드(컨트롤러)로 예외를 던진다.
        CustomerImage parseCustomerImage = Customer.parseImageInfo(multipartFile);
        Customer findCustomer = customerRepository.findOne(customerId);

        findCustomer.updateCustomerImage(parseCustomerImage);   //customer 엔티티 내 customerImage 필드를 업데이트해주는 비즈니스 로직을 만들었다.
        //이처럼 Transaction 단위 내 JPA 영속성 컨텍스트에서 관리하는 엔티티 내 필드 상태에 변화가 생기게 되면 Dirty checking을 통해 알아서 업데이트 쿼리를 날려주게 된다.

        return findCustomer;
    }
}
