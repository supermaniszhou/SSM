package com.controller;

import com.pojo.Student;
import com.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testng.annotations.NoInjection;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/23.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired(required = true)
    private StudentServiceImpl<Student> service;

    @RequestMapping("/list")
    public String list(Model model){
        try {
            List<Student> list=service.getall();
            model.addAttribute("list",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "list";
    }
}
