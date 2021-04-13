package com.tt.wkkt.service;

import java.util.HashMap;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface StudentPaperService {
    HashMap<String,Object> allPaper(String teacherName, Integer pageNum, Integer pageSize);
}
