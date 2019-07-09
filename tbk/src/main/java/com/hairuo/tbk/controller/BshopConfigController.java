package com.hairuo.tbk.controller;

import com.hairuo.tbk.entity.BshopConfig;
import com.hairuo.tbk.repository.BshopConfigRepository;
import com.hairuo.tbk.pojo.Result;
import com.hairuo.tbk.pojo.ResultCode;
import com.hairuo.tbk.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Description 商城配置控制类
 * @Author czhe
 */
@RequestMapping("/shop")
@RestController
@Transactional
public class BshopConfigController {

    @Autowired
    private HttpSession session;
    @Autowired
    private BshopConfigRepository bshopConfigRepository;

    /**
     * @Description 根据用户id查询商城信息
     */
    @GetMapping("/find")
    public Result find() {
        try {
            BshopConfig bshopConfig = bshopConfigRepository.getBshopConfigByUuid(session.getAttribute("currentUser").toString());
            if (bshopConfig != null) {
                return new Result(ResultCode.SUCCESS, bshopConfig);
            }
            return new Result(ResultCode.LOGICERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

    /**
     * @Description 如果已经配置则修改否则新增
     */
    @PostMapping("/save")
    public Result update(@RequestParam("file") MultipartFile file,
                         @RequestParam("welcome") String welcome,
                         @RequestParam("record") String record,
                         @RequestParam("remark") String remark,
                         @RequestParam("id") String userUuid) {
        if (file.isEmpty() || StringUtils.isBlank(welcome) || StringUtils.isBlank(record) || StringUtils.isBlank(remark) || StringUtils.isBlank(userUuid)) {
            return new Result(ResultCode.PARAMERROR);
        }
        int result = 0;
        try {
            String fileName = file.getOriginalFilename();
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/uploadFiles";
            if (FileUtil.fileupload(file, path, fileName)) {
                String logo = path + "/" + fileName;
                BshopConfig bshopConfig = bshopConfigRepository.getBshopConfigByUuid(userUuid);
                if (bshopConfig != null) {
                    result = bshopConfigRepository.updateBshopConfigByUserUuid(logo, welcome, record, remark, userUuid);
                } else {
                    result = bshopConfigRepository.saveBshopConfig(logo, welcome, record, remark, userUuid);
                }
                if (result > 0) {
                    return new Result(ResultCode.SUCCESS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

}
