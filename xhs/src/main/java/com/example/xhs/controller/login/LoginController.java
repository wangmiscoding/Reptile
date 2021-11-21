package com.example.xhs.controller.login;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.xhs.pojo.dto.LoginDto;
import com.example.xhs.pojo.dto.PersonalizeDto;
import com.example.xhs.pojo.dto.ShowDto;
import com.example.xhs.pojo.entity.User;
import com.example.xhs.service.ShowService;
import com.example.xhs.service.UserService;

import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangmeng
 * @since 2021/1/6
 */
@Controller
@RequestMapping(value = "login")
@ConfigurationProperties(prefix = "xhs")
public class LoginController {
    @Setter
    private String staticDir;
    @Autowired
    private ShowService showService;

    @Autowired
    UserService service;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping()
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping("index")
    public String toIndex(HttpServletRequest request, Model model) {
        //个性化
        //从session获取
        HttpSession session = request.getSession();
        PersonalizeDto personalizeDto= (PersonalizeDto)session.getAttribute("personalize");
        List<ShowDto> show = showService.show(personalizeDto);
        model.addAttribute("show", show);
        return "redirect:/index";
    }

    /**
     * 跳转至注册页面
     *
     * @return
     */
    @RequestMapping("register")
    public String toRegister() {
        return "register";
    }

    /**
     * 登录
     *
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("to")
    public String login(LoginDto dto, HttpServletRequest request) {
        User user = service.get(dto);
        if (user == null) {
            return "error";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "success";
        }
    }

    /**
     * 注册
     *
     * @param user 用户对象
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("toRegister")
    public String toRegister(User user, HttpServletRequest request) {
        //判断是否存在重复
        String s = service.registerCheck(user);
        if ("success".equals(s)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        return s;
    }

    /**
     * 上传头像
     * @param file 文件
     * @param request 请求
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("uploadImg")
    public  Map<String,Object> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String,Object> map=new HashMap<>();
        String fileName = file.getOriginalFilename();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        File dir = new File(staticDir+"img\\user");
        String newName = user.getId() + Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
        user.setImg("/img/user/"+newName);
        //修改头像
        service.updateImg(user);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }
        File dest = new File(dir +File.separator+newName );
        file.transferTo(dest);
        map.put("msg","success");
        return map;
    }


}
