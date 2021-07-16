package com.tt.wkkt.mapper;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.tt.wkkt.model.ReviewPaper;
import com.tt.wkkt.model.ShowReviewQuestion;
import com.tt.wkkt.vo.resp.ReviewPaperRespVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface StudentSendPaperMapper {

    int insertReviewPaper(ReviewPaper reviewPaper);

    ArrayList<Integer> queryPaperIdByTeacherUser(String teacherUser);

    ArrayList<ReviewPaper> queryBypaperIdAndStatus(int paperId);

    ArrayList<ReviewPaperRespVO> queryAllReviewPaperByPaperId(@Param("paperId") int paperId, @Param("teacherUser")String teacherUser);

    boolean insertReviewQuestion(ShowReviewQuestion reviewQuestion);

    boolean updateTotalByPaperNameAndStudentUser(@Param("paperName") String paperName,@Param("studentUser")String studentUser,@Param("totalGrade")int totalGrade);
}
