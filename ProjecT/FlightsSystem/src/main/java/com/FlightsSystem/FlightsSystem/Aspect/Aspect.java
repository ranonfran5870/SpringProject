package com.FlightsSystem.FlightsSystem.Aspect;


import com.FlightsSystem.FlightsSystem.MongoDB.DTO.LogCount;
import com.FlightsSystem.FlightsSystem.MongoDB.Repository.LogCountRepo;
import com.FlightsSystem.FlightsSystem.MongoDB.Service.LogCountService;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    @Autowired
    private LogCountRepo logCountRepo;
    @Autowired
    LogCountService logCountService;
    @Pointcut("execution (* com.FlightsSystem.FlightsSystem.DAO.*.Add*())")
    public void alldao(){}
    @Before("alldao()")
    public void beforeaddin(){
        var test = new LogCount(1, LocalDateTime.now());
        test.setId(logCountService.generateSequence(LogCount.SEQUENCE_NAME));
        logCountRepo.save(test);
        System.out.println("\n =====> this from aspect @before adding");
    }

}
