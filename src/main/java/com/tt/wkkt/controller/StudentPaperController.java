package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.service.SetTestService;
import com.tt.wkkt.service.StudentPaperService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.tt.wkkt.common.ResultCode.ERROR;
import static com.tt.wkkt.common.ResultCode.OK;

/**
 * @Author tianting
 * @Description 学生试卷界面的操作
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/studentPaper")
public class StudentPaperController {
    @Autowired
    StudentPaperService studentPaperService;
    @Autowired
    SetTestService setTestService;
    /*学生端显示所有的试卷*/
    @RequestMapping(value = "/allPaper",method = RequestMethod.POST)
    public Result allPaper(@RequestParam("userName") String userName, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        Result result=new Result();
        try{
            HashMap<String,Object> map=studentPaperService.allPaper(userName,pageNum,pageSize);
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

    /*开始考试，获得所有的题目*/
    @RequestMapping(value = "/getAllTest",method = RequestMethod.POST)
    public Result getAllTest(@Param("id")int id, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            Result result = new Result<>();
            HashMap<String, Object> map = setTestService.checkPaper(id, pageNum, pageSize);
            result.setData(map);
            result.setMsg("获得试卷题目");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("获得试卷题目失败");
        }
    }

}
