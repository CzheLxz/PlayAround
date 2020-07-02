package com.czhe.sysmanage.controller;

import com.czhe.sysmanage.config.RabbitConfig;
import com.czhe.sysmanage.entity.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    
    @GetMapping
    public void defaultMessage() {
        Book book = new Book("001", "studyRabbitMq");
        this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book);
        this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, book);
    }
}