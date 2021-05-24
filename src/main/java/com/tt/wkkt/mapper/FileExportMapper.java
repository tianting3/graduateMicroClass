package com.tt.wkkt.mapper;

import com.tt.wkkt.model.StudentGrade;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface FileExportMapper {

    List<StudentGrade> selectAllGrade(int courseId);

    int selectCourseId(String userName);
}
