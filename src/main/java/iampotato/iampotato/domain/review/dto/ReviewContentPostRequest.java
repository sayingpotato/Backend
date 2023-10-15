package iampotato.iampotato.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReviewContentPostRequest {

    private Long reviewId;

    private List<String> reviewContents;

}
