package iampotato.iampotato.domain.customer.domain;
import iampotato.iampotato.domain.customer.exception.CustomerException;
import iampotato.iampotato.domain.customer.exception.CustomerExceptionGroup;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
@Entity
@Getter
@Builder    //==정적 팩토리 메서드에서 빌더 패턴으로 변경!==//
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
public class Customer implements UserDetails {

    //==정적 팩토리 메서드==//
//    public static Customer createCustomer(String loginId, String password, String nickname) {
//        Customer customer = new Customer();
//        customer.loginId = loginId;
//        customer.password = password;
//        customer.nickname = nickname;
//        customer.createdDate = LocalDateTime.now();
//        customer.modifiedDate = LocalDateTime.now();
//        return customer;
//    }
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "customer_id")
    private String id;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    private String ssn;

    @NotNull
    private String nickname;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'UNAUTHORIZED'")
    private CustomerStatus customerStatus;  //회원 가입 상태 [COMPLETE, UNAUTHORIZED]

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @NotNull
    private String customerNumber;

    @NotNull
    private String customerDept;

    @NotNull
    private String customerCollege;
    private String customerGrade;
    @Embedded
    private CustomerImage customerImage;
    //==비즈니스 로직==//
    /*
    customerImage(학생증 이미지) 업로드
     */
    public void updateCustomerImage(CustomerImage customerImage) {
        this.customerImage = customerImage;
    }

    public void updateCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public CustomerImage parseImageInfo(MultipartFile multipartFile) throws Exception {
        if (multipartFile.isEmpty()) {  //들어오는 이미지 파일이 비어있으면 예외 메세지 출력하고 상위 호출 메서드로 예외를 던짐
            throw new CustomerException(CustomerExceptionGroup.CUSTOMER_IMAGE_NULL);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());   //이미지를 저장할 때 파일 이름에 업로드한 날짜를 함께 저장하기 위함
        //프로젝트 폴더 내 resources의 폴더에 저장하기 위해서 절대 경로를 설정
        //Windows와 Linux의 경로 표기법이 \와 /로 다르므로 이를 모두 호환하기 위해 File.separator 사용
        String absolutePath = new File("").getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String path = "images" + File.separator + currentDate + File.separator;
        File file = new File(absolutePath + path);
        if (!file.exists()) {   //저장할 경로에 디렉토리가 존재하지 않는 경우
            // 파일이 존재하는 경로 내 모든 디렉토리가 존재하지 않는 경우 모두 생성
            file.mkdirs();
        }
        //업로드할 이미지 파일의 확장자를 가져와서 jpeg, png, gif 파일인지 확인한 뒤 해당 확장자만 받아준다
        String contentType = multipartFile.getContentType();    //확장자를 판단하기 위해
        String fileExtension;   //이미지 파일의 확장자를 저장
        if (ObjectUtils.isEmpty(contentType)) { //확장자 명이 없는지 검사
            throw new CustomerException(CustomerExceptionGroup.CUSTOMER_IMAGE_EXTENSION_NULL);
        } else {
            if (contentType.contains("image/jpeg")) {
                fileExtension = ".jpg";
            } else if (contentType.contains("image/png")) {
                fileExtension = ".png";
            } else if (contentType.contains("image/gif")) {
                fileExtension = ".gif";
            } else {
                throw new CustomerException(CustomerExceptionGroup.CUSTOMER_IMAGE_EXTENSION_WRONG);
            }
        }
        //저장하는 파일의 이름이 겹치면 안되므로 나노 초를 활용하여 임의이 이름 지정 +++ 추가적으로 더 파일 중복을 막기 위해 3자리 수의 난수를 섞어 만듬
        String newFileName = System.nanoTime() + fileExtension;
        String filePath = absolutePath + path + newFileName;
        Path pathInstance = Paths.get(filePath);
        if(Files.exists(pathInstance)) {    //나노 타임으로 해싱한 이름 조차도 이미 존재하는지 중복 여부를 검사
            String numRandom = "";
            for(int i=0; i<3; i++) {
                char ch = (char)((int)(Math.random()*10)+48);   //세자리 난수를 생성
                numRandom += ch;
            }
            newFileName = numRandom + newFileName.substring(3); //세자리 난수를 섞어 파일명 재정의
        }
        CustomerImage customerImage = CustomerImage.builder()
                .customerOriginalImage(multipartFile.getOriginalFilename())
                .customerStoredImage(absolutePath + path + newFileName)
                .fileSize(multipartFile.getSize())
                .build();
        file = new File(filePath);
        multipartFile.transferTo(file); //multipartFile을 실제로 application단에 업로드 하기 위함
        return customerImage;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
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

    public void updateProfile(String nickname) {
        this.nickname = nickname;
    }
}
