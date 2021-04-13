package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.service.FileExportService;
import com.tt.wkkt.util.FileUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        try{
            List<StudentGrade> grades = fileExportService.exportGrade(1);
            // 创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建表
            HSSFSheet sheet = workbook.createSheet("学生成绩表");
            // 创建行
            HSSFRow row = sheet.createRow(0);
            // 创建单元格样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            // 表头
            String[] head = {"编号", "学生账号","抢答分数","期末分数","课程id","课程名","老师账号"};
            HSSFCell cell;
            // 设置表头
            for(int iHead=0; iHead<head.length; iHead++) {
                cell = row.createCell(iHead);
                cell.setCellValue(head[iHead]);
                cell.setCellStyle(cellStyle);
            }
            // 设置表格内容
            for(int iBody=0; iBody<grades.size(); iBody++) {
                row = sheet.createRow(iBody+1);
                StudentGrade u = grades.get(iBody);
                String[] gradeArray = new String[7];
                gradeArray[0]=String.valueOf(u.getId());
                gradeArray[1]=u.getStudentUser();
                gradeArray[2]=String.valueOf(u.getCompetitiveGrade());
                gradeArray[3]=String.valueOf(u.getExamGrade());
                gradeArray[4]=String.valueOf(u.getCourseId());
                gradeArray[5]=u.getCourseName();
                gradeArray[5]=u.getTeacherUser();
                for(int iArray=0; iArray<gradeArray.length; iArray++) {
                    row.createCell(iArray).setCellValue(gradeArray[iArray]);
                }
            }
            // 生成Excel文件
            FileUtil.createFile(response, workbook);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
