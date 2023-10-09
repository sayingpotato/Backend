package iampotato.iampotato.domain.review.application;

import iampotato.iampotato.domain.review.dao.ReviewRepository;
import iampotato.iampotato.domain.review.domain.Review;
import iampotato.iampotato.domain.review.domain.ReviewDetail;
import iampotato.iampotato.domain.review.dto.ReviewContentPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review postReviewContent(ReviewContentPostRequest request) {

        Review review = reviewRepository.findById(request.getReviewId());
        review.reviewContent();

        request.getReviewContents().stream()
                .map(ReviewDetail::find)
                .forEach(review::addReviewDetail);

        review.checkReview();

        return review;
    }
}
