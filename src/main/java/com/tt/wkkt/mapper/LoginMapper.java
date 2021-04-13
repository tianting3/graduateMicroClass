package com.tt.wkkt.mapper;

import com.tt.wkkt.model.Student;
import com.tt.wkkt.model.Teacher;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface LoginMapper {
    Student checkStudent(String userName, String password);

    Teacher checkTeacher(String userName,String password);
}
