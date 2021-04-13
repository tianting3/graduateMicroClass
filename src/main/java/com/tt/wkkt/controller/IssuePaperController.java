package com.tt.wkkt.controller;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.IssuePaper;
import com.tt.wkkt.service.IssuePaperService;
import com.tt.wkkt.service.WebSocket;
import com.tt.wkkt.vo.req.FeedbackReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.tt.wkkt.common.ResultCode.*;
import static com.tt.wkkt.common.ResultCode.ERROR;

/**
 * @Author tianting
 * @Description 发表试卷相关的控制层
 * @Param
 * @return
 **/

@RestController
@RequestMapping("/issue")
public class IssuePaperController {

    @Autowired
    IssuePaperService issuePaperService;
    @Autowired
    WebSocket webSocket;
    /*发布试卷*/
    @RequestMapping(value = "/issuePaper",method = RequestMethod.POST)
    public Result issuePaper(@RequestBody IssuePaper issuePaper){

        try{
            Result result = issuePaperService.issuePaper(issuePaper);
            if (result.getMsg()=="发布试卷成功"){
                webSocket.sendMessage("老师发布新的试卷");
            }
            System.out.println("成功发布试卷");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("老师发布试卷失败");
        }
    }


}
