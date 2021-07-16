package com.tt.wkkt;

import com.tt.wkkt.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WkktApplicationTests {
    @Autowired
    StudentRepository studentRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void findMaxId(){
        System.out.println("================================================");
        System.out.println(studentRepository.findMaxId());
    }

}
