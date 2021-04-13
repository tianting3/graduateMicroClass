package com.tt.wkkt.mapper;

import com.tt.wkkt.model.Question;
import com.tt.wkkt.vo.req.PartQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface ManageQuestionMapper {

    List<QuestionRespVO> selectAllByuserName(PartQuestionReqVO PartQuestionReqVO);

    boolean deleteById(int id);

    boolean addQuestion(Question question);

    Question selectById(int id);

    boolean updateById(Question question);

    List<Question> selectByTypeOrDegree(PartQuestionReqVO partQuestionReqVO);

    boolean deleteManyById(List ids);
}
