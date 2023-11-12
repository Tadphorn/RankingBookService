package com.example.rankingbook.query.rest;

import com.example.rankingbook.core.data.BookEntity;
import com.example.rankingbook.core.data.BookRepository;
import com.example.rankingbook.query.FindBooksQuery;
import com.example.rankingbook.query.RankBookType;
import com.example.rankingbook.query.RecommendBookQuery;
import com.example.rankingbook.query.SortBookViewQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RankingQueryHandler {
    private final BookRepository bookRepository;
    private final MongoTemplate mongoTemplate;

    public RankingQueryHandler(BookRepository bookRepository, MongoTemplate mongoTemplate) {
        this.bookRepository = bookRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @QueryHandler
    public List<BookRestModel> findBooks(FindBooksQuery query) {
        System.out.println("find book");
        List<BookRestModel> bookRest = new ArrayList<>();
        //mongo query command
        List<BookEntity> storedBooks = bookRepository.findAll();
        for (BookEntity bookEntity : storedBooks) {
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            bookRest.add(bookRestModel);
        }
        return bookRest;
    }

    //sort desc book view
    @QueryHandler
    public List<BookRestModel> findBooksByView(SortBookViewQuery query) {
        System.out.println("do sort" + query.getInfoPage());
        List<BookRestModel> bookRest = new ArrayList<>();
        //destruct info
        ArrayList infoPage = query.getInfoPage();
        String category = (String) infoPage.get(0);
        String type = (String) infoPage.get(1);

        System.out.println("Category: " + category);
        System.out.println("Type: " + type);
        //mongo query command
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("category").is(category),
                Criteria.where("type").is(type)
        );
        Query query1 = new Query(criteria).with(Sort.by(Sort.Order.desc("view")));
        List<BookEntity> storedBooks = mongoTemplate.find(query1, BookEntity.class);
        for (BookEntity bookEntity : storedBooks) {
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            bookRest.add(bookRestModel);
        }
        return bookRest;
    }

    @QueryHandler
    public List<BookRestModel> recommendBook(RecommendBookQuery query) {
        System.out.println("do sort" + query.getCategory());
        List<BookRestModel> bookRest = new ArrayList<>();
        //mongo query command
        Criteria criteria = Criteria.where("category").is(query.getCategory());
        Query query1 = new Query(criteria).with(Sort.by(Sort.Order.desc("view")));
        List<BookEntity> storedBooks = mongoTemplate.find(query1, BookEntity.class);
        for (BookEntity bookEntity : storedBooks) {
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            bookRest.add(bookRestModel);
        }
        return bookRest;
    }

    //for home rank
    @QueryHandler
    public List<BookRestModel> rankBookType(RankBookType query) {
        System.out.println("do sort" + query.getBookType());
        List<BookRestModel> bookRest = new ArrayList<>();
        //mongo query command
        Criteria criteria = Criteria.where("type").is(query.getBookType());
        Query query1 = new Query(criteria).with(Sort.by(Sort.Order.desc("view")));
        List<BookEntity> storedBooks = mongoTemplate.find(query1, BookEntity.class);
        for (BookEntity bookEntity : storedBooks) {
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            bookRest.add(bookRestModel);
        }
        return bookRest;
    }
}
