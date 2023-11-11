package com.example.rankingbook.query;

import com.example.rankingbook.core.data.Books;
import com.example.rankingbook.query.rest.BookRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingBookService {
    @Autowired
    private QueryGateway queryGateway;
    private Books books = new Books();
    public RankingBookService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @RabbitListener(queues = "GetBookQueue")
    public ArrayList<BookRestModel> getBook() {
        System.out.println("GET BOOKS666");
        FindBooksQuery findBooksQuery = new FindBooksQuery();
        ArrayList<BookRestModel> dataFetch = (ArrayList<BookRestModel>) queryGateway.query(
                findBooksQuery,
                ResponseTypes.multipleInstancesOf(BookRestModel.class)
        ).join();
        //store all book in books model
        books.setModel(dataFetch);
        System.out.println(books.getModel());
        return dataFetch;
    }

    //sort desc book view
    @RabbitListener(queues = "SortBookViewQueue")
    public List<BookRestModel> getBookByView(ArrayList infoPage) {
        SortBookViewQuery sortBookByView = new SortBookViewQuery(infoPage);
        return queryGateway.query(
                sortBookByView,
                ResponseTypes.multipleInstancesOf(BookRestModel.class)
        ).join();
    }

    //recommend book
    @RabbitListener(queues = "recommendQueue")
    public List<BookRestModel> getRecommend(String infoPage) {
        RecommendBookQuery recommendBookQuery = new RecommendBookQuery(infoPage);
        return queryGateway.query(
                recommendBookQuery,
                ResponseTypes.multipleInstancesOf(BookRestModel.class)
        ).join();
    }




}
