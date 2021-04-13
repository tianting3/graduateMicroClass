package com.tt.wkkt.mapper;

import com.tt.wkkt.model.IssuePaper;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface IssuePaperMapper {
    boolean issuePaper(IssuePaper issuePaper);
    /*查询是否名字重复*/
    List<String> queryAllTestName(String teacherName);
}
