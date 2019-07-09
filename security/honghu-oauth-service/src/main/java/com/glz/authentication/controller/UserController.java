package com.glz.authentication.controller;

import com.glz.authentication.oauth.aop.MyLog;
import com.glz.authentication.service.AuthShopuserService;
import com.glz.authentication.service.SysAppauthService;
import com.glz.authentication.utils.*;
import com.oauth.entity.AuthShopuser;
import com.oauth.entity.SysAppauth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author czhe
 * @Description AllApi
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private SysAppauthService sysAppauthService;

    @Autowired
    private AuthShopuserService authShopuserService;


    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * @param version      版本号
     * @param sndChnlNo    登录渠道
     * @param clientId
     * @param clientSecret
     * @param mobile       手机号
     * @param smsCode      验证码
     * @param password     密码
     * @param type         登录类型
     * @param openId       第三方Id
     * @return
     * @Description注册
     */
    @MyLog(value = "用户注册")
    @PostMapping("/register")
    public ResponseResult register(@RequestParam("version") String version, @RequestParam("sndChnlNo") String sndChnlNo, @RequestParam("clientId") String clientId,
                                   @RequestParam("clientSecret") String clientSecret, @RequestParam("mobile") String mobile, @RequestParam("smsCode") String smsCode,
                                   @RequestParam("password") String password, @RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "openId", required = false) String openId) {
        if (!VerifyUtils.verifySndChnlNo(sndChnlNo)) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0001);
        }
        if (!VerifyUtils.isMobile(mobile)) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0018);
        }
        if (!VerifyUtils.verifyPassword(password)) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0005);
        }
        if (!StringUtils.isEmpty(type)) {
            if (!VerifyUtils.verifyRegisterTpye(type)) {
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0014);
            }
            if (StringUtils.isEmpty(openId)) {
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0006);
            }
        }
        //查询clientId和clientSecret是否存在
        SysAppauth sysAppauth = new SysAppauth();
        sysAppauth.setClientId(clientId);
        sysAppauth.setClientSecret(clientSecret);
        //判断ClientId和ClientSecret是否存在
        try {
            if (!StringUtils.isEmpty(sysAppauthService.FindByClientIdAndClientSecret(sysAppauth))) {
                //验证手机号是否唯一
                if (StringUtils.isEmpty(authShopuserService.findByUserName(mobile))) {
                    //注册用户
                    AuthShopuser authShopuse = new AuthShopuser();
                    authShopuse.setName("galaz_" + mobile.substring(mobile.length() - 4));
                    authShopuse.setUsername(mobile);
                    authShopuse.setStatus(0);
                    authShopuse.setMobile(mobile);
                    authShopuse.setPassword(Md5Encrypt.md5(password));
                    authShopuse.setSndchnlno(sndChnlNo);
                    authShopuse.setAuthorities("admin");
                    authShopuse.setSex(-1);
                    if (type == "1") {
                        authShopuse.setQqOpenid(openId);
                    } else if (type == "2") {
                        authShopuse.setWxOpenid(openId);
                    } else if (type == "3") {
                        authShopuse.setSinaOpenid(openId);
                    }
                    authShopuserService.insertSelective(authShopuse);
                    return ResponseResult.success(ResponseCode.RESPONSE_CODEEO0000, null);
                } else {
                    return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0003);
                }
            } else {
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0002);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0017);
        }

    }

    /**
     * 更新绑定用户密码
     *
     * @param version      版本号
     * @param sndChnlNo    渠道
     * @param access_token 用户token
     * @param password     用户密码
     * @return 校验成功或失败的响应码和响应信息
     */
    @MyLog(value = "更新绑定用户密码")
    @PostMapping("/updateBoundPassword")
    public ResponseResult updateBoundPassword(@RequestParam("version") String version,
                                              @RequestParam("sndChnlNo") String sndChnlNo,
                                              @RequestParam("access_token") String access_token,
                                              @RequestParam("password") String password) {
        //判断传入的数据有没有空数据
        if (version == null || sndChnlNo == null || access_token == null || password == null) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0006);
        }
        //校验token并获取用户信息
        AuthShopuser authShopuser = tokenUtil.getAuth(access_token);
        if (authShopuser == null) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0016);
        }

        //渠道校验
        if (!VerifyUtils.verifySndChnlNo(sndChnlNo)) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0001);
        }
        //校验密码
        if (!VerifyUtils.verifyPassword(password)) {
            //校验失败返回错误编码和信息
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0005);
        } else {
            try {
                //加密后跟新到数据库
                authShopuserService.updatePassword(authShopuser.getId(), Md5Encrypt.md5(password));
                //更新成功返回成功编码和信息
            } catch (Exception e) {
                e.printStackTrace();
                //更新失败返回错误编码和信息
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0011);
            }
        }
        //返回成功编码和消息
        return ResponseResult.success(ResponseCode.RESPONSE_CODEEO0000, null);

    }


    /**
     * 更新绑定用户手机
     *
     * @param access_token 令牌
     * @param mobileNumber 手机号
     * @param code         用户输入的验证码
     * @param sndChnlNo    渠道
     * @return 操作码
     */
    @MyLog(value = "更新绑定用户手机")
    @PostMapping("/updateBindingByPhone")
    public ResponseResult updateUserMobile(@RequestParam("version") String version,
                                           @RequestParam("access_token") String access_token,
                                           @RequestParam("mobileNumber") String mobileNumber,
                                           @RequestParam("code") String code,
                                           @RequestParam("sndChnlNo") String sndChnlNo) {

        //验证渠道
        if (VerifyUtils.verifySndChnlNo(sndChnlNo) == false) {
            logger.info(sndChnlNo + "校验有误！");
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0001);
        }

        //验证密令
        AuthShopuser authShopuser = tokenUtil.getAuth(access_token);
        if (authShopuser == null) {
            logger.info(access_token + "令牌校验有误！");
            //令牌校验有误
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0009);
        }


        //验证码校验
        String sessionCode = "6666";
        AuthShopuser authShopuserByMobile = authShopuserService.selectByPrimaryKeyMobile(mobileNumber);
        if (authShopuserByMobile != null) {
            logger.info(mobileNumber + "用户存在，更新手机号失败！");
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0010);
        }
        if (VerifyUtils.isMobile(mobileNumber)) {
            if (!sessionCode.equals(code)) {
                logger.info("验证码校验失败：" + code);
                //验证码有误
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0004);
            }
        } else {
            logger.info("手机号不合法：" + mobileNumber);
            //手机号不合法，返回错误码
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0018);
        }

        //手机号修改
        AuthShopuser updateAuthShopuser = new AuthShopuser();
        updateAuthShopuser.setUsername(mobileNumber);
        updateAuthShopuser.setMobile(mobileNumber);
        updateAuthShopuser.setId(authShopuser.getId());
        try {
            if (authShopuserService.updateByPrimaryKeySelective(updateAuthShopuser) > 0) {
                logger.info("用户手机号码修改为：" + mobileNumber);
            } else {
                logger.info(authShopuser.getMobile() + "用户手机号码修改有误！");
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0011);
            }
        } catch (Exception e) {
            logger.info(authShopuser.getMobile() + "用户手机号码修改有误！");
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0011);
        }

        //操作成功
        return ResponseResult.success(ResponseCode.RESPONSE_CODEEO0000, null);
    }


    /**
     * 更新用户信息
     *
     * @param version      版本
     * @param sndChnlNo    渠道
     * @param access_token 令牌
     * @param name         姓名
     * @param sex          性别
     * @param birthday     生日
     * @return 操作码
     */
    @MyLog(value = "更新用户信息")
    @PostMapping(value = "/updateAuthShopuser")
    public ResponseResult updateAuthShopuser(@RequestParam("version") String version, @RequestParam("sndChnlNo") String sndChnlNo,
                                             @RequestParam("access_token") String access_token, @RequestParam("name") String name,
                                             @RequestParam("sex") String sex, @RequestParam("birthday") String birthday) {
        try {
            //效验渠道
            if (!VerifyUtils.verifySndChnlNo(sndChnlNo)) {
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0001);
            }
            //效验token并获取用户信息
            AuthShopuser authShopuser1 = tokenUtil.getAuth(access_token);
            if (authShopuser1 == null) {
                logger.error("更新用户-用户身份已经过期");
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0016);
            }
            //效验输入的姓名、性别、生日格式是否正确
            if (VerifyUtils.checkLawful(name, sex, birthday) == false) {
                logger.error("更新用户-请检查姓名,性别,生日格式是否正确");
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0019);
            }

            //进行日期转换
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateformat.parse(birthday);

            //给对象赋值
            AuthShopuser authShopuser = new AuthShopuser();
            authShopuser.setName(name);
            authShopuser.setSex(Integer.valueOf(sex));
            authShopuser.setBirthday(date);
            authShopuser.setId(authShopuser1.getId());

            //以上验证成功后像数据库进行更新操作
            int result = authShopuserService.updateByPrimaryKeySelective(authShopuser);
            if (result > 0) {
                logger.info("更新用户成功");
                return ResponseResult.success(ResponseCode.RESPONSE_CODEEO0000, null);
            } else {
                logger.error("更新用户失败");
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0011);
            }
        } catch (Exception e) {
            logger.error("更新用户失败");
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0011);
        }

    }


    /**
     * 绑定/解绑第三方账号信息
     *
     * @param version      版本号
     * @param sndChnlNo    渠道
     * @param access_token access_token
     * @param operatorType 操作类型:0-绑定；1-解绑
     * @param type         第三方类型，4-QQ,5-微信，6-新浪
     * @param openId       第三方openId,当操作类型为1时，可不用上送
     * @return
     */
    @MyLog(value = "绑定/解绑第三方账号信息")
    @PostMapping(value = "/bindAccount")
    public ResponseResult bindAccount(@RequestParam("version") String version,
                                      @RequestParam("sndChnlNo") String sndChnlNo,
                                      @RequestParam("access_token") String access_token,
                                      @RequestParam("operatorType") String operatorType,
                                      @RequestParam("type") String type,
                                      @RequestParam(value = "openId", required = false) String openId) {
        if (version == null || sndChnlNo == null || access_token == null ||
                operatorType == null || type == null) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0006);
        }
        //校验token并获取用户信息
        AuthShopuser authShopuser = tokenUtil.getAuth(access_token);

        //渠道校验
        if (!VerifyUtils.verifySndChnlNo(sndChnlNo)) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0001);
        }
        //判断第三方类型
        if (!VerifyUtils.verifyRegisterTpye(type)) {
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0014);
        }
        if (operatorType.equals("0")) {
            //当操作类型为0时绑定
            //判断是否是绑定状态
            if (authShopuserService.checkBindAccount(authShopuser.getId(), type) != null) {
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0010);
            }
            try {
                authShopuserService.updateData(authShopuser, type, openId);
                logger.info("用户绑定第三方账号成功");
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("用户绑定第三方账号失败");
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0011);
            }
        } else if (operatorType.equals("1")) {
            //当操作类型为1时解绑
            //判断是否是解绑状态
            if (authShopuserService.checkBindAccount(authShopuser.getId(), type) == null) {
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0020);
            }
            try {
                authShopuserService.updateData(authShopuser, type, null);
                logger.info("用户解绑第三方账号成功");
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("用户解绑第三方账号失败");
                return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0011);
            }
        } else {
            //传入的操作类型有误
            return ResponseResult.failure(ResponseCode.RESPONSE_CODEEO0015);
        }
        logger.info("用户绑定/解绑第三方账号成功");
        return ResponseResult.success(ResponseCode.RESPONSE_CODEEO0000, null);
    }


}
