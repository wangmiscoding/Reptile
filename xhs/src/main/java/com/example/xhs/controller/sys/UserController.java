package com.example.xhs.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.xhs.pojo.entity.User;
import com.example.xhs.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public String toAccount(Model model, HttpServletResponse response, HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        if(u==null){
            return "login";
        }else {
            User byId = service.getById(Integer.valueOf(u.getId()));
            model.addAttribute("user",byId);
            return "set";
        }
    }

    @GetMapping("esc")
    public String esc(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/login";
    }

    @PostMapping
    public String update(User user){
        service.update(user);
        return "redirect:/user";
    }

}
