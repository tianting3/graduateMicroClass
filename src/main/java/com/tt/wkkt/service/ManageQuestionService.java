package com.tt.wkkt.service;

import com.tt.wkkt.model.Question;
import com.tt.wkkt.vo.req.PartQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;

import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface ManageQuestionService {
    HashMap<String,Object> pageQuestion(PartQuestionReqVO partQuestionReqVO, Integer pageNum, Integer pageSize);

    List<QuestionRespVO> allQuestion(PartQuestionReqVO question);
    boolean deleteQuestion(int id);

    boolean addQuestion(Question question);

    Question updateQuestion(int id);

    boolean updateById(Question question);

    List<Question> partQuestion(PartQuestionReqVO partQuestionReqVO);

    boolean deleteMany(List ids);
}
