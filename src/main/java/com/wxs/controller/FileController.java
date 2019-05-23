package com.wxs.controller;

import com.wxs.service.VedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin2.message.GetAppletMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping("/upload")
public class FileController {

    // 输出文件夹 --> 应该被 nginx 代理
    private String path = "D:\\vedio\\";
    // 数据库存储的path
    private String dbPath = "vedio/";


    @Resource(name = "vedioServiceImpl")
    private VedioService vedioService;

    @RequestMapping(value = "vedio", produces = {"application/json;charset=UTF-8"})
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
                         @RequestParam("cid") Integer cid, HttpServletRequest request) {
        FileOutputStream out = null;
        InputStream is = null;
        String ct = System.currentTimeMillis()+"";
        String datebasePath = dbPath + ct + ".mp4"; // 数据库访问路径 , 到时vedio 通过此路径访问 代理目录下的视频
        String realPath = request.getServletContext().getRealPath("");
        // 获取项目根目录
        File file1 = new File(realPath+"/vedio/" + System.currentTimeMillis() + ".mp4");

        try {
            byte[] bs = file.getBytes();
            out = new FileOutputStream(file1);
            is = new ByteArrayInputStream(bs);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 保存储存路径，上传文件，对应的课程id，教师id，创建时间，课程名字
        // 获取用户名

        // 获取文件名
        vedioService.addVedio(name,datebasePath, cid);
        return "success";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        return servletPath;
    }
}
