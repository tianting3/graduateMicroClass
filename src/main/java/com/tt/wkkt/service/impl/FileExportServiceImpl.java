package com.tt.wkkt.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.mapper.FileExportMapper;
import com.tt.wkkt.mapper.LoginMapper;
import com.tt.wkkt.mapper.SetTestMapper;
import com.tt.wkkt.mapper.StudentSendPaperMapper;
import com.tt.wkkt.model.ReviewPaper;
import com.tt.wkkt.model.Student;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.model.TestPaper;
import com.tt.wkkt.service.FileExportService;
import com.tt.wkkt.util.KsBeanUtil;
import com.tt.wkkt.vo.resp.GradeExportRespVO;
import com.tt.wkkt.vo.resp.TeacherCourseRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.wkkt.util.FileUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class FileExportServiceImpl implements FileExportService {

    @Autowired
    FileExportMapper fileExportMapper;

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    SetTestMapper setTestMapper;

    @Autowired
    StudentSendPaperMapper studentSendPaperMapper;



    @Override
    public List<GradeExportRespVO> exportGrade(Integer paperId) {
        ArrayList<ReviewPaper> papers = studentSendPaperMapper.queryBypaperIdAndStatus(paperId);
        ArrayList<GradeExportRespVO> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(papers)){
            for (ReviewPaper paper:papers){
                GradeExportRespVO gradeExportRespVO = KsBeanUtil.copyPropertiesThird(paper, GradeExportRespVO.class);
                String studentName = loginMapper.queryStudentNameByUsername(paper.getStudentUser());
                gradeExportRespVO.setStudentName(studentName);
                list.add(gradeExportRespVO);
            }
        }
        return list;
    }

    @Override
    public HashMap<String,Object> getAllGrade(String userName, Integer pageNum, Integer pageSize) {
        ArrayList<Integer> paperIds = studentSendPaperMapper.queryPaperIdByTeacherUser(userName);
        PageHelper.startPage(pageNum,pageSize);
        List<TestPaper> list = setTestMapper.queryByPaperIds(paperIds);
        PageInfo<TestPaper> info=new PageInfo<>(list);
        HashMap<String,Object> map=new HashMap<>();
        map.put("data",info.getList());/*返回指定页面的数据*/
        map.put("nowPage",pageNum);/*返回当前页面*/
        map.put("total",((int)info.getTotal()+pageSize-1)/pageSize);//返回总页面数
        map.put("size",info.getTotal());
        return map;

    }
}
