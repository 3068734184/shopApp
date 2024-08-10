package com.example.shopweb.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.shopmodel.info.ImageInfo;
import com.example.shopmodel.info.UploadInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @PostMapping
    public String upload(@RequestBody UploadInfo uploadInfo) {
//        log.info("uploadInfo:{}" , uploadInfo);
        String name = uploadInfo.getName();
        String bases64 = uploadInfo.getBase64();
        //切割base64，返回字符串数组
        try {
            String[] base64s = StrUtil.splitToArray(bases64, "base64,");
            byte[] bytes = Base64.decode(base64s[1]);
            name = IdUtil.getSnowflakeNextIdStr() + "_" + name;
            log.info("name:{}" ,name);
            FileUtil.writeBytes(bytes, "/usr/local/nginx/html/images" + name);
        } catch (IORuntimeException e) {
            throw new RuntimeException(e);
        }

        return "http://127.0.0.1:81/images/" + name;
    }


    @GetMapping
    public List<ImageInfo> get() {
        List<String> fileName = FileUtil.listFileNames("/usr/local/nginx/html/images");
        log.info("fileName:{}" , fileName);
        List<ImageInfo> collect = fileName.stream().map(
                item -> {
                    return new ImageInfo(item, "http://127.0.0.1:81/images/" + item);
                }
        ).collect(Collectors.toList());
        return collect;
    }
}
