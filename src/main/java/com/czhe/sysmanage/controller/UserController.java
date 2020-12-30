package com.czhe.sysmanage.controller;

import com.czhe.sysmanage.paramCheck.ParamCheck;
import com.czhe.sysmanage.retrunHandle.BizException;
import com.czhe.sysmanage.retrunHandle.ResultBody;
import com.czhe.sysmanage.websocket.WebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private DocumentConverter converter;


    @GetMapping("/test")
    public boolean test() {
        System.out.println("开始...");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }

    @GetMapping("/testNull")
    public boolean testNull() {
        System.out.println("开始...");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");
        return true;
    }

    @PostMapping("/testBizException")
    public boolean testBizException() {

        System.out.println("开始...");
        //如果姓名为空就手动抛出一个自定义的异常！
        String userName = null;
        if (userName == null) {
            throw new BizException("-1", "用户姓名不能为空！");
        }
        return true;
    }

    @GetMapping("/testSuccess")
    public ResultBody testSuccess() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        return ResultBody.success(map);
    }

    @GetMapping("/testError")
    public ResultBody testError() {
        return ResultBody.error("502", "错误网关");
    }

    @GetMapping("/hello")
    public ResultBody hello(@ParamCheck(defaultValue = "阿三") @RequestParam("username") String name) {
        return ResultBody.success(name);
    }

    @GetMapping("/sendTo")
    public String sendTo(@RequestParam("userId") String userId, @RequestParam("msg") String msg) throws IOException {
        webSocket.sendMessageTo(msg, userId);
        return "推送成功!";
    }

    @GetMapping("/sendToAll")
    public String sendToAll(@RequestParam("msg") String msg) throws IOException {
        webSocket.sendMessageAll(msg, "system");
        return "推送成功!";
    }

    @ApiOperation(value = "在线预览文档", notes = "转换文档为PDF格式预览")
    @ApiImplicitParam(name = "filePath", value = "文件路径", required = true, dataType = "String", paramType = "query")
    @GetMapping("/displayDocumentOnline")
    public void displayDocumentOnline(@RequestParam("filePath") String filePath, HttpServletResponse response) {
        try {
            File file = new File(filePath);//需要转换的文件
            File newFile = new File("F:/");//转换后文件生成的地址
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            String savePath = "F:/";//pdf文件生成保存的路径
            String fileName = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
            String fileType = ".pdf";
            String newFileMix = savePath + fileName + fileType;

            //文件转化
            converter.convert(file).to(new File(newFileMix)).execute();
            //使用response 将pdf文件以流的方式发送到前端浏览器
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream in = new FileInputStream(new File(newFileMix));
            int i = IOUtils.copy(in, outputStream);
            in.close();
            outputStream.close();
            System.out.println("流已经关闭 该文件字节大小: " + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
 
 
 
 