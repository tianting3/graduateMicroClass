package com.tt.wkkt.service;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.model.StudentGrade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface FileExportService  {
    List<StudentGrade> exportGrade(int courseId);
}
