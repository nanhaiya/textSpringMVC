package com.little_boy.controller;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;


import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class UserController {
    @RequestMapping("/fileUoLoad")
    public String fileUoLoad(HttpServletRequest request) throws Exception {
        System.out.println("文件上传...");

        //使用fileupload组件完成文件上传
        //上传位置
        String Path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径文件夹是否存在
        File file=new File(Path);
        if(!file.exists()){
            //创建文件夹
            file.mkdirs();
        }

        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> items = upload.parseRequest(request);
        //遍历
        for (FileItem item:items){
            //进行判断，当前item对象是否是上传文件项
            if (item.isFormField()) {
                //普通表单项
            }else{
                //上传文件项
                //获取上传文件名称
                String name = item.getName();
                //把文件的名称设置唯一的值，uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                name=uuid+"_"+name;
                //完成文件上传
                item.write(new File(Path,name));
                //删除缓存
                item.delete();
            }
        }

        return "fileSuccess";
    }

    /*springMVC文件上传*/
    @RequestMapping("/fileUoLoadMVC")
    public String fileUoLoadMVC(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springMVC文件上传...");

        //使用fileupload组件完成文件上传
        //上传位置
        String Path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径文件夹是否存在
        File file=new File(Path);
        if(!file.exists()){
            //创建文件夹
            file.mkdirs();
        }

                //上传文件项
                //获取上传文件名称
                String name =upload.getOriginalFilename();

                //把文件的名称设置唯一的值，uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                name=uuid+"_"+name;
                //完成文件上传
                upload.transferTo(new File(Path,name));

        return "fileSuccess";
    }

    /*跨服务器文件上传*/
    @RequestMapping("/fileUoLoadMVC")
    public String fileUoLoadOtherServlet( MultipartFile upload) throws Exception {
        System.out.println("springMVC文件上传...");

        //定义上传服务器路径 http://localhost:9999/uploads/
        String path="";
        //上传文件项
        //获取上传文件名称
        String name =upload.getOriginalFilename();

        //把文件的名称设置唯一的值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        name=uuid+"_"+name;

        //创建客户端的对象
        Client client = Client.create();

        //和图片服务器进行连接
        WebResource webResource = client.resource(path + name);

        //上传文件
        webResource.put(upload.getBytes());

        return "fileSuccess";
    }
}
