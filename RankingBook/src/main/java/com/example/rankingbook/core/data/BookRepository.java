package com.example.rankingbook.core.data;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, String> {
    @Query(value = "{ 'bookId' : ?0 }")
    public BookEntity findBookByBookId(String bookId);

//    List<BookEntity> findAll(Sort sort);
}
