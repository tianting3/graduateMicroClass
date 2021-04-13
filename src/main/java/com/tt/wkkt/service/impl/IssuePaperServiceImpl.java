package com.tt.wkkt.service.impl;

import com.tt.wkkt.common.DateUtil;
import com.tt.wkkt.common.Result;
import com.tt.wkkt.mapper.IssuePaperMapper;
import com.tt.wkkt.model.IssuePaper;
import com.tt.wkkt.service.IssuePaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class IssuePaperServiceImpl implements IssuePaperService {
    @Autowired
    IssuePaperMapper issuePaperMapper;

    @Override
    public Result issuePaper(IssuePaper issuePaper) {
        Result result = new Result<>();
        /*试卷名*/
        String paperTestName = issuePaper.getTestName();
        String teacherName = issuePaper.getTeacherName();
        List<String> names = issuePaperMapper.queryAllTestName(teacherName);
        if (names.contains(paperTestName)){
            result.setMsg("请勿重复发布");
            return result;
        }
        Date curren=new Date();
        String yyyyMmDdHhMmSs = DateUtil.getYyyyMmDdHhMmSs(curren);
        issuePaper.setIssueTime(yyyyMmDdHhMmSs);
        boolean paper = issuePaperMapper.issuePaper(issuePaper);
        result.setData(paper);
        result.setMsg("发布试卷成功");
        return result;
    }


}
