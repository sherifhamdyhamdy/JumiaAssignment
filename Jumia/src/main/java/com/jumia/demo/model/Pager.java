package com.jumia.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
    private int numberOfPages;
    private int totalCount;
    private int totalDisplayedRows;
}
