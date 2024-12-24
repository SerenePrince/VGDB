package com.vgdb.vgdb.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Document(collection = "reviews")
@Data
@NoArgsConstructor(onConstructor = @__(@Generated))
@AllArgsConstructor(onConstructor = @__(@Generated))
@Builder
public class Review {

    @Id
    private ObjectId id;

    @NotNull(message = "Game ID cannot be null")
    private ObjectId gameId;

    private String gameName;

    @Builder.Default
    @NotBlank(message = "Username cannot be blank")
    private String username = "anonymous";

    @NotBlank(message = "Review header cannot be blank")
    @Size(min = 5, max = 255, message = "Review header must be between 5 and 255 characters")
    private String reviewHeader;

    @NotBlank(message = "Review body cannot be blank")
    @Size(min = 10, max = 1000, message = "Review body must be between 10 and 1000 characters")
    private String reviewBody;

    @NotNull(message = "Score cannot be null")
    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 10, message = "Score must be at most 10")
    private Integer score;
}
