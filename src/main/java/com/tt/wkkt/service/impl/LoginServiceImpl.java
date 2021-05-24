package com.tt.wkkt.service.impl;

import com.tt.wkkt.common.Result;
import com.tt.wkkt.mapper.LoginMapper;
import com.tt.wkkt.model.Student;
import com.tt.wkkt.model.Teacher;
import com.tt.wkkt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tt.wkkt.common.ResultCode.ERROR;
import static com.tt.wkkt.common.ResultCode.OK;

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

    @Override
    public Result register(String userName, String new_password, String old_password, int role,String name) {
        Result result = new Result<>();
        boolean b=false;
        if (!new_password.equals(old_password)){
            result.setCode(ERROR.getCode());
            result.setMsg("两次密码不一致");
            return result;
        }
        if (role==1){//学生
            List<String> list = loginMapper.queryAllStudentUsername();
            if (list.contains(userName)){
                result.setCode(ERROR.getCode());
                result.setMsg("账号已经存在，请重新输入");
                return result;
            }
            Student student = new Student();
            student.setPassword(new_password);
            student.setStudentName(name);
            student.setUserName(userName);
            b = loginMapper.registerStudent(student);
        }else if (role==2){//老师
            List<String> list = loginMapper.queryAllTeacherUsername();
            if (list.contains(userName)){
                result.setCode(ERROR.getCode());
                result.setMsg("账号已经存在，请重新输入");
                return result;
            }
            Teacher teacher = new Teacher();
            teacher.setPassword(new_password);
            teacher.setTeacherName(name);
            teacher.setUserName(userName);
            b=loginMapper.registerTeacher(teacher);
        }else{
            result.setCode(ERROR.getCode());
            result.setMsg("系统错误");
        }
        if (b==true){
            result.setCode(OK.getCode());
            result.setMsg("注册成功");
        }else{
            result.setCode(ERROR.getCode());
            result.setMsg("注册失败");
        }
        return result;
    }
}
