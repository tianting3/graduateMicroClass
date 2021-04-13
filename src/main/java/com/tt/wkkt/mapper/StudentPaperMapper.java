package com.tt.wkkt.mapper;

import com.tt.wkkt.model.IssuePaper;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface StudentPaperMapper {
    List<IssuePaper> queryAllPaperByTeachername(String teacherName);

}
