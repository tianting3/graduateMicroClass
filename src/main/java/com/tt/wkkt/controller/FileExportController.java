package com.tt.wkkt.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.ReviewPaper;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.service.FileExportService;
import com.tt.wkkt.util.FileUtil;
import com.tt.wkkt.vo.resp.GradeExportRespVO;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.tt.wkkt.common.ResultCode.ERROR;
import static com.tt.wkkt.common.ResultCode.OK;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/file")
public class FileExportController {

    @Autowired
    FileExportService fileExportService;

    @RequestMapping(value = "/exportExcel",method = RequestMethod.POST)
    public Result exportExcel(@RequestParam("paperId") Integer paperId, HttpSession session) {
        Result result = new Result<>();
        try {

            String user= (String) session.getAttribute("userName");
            List<GradeExportRespVO> grades = fileExportService.exportGrade(paperId);

            if (CollectionUtil.isNotEmpty(grades)){
                String fileName = grades.get(0).getPaperName();
                Date date = new Date();

                // 通过工具类创建writer
                ExcelWriter writer = ExcelUtil.getWriter("E:/graduate/micro class/export/" + fileName + date.getTime() + ".xlsx");
                // 合并单元格后的标题行，使用默认标题样式
                writer.merge(3, "测试成绩表");
                // 一次性写出内容，使用默认样式，强制输出标题
                writer.write(grades, true);
                // 关闭writer，释放内存
                writer.close();
                result.setMsg("导出成功");
                result.setCode(OK.getCode());
                return result;
            }
            result.setMsg("没有成绩数据");
            result.setCode(OK.getCode());
            return result;


        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("导出失败");
            result.setCode(ERROR.getCode());
            return result;
        }
    }


    @RequestMapping(value = "/allGrade",method = RequestMethod.POST)
    public Result allGrade(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        Result result = new Result<>();
        try {
            String user= (String) session.getAttribute("userName");
            System.out.println(user);
            HashMap<String, Object> grades = fileExportService.getAllGrade(user, pageNum, pageSize);
            result.setCode(OK.getCode());
            result.setData(grades);
            result.setMsg("成功获得所有测试");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ERROR.getCode());
            result.setMsg("获得所有的测试");
        }
        return  result;
    }
}
