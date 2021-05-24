package com.tt.wkkt.controller;

import com.tt.wkkt.common.Result;

import com.tt.wkkt.model.Student;
import com.tt.wkkt.model.Teacher;
import com.tt.wkkt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.tt.wkkt.common.ResultCode.ERROR;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@RestController
/*@CrossOrigin(origins = "http://127.0.0.1:8020/")*/
/*@RequestMapping("/login")*/
public class LoginController {

    @Autowired
    LoginService loginService;
    /*role为1时为学生
    * role为2时为老师*/
    @ResponseBody
    @RequestMapping(value = "/doLogin",method = RequestMethod.GET)

   public Result login(@RequestParam String userName, String password, int role, HttpSession session){
        /*HttpSession session = request.getSession();*/
        Result response = new Result();
        try {

            if (role == 1) {
                session.setAttribute("userName",userName);

                /*查询学生表*/
               Student re=loginService.checkLogin(userName, password);
                if (re!=null) {
                    response.setData(re);
                    response.setCode("200");
                    response.setMsg("成功登录");
                }else{
                    response.setCode("205");
                    response.setMsg("重新输入账号密码");
                }
                System.out.println("成功");
                return response;
            } else {
                /*查询老师表*/
                session.setAttribute("userName",userName);
                Teacher re=loginService.checkTeacherLogin(userName, password);
                if (re!=null) {
                    response.setData(re);
                    response.setCode("200");
                    response.setMsg("成功登录");
                }else{
                    response.setCode("205");
                    response.setMsg("重新输入账号密码");
                }
                System.out.println("成功");
                return response;

            }
        }catch (Exception e){
            response.setCode("505");
            response.setMsg("登陆失败");
            return response;
        }
   }


    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)

    public Result doRegister( String userName, String new_password, String old_password, int role,String name){
        Result result = new Result();
        try {
            if (userName.equals(null)||new_password.equals(null)||old_password.equals(null)||name.equals(null)||
                    userName.equals("")||new_password.equals("")||old_password.equals("")||name.equals("")){
                result.setCode(ERROR.getCode());
                result.setMsg("请检查注册信息，不能为空");
                return result;
            }
            return  loginService.register(userName, new_password, old_password, role,name);
        }catch (Exception e){
            result.setCode(ERROR.getCode());
            result.setMsg("注册失败");
            return result;
        }
    }

}
