package com.tt.wkkt.mapper;

import com.tt.wkkt.model.Student;
import com.tt.wkkt.model.Teacher;

import java.util.List;
import java.util.stream.Stream;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface LoginMapper {
    Student checkStudent(String userName, String password);

    Teacher checkTeacher(String userName,String password);

    String queryStudentNameByUsername(String userName);

    String queryteacherNameByUsername(String userName);

    boolean registerStudent(Student student);

    boolean registerTeacher(Teacher teacher);

    List<String> queryAllStudentUsername();

    List<String> queryAllTeacherUsername();

}
