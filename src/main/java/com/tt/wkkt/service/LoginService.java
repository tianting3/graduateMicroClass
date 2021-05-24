package com.tt.wkkt.service;

import com.tt.wkkt.common.Result;
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

    Result register(String userName, String new_password, String old_password, int role,String name);
}
