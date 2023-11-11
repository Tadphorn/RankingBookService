package com.example.rankingbook.query;

import java.util.ArrayList;

public class SortBookViewQuery {
    private final ArrayList infoPage;

    public SortBookViewQuery(ArrayList infoPage) {
        this.infoPage = infoPage;
    }
    public ArrayList getInfoPage() {
        return infoPage;
    }
}
