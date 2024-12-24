package com.vgdb.vgdb.service;

import com.api.igdb.utils.ImageBuilderKt;
import com.api.igdb.utils.ImageSize;
import com.api.igdb.utils.ImageType;
import com.vgdb.vgdb.api.GameApiClient;
import com.vgdb.vgdb.dto.GameApiResponse;
import com.vgdb.vgdb.model.Game;
import com.vgdb.vgdb.model.Review;
import com.vgdb.vgdb.repository.GameRepository;
import com.vgdb.vgdb.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameApiClient gameApiClient;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGame(ObjectId id) {
        return gameRepository.findById(id);
    }

    public List<Game> searchGamesByName(String name) {
        return gameRepository.findByNameIgnoreCaseContaining(name);
    }

    public void syncGamesWithApi() {
        List<GameApiResponse> apiResponses = gameApiClient.fetchGamesFromApi();
        if (apiResponses.isEmpty()) {
            log.info("No games found in the API.");
            return;
        }

        List<Game> existingGames = gameRepository.findAll();
        var existingGamesMap = existingGames.stream()
                .collect(Collectors.toMap(Game::getName, game -> game));

        List<Game> gamesToSave = apiResponses.stream()
                .map(apiResponse -> buildOrUpdateGame(apiResponse, existingGamesMap))
                .toList();

        if (!gamesToSave.isEmpty()) {
            gameRepository.saveAll(gamesToSave);
            log.info("Saved {} games to the database.", gamesToSave.size());
        }
    }

    private Game buildOrUpdateGame(GameApiResponse apiResponse, Map<String, Game> existingGamesMap) {
        String gameName = apiResponse.getName();
        Game game = existingGamesMap.getOrDefault(gameName, new Game());

        boolean isNew = game.getId() == null;
        log.info(isNew ? "Adding new game: {}" : "Updating existing game: {}", gameName);

        game.setName(apiResponse.getName());
        game.setImageUrl(buildImageUrl(apiResponse));
        game.setReleaseDate(apiResponse.getFirstReleaseDateAsDate().toString());
        game.setDescription(apiResponse.getSummary());
        game.setDevelopers(extractDevelopers(apiResponse));
        game.setGenres(extractGenres(apiResponse));
        game.setPlatforms(extractPlatforms(apiResponse));
        game.setReviews(isNew ? Collections.emptyList() : game.getReviews());

        return game;
    }

    private String buildImageUrl(GameApiResponse apiResponse) {
        return ImageBuilderKt.imageBuilder(
                apiResponse.getCover().getImageId(),
                ImageSize.COVER_BIG,
                ImageType.PNG
        );
    }

    private List<String> extractDevelopers(GameApiResponse apiResponse) {
        return Optional.ofNullable(apiResponse.getInvolvedCompanies())
                .orElse(Collections.emptyList())
                .stream()
                .map(company -> company.getCompany().getName())
                .toList();
    }

    private List<String> extractGenres(GameApiResponse apiResponse) {
        return Optional.ofNullable(apiResponse.getGenres())
                .orElse(Collections.emptyList())
                .stream()
                .map(GameApiResponse.Genre::getName)
                .toList();
    }

    private List<String> extractPlatforms(GameApiResponse apiResponse) {
        return Optional.ofNullable(apiResponse.getPlatforms())
                .orElse(Collections.emptyList())
                .stream()
                .map(GameApiResponse.Platform::getName)
                .toList();
    }

    public void saveGames(List<Game> games) {
        gameRepository.saveAll(games);
    }

    public Game addReviewToGame(ObjectId gameId, Review review) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with ID: " + gameId));
        review.setGameId(gameId);
        review.setGameName(game.getName());

        reviewRepository.save(review);
        game.getReviews().add(review);
        return gameRepository.save(game);
    }

    public Game removeReviewFromGame(ObjectId gameId, ObjectId reviewId) {
        // Fetch the game from the database
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Remove the review from the game's reviews list
        game.getReviews().removeIf(review -> review.getId().equals(reviewId));

        // Save the updated game document
        gameRepository.save(game);

        return game;
    }
}
