package com.tt.wkkt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tt.wkkt.mapper.StudentPaperMapper;
import com.tt.wkkt.model.IssuePaper;
import com.tt.wkkt.service.StudentPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class StudentPaperServiceImpl implements StudentPaperService {
    @Autowired
    StudentPaperMapper studentPaperMapper;
    @Override
    public HashMap<String, Object> allPaper(String teacherName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<IssuePaper> list = studentPaperMapper.queryAllPaperByTeachername(teacherName);
        PageInfo<IssuePaper> info = new PageInfo<>(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",info.getList());
        map.put("nowPage",pageNum);/*返回当前页面*/
        map.put("total",((int)info.getTotal()+pageSize-1)/pageSize);//返回总页面数
        map.put("size",info.getTotal());
        return map;
    }
}
