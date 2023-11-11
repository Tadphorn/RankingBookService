package com.example.rankingbook.query.rest;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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

    @GetMapping("/getPopularView")
    public ArrayList getPopularView() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object book = rabbitTemplate.convertSendAndReceive("BookExchange", "descbookview", "");
        return (ArrayList) book;
    }

}
