package com.tt.wkkt.mapper;

import com.tt.wkkt.model.Question;
import com.tt.wkkt.vo.req.ChoiceByConditionReqVO;
import com.tt.wkkt.vo.req.ChoiceQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface TeacherTestMapper {
    int selectTypeById(int id);
    int insertQustion(ChoiceQuestionReqVO choiceQuestionReqVO);
    List<Integer> queryAllQuestionId();
//    添加到试卷里的题目
    List<Question> querySuccessAdd(String userName);
    boolean deleteQuestionById(int questionId);
    List<QuestionRespVO> selectByCondition(ChoiceByConditionReqVO choiceQuestionReqVO);
    QuestionRespVO selectQuestionById(int id);
    int selectCountNotAdd();
    boolean deleteAllNotPaperid();
}
