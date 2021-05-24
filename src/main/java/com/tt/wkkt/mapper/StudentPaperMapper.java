package com.tt.wkkt.mapper;

import com.tt.wkkt.model.IssuePaper;
import com.tt.wkkt.model.ShowReviewQuestion;
import com.tt.wkkt.vo.req.RemarkPaperReqVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface StudentPaperMapper {
    List<IssuePaper> queryAllPaperByTeachername(String teacherName);

    int queryExistSubmit(@Param("paperId")int paperId,@Param("studentUser")String studentUser);

    int queryIdByStudentUserAndPaperId(RemarkPaperReqVO remarkPaperReqVO);

    List<ShowReviewQuestion> queryReviewQuestionByReviewId(int reviewId);

}
