package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.Question;
import com.tt.wkkt.service.ManageQuestionService;
import com.tt.wkkt.vo.req.PartQuestionReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tt.wkkt.common.ResultCode.*;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@RestController
public class ManageQuestionController {

    @Autowired
    ManageQuestionService manageQuestionService;

    @RequestMapping(value = "/manageQuestion",method = RequestMethod.POST)
    public Result teacherQuestion(@RequestBody PartQuestionReqVO question){
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
            List<QuestionRespVO> respVOList=manageQuestionService.allQuestion(question);
            for(int i=0;i<respVOList.size();i++){
                if (respVOList.get(i).getDegree().equals("1")){
                    respVOList.get(i).setDegree("简单");
                }else if (respVOList.get(i).getDegree().equals("2")){
                    respVOList.get(i).setDegree("中等");
                }else{
                    respVOList.get(i).setDegree("难");
                }
                if (respVOList.get(i).getType().equals("1")){
                    respVOList.get(i).setType("单选题");
                }else  if (respVOList.get(i).getType().equals("2")){
                    respVOList.get(i).setType("多选题");
                }else  if (respVOList.get(i).getType().equals("3")){
                    respVOList.get(i).setType("判断题");
                }else  if (respVOList.get(i).getType().equals("4")){
                    respVOList.get(i).setType("问答题");
                }else  {
                    respVOList.get(i).setType("讨论题");
                }
            }
            result.setData(respVOList);
            result.setCode(OK.getCode());
            result.setMsg(OK.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }
    }

    /*分页查询*/
    @RequestMapping(value = "/pageQuestion",method = RequestMethod.POST)
    public Result pageQuestion(@RequestBody PartQuestionReqVO question, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
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
            HashMap<String,Object> map=manageQuestionService.pageQuestion(question,pageNum,pageSize);
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

    @RequestMapping(value = "/deleteQuestion",method = RequestMethod.GET)
    public Result deleteQuestion(int id){
        Result result=new Result();
        try{
            boolean re=manageQuestionService.deleteQuestion(id);
            result.setData(re);
            result.setCode(OK.getCode());
            result.setMsg(OK.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }

    }



    @RequestMapping(value = "/addQuestion",method = RequestMethod.POST)
    public Result addQuestion(@RequestBody Question question){
        Result result=new Result();
        try{

            if (!question.getAnswer().equals("")&&!question.getDegree().equals("")&&!question.getItems().equals("")&&!question.getQuestion().equals("")&&!question.getType().equals("")&&
                    !question.getAnswer().equals(null)&&!question.getDegree().equals(null)&&!question.getItems().equals(null)&&!question.getQuestion().equals(null)&&!question.getType().equals(null)){
                if (question.getDegree().equals("简单")){
                    question.setDegree("1");
                }else if (question.getDegree().equals("中等")){
                    question.setDegree("2");
                }else {
                    question.setDegree("3");
                }
                if (question.getType().equals("单选题")){
                    question.setType("1");
                }else  if (question.getType().equals("多选题")){
                    question.setType("2");
                }else if (question.getType().equals("判断题")){
                    question.setType("3");
                }else if (question.getType().equals("问答题")){
                    question.setType("4");
                }else{
                    question.setType("5");
                }
                    boolean re=manageQuestionService.addQuestion(question);
                result.setData(question);
                result.setCode(OK.getCode());
                result.setMsg(OK.getMsg());
                return result;
            }

            result.setCode(NOPARAMETER.getCode());
            result.setMsg(NOPARAMETER.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }
    }

    @RequestMapping(value = "/updateQuestion",method = RequestMethod.GET)
    public Result updateQuestion(int id){
        Result result=new Result();
        try{
            Question re=manageQuestionService.updateQuestion(id);
            result.setData(re);
            result.setCode(OK.getCode());
            result.setMsg(OK.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }

    }


    /*提交修改信息，进行修改*/
    @RequestMapping(value = "/updateBtn",method = RequestMethod.POST)
    public Result updateBtn(@RequestBody Question question){
        Result result=new Result();
        try{

            if (!question.getAnswer().equals("")&&!question.getDegree().equals("")&&!question.getItems().equals("")&&!question.getQuestion().equals("")&&!question.getType().equals("")&&
                    !question.getAnswer().equals(null)&&!question.getDegree().equals(null)&&!question.getItems().equals(null)&&!question.getQuestion().equals(null)&&!question.getType().equals(null)){
                if (question.getDegree().equals("简单")){
                    question.setDegree("1");
                }else if (question.getDegree().equals("中等")){
                    question.setDegree("2");
                }else {
                    question.setDegree("3");
                }
                if (question.getType().equals("单选题")){
                    question.setType("1");
                }else  if (question.getType().equals("多选题")){
                    question.setType("2");
                }else if (question.getType().equals("判断题")){
                    question.setType("3");
                }else if (question.getType().equals("问答题")){
                    question.setType("4");
                }else{
                    question.setType("5");
                }
                boolean re=manageQuestionService.updateById(question);
                result.setData(re);
                result.setCode(OK.getCode());
                result.setMsg(OK.getMsg());
                return result;
            }

            result.setCode(NOPARAMETER.getCode());
            result.setMsg(NOPARAMETER.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }
    }

    @RequestMapping(value = "/selectPartQuestion",method = RequestMethod.POST)
    public Result selectPartQuestion(@RequestBody PartQuestionReqVO question){
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
            List<Question> re=manageQuestionService.partQuestion(question);
            result.setData(re);
            result.setCode(OK.getCode());
            result.setMsg(OK.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }
    }

    /*批量删除*/
    @RequestMapping(value = "/deleteManyBtn",method = RequestMethod.POST)
    public Result deleteManyBtn(@RequestBody List<Integer> ids){
        Result result=new Result();
        try{
            boolean re=manageQuestionService.deleteMany(ids);
            System.out.println(ids);
            result.setData(re);
            result.setCode(OK.getCode());
            result.setMsg(OK.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }
    }



}
