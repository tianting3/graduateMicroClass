package com.tt.wkkt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.mapper.TeacherUploadMapper;
import com.tt.wkkt.model.FileUpload;
import com.tt.wkkt.service.TeacherUploadService;
import com.tt.wkkt.vo.req.FilesReqVO;
import com.tt.wkkt.vo.resp.QuestionRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class TeacherUploadServiceImpl implements TeacherUploadService {
    @Autowired
    TeacherUploadMapper teacherUploadMapper;
    /*@Override
    public Result upload(MultipartFile file) {
        if (file.isEmpty()){
            return Result.error("400","文件为空");
        }
        String originalFilename=file.getOriginalFilename();
        String filename=System.currentTimeMillis()+"."+originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String filePath="E:\\graduate\\micro class\\file\\";
        File dest=new File(filePath+filename);
        *//*查看是否存在目录*//*
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);*//*把文件拷贝到dest*//*
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("500","上传失败");
        }
        return Result.ok(filename);
    }*/
    @Override
    public Result upload(MultipartFile file) {
        if (file.isEmpty()){
            return Result.error("400","文件为空");
        }

        FileUpload fileUpload=new FileUpload();
        String originalFilename=file.getOriginalFilename();
            String filePath="E:\\graduate\\micro class\\file\\";
            String address=filePath+originalFilename;
            fileUpload.setAddress(address);
            fileUpload.setFileName(originalFilename);
            File dest=new File(filePath+originalFilename);
            boolean insertFile=teacherUploadMapper.insertFile(fileUpload);//存入数据库
            /*查看是否存在目录*/
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try{
                file.transferTo(dest);/*把文件拷贝到dest*/
            }catch (Exception e){
                e.printStackTrace();
                return Result.error("500","上传失败");
            }
            return Result.ok(address);

    }

    @Override
    public Result uploadImage(MultipartFile file) {
        if (file.isEmpty()){
            return Result.error("400","文件为空");
        }

        FileUpload fileUpload=new FileUpload();
        String originalFilename=file.getOriginalFilename();
        String isExsit=teacherUploadMapper.queryIsExsit(originalFilename);
        System.out.println(isExsit);

            String filePath="E:\\graduate\\micro class\\file\\";
            String address=filePath+originalFilename;
            fileUpload.setAddress(address);
            fileUpload.setFileName(originalFilename);
            File dest=new File(filePath+originalFilename);
            boolean insertFile=teacherUploadMapper.insertFile(fileUpload);//存入数据库
            /*查看是否存在目录*/
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try{
                file.transferTo(dest);/*把文件拷贝到dest*/
            }catch (Exception e){
                e.printStackTrace();
                return Result.error("500","上传失败");
            }
            return Result.ok(address);

    }

    @Override
    public boolean teacherFile(FilesReqVO filesReqVO) {
        boolean teacherFiles=teacherUploadMapper.insertTeacherFile(filesReqVO);
        return teacherFiles;
    }

    @Override
    public HashMap<String,Object> queryByTeacherName(String teacherName,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FilesReqVO>  list=teacherUploadMapper.queryByTeacherName(teacherName);
        PageInfo<FilesReqVO> info=new PageInfo<>(list);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",info.getList());/*返回指定页面的数据*/
        map.put("nowPage",pageNum);/*返回当前页面*/
        map.put("total",((int)info.getTotal()+pageSize-1)/pageSize);//返回总页面数
        map.put("size",info.getTotal());
         return map;
    }

    @Override
    public boolean deleteManyFilesById(List list) {
        List<String> paths=teacherUploadMapper.queryPathsByIds(list);
        List names=new ArrayList();
        for (String path:paths){
            String name=path.substring(path.lastIndexOf("\\")+1);
            names.add(name);
            File file = new File(path);
            if(file.exists()){
                file.delete();
            }
        }
        int deleByNames=teacherUploadMapper.deleteUploadByFilenames(names);
        return teacherUploadMapper.deleteManyFilesByIds(list);
    }

    @Override
    public boolean deleteFile(int id) {
//        根据Id查询地址，判断是否存在文件，如果存在就删除，同时删除上传数据库的资料
            String path=teacherUploadMapper.queryPathById(id);
            String fileName=path.substring(path.lastIndexOf("\\")+1);
             System.out.println(fileName);
             /*根据文件名删除上传数据库的资料*/
           int delUpload=teacherUploadMapper.deleteUploadByFilename(fileName);
            File file = new File(path);
               if(file.exists()){
                  file.delete();
               }
            boolean re=teacherUploadMapper.deleteFileById(id);
               return  re;
    }


}
