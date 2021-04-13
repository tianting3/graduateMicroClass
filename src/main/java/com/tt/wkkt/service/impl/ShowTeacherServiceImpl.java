package com.tt.wkkt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tt.wkkt.mapper.ShowTeacherListMapper;
import com.tt.wkkt.service.ShowTeacherService;
import com.tt.wkkt.vo.req.FilesReqVO;
import com.tt.wkkt.vo.resp.TeacherCourseRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class ShowTeacherServiceImpl implements ShowTeacherService {

    @Autowired
    ShowTeacherListMapper showTeacherListMapper;
    @Override
    public HashMap<String,Object> teacherList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TeacherCourseRespVO> list=showTeacherListMapper.queryTeacherCourse();
        PageInfo<TeacherCourseRespVO> info=new PageInfo<>(list);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",info.getList());/*返回指定页面的数据*/
        map.put("nowPage",pageNum);/*返回当前页面*/
        map.put("total",((int)info.getTotal()+pageSize-1)/pageSize);//返回总页面数
        map.put("size",info.getTotal());
        return map;
    }

    @Override
    public  HashMap<String,Object> getFiles(String userName,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<String> list=showTeacherListMapper.queryFilesByTeachername(userName);
        PageInfo<String> info=new PageInfo<>(list);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",info.getList());/*返回指定页面的数据*/
        map.put("nowPage",pageNum);/*返回当前页面*/
        map.put("total",((int)info.getTotal()+pageSize-1)/pageSize);//返回总页面数
        map.put("size",info.getTotal());
        return map;
    }

    @Override
    public String getPathByFilename(String fileName) {
        return showTeacherListMapper.queryPathByFilename(fileName);
    }


}
