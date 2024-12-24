package com.vgdb.vgdb.repository;

import com.vgdb.vgdb.model.Game;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, ObjectId> {
    List<Game> findByNameIgnoreCaseContaining(String name);
}
