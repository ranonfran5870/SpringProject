package com.FlightsSystem.FlightsSystem.Controlles;

import com.FlightsSystem.FlightsSystem.MongoDB.DTO.LogCount;
import com.FlightsSystem.FlightsSystem.MongoDB.Service.LogCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MongoControllerTest {
    @Autowired
    LogCountService logCountService;


    @PostMapping("/mongo")
    public void add(@RequestBody LogCount logCount){
        logCountService.addLC(logCount);
    }
}
