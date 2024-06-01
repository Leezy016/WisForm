package com.wisform.entity;

import java.util.List;

public class ApiFResponse {
    private List<String> formlist;
    public ApiFResponse(List<String> formlist){
        this.formlist = formlist;
    }
}
