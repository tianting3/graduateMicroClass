package com.tt.wkkt.mapper;

import com.tt.wkkt.model.ChangeQues;
import com.tt.wkkt.model.TestPaper;
import com.tt.wkkt.vo.req.ChoiceQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface SetTestMapper {

    Integer querySumScoreByTeacherName(String teacherName);

    int queryTestCount(String teacherName);

    boolean updatePaperId(@Param("id")int id,@Param("teacherName")String teacherName);

    boolean insertPaper(TestPaper testPaper);

    TestPaper queryByPaperId(int paperId);

    List<TestPaper> queryByPaperIds(List paperIds);

    int queryIdByPaperName(String testName);

    List<String> queryAllTestName();

    List<TestPaper> queryAllPaper(String teacherName);

    boolean deletePaperById(int id);
    boolean deleteChoiceTestByPaperId(int id);
    boolean updatePaperById(TestPaper testPaper);

    List<Integer> selectAllTestByPaperId(int id);

    List<QuestionRespVO> selectAllquestionById(List ids);

    List<ChangeQues> selectquestionById(List ids);//查询问题 将items变成数组

    int getTimeByPaperId(int id);

    boolean removeIssuePaper(int paperId);
}
