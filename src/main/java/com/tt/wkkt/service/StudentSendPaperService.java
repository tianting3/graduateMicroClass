package com.tt.wkkt.service;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.ReviewPaper;
import com.tt.wkkt.model.ShowReviewQuestion;
import com.tt.wkkt.vo.req.RemarkPaperReqVO;
import com.tt.wkkt.vo.req.SendPaperReqVO;
import com.tt.wkkt.vo.resp.ReviewPaperRespVO;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface StudentSendPaperService {

    Result insertReviewPaper(Map<String,Object> map, HttpSession session);

    HashMap<String,Object> allReviewPaperByPaperId(int paperId, HttpSession session, Integer pageNum, Integer pageSize);

    HashMap<String, Object> remarkPaperByPaperNameAndStudentUser(RemarkPaperReqVO remarkPaperReqVO);
}
