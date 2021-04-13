package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.common.ResultCode;
import com.tt.wkkt.model.AskQuestion;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.service.FeedbackService;
import com.tt.wkkt.service.WebSocket;
import com.tt.wkkt.vo.req.FeedbackReqVo;
import com.tt.wkkt.vo.resp.GetFeedbackRespVO;
import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

import static com.tt.wkkt.common.ResultCode.*;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/

@RestController
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    WebSocket webSocket;
    /*学生发送意见反馈*/
    @RequestMapping(value = "/propose",method = RequestMethod.POST)
    public Result sendPropose(@RequestBody FeedbackReqVo feedbackReqVo, HttpSession session){
        Result result=new Result();
        try{
            System.out.println(session.getAttribute("userName"));
            String userName=(String)session.getAttribute("userName");
            String way=feedbackReqVo.getWay();
            String tpro=feedbackReqVo.gettPropose();
            String wPro=feedbackReqVo.getwPropose();
            String feed=feedbackReqVo.getFeedback();
            String atmo=feedbackReqVo.getAtmosphere();
            if (way.equals("0")||tpro.equals("")||wPro.equals("")||feed.equals("")||atmo.equals("0")){
                result.setCode(NOPARAMETER.getCode());
                result.setMsg(NOPARAMETER.getMsg());
                return result;
            }
            boolean re=feedbackService.studentFeedback(feedbackReqVo);/*是否添加成功*/
            if (re==true) {
                System.out.println("成功插入");
                webSocket.sendMessage("收到一条反馈");
            }else{
                System.out.println("插入失败");
            }

            result.setCode(OK.getCode());
            result.setMsg(OK.getMsg());
            System.out.println("成功");

            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }
    }



    /*老师获得反馈信息*/
    @RequestMapping("/getFeedback")
    public Result getFeedback(){
        Result result=new Result();
        try{
            List<GetFeedbackRespVO> respVO=feedbackService.techerFeedBack();
            result.setData(respVO);
            result.setCode(OK.getCode());
            result.setMsg(OK.getMsg());
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg(ERROR.getMsg());
            return result;
        }
    }




    /*学生提问*/
    @RequestMapping(value = "/studentAsk",method = RequestMethod.POST)
    public Result studentAsk(@RequestBody AskQuestion askQuestion, HttpSession session){
        Result result=new Result();
        try{
            String userName=(String)session.getAttribute("userName");
            if (askQuestion.getAskQuestion().length()<=5){
                result.setCode(ERROR.getCode());
                result.setMsg("没有填写问题");
                return result;
            }
            askQuestion.setUserName(userName);

            System.out.println(askQuestion.getAskQuestion());
            boolean re=feedbackService.studentAsk(askQuestion);/*是否添加成功*/
            if (re==true) {
                webSocket.sendMessage("收到一条提问反馈");
            }else{
                result.setCode(ERROR.getCode());
                result.setMsg("学生提问失败");
                return result;
            }

            result.setCode(OK.getCode());
            result.setMsg("学生提问成功");
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg("学生提问失败");
            return result;
        }
    }




    /*老师收到学生的提问*/
    @RequestMapping(value = "/getAllAskQuestion",method = RequestMethod.GET)
    public Result getAllAskQuestion(HttpSession session){
        Result result=new Result();
        try{
            String userName=(String)session.getAttribute("userName");
            List<AskQuestion> allAskQue = feedbackService.getAllAskQue(userName);
            result.setData(allAskQue);
            result.setCode(OK.getCode());
            result.setMsg("成功获得提问");
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg("获得提问失败");
            return result;
        }
    }


    /*老师按按钮修改回复状态*/
    @RequestMapping(value = "/modifyReply",method = RequestMethod.POST)
    public Result modifyReply(@RequestParam("id") int id, HttpSession session){
        Result result=new Result();
        try{
            boolean modifyReply = feedbackService.modifyReply(id);
            result.setData(modifyReply);
            result.setCode(OK.getCode());
            result.setMsg("修改状态成功");
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg("修改状态失败");
            return result;
        }
    }


    /*老师设置抢答成绩给学生*/
    @RequestMapping(value = "/GradeToStudent",method = RequestMethod.POST)
    public Result GradeToStudent(@RequestBody StudentGrade studentGrade, HttpSession session){
        Result result=new Result();
        try{
            String teacherUser=(String)session.getAttribute("userName");
            studentGrade.setTeacherUser(teacherUser);
            boolean b = feedbackService.GradeToStudent(studentGrade);
            result.setData(b);
            result.setCode(OK.getCode());
            result.setMsg("打分成功");
            return result;
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg("打分失败");
            return result;
        }
    }
}
