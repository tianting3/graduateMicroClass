package com.tt.wkkt.service.impl;

import com.tt.wkkt.mapper.LoginMapper;
import com.tt.wkkt.model.Student;
import com.tt.wkkt.model.Teacher;
import com.tt.wkkt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
    public Student checkLogin(String userName, String password){

        return loginMapper.checkStudent(userName,password);
    }

    public Teacher checkTeacherLogin(String name,String password){
        return loginMapper.checkTeacher(name,password);
    }
}
