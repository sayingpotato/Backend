package iampotato.iampotato.domain.customer.application;
import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.dto.TokenResponse;
import iampotato.iampotato.domain.customer.exception.CustomerException;
import iampotato.iampotato.domain.customer.exception.CustomerExceptionGroup;
import iampotato.iampotato.domain.customer.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerSignInService {
    private final CustomerRepository customerRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse signIn(String loginId, String password) {
        Customer customer = customerRepository.findByLoginId(loginId).orElseThrow(() -> new CustomerException(CustomerExceptionGroup.CUSTOMER_ID_NULL));

        if (!customer.getPassword().equals(password)) {
            throw new CustomerException(CustomerExceptionGroup.CUSTOMER_PASSWORD_WRONG);
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customer.getId(), password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenResponse tokenResponse = jwtTokenProvider.generateToken(authentication);

        return tokenResponse;
    }

    public TokenResponse addCustomerStatus(TokenResponse tokenResponse, String loginId) {
        Customer customer = customerRepository.findByLoginId(loginId).orElseThrow(() -> new CustomerException(CustomerExceptionGroup.CUSTOMER_ID_NULL));
        tokenResponse.setCustomerStatus(customer.getCustomerStatus());
        return tokenResponse;
    }
}