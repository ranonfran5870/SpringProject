package com.FlightsSystem.FlightsSystem.MongoDB.Service;

import com.FlightsSystem.FlightsSystem.MongoDB.DTO.DatabaseSequence;
import com.FlightsSystem.FlightsSystem.MongoDB.DTO.LogCount;
import com.FlightsSystem.FlightsSystem.MongoDB.Repository.LogCountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class LogCountService {


    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public LogCountService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public  long generateSequence(String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }


    @Autowired
    private LogCountRepo logCountRepo;

    public List<LogCount> getAll(){
        return logCountRepo.findAll();
    }


    public void addLC(LogCount logCount){
        logCountRepo.save(logCount);
    }
}
