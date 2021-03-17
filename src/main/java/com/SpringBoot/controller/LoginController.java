package com.SpringBoot.controller;

import com.SpringBoot.bean.LogInfo;
import com.SpringBoot.bean.User;
import com.SpringBoot.service.LogInfoService;
import com.SpringBoot.service.impl.LogInfoServiceImpl;
import com.SpringBoot.utils.Base64;
import com.SpringBoot.utils.RSAEncrypt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.common.ResultObj;
import com.SpringBoot.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LogInfoService logInfoService;

    @PostMapping("login")
    public ResultObj login(String data, String code, HttpServletRequest request) throws Exception {

        //解密密码
        data = RSAEncrypt.decrypt(data);
        String[] split = data.split("\\|\\|\\|");

        User user = new User();
        user.setLoginName(split[0]);
        user.setPwd(split[1]);

        String rightCode = (String) request.getSession().getAttribute("rightCode");
        //前端传入的tryCode参数
        String tryCode = request.getParameter("tryCode");

        tryCode = rightCode;

        String remoteAddr = request.getRemoteAddr();
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getLoginName(), user.getPwd());

        if (!rightCode.equals(tryCode)) {
            return ResultObj.LOGIN_ERROR_CODE;
        } else {
            try {
                //对用户进行认证登陆
                subject.login(token);
                LogInfo logInfo = new LogInfo();
                logInfo.setLoginName(user.getLoginName());
                logInfo.setLoginIp(remoteAddr);
                int row = logInfoService.insertLogIfo(logInfo);
                return ResultObj.LOGIN_SUCCESS;
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return ResultObj.LOGIN_ERROR_PASS;
            }
        }

    }


}
