package com.vgdb.vgdb.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "games")
@Data
@AllArgsConstructor(onConstructor = @__(@Generated))
@NoArgsConstructor(onConstructor = @__(@Generated))
@Builder
public class Game {

    @Id
    private ObjectId id;

    @NotNull
    @Size(max = 255)
    @Indexed(unique = true)
    private String name;

    @Field("image_url")
    private String imageUrl;

    @Field("first_release_date")
    private String releaseDate;

    private List<String> developers;

    @Size(max = 5000)
    private String description;

    private List<String> genres;

    private List<String> platforms;

    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
}
