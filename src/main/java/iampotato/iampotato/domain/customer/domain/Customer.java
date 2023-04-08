package iampotato.iampotato.domain.customer.domain;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder    //==정적 팩토리 메서드에서 빌더 패턴으로 변경!==//
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Customer {

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

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String loginId;

    private String password;

    private String ssn;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;  //회원 가입 상태 [COMPLETE, UNAUTHORIZED]

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String customerNumber;

    private String customerDept;

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

    public static CustomerImage parseImageInfo(MultipartFile multipartFile) throws Exception {
        if (multipartFile.isEmpty()) {  //들어오는 이미지 파일이 비어있으면 예외 메세지 출력하고 상위 호출 메서드로 예외를 던짐
            throw new IllegalStateException("이미지 파일이 비어있습니다.");
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
            throw new IllegalStateException("이미지 파일의 확장자가 존재하지 않는 잘못된 파일입니다.");
        } else {
            if (contentType.contains("image/jpeg")) {
                fileExtension = ".jpg";
            } else if (contentType.contains("image/png")) {
                fileExtension = ".png";
            } else if (contentType.contains("image/gif")) {
                fileExtension = ".gif";
            } else {
                throw new IllegalStateException("이미지 파일의 확장자가 허용되지 않는 확장자입니다. jpg, png, gif 파일만 사용 가능합니다.");
            }
        }

        //저장하는 파일의 이름이 겹치면 안되므로 나노 초를 활용하여 임의이 이름 지정
        String newFileName = System.nanoTime() + fileExtension;
        CustomerImage customerImage = CustomerImage.builder()
                .customerOriginalImage(multipartFile.getOriginalFilename())
                .customerStoredImage(absolutePath + path + newFileName)
                .fileSize(multipartFile.getSize())
                .build();

        file = new File(absolutePath + path + newFileName);
        multipartFile.transferTo(file); //multipartFile을 실제로 application단에 업로드 하기 위함

        return customerImage;
    }
}
