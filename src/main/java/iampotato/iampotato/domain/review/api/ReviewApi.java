package iampotato.iampotato.domain.review.api;

import iampotato.iampotato.domain.review.application.ReviewService;
import iampotato.iampotato.domain.review.domain.Review;
import iampotato.iampotato.domain.review.dto.ReviewContentPostRequest;
import iampotato.iampotato.domain.review.dto.ReviewContentPostResponse;
import iampotato.iampotato.global.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewApi {

    private final ReviewService reviewService;

    @Tag(name = "리뷰 페이지")
    @Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다.")
    @PostMapping("api/v1/review/content")
    public Result<ReviewContentPostResponse> postReviewContent(@RequestBody ReviewContentPostRequest request) {

        Review review = reviewService.postReviewContent(request);

        ReviewContentPostResponse response = new ReviewContentPostResponse(review);

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, response);
    }
}
