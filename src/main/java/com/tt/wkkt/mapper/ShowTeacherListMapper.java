package com.tt.wkkt.mapper;

import com.tt.wkkt.vo.resp.TeacherCourseRespVO;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface ShowTeacherListMapper {
    List<TeacherCourseRespVO> queryTeacherCourse();

    List<String> queryFilesByTeachername(String teacherName);

    String queryPathByFilename(String fileName);
}
