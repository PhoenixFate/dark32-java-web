package com.phoenix.content.service;

import com.phoenix.common.pojo.EasyUIDataGridResult;
import com.phoenix.content.pojo.Content;

import java.util.List;

public interface ContentService {

    EasyUIDataGridResult list(Long categoryId, Integer page, Integer rows);

    Integer create(Content content);

    Integer edit(Content content);

    Integer delete(Long[] id);

    List<Content> getContentByCid(Long categoryId);
}
