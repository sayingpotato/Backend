package iampotato.iampotato.domain.owner.application;

import iampotato.iampotato.domain.customer.dto.TokenResponse;
import iampotato.iampotato.domain.customer.jwt.JwtTokenProvider;
import iampotato.iampotato.domain.owner.dao.OwnerRepository;
import iampotato.iampotato.domain.owner.domain.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OwnerSignInService {
    private final OwnerRepository ownerRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse signIn(String loginId, String password) {
        Owner owner = ownerRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디"));

        if (!owner.getPassword().equals(password)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않음");
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(owner.getId(), password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenResponse tokenResponse = jwtTokenProvider.generateToken(authentication);

        return tokenResponse;
    }

    public TokenResponse addOwnerStatus(TokenResponse tokenResponse, String loginId) {
        Owner owner = ownerRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디"));
        tokenResponse.setOwnerStatus(owner.getOwnerStatus());
        return tokenResponse;
    }
}