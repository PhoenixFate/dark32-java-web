package com.phoenix.search.service;

import com.phoenix.common.pojo.SearchResult;

public interface SearchService {

    SearchResult searchItem(String keyword, Integer page, Integer rows);

}
