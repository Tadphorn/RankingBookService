package com.example.rankingbook.query;

import java.util.ArrayList;

public class RecommendBookQuery {
    private final String category;
    public RecommendBookQuery(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }
}
