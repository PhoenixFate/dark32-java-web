package com.phoenix.item.service;

import com.phoenix.common.pojo.EasyUITreeNode;
import java.util.List;

public interface ItemCategoryService {

    List<EasyUITreeNode> getListByParentId(Long id);

}
