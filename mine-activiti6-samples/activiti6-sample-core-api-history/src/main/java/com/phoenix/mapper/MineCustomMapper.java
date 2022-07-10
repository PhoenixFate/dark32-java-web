package com.phoenix.mapper;

import org.activiti.engine.task.Task;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MineCustomMapper {

    @Select("select * from act_ru_task")
    public List<Map<String,Object>> findAllTask();

}
