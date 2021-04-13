package com.tt.wkkt.mapper;

import com.tt.wkkt.model.AskQuestion;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.vo.req.FeedbackReqVo;
import com.tt.wkkt.vo.resp.GetFeedbackRespVO;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface FeedbackMapper {
    boolean insertFeedback(FeedbackReqVo feedbackReqVo);

    List<GetFeedbackRespVO> selectFeedback();

    boolean addAskQues(AskQuestion askQuestion);

    List<AskQuestion> getAskQuestionByTeacherName(String teacher);

    boolean updateReply1(int id);

    boolean updateReply0(int id);

    int queryReplyById(int id);

    StudentGrade queryCourseIdAndNameByUsername(String teacherUser);

    boolean insertCompetitiveGrade(StudentGrade studentGrade);
}
