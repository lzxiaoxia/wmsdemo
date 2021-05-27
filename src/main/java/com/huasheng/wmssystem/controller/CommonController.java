package com.huasheng.wmssystem.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.io.File;
import java.io.IOException;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/18 11:59
 * @Description ：公共接口
 */
@Slf4j
@Controller
public class CommonController {

    @RequestMapping(value = "/docs")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("redirect:" +"/swagger-ui/index.html#/");
        return modelAndView;
    }


    @PostMapping("/upload")
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(paramType = "form", dataType="__file", name = "file", required = false)})
    public String upload(@RequestParam("file") MultipartFile file) {
     /*   if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }*/

        String fileName = file.getOriginalFilename();
        String filePath = "/UpLoadFiles/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return "上传失败！";
    }


}