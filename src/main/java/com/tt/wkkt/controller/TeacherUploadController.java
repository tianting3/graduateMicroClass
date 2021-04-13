package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.FileUpload;
import com.tt.wkkt.service.TeacherUploadService;
import com.tt.wkkt.service.WebSocket;
import com.tt.wkkt.vo.req.FilesReqVO;
import org.apache.tomcat.jni.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description 老师上传视频或者PPt
 * @Param
 * @return
 **/
@RestController
public class TeacherUploadController {
    @Autowired
    TeacherUploadService teacherUploadService;


    @Autowired
    WebSocket webSocket;
    /**
     * 上传文件的接口
     * @author: tianting
     * @date:2021/2/7
     */
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Result uploadFile(@RequestParam("file")MultipartFile file){
        try{

            return teacherUploadService.upload(file);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","上传失败");
        }

    }

    @RequestMapping(value = "uploadImage")
    public Result uploadImage(@RequestParam("file")MultipartFile file){
        try{

            return teacherUploadService.uploadImage(file);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","上传失败");
        }

    }

    /**
     * 上传文件后，将文件地址以及老师账号存储在files中
     * @author: tianting
     *
     */
    @RequestMapping(value = "teacherFile",method = RequestMethod.POST)
    public Result teacherFile(@RequestBody FilesReqVO file){
        try{
            boolean re=teacherUploadService.teacherFile(file);
            System.out.println(file.getFileName());
            System.out.println(file.getTeacherName());
            System.out.println(file.getPath());
            if(re==true){
                webSocket.sendMessage("老师有新上传文件");
            }
            return Result.ok(re);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","老师存储文件地址失败");
        }

    }

    /**
     * 根据账号显示文件列表
     * @author: tianting
     * @date:2020/9/
     */
    @RequestMapping(value = "fileList",method = RequestMethod.POST)
    public Result fileList(@RequestParam("teacherName") String teacherName,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            HashMap<String,Object> re=teacherUploadService.queryByTeacherName(teacherName,pageNum,pageSize);
            return Result.ok(re);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","查询文件列表失败");
        }

    }

    /*删除多行*/
    @RequestMapping(value = "deleteManyFilesById",method = RequestMethod.POST)
    public Result deleteManyFilesById(@RequestBody List<Integer> ids){
        try{
            boolean re=teacherUploadService.deleteManyFilesById(ids);
            if (re==true){
                webSocket.sendMessage("老师删除多个文件");
            }
            return Result.ok(re);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","删除多项失败");
        }

    }
    /*删除文件*/
    @RequestMapping(value = "deleteFile",method = RequestMethod.GET)
    public Result deleteFile(Integer id){
        try{
            boolean re=teacherUploadService.deleteFile(id);
            if (re==true){
                webSocket.sendMessage("老师删除文件");
            }
            return Result.ok(re);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","删除文件失败");
        }

    }

    @RequestMapping(value = "/openFile",method = RequestMethod.GET)
    public Result openFile(String path){
        try{
            File file=new File(path);
            file.setWritable(false);

            Desktop.getDesktop().open(file);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","打开文件失败");
        }
    }


//public static void main(String[] args) {
//    try{
//        Desktop.getDesktop().open(new File("E:\\graduate\\micro class\\file\\.txt"));
//    }catch (Exception e){
//        e.printStackTrace();
//    }
//}
}
