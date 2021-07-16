package com.tt.wkkt.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.tt.wkkt.model.Student;
import com.tt.wkkt.repository.StudentRepository;
import com.tt.wkkt.service.StudentJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*import com.tt.wkkt.util.RedisUtil;*/

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@RestController
public class RedisTestController {
    /*  @Autowired
      RedisUtil redisUtil;
  */
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StudentJpaService studentJpaService;

    /*@RequestMapping("/set")
    public String set(){
        Student student = new Student("zhangsan", "zhangsan", "123456");
        redisUtil.set("student",student);
        return "success saved user~!";
    }
    @RequestMapping("/get")
    public String get() {
        Student student = (Student)redisUtil.get("student");
        return student.toString();
    }*/

    @RequestMapping("/setString")
    public String setString() {
//        Student student = new Student("zhangsan", "zhangsan", "123456");
//        redisTemplate.opsForValue().set("student",student);

        return "success saved user~!";
    }

    @RequestMapping("/getString")
    public String getString() {
        Student st = (Student) redisTemplate.opsForValue().get("student");
        return st.toString();
    }

    @RequestMapping("/findMaxId")
    public void findMaxId() {
        System.out.println("================================================");
        System.out.println(studentRepository.findMaxId());
    }

    @RequestMapping("/findIdByUserName")
    public void findIdByUserName() {
        System.out.println("================================================");
        System.out.println(studentRepository.findIdByUserName("wangwu"));
    }

    @RequestMapping("/findAll")
    public void findAll() {
        System.out.println("================================================");
        List<Student> all = studentRepository.findAllOrOrderById();
        System.out.println(all);
    }

    @RequestMapping("/findById")
    public void findById() {
        System.out.println("================================================");
        System.out.println(studentRepository.findById(1).orElse(null));
    }

    @RequestMapping("/save")
    public void save() {
        System.out.println("================================================");
        Student student = new Student();
        student.setUserName("ty");
        student.setStudentName("统一");
        student.setPassword("123");
        System.out.println(studentRepository.save(student));
    }

    @RequestMapping("/saveAll")
    public void saveAll() {
        System.out.println("================================================");
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Student student = new Student();
            student.setUserName("ty" + i);
            student.setStudentName("统一" + i);
            student.setPassword("123" + i);
            students.add(student);
        }

        System.out.println(studentRepository.saveAll(students));
    }

    @RequestMapping("/update")

    public void update() {
        System.out.println("================================================");
        System.out.println(studentRepository.updateById("new password", 22));
    }

    @RequestMapping("/delete")
    public void delete() {
        System.out.println("================================================");
        studentRepository.deleteById(23);
        System.out.println("删除Id为23成功");
    }


    /*
    *
    * JPA分页
    * */
    @RequestMapping("/index")
    public String index(@RequestParam(value = "page", defaultValue = "2") Integer page, @RequestParam(value = "size", defaultValue = "3") Integer size) {
        System.out.println("================================================");
        String jsonString = JSON.toJSONString(studentJpaService.getStudentWithPage(page, size));
        System.out.println(jsonString);
        return jsonString;
    }

}
