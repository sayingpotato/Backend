package iampotato.iampotato.domain.review.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReviewContentPostRequest {

    private Long reviewId;

    private List<String> reviewContents;

}
