package com.example.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    //新增
    @PostMapping("/students")
    public  String insert(@RequestBody Student student){
        studentRepository.save(student);
        return "執行資料庫的Create 操作";
    }

    //修改
    @PutMapping("/students/{studentId}")
    public String update(@PathVariable Integer studentId ,
                         @RequestBody Student student) {
        //student.setId(studentId);
        //studentRepository.save(student);

        Student s = studentRepository.findById(studentId).orElse(null);
        if(s != null ){
            s.setName(student.getName());
            studentRepository.save(s);
            return "執行資料庫的update 操作";
        }else{
            return "Update 失敗，數據不存在";
        }


    }

    //刪除
    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId){
        studentRepository.deleteById(studentId);
        return "執行資料庫的delete 操作";
    }

    //查詢
    @GetMapping("/students/{studentId}")
    public Student read(@PathVariable Integer studentId){
        Student student = studentRepository.findById(studentId).orElse(null);
        return student;
    }
}
