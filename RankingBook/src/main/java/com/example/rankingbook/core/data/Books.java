package com.example.rankingbook.core.data;

import com.example.rankingbook.query.rest.BookRestModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Books {
    private ArrayList<BookRestModel> model = new ArrayList<BookRestModel>();
    public ArrayList<BookRestModel> getModel() {
        return model;
    }
    public void setModel(ArrayList<BookRestModel> model) {
        this.model = model;
    }
}
