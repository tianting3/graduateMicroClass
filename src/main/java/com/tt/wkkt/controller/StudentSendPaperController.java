package com.tt.wkkt.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.ReviewPaper;
import com.tt.wkkt.model.ShowReviewQuestion;
import com.tt.wkkt.service.StudentSendPaperService;
import com.tt.wkkt.vo.req.RemarkPaperReqVO;
import com.tt.wkkt.vo.req.SendPaperReqVO;
import com.tt.wkkt.vo.resp.ReviewPaperRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RestController
public class StudentSendPaperController {
    @Autowired
    StudentSendPaperService studentSendPaperService;

    @RequestMapping("/submitPaperReturnImmediately")
    public Result submitPaperReturnImmediately(@RequestBody Map<String,Object> map , HttpSession session){
        Result result = new Result<>();
        try{
            result= studentSendPaperService.insertReviewPaper(map,session);
            result.setCode(OK.getCode());
            result.setMsg("成功提交试卷");
        }catch (Exception e){
            result.setMsg("提交试卷失败");
            result.setCode(ERROR.getCode());
            e.printStackTrace();
        }
        return result;
    }

    /*没有问答题和讨论题*/
    @RequestMapping(value = "/allReviewPaperList",method = RequestMethod.POST)
    public Result allReviewPaperList(@RequestParam("id") int id,HttpSession session,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        Result result = new Result<>();
        try{
            HashMap<String, Object> map = studentSendPaperService.allReviewPaperByPaperId(id, session, pageNum, pageSize);
            result.setData(map);
            result.setCode(OK.getCode());
            result.setMsg("成功提交试卷");
        }catch (Exception e){
            result.setMsg("提交试卷失败");
            result.setCode(ERROR.getCode());
            e.printStackTrace();
        }
        return result;
    }

    /*根据试卷名以及学生账号批阅试卷*/
    @RequestMapping(value = "/remarkPaper",method = RequestMethod.POST)
    public Result remarkPaper(@RequestBody RemarkPaperReqVO remarkPaperReqVO){
        Result result = new Result<>();
        try{
            HashMap<String, Object> map = studentSendPaperService.remarkPaperByPaperNameAndStudentUser(remarkPaperReqVO);
            result.setData(map);
            result.setCode(OK.getCode());
        }catch (Exception e){
            result.setMsg("批阅试卷失败");
            result.setCode(ERROR.getCode());
            e.printStackTrace();
        }
        return result;
    }


}
