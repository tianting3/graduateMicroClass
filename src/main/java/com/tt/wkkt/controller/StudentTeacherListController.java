package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.service.ShowTeacherService;
import com.tt.wkkt.vo.resp.TeacherCourseRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description 学生页面的老师页面信息
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/student")
public class StudentTeacherListController {

    @Autowired
    ShowTeacherService showTeacherService;
    @RequestMapping("/teacherCourse")
    public Result teacherCourse(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            HashMap<String,Object> list=showTeacherService.teacherList(pageNum,pageSize);
           // List<TeacherCourseRespVO> list=showTeacherService.teacherList();
            System.out.println("ok===");
            return Result.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","查询老师列表失败");
        }
    }

    @RequestMapping(value = "/studentGetFiles",method = RequestMethod.POST)
    public Result studentGetFiles(@RequestParam("teacherName")String teacherName,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            HashMap<String,Object> list=showTeacherService.getFiles(teacherName,pageNum,pageSize);
            return Result.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","查询老师列表失败");
        }
    }

    /*根据文件名获取地址*/
    @RequestMapping(value = "/studentGetPath",method = RequestMethod.POST)
    public Result studentGetPath(@RequestParam("fileName") String fileName){
        try{
            String path=showTeacherService.getPathByFilename(fileName);
            return Result.ok(path);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","查询老师列表失败");
        }
    }

    /*下载txt文件*/
    @RequestMapping(value = "/fileDownload",method = RequestMethod.POST)
    public String fileDownload(HttpServletResponse response,@RequestParam("path") String path,@RequestParam("fileName")String fileName) throws Exception{
        File file=new File(path);
        if (!file.exists())
        {
            System.out.println("不存在");
            return "不存在";
        }
        FileInputStream fileInputStream=new FileInputStream(file);
        response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[2048];
        int len = 0;
        while ((len = fileInputStream.read(bytes))>0){
            outputStream.write(bytes,0,len);
        }
        fileInputStream.close();
        outputStream.close();
        return "";
    }


    @RequestMapping(value = "/studentDownload",method = RequestMethod.POST)
    public String studentDownload(HttpServletResponse response,@RequestParam("path") String path,@RequestParam("fileName")String fileName){
        try{
            File file=new File(path);
            System.out.println(file.exists());
            if (file.exists()){
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download  successfully!");
                    return "successfully";

                } catch (Exception e) {
                    System.out.println("Download  failed!");
                    return "failed";

                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();

        }
        return "";
    }
}
