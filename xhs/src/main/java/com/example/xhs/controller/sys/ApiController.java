package com.example.xhs.controller.sys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangm
 * @since 2021/3/27
 */
@RestController
@RequestMapping("api")
public class ApiController {


    @PostMapping("upload")
    public Map<String,Integer> uploadImg(@RequestParam("file") MultipartFile file){
        Map<String,Integer> map=new HashMap<>();
        map.put("status",0);
        return map;
    }
}
