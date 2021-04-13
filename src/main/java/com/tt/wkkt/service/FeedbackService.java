package com.tt.wkkt.service;

import com.tt.wkkt.model.AskQuestion;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.vo.req.FeedbackReqVo;
import com.tt.wkkt.vo.resp.GetFeedbackRespVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/

public interface FeedbackService {

    /*学生反馈*/
    boolean studentFeedback (FeedbackReqVo feedbackReqVo);

    List<GetFeedbackRespVO> techerFeedBack();

    boolean studentAsk(AskQuestion askQuestion);

    /*获得所有的提问*/
    List<AskQuestion> getAllAskQue(String teacher);


    /*修改状态*/
    boolean modifyReply(int id);


    boolean GradeToStudent(StudentGrade student);
}
