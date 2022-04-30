package com.FlightsSystem.FlightsSystem.MongoDB.Repository;

import com.FlightsSystem.FlightsSystem.MongoDB.DTO.LogCount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogCountRepo extends MongoRepository<LogCount,Long> {
    LogCountRepo findTopByOrderByIdDesc();
}
