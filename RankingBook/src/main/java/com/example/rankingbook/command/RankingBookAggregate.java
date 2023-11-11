package com.example.rankingbook.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class RankingBookAggregate {
    @AggregateIdentifier
    private String bookId;
    private String title;
    private String description;
    private String category;
    private String type;
    private String cover;
    private Integer view;
    private Integer like;
    private Integer comment;
    private String status;
    private String authorId;

    public RankingBookAggregate() {
    }

//    @CommandHandler
//    public BookAggregate(CreateBookCommand createBookCommand) {
//        System.out.println("Book Aggregate");
//        BookCreateEvent bookCreateEvent = new BookCreateEvent();
//        BeanUtils.copyProperties(createBookCommand, bookCreateEvent);
//        AggregateLifecycle.apply(bookCreateEvent);
//    }
//
//    @EventSourcingHandler
//    public void on(BookCreateEvent bookCreateEvent) {
//        System.out.println("ON AGGREGATE");
//        this.bookId = bookCreateEvent.getBookId();
//        this.title = bookCreateEvent.getTitle();
//        this.description = bookCreateEvent.getDescription();
//        this.category = bookCreateEvent.getCategory();
//        this.type = bookCreateEvent.getType();
//        this.cover = bookCreateEvent.getCover();
//        this.view = bookCreateEvent.getView();
//        this.like = bookCreateEvent.getLike();
//        this.comment = bookCreateEvent.getComment();
//        this.status = bookCreateEvent.getStatus();
//        this.authorId = bookCreateEvent.getAuthorId();
//    }
}
