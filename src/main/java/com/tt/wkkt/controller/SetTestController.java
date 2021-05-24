package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.TestPaper;
import com.tt.wkkt.service.SetTestService;
import com.tt.wkkt.service.WebSocket;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

import static com.tt.wkkt.common.ResultCode.OK;

/**
 * @Author tianting
 * @Description 老师设置试卷页面
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/setTest")
public class SetTestController {

    @Autowired
    SetTestService setTestService;

    @Autowired
    WebSocket webSocket;
    /*查询添加成功后的总分和测试题目数量*/
    @RequestMapping(value = "/allScore",method = RequestMethod.POST)
    public Result allScore(@RequestParam("teacherName")String teacherName){

        try{
            Result result = new Result<>();
            HashMap<String, Object> map = setTestService.getAllScoreAndTestCount(teacherName);
            if (map.get("allScore")==null){
                map.put("allScore",0);
            }
            result.setData(map);
            result.setMsg("成功查询总分");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询总分失败");
        }
    }


    @RequestMapping(value = "/addPaper",method = RequestMethod.POST)
    public Result addPaper(@RequestBody TestPaper testPaper){

        try{

            Result addPaper = setTestService.addPaper(testPaper);

            return addPaper;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("添加试卷失败");
        }
    }



    /*查询所有的试题*/
    @RequestMapping(value = "/allPaper",method = RequestMethod.POST)
    public Result allPaper( @RequestParam("teacherName")String teacherName,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            Result result = new Result<>();
            HashMap<String, Object> map = setTestService.getAllPaper(teacherName, pageNum, pageSize);
            result.setData(map);
            result.setMsg("查询所有试卷成功");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询所有试卷失败");
        }
    }

/*查询是否可以答题，*/
    @RequestMapping(value = "/judgeResponse",method = RequestMethod.POST)
    public Result judgeResponse(HttpSession session,@RequestParam("paperId") int  paperId){
        Result result = new Result<>();
        try{
            String user = (String)session.getAttribute("userName");
            boolean response = setTestService.judgeCanResponse(user, paperId);
            if (!response){
                result.setMsg("已经提交过试卷");
            }
            result.setData(response);
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("系统错误");
        }
    }
    /*查询所有已发布试题*/
    @RequestMapping(value = "/issuedPaper",method = RequestMethod.POST)
    public Result issuedPaper(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            Result result = new Result<>();
            String userName=(String)session.getAttribute("userName");
            HashMap<String, Object> map = setTestService.getIssuedPaper(userName, pageNum, pageSize);
            result.setData(map);
            result.setMsg("查询所有已发布试卷成功");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询所有试卷失败");
        }
    }

    /*删除指定试卷*/
    @RequestMapping(value = "/removePaper",method = RequestMethod.POST)
    public Result removePaper( @RequestParam("id")int id){
        try{
            Result result = new Result<>();
            boolean removePaper = setTestService.removePaper(id);
            if (removePaper==false){
                result.setMsg("删除试卷失败");
            }else {
                result.setMsg("删除试卷成功");
            }
            webSocket.sendMessage("老师删除试卷");
            result.setData(removePaper);

            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("删除试卷失败");
        }
    }


    /*修改指定试卷*/
    @RequestMapping(value = "/updatePaper",method = RequestMethod.POST)
    public Result updatePaper( @RequestBody TestPaper testPaper){
        try{
            Result result = new Result<>();
            boolean updatePaper = setTestService.updatePaper(testPaper);
            result.setData(updatePaper);
            result.setMsg("修改试卷成功");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("修改试卷失败");
        }
    }

    /*查看试卷的题目*/
    @RequestMapping(value = "/checkPaper",method = RequestMethod.POST)
    public Result checkPaper(@Param("id")int id,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            Result result = new Result<>();
            HashMap<String, Object> map = setTestService.checkPaper(id, pageNum, pageSize);
            result.setData(map);
            result.setMsg("查看试卷成功");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查看试卷失败");
        }
    }
    /*获得所有的题目*/
    @RequestMapping(value = "/getAllTest",method = RequestMethod.POST)
    public Result getAllTest(@Param("id")int id){
        try{
            Result result = new Result<>();
            HashMap<String, Object> map = setTestService.getAllTest(id);
            result.setData(map);
            result.setMsg("查看试卷成功");
            result.setCode(OK.getCode());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查看试卷失败");
        }
    }

}
