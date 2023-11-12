package com.example.rankingbook.query.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/book")
public class RankingQueryController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public RankingQueryController(Environment env, CommandGateway commandGateway){
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @GetMapping("/getBook")
    public ArrayList getBooks() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object book = rabbitTemplate.convertSendAndReceive("BookExchange", "getbook", "");
        return (ArrayList) book;
    }

    //Type Ranking
    @GetMapping("/getPopularView/{category}/{type}")
    public ArrayList getPopularView(@PathVariable("category") String category, @PathVariable("type") String type) {
        // Create a list to store the parameters
        List<String> parameters = new ArrayList<>();
        parameters.add(category);
        parameters.add(type);
        // Convert the list to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParameters;
        try {
            jsonParameters = objectMapper.writeValueAsString(parameters);
        } catch (JsonProcessingException e) {
            return new ArrayList();
        }
        // Send the JSON message using RabbitMQ
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(jsonParameters.getBytes(), messageProperties);

        Object book = rabbitTemplate.convertSendAndReceive("BookExchange", "descbookview", message);
        return (ArrayList) book;
    }

    //Recommend book
    @GetMapping("/getRecommendBook/{category}")
    public ArrayList getRecommendBook(@PathVariable("category") String category) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object book = rabbitTemplate.convertSendAndReceive("BookExchange", "recommend", category);
        return (ArrayList) book;
    }

    @GetMapping("/rankBookType/{bookType}")
    public ArrayList getRankedBookType(@PathVariable("bookType") String bookType) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object book = rabbitTemplate.convertSendAndReceive("BookExchange", "rankbytype", bookType);
        return (ArrayList) book;
    }
}
