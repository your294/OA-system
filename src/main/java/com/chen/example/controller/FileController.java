package com.chen.example.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.chen.example.base.BaseResponse;
import com.chen.example.base.ResultUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author 86199
 */
@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${server.port}")
    private String port;

    private static final String IP = "http://localhost";
    /**
     * 文件上传
     * @param file
     * @return  ResultUtils.success("ok")
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public BaseResponse<String> upload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        //定义文件唯一标识符(前缀）
        String uuid = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/" + uuid + "-" + originalFilename;

        //String rootFilePath = "C:\\Users\\86199\\IdeaProjects\\Sec-backen\\src\\main\\resources\\files\\" + originalFilename;
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        return ResultUtils.success(IP + ":" + port + "/files/" + uuid);
    }

    /**
     * 用作富文本文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/editor/upload")
    public JSONObject editorUpload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        //定义文件唯一标识符(前缀）
        String uuid = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/" + uuid + "-" + originalFilename;

        //String rootFilePath = "C:\\Users\\86199\\IdeaProjects\\Sec-backen\\src\\main\\resources\\files\\" + originalFilename;
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        String url = IP + ":" + port + "/files/" + uuid;
        JSONObject json = new JSONObject();
        json.set("errno", 0);
        JSONArray arr = new JSONArray();
        JSONObject object = new JSONObject();
        object.set("url", url);
        arr.add(object);
        json.set("data", arr);
        return json;
    }

    /**
     * 文件下载
     * @param uuid,response
     * @param response
     */
    @GetMapping("{uuid}")
    public void getFiles(@PathVariable String uuid, HttpServletResponse response){
        OutputStream os;
        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String avatar = fileNames.stream().filter(name -> name.contains(uuid)).findAny().orElse("");

        try {
            if (StrUtil.isNotEmpty(avatar)){
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }  catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件下载失败");
        }
    }
}
