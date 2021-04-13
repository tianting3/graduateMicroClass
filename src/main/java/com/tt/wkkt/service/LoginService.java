package com.tt.wkkt.service;

import com.tt.wkkt.model.Student;
import com.tt.wkkt.model.Teacher;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface LoginService {
    Student checkLogin(String userName, String password);

    Teacher checkTeacherLogin(String userName,String password);
}
