package com.santander.kafka.producer;

import com.google.gson.Gson;
import com.santander.kafka.model.DemoModel;
import com.santander.kafka.model.DemoModel2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAsync
@RestController
@RequestMapping("/api/kafka")
public class KafkaProducerController {

    private Gson jsonConverter;
    private KafkaTemplate<String, String> template;

    @Autowired
    public KafkaProducerController(KafkaTemplate<String, String> template, Gson jsonConverter) {
        this.template = template;
        this.jsonConverter = jsonConverter;
    }

    @PostMapping("/demo")
    public void postDemo(@RequestBody DemoModel model) {
        System.out.println("Producing " + model.toString());
        template.send("messages", jsonConverter.toJson(model));
    }


    @PostMapping("/demo2")
    public void postDemo2(@RequestBody DemoModel2 model) {
        System.out.println("Producing " + model.toString());
        template.send("messages", jsonConverter.toJson(model));
    }
}
