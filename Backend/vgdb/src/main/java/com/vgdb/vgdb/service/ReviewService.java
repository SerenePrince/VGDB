package com.vgdb.vgdb.service;

import com.vgdb.vgdb.model.Review;
import com.vgdb.vgdb.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Retrieves all reviews from the database.
     *
     * @return a list of all reviews
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Adds a new review to the database.
     *
     * @param review the review to be added
     * @return the saved review
     */
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Searches for reviews associated with a specific game by its unique identifier.
     *
     * @param gameId the unique identifier of the game
     * @return a list of reviews associated with the given gameId
     */
    public List<Review> searchReviewsByGameId(ObjectId gameId) {
        return reviewRepository.findByGameId(gameId);
    }

    /**
     * Searches for reviews associated with a specific game by its name.
     *
     * @param gameName the name of the game
     * @return a list of reviews associated with the given gameName
     */
    public List<Review> searchReviewsByGameName(String gameName) {
        return reviewRepository.findByGameName(gameName);
    }

    public void deleteReview(ObjectId reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
