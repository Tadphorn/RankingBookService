package com.example.rankingbook.query.rest;

import com.example.rankingbook.core.data.BookEntity;
import com.example.rankingbook.core.data.BookRepository;
import com.example.rankingbook.query.FindBooksQuery;
import com.example.rankingbook.query.SortBookViewQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RankingQueryHandler {
    private final BookRepository bookRepository;

    public RankingQueryHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        System.out.println("do sort");
        List<BookRestModel> bookRest = new ArrayList<>();
        //mongo query command
        Sort sortByViewDesc = Sort.by(Sort.Order.desc("view"));
        List<BookEntity> storedBooks = bookRepository.findAll(sortByViewDesc);
//        Criteria criteria = Criteria.where("type").is("Romantic");
//        Query query1 = new Query(criteria).with(Sort.by(Sort.Order.desc("view")));
//        List<BookEntity> storedBooks = mongoTemplate.find(query1, BookEntity.class);
        for (BookEntity bookEntity : storedBooks) {
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            bookRest.add(bookRestModel);
        }
        return bookRest;
    }
}
