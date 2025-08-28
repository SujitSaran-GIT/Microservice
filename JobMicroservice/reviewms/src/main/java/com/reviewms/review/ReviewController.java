package com.reviewms.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reviewms.review.messaging.ReviewMessageProducer;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    private ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean isCreated = reviewService.createReview(companyId, review);
        if (isCreated){
            reviewMessageProducer.sendReviewMessage(review);
            return new ResponseEntity<>("Review added successful",HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Review can't added",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        List<Review> isFound = reviewService.getAllReviews(companyId);
        if (isFound!=null){
            return new ResponseEntity<>(isFound, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review isReviewId = reviewService.getReviewById(reviewId);

        if (isReviewId!=null){
            return new ResponseEntity<>(reviewService.getReviewById(reviewId),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(reviewId, review);

        if (isReviewUpdated){
            return  new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review can't found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);

        if (isReviewDeleted){
            return  new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review can't found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/average")
    public Double getAverageReview(@RequestParam Long companyId){
        List<Review> reviewList = reviewService.getAllReviews(companyId);

        return reviewList.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
