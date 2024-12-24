package com.vgdb.vgdb.controller;

import com.vgdb.vgdb.model.Game;
import com.vgdb.vgdb.model.Review;
import com.vgdb.vgdb.service.GameService;
import com.vgdb.vgdb.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private GameService gameService;

    /**
     * Fetch all reviews from the database.
     *
     * @return List of all reviews
     */
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    /**
     * Search for reviews by game name.
     *
     * @param name String name of the game
     * @return List of matching reviews
     */
    @GetMapping("/search")
    public ResponseEntity<List<Review>> searchReviewsByGameName(@RequestParam String name) {
        List<Review> reviews = reviewService.searchReviewsByGameName(name);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    /**
     * Search for reviews by game ID.
     *
     * @param id ObjectId of the game
     * @return List of matching reviews
     */
    @GetMapping("/search-by-id")
    public ResponseEntity<List<Review>> searchReviewsByGameId(@RequestParam ObjectId id) {
        List<Review> reviews = reviewService.searchReviewsByGameId(id);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    /**
     * Create a new review in the database and add it to the corresponding game.
     *
     * @param gameId The ID of the game to which the review is linked
     * @param review The review object to save
     * @return ResponseEntity with the created review and the updated game
     */
    @PostMapping("/{gameId}")
    public ResponseEntity<Game> createReview(@PathVariable ObjectId gameId, @Valid @RequestBody Review review) {
        try {
            // Create the review and save it in the reviews collection
            Review savedReview = reviewService.addReview(review);

            // Call the service to add the review to the game and update the game
            Game updatedGame = gameService.addReviewToGame(gameId, savedReview);

            // Build the location URI for the created review
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedReview.getId())
                    .toUri();

            // Return the updated game and the location of the review
            return ResponseEntity.created(location).body(updatedGame);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Or handle specific error response
        }
    }

    /**
     * Delete a review from the database and remove it from the associated game.
     *
     * @param gameId   The ID of the game from which the review will be removed
     * @param reviewId The ID of the review to delete
     * @return ResponseEntity indicating the result of the deletion
     */
    @DeleteMapping("/{gameId}/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable ObjectId gameId, @PathVariable ObjectId reviewId) {
        try {
            // First, remove the review from the game's review list
            Game updatedGame = gameService.removeReviewFromGame(gameId, reviewId);

            // Then, delete the review from the reviews collection
            reviewService.deleteReview(reviewId);

            // Return a success message
            return ResponseEntity.ok("Review deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting review");
        }
    }
}