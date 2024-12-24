package com.vgdb.vgdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameApiResponse {

    private int id;

    @NonNull
    private String name;

    private Cover cover;

    @JsonProperty("first_release_date")
    private long firstReleaseDate;

    @JsonProperty("involved_companies")
    private List<InvolvedCompany> involvedCompanies;

    private String summary;

    private List<Genre> genres;

    private List<Platform> platforms;

    @JsonIgnore
    public LocalDate getFirstReleaseDateAsDate() {
        return Instant.ofEpochSecond(firstReleaseDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Cover {
        private int id;

        @JsonProperty("image_id")
        private String imageId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Genre {
        private int id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Platform {
        private int id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InvolvedCompany {
        private int id;
        private Company company;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Company {
        private int id;
        private String name;
    }
}

