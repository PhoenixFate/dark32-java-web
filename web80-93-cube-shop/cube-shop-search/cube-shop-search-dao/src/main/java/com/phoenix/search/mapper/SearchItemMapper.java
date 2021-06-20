package com.phoenix.search.mapper;

import com.phoenix.common.pojo.SearchItem;
import java.util.List;

public interface SearchItemMapper {

    List<SearchItem> getItemList();

    SearchItem getItemById(Long id);
}
