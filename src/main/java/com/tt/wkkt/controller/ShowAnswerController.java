package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.service.CompetitiveAnswerService;
import com.tt.wkkt.service.WebSocket;
import com.tt.wkkt.vo.resp.AnswerPeopleResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.tt.wkkt.common.ResultCode.ERROR;
import static com.tt.wkkt.common.ResultCode.OK;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
/*@RequestMapping("/answer")*/
    @RestController

public class ShowAnswerController {
        @Autowired
    CompetitiveAnswerService competitiveAnswerService;
        @Autowired
    WebSocket webSocket;


    @RequestMapping(value = "/answerPeople",method = RequestMethod.GET)
    @ResponseBody
    public  Result answerPeople(HttpSession session){
        System.out.println(session.getAttribute("userName"));
        Result response=new Result();
        try{
            String user= (String) session.getAttribute("userName");
            List<AnswerPeopleResp> list=competitiveAnswerService.showCompetitive(user);
            System.out.println(list);
            for (AnswerPeopleResp a:list){
                if (a.getUserName().equals(user)){
                    response.setMsg("抢答成功");
                    webSocket.sendMessage("抢答成功");
                }else {
                    response.setMsg("抢答失败");
                }
            }
            response.setData(list);
            response.setCode("200");
            return response;
        }catch (Exception e){
            response.setCode("505");
            response.setMsg("抢答失败");
            return response;
        }

    }


    /*老师按开始抢答，发送可以抢答的消息给学生端*/
    @ResponseBody
    @RequestMapping(value = "/startCompetitve",method = RequestMethod.GET)
    public void sendMessageToStudent(String btn){
        try {
            competitiveAnswerService.resetCompetive();
            webSocket.onMessage(btn);
            System.out.println("+++++++++++++++++++++++++++++++++++++==="+btn);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /*查询是谁抢答成功*/
    @ResponseBody
    @RequestMapping(value = "/competitiveSuccessStudent",method = RequestMethod.GET)
    public Result competitiveSuccessStudent(){
        Result result = new Result<>();
        try {
            List<AnswerPeopleResp> list = competitiveAnswerService.getSuccessStudent();
            result.setData(list);
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(ERROR.getCode());
            return result;
        }

    }
}
