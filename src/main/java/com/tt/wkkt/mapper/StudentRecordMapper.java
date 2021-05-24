package com.tt.wkkt.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author tianting
 * @Description 学生成绩表
 * @Param
 * @return
 **/
public interface StudentRecordMapper {

    /*根据课程id查询总的课程抢答分*/
    int queryAllComptitiveGradeByCourseId(@Param("courseId")int courseId,@Param("studentUser")String studentUser);
}
