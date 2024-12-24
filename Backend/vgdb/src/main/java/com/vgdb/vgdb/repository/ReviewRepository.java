package com.vgdb.vgdb.repository;

import com.vgdb.vgdb.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    // Search by gameId
    List<Review> findByGameId(ObjectId gameId);

    // Search by gameName
    List<Review> findByGameName(String gameName);
}
