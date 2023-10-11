package iampotato.iampotato.domain.owner.api;

import iampotato.iampotato.domain.customer.dto.SignInRequest;
import iampotato.iampotato.domain.customer.dto.TokenResponse;
import iampotato.iampotato.domain.owner.application.OwnerService;
import iampotato.iampotato.domain.owner.application.OwnerSignInService;
import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.owner.dto.*;
import iampotato.iampotato.global.util.Result;
import iampotato.iampotato.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OwnerApi {

    private final OwnerService ownerService;

    private final OwnerSignInService ownerSignInService;

    @Tag(name = "점주")
    @Operation(summary = "점주 회원가입", description = "점주의 정보를 등록할때 사용되는 API 입니다.")
    @PostMapping("/api/v1/owner/signUp")
    public Result<OwnerSignUpResponse> signUp(OwnerSignUpRequest request) {    //회원 가입하는 POST API

        //Spring security로 Password Hash 암호화 로직 추가하기
        Owner owner = Owner.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .ownerBusinessNumber(request.getOwnerBusinessNumber())
                .build();
        OwnerSignUpResponse response = new OwnerSignUpResponse(ownerService.signUp(owner));

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, response);
    }

    @Tag(name = "점주")
    @Operation(summary = "점주 로그인", description = "점주가 로그인할때 사용되는 API 입니다.")
    @PostMapping("/api/v1/owner/signIn")
    public Result<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
        TokenResponse tokenResponse = ownerSignInService.signIn(signInRequest.getLoginId(), signInRequest.getPassword());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, ownerSignInService.addOwnerStatus(tokenResponse, signInRequest.getLoginId()));
    }

    @Tag(name = "점주")
    @Operation(summary = "보유 가게 가져오기", description = "점주가 보유한 모든 가게를 가져옵니다..")
    @GetMapping("/api/v1/owner/store")
    public Result<OwnerStoresResponse> getOwnerStores(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                      @RequestParam(value = "limit", defaultValue = "100") int limit) {

        Owner owner = ownerService.getOwnerStores(SecurityUtil.getCurrentUserId(), offset, limit);
        OwnerStoresResponse response = new OwnerStoresResponse(owner);

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, response);
    }

    @Tag(name = "관리자 점주 페이지")
    @Operation(summary = "비인증 점주 가져오기", description = "관리자페이지에서 인증되지 않은 점주들의 정보를 가져올때 사용되는 API 입니다.")
    @GetMapping("/api/v1/owner/unauthorization")
    public Result<List<OwnerUnauthorizedResponse>> getUnauthorizedOwner() {

        List<Owner> unauthorizedOwners = ownerService.getUnauthorizedOwners();

        List<OwnerUnauthorizedResponse> responses = unauthorizedOwners.stream()
                .map(OwnerUnauthorizedResponse::new)
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, responses);
    }

    @Tag(name = "관리자 점주 페이지")
    @Operation(summary = "인증하기", description = "인증되지 않은 점주를 인증할때 사용되는 API 입니다.")
    @PutMapping("/api/v1/owner/authorization")
    public Result<OwnerAuthorizeResponse> authorizeOwner(OwnerAuthorizeRequest request) {

        Owner owner = ownerService.authorizeOwner(request.getId());
        OwnerAuthorizeResponse response = new OwnerAuthorizeResponse(owner);

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, response);
    }
}
