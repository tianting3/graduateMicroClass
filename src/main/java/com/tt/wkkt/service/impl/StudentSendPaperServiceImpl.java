package com.tt.wkkt.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.mapper.*;
import com.tt.wkkt.model.*;
import com.tt.wkkt.service.StudentSendPaperService;
import com.tt.wkkt.util.KsBeanUtil;
import com.tt.wkkt.vo.req.RemarkPaperReqVO;
import com.tt.wkkt.vo.req.SendPaperReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import com.tt.wkkt.vo.resp.ReviewPaperRespVO;
import com.tt.wkkt.vo.resp.ShowReviewQuestionRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.tt.wkkt.common.ResultCode.ERROR;
import static com.tt.wkkt.common.ResultCode.OK;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class StudentSendPaperServiceImpl implements StudentSendPaperService {

    @Autowired
    ManageQuestionMapper manageQuestionMapper;

    @Autowired
    SetTestMapper setTestMapper;

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    StudentPaperMapper studentPaperMapper;

    @Autowired
    StudentSendPaperMapper studentSendPaperMapper;

    public int returnTotalGrade(Map<String,Object> map ) {

        int totalGrade=0;
        for (Map.Entry<String,Object> entry:map.entrySet()){
            String key = entry.getKey();

            Question question = manageQuestionMapper.selectById(Integer.valueOf(key));
            String answer = question.getAnswer();
            if (answer.equalsIgnoreCase(entry.getValue().toString())){
                totalGrade+=Integer.valueOf(question.getQuestionScore());
            }
        }
        return totalGrade;
    }

    public Result insertReviewPaper(Map<String,Object> map, HttpSession session){
        Result result = new Result();
        String studentUser = (String)session.getAttribute("userName");
        List<String> types = (List<String>) Convert.toList(map.get("types"));
        HashMap hashMap = JSON.parseObject(map.get("map") +"", HashMap.class);
        String paperId = map.get("paperId") + "";
        int totalGrade = returnTotalGrade(hashMap);
        TestPaper testPaper = setTestMapper.queryByPaperId(Integer.valueOf(paperId));
        ReviewPaper reviewPaper = new ReviewPaper();
        reviewPaper.setTotalGrade(totalGrade);
        reviewPaper.setPaperId(testPaper.getId());
        reviewPaper.setPaperName(testPaper.getTestName());
        reviewPaper.setStudentUser(studentUser);
        reviewPaper.setTeacherUser(testPaper.getTeacherName());
        reviewPaper.setUploadTime(DateUtil.now());
        if ((types.contains("?????????")||types.contains("?????????"))){
            reviewPaper.setStatus(0);
            reviewPaper.setType(2);
        }else {
            reviewPaper.setStatus(1);
            reviewPaper.setType(1);
        }
        int insertPaper = studentSendPaperMapper.insertReviewPaper(reviewPaper);
        if (insertPaper>0){
            RemarkPaperReqVO reqVO = new RemarkPaperReqVO();
            reqVO.setStudentUser(reviewPaper.getStudentUser());
            reqVO.setPaperName(reviewPaper.getPaperName());
            int reviewId = studentPaperMapper.queryIdByStudentUserAndPaperId(reqVO);
            Iterator iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                int questionId = Integer.parseInt(entry.getKey().toString());
                String uploadAnswer = entry.getValue().toString();
                ShowReviewQuestion showReviewQuestion = new ShowReviewQuestion();
                showReviewQuestion.setQuestionId(questionId);
                showReviewQuestion.setReviewId(reviewId);
                showReviewQuestion.setUploadAnswer(uploadAnswer);
                boolean isOk = studentSendPaperMapper.insertReviewQuestion(showReviewQuestion);
                if(!isOk){
                    result.setCode(ERROR.getCode());
                    result.setMsg("???????????????show_review_question??????");
                }
            }

        }else {
            result.setCode(ERROR.getCode());
        }
        result.setCode(OK.getCode());
        result.setMsg("??????????????????");
        return result;
    }


    @Override
    public HashMap<String,Object> allReviewPaperByPaperId(int paperId, HttpSession session,Integer pageNum,Integer pageSize) {
        String teacherUser = (String)session.getAttribute("userName");
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<ReviewPaperRespVO> reviewPapers = studentSendPaperMapper.queryAllReviewPaperByPaperId(paperId, teacherUser);
        for (ReviewPaperRespVO reviewPaperRespVO:reviewPapers){
            reviewPaperRespVO.setStudentName(loginMapper.queryStudentNameByUsername(reviewPaperRespVO.getStudentUser()));
        }
        PageInfo<ReviewPaperRespVO> info=new PageInfo<>(reviewPapers);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",info.getList());/*???????????????????????????*/
        map.put("nowPage",pageNum);/*??????????????????*/
        map.put("total",((int)info.getTotal()+pageSize-1)/pageSize);//??????????????????
        map.put("size",info.getTotal());
        System.out.println(map.get("size"));
        return map;

    }

    @Override
    public HashMap<String, Object> remarkPaperByPaperNameAndStudentUser(RemarkPaperReqVO remarkPaperReqVO) {
        int reviewId = studentPaperMapper.queryIdByStudentUserAndPaperId(remarkPaperReqVO);
        List<ShowReviewQuestion> showReviewQuestions = studentPaperMapper.queryReviewQuestionByReviewId(reviewId);
        List list = new ArrayList<>();
        ArrayList<ShowReviewQuestionRespVO> showReviewQuestionRespVOS = new ArrayList<>();
        for (ShowReviewQuestion showReviewQuestion:showReviewQuestions){
            ShowReviewQuestionRespVO showReviewQuestionRespVO = KsBeanUtil.copyPropertiesThird(showReviewQuestion, ShowReviewQuestionRespVO.class);
            showReviewQuestionRespVO.setUploadAnswer(showReviewQuestion.getUploadAnswer().toCharArray());
            showReviewQuestionRespVOS.add(showReviewQuestionRespVO);
            list.add(showReviewQuestion.getQuestionId());
        }
        List<Question> allQuestion = manageQuestionMapper.queryByQuestionsId(list);
        List<ChangeQues> changeQues= new ArrayList<>();
        for(Question question:allQuestion){
            ChangeQues ques = KsBeanUtil.copyPropertiesThird(question, ChangeQues.class);
            changeQues.add(ques);
        }
        for (ChangeQues question:changeQues) {
            if (question.getType().equals("1")){
                question.setType("?????????");
            }else if (question.getType().equals("2")){
                question.setType("?????????");
            }else if (question.getType().equals("3")){
                question.setType("?????????");
            }else if (question.getType().equals("4")){
                question.setType("?????????");
            }else{
                question.setType("?????????");
            }
            if (question.getType().equals("?????????")||question.getType().equals("?????????")||question.getType().equals("?????????")){
                String[] item = question.getItems().split("@");
                question.setItemsArr(item);
            }

        }
        int totalGrade = studentPaperMapper.queryTotalGradeByStudentUserAndPaperId(remarkPaperReqVO);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("question",changeQues);
        hashMap.put("answer",showReviewQuestionRespVOS);
        hashMap.put("totalGrade",totalGrade);
        return hashMap;
    }

    @Override
    public Result insertIncludeObjective(Map<String, Object> map) {
        Result result = new Result<>();
        HashMap objetctiveMap = JSON.parseObject(map.get("objective") +"", HashMap.class);
        int totalGrade = returnTotalGrade(objetctiveMap);
        HashMap<String,Object> subjective = JSON.parseObject(map.get("map") + "", HashMap.class);
        for (Map.Entry<String,Object> entry:subjective.entrySet()){
            int value = Integer.parseInt((String) entry.getValue());
            totalGrade=totalGrade+value;
        }
        String paperName = map.get("paperName").toString();
        String studentUser = map.get("studentUser").toString();
        boolean updateGrade = studentSendPaperMapper.updateTotalByPaperNameAndStudentUser(paperName, studentUser, totalGrade);
        if (updateGrade){
            result.setCode(OK.getCode());
            result.setMsg("????????????");
            return result;
        }else {
            result.setCode(ERROR.getCode());
            result.setMsg("????????????");
            return result;
        }
    }
}
