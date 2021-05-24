package com.tt.wkkt.service;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.ReviewPaper;
import com.tt.wkkt.model.StudentGrade;
import com.tt.wkkt.vo.resp.GradeExportRespVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface FileExportService  {
    List<GradeExportRespVO> exportGrade(Integer paperId);

    HashMap<String,Object> getAllGrade(String userName, Integer pageNum, Integer pageSize);
}
