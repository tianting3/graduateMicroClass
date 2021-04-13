package com.tt.wkkt.service;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.TestPaper;

import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface SetTestService {
    HashMap<String, Object> getAllScoreAndTestCount(String techerName);

    Result addPaper(TestPaper testPaper);

    HashMap<String, Object> getAllPaper(String teacherName,Integer pageNum, Integer pageSize);

    boolean removePaper(int id);

    boolean updatePaper(TestPaper testPaper);

    HashMap<String,Object> checkPaper(int id,int pageNum,int pageSize);
    HashMap<String,Object> getAllTest(int id);
}
