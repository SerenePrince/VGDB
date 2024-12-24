package com.vgdb.vgdb.api;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.JsonRequestKt;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vgdb.vgdb.dto.GameApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameApiClient {

    private final ObjectMapper objectMapper;

    @Value("${twitch.client-id}")
    private String clientId;

    @Value("${twitch.client-secret}")
    private String clientSecret;

    /**
     * Authenticates with Twitch to obtain an access token.
     *
     * @return TwitchToken containing the access token, or null if authentication fails.
     */
    private TwitchToken authorizeAccess() {
        if (clientId == null || clientSecret == null || clientId.isBlank() || clientSecret.isBlank()) {
            log.error("Twitch Client ID or Client Secret is not configured.");
            return null;
        }

        try {
            return TwitchAuthenticator.INSTANCE.requestTwitchToken(clientId, clientSecret);
        } catch (Exception e) {
            log.error("Error during Twitch authentication: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Fetches a list of games from the API using the configured query.
     *
     * @return List of GameApiResponse objects containing the game data.
     */
    public List<GameApiResponse> fetchGamesFromApi() {
        TwitchToken token = authorizeAccess();
        if (token == null) {
            log.warn("Failed to authenticate with Twitch. Returning empty list.");
            return Collections.emptyList();
        }

        IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
        wrapper.setCredentials(clientId, token.getAccess_token());

        try {
            String json = JsonRequestKt.jsonGames(wrapper, buildGameQuery());
            log.info("API Response JSON: {}", json);
            return objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON response: {}", e.getMessage(), e);
        } catch (RequestException e) {
            log.error("Error during API request: {}", e.getMessage(), e);
        }

        return Collections.emptyList();
    }

    /**
     * Builds the query for fetching game data from the API.
     *
     * @return Configured APICalypse query object.
     */
    private APICalypse buildGameQuery() {
        return new APICalypse()
                .fields("name, cover.image_id, first_release_date, summary, genres.name, involved_companies.company.name, platforms.name")
                .sort("game.rating_count", Sort.ASCENDING)
                .where("id = (1075, 300461, 2132, 26226, 2928, 891, 14593, 36911, 1277)");
    }
}
