package com.example.rankingbook.core.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Book")
public class BookEntity {
    @Id
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

    public BookEntity() {
    }

    public BookEntity(String bookId, String title, String description, String category, String type, String cover, Integer view, Integer like, Integer comment, String status, String authorId) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.cover = cover;
        this.view = view;
        this.like = like;
        this.comment = comment;
        this.status = status;
        this.authorId = authorId;
    }
}
