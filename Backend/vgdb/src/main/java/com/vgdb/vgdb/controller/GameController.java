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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from your React app
public class GameController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    private GameService gameService;

    /**
     * Fetch all games from the database.
     *
     * @return List of all games
     */
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        if (games.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(games);
    }

    /**
     * Search for games by name.
     *
     * @param name String name of the game
     * @return List of matching games
     */
    @GetMapping("/search")
    public ResponseEntity<List<Game>> searchGames(@RequestParam String name) {
        List<Game> games = gameService.searchGamesByName(name);
        if (games.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(games);
    }

    /**
     * Search for a game by its ID.
     *
     * @param id ObjectId of the game
     * @return ResponseEntity with the game if found, or 404 Not Found if not
     */
    @GetMapping("/search-by-id")
    public ResponseEntity<Game> searchGameById(@RequestParam ObjectId id) {
        Optional<Game> game = gameService.getGame(id);
        return game.map(ResponseEntity::ok) // If the game is found, return 200 OK with the game
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not, return 404 Not Found
    }

    /**
     * Sync games with the external API.
     * This method adds new games and updates existing ones.
     *
     * @return Success or error message
     */
    @PostMapping("/sync")
    public ResponseEntity<String> syncGamesWithApi() {
        try {
            gameService.syncGamesWithApi();
            return ResponseEntity.ok("Games synced successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to sync games: " + e.getMessage());
        }
    }

    /**
     * Add a review to a game.
     *
     * @param id ObjectId of the game
     * @param review Review object to be added
     * @return Updated game with the new review
     */
    @PostMapping("/{id}/reviews")
    public ResponseEntity<Game> addReviewToGame(@PathVariable ObjectId id, @RequestBody Review review) {
        try {
            Review savedReview = reviewService.addReview(review);
            Game updatedGame = gameService.addReviewToGame(id, savedReview);
            return ResponseEntity.ok(updatedGame);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
