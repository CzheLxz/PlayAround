package com.hairuo.tbk.controller;

import com.hairuo.tbk.entity.BphotoConfig;
import com.hairuo.tbk.repository.BphotoConfigRepository;
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
import java.util.List;

/**
 * @Description 滚动信息控制类
 * @Author czhe
 */
@RequestMapping("/photo")
@RestController
@Transactional
public class BphotoConfigController {

    @Autowired
    private HttpSession session;
    @Autowired
    private BphotoConfigRepository bphotoConfigRepository;

    /**
     * @Description 根据用户id查询
     */
    @GetMapping("/showList")
    public Result showList() {
        List<BphotoConfig> bphotoConfigList = bphotoConfigRepository
                .findAllByUserUuid(session.getAttribute("currentUser").toString());
        return new Result(ResultCode.SUCCESS, bphotoConfigList);
    }

    /**
     * @Description 新增
     */
    @PostMapping("/save")
    public Result save(@RequestParam("file") MultipartFile file,
                       @RequestParam("url") String url,
                       @RequestParam("remark") String remark,
                       @RequestParam("user_id") String userUuid) {
        if (file.isEmpty() || StringUtils.isBlank(url) || StringUtils.isBlank(remark) || StringUtils.isBlank(userUuid)) {
            return new Result(ResultCode.PARAMERROR);
        }
        String fileName = file.getOriginalFilename();
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/uploadFiles";
        if (FileUtil.fileupload(file, path, fileName)) {
            String bShow = path + "/" + fileName;
            int result = bphotoConfigRepository.saveBphotoConfig(bShow, remark, url, userUuid);
            if (result > 0) {
                return new Result(ResultCode.SUCCESS);
            }
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

    /**
     * @Description 修改
     */
    @PostMapping("/update")
    public Result update(@RequestParam("files") MultipartFile file,
                         @RequestParam("address") String url,
                         @RequestParam("remarks") String remark,
                         @RequestParam("user_ids") Integer id) {
        if (file.isEmpty() || StringUtils.isBlank(url) || StringUtils.isBlank(remark) || id == null) {
            return new Result(ResultCode.PARAMERROR);
        }
        String fileName = file.getOriginalFilename();
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/uploadFiles";
        if (FileUtil.fileupload(file, path, fileName)) {
            String bShow = path + "/" + fileName;
            int result = bphotoConfigRepository.updateBphotoConfigById(bShow, url, remark, id);
            if (result > 0) {
                return new Result(ResultCode.SUCCESS);
            }
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

    /**
     * @Description 删除
     */
    @PostMapping("/remove")
    public Result remove(@RequestParam("id") Integer id) {
        if (id == null) {
            return new Result(ResultCode.PARAMERROR);
        }
        int result = bphotoConfigRepository.removeBphotoConfigById(id);
        if (result > 0) {
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.SYSTEMERROR);
    }
}
