package com.tt.wkkt.service.impl;

import com.tt.wkkt.common.DateUtil;
import com.tt.wkkt.mapper.FeedbackMapper;
import com.tt.wkkt.model.AskQuestion;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.service.FeedbackService;
import com.tt.wkkt.vo.req.FeedbackReqVo;
import com.tt.wkkt.vo.resp.GetFeedbackRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class FeedbackDerviceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public boolean studentFeedback(FeedbackReqVo feedbackReqVo) {
        boolean res=feedbackMapper.insertFeedback(feedbackReqVo);
        return res;
    }

    @Override
    public List<GetFeedbackRespVO> techerFeedBack() {
        List<GetFeedbackRespVO> respVO=feedbackMapper.selectFeedback();
        return respVO;
    }

    @Override
    public boolean studentAsk(AskQuestion askQuestion) {
        askQuestion.setAskQuestion(askQuestion.getAskQuestion().replace("\n","\n\n\n\n\n\n\n\n"));
        Date date = new Date();
        String time = DateUtil.getYyyyMmDdHhMmSs(date);
        askQuestion.setTime(time);
        boolean addAskQues = feedbackMapper.addAskQues(askQuestion);
        return addAskQues;
    }

    @Override
    public List<AskQuestion> getAllAskQue(String teacher) {
        List<AskQuestion> questionList = feedbackMapper.getAskQuestionByTeacherName(teacher);
        return questionList;
    }

    @Override
    public boolean modifyReply(int id) {
        int reply = feedbackMapper.queryReplyById(id);
        if (reply==0){
            return feedbackMapper.updateReply1(id);
        }else if (reply==1){
            return feedbackMapper.updateReply0(id);
        }else {
            return false;
        }

    }

    @Override
    public boolean GradeToStudent(StudentGrade student) {

        StudentGrade queryCourseIdAndNameByUsername = feedbackMapper.queryCourseIdAndNameByUsername(student.getTeacherUser());
        student.setCourseId(queryCourseIdAndNameByUsername.getCourseId());
        student.setCourseName(queryCourseIdAndNameByUsername.getCourseName());
        boolean competitiveGrade = feedbackMapper.insertCompetitiveGrade(student);
        return competitiveGrade;
    }
}
