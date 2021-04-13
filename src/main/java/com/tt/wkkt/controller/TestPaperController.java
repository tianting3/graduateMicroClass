package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.service.TestPaperService;
import com.tt.wkkt.vo.req.ChoiceByConditionReqVO;
import com.tt.wkkt.vo.req.ChoiceQuestionReqVO;
import com.tt.wkkt.vo.req.PartQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.tt.wkkt.common.ResultCode.ERROR;
import static com.tt.wkkt.common.ResultCode.OK;

/**
 * @Author tianting
 * @Description 试卷信息
 * @Param
 * @return
 **/
@RestController
@RequestMapping(value = "/testPaper")
public class TestPaperController {
    @Autowired
    TestPaperService testPaperService;
/*添加题目*/
    @RequestMapping(value = "/addProblem",method = RequestMethod.POST)
    public Result addProblem(@RequestBody ChoiceQuestionReqVO choiceQuestionReqVO){
        try{

//            判断choiceQuestion里面是否有question——id，有则返回添加过了，否则返回添加成功

            return testPaperService.addQuestion(choiceQuestionReqVO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("添加题目失败");
        }
    }

    /*添加成功后的试题*/
    @RequestMapping(value = "/successAdd",method = RequestMethod.POST)
    public Result successAdd(@RequestParam("userName") String userName, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        Result result=new Result();
        try{
            HashMap<String,Object> map=testPaperService.allAddAfterQuestion(userName,pageNum,pageSize);
            result.setData(map);
            result.setCode(OK.getCode());
            result.setMsg(OK.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }
    }


    /*移除添加成功的试题*/
    @RequestMapping(value = "/removeQuestion",method = RequestMethod.POST)
    public Result removeQuestion(@RequestParam("questionId")int questionId){

        try{
            boolean re=testPaperService.removeQustion(questionId);
            if (re==true){
                return Result.ok("试题移除成功");
            }else {
                return Result.ok("试题移除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("移除试题失败");
        }
    }

    /*根据条件筛选*/
    @RequestMapping(value = "/ChoiceByCondition",method = RequestMethod.POST)
    public Result ChoiceByCondition(@RequestBody ChoiceByConditionReqVO question, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        Result result=new Result();
        try{

            if (question.getDegree().equals("简单")){
                question.setDegree("1");
            }else if (question.getDegree().equals("中等")){
                question.setDegree("2");
            }else if (question.getDegree().equals("难")){
                question.setDegree("3");
            }else {
                question.setDegree(null);
            }
            if (question.getType().equals("单选题")){
                question.setType("1");
            }else  if (question.getType().equals("多选题")){
                question.setType("2");
            }else if (question.getType().equals("判断题")){
                question.setType("3");
            }else if (question.getType().equals("问答题")){
                question.setType("4");
            }else if (question.getType().equals("讨论题")){
                question.setType("5");
            }else{
                question.setType(null);
            }
            HashMap<String, Object> map = testPaperService.getPartQuestion(question, pageNum, pageSize);
            result.setData(map);
            result.setCode(OK.getCode());
            result.setMsg("筛选成功");
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg("筛选失败");
            return result;
        }
    }


    /*根据id获取问题*/
    @RequestMapping(value = "/selectQuestion",method = RequestMethod.POST)
    public Result selectQuestion(@RequestParam("id")int questionId){

        try{
            Result<QuestionRespVO> result = new Result<>();
            QuestionRespVO questionRespVO=testPaperService.selectQuestion(questionId);
            result.setData(questionRespVO);
            result.setMsg("根据id查询问题成功");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("根据id查询问题失败");
        }
    }

    /*退出当前页面，则删除所有没有成功添加到试卷中的题目*/
    @RequestMapping(value = "/deleteAllFailTest",method = RequestMethod.GET)
    public Result deleteAllFailTest(){

        try{
            Result result = new Result<>();
            boolean allFailTest = testPaperService.deleteAllFailTest();
            result.setData(allFailTest);
            result.setMsg("成功删除未添加成功的题目");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("删除未添加成功的题目失败");
        }
    }
}
