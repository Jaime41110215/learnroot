package com.mini.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wanghongchao
 * @time 2020/6/11
 */
@Controller
@RequestMapping("/multipart")
public class MultipartController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get("F:\\workspace\\learnroot\\app\\"+ file.getOriginalFilename());
        Path write = Files.write(path, bytes);
        System.out.println(write);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }

}
