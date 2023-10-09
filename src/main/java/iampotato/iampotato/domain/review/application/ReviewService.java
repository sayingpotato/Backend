package iampotato.iampotato.domain.review.application;

import iampotato.iampotato.domain.review.dao.ReviewRepository;
import iampotato.iampotato.domain.review.domain.Review;
import iampotato.iampotato.domain.review.domain.ReviewDetail;
import iampotato.iampotato.domain.review.dto.ReviewContentPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review postReviewContent(ReviewContentPostRequest request) {

        Review review = reviewRepository.findById(request.getReviewId());
        review.reviewContent();

        List<String> reviewContents = request.getReviewContents();
        if (reviewContents != null && reviewContents.size() != 0) {
            reviewContents.stream()
                    .map(ReviewDetail::find)
                    .forEach(review::addReviewDetail);
        }

        review.checkReview();

        return review;
    }
}
