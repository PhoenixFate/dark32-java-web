package com.phoenix.content.service;

import com.phoenix.common.pojo.EasyUITreeNode;
import com.phoenix.common.utils.CommonResult;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode>  getListByParentId(Long id);

    CommonResult create(String name, Long parentId);

    Integer delete(Long id);

    Integer update(String name, Long id);
}
