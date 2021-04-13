package com.tt.wkkt.service;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.vo.req.ChoiceByConditionReqVO;
import com.tt.wkkt.vo.req.ChoiceQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface TestPaperService {
//    查询题型
    int selectType(int id);
    Result addQuestion(ChoiceQuestionReqVO choiceQuestionReqVO);
    HashMap<String,Object> allAddAfterQuestion(String userName, Integer pageNum, Integer pageSize);
    boolean removeQustion(int questionId);
    /*条件筛序后得到的问题*/
    HashMap<String,Object> getPartQuestion(ChoiceByConditionReqVO choiceQuestionReqVO, Integer pageNum, Integer pageSize);
    QuestionRespVO selectQuestion(int id);
    boolean deleteAllFailTest();
}
