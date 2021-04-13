package com.tt.wkkt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.mapper.TeacherTestMapper;
import com.tt.wkkt.model.Question;
import com.tt.wkkt.service.TestPaperService;
import com.tt.wkkt.vo.req.ChoiceByConditionReqVO;
import com.tt.wkkt.vo.req.ChoiceQuestionReqVO;
import com.tt.wkkt.vo.req.PartQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class TestPaperServiceImpl implements TestPaperService {
    @Autowired
    TeacherTestMapper teacherTestMapper;
    @Override
    public int selectType(int id) {
        return teacherTestMapper.selectTypeById(id);
    }

    @Override
    public Result addQuestion(ChoiceQuestionReqVO choiceQuestionReqVO) {
        int id=choiceQuestionReqVO.getQuestionId();
        List<Integer> list=teacherTestMapper.queryAllQuestionId();
        if (list.contains(id)){
            return Result.ok("已经添加该题目");
        }
        int type=selectType(id);
        if (type==1){
            choiceQuestionReqVO.setOrderNum(1);
        }else if (type==2){
            choiceQuestionReqVO.setOrderNum(2);
        }else if (type==3){
            choiceQuestionReqVO.setOrderNum(3);
        }else if (type==4){
            choiceQuestionReqVO.setOrderNum(4);
        }else  {
            choiceQuestionReqVO.setOrderNum(5);
        }
        int insertQues=teacherTestMapper.insertQustion(choiceQuestionReqVO);
        return Result.ok("添加成功");
    }

    @Override
    public HashMap<String, Object> allAddAfterQuestion(String userName, Integer pageNum, Integer pageSize) {
//        判断是否有未添加到试卷里的题目，如果有的话则显示，否则为空
        int count = teacherTestMapper.selectCountNotAdd();
        if (count>0){
            PageHelper.startPage(pageNum,pageSize);
            List<Question> list=teacherTestMapper.querySuccessAdd(userName);
            PageInfo<Question> questionPageInfo = new PageInfo<>(list);
            HashMap<String, Object> map = new HashMap<>();
            map.put("data",questionPageInfo.getList());/*返回指定页面的数据*/
            map.put("nowPage",pageNum);/*返回当前页面*/
            map.put("total",((int)questionPageInfo.getTotal()+pageSize-1)/pageSize);//返回总页面数
            map.put("size",questionPageInfo.getTotal());
            return map;
        }

        return null;
    }

    @Override
    public boolean removeQustion(int questionId) {
        return teacherTestMapper.deleteQuestionById(questionId);
    }

    @Override
    public HashMap<String, Object> getPartQuestion(ChoiceByConditionReqVO question, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<QuestionRespVO> list = teacherTestMapper.selectByCondition(question);
        for(int i=0;i<list.size();i++){
            if (list.get(i).getDegree().equals("1")){
                list.get(i).setDegree("简单");
            }else if (list.get(i).getDegree().equals("2")){
                list.get(i).setDegree("中等");
            }else{
                list.get(i).setDegree("难");
            }
            if (list.get(i).getType().equals("1")){
                list.get(i).setType("单选题");
            }else  if (list.get(i).getType().equals("2")){
                list.get(i).setType("多选题");
            }else  if (list.get(i).getType().equals("3")){
                list.get(i).setType("判断题");
            }else  if (list.get(i).getType().equals("4")){
                list.get(i).setType("问答题");
            }else  {
                list.get(i).setType("讨论题");
            }
        }
        PageInfo<QuestionRespVO> info=new PageInfo<>(list);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",info.getList());/*返回指定页面的数据*/
        map.put("nowPage",pageNum);/*返回当前页面*/
        map.put("total",((int)info.getTotal()+pageSize-1)/pageSize);//返回总页面数
        map.put("size",info.getTotal());
        return map;
    }

    @Override
    public QuestionRespVO selectQuestion(int id) {
        QuestionRespVO question = teacherTestMapper.selectQuestionById(id);
        if (question.getDegree().equals("1")){
            question.setDegree("简单");
        }else if (question.getDegree().equals("2")){
            question.setDegree("中等");
        }else{
            question.setDegree("难");
        }
        if (question.getType().equals("1")){
            question.setType("单选题");
        }else  if (question.getType().equals("2")){
            question.setType("多选题");
        }else  if (question.getType().equals("3")){
            question.setType("判断题");
        }else  if (question.getType().equals("4")){
            question.setType("问答题");
        }else  {
            question.setType("讨论题");
        }
        return question;
    }

    @Override
    public boolean deleteAllFailTest() {
        int count = teacherTestMapper.selectCountNotAdd();
        if (count>0){
            boolean deleteAllNotPaperid = teacherTestMapper.deleteAllNotPaperid();
            return deleteAllNotPaperid;
        }
        return true;
    }
}
