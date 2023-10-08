package iampotato.iampotato.domain.review.dto;

import iampotato.iampotato.domain.review.domain.Review;
import lombok.Data;

@Data
public class ReviewContentPostResponse {

    private Long reviewId;

    public ReviewContentPostResponse(Review review) {
        this.reviewId = review.getId();
    }
}
