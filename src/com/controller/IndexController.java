package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/toupload")
    public String toupload() {
        return "uploadForm";
    }
}
