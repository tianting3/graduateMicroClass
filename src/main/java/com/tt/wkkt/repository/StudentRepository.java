package com.tt.wkkt.repository;

import com.tt.wkkt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public interface StudentRepository extends JpaRepository<Student,Integer>, JpaSpecificationExecutor<Student> {

    @Query("select s.id from Student s where s.userName=?1")
    Integer findIdByUserName(String userName);

    @Query("select max(s.id) from Student s")
    Integer findMaxId();

    @Query("update Student s set s.password=?1 where s.id=?2")
    @Modifying
    @Transactional
    int updateById(String password,Integer id );

    @Query("select s from Student s order by s.id desc ")
    List<Student> findAllOrOrderById();
}
