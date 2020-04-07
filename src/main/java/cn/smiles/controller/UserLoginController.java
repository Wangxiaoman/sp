package cn.smiles.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.smiles.constants.CommonStatus;
import cn.smiles.constants.ResultJson;
import cn.smiles.log.CommonLogger;
import cn.smiles.service.UserService;
import cn.smiles.tools.CommonTools;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserLoginController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResultJson register(HttpServletRequest request, @RequestBody JSONObject param) {
        if (param == null) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        CommonLogger.info("register login, param:{}", param);
        // param check
        String phone = param.getString("phone");
        if (!CommonTools.isPhone(phone)) {
            return new ResultJson(CommonStatus.USER_PHONE_ERROR);
        }
        String account = param.getString("account");
        if (StringUtils.isBlank(account)) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        String password = param.getString("password");
        if (!CommonTools.isPhone(phone)) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }

        CommonStatus commonStatus = userService.register(account, phone, password);
        return new ResultJson(commonStatus);
    }

    @PostMapping("/login")
    public ResultJson login(HttpServletRequest request, @RequestBody JSONObject param) {
        if (param == null) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        CommonLogger.info("login, param:{}", param);
        // param check
        String account = param.getString("account");
        if (StringUtils.isBlank(account)) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        String password = param.getString("password");
        if (StringUtils.isBlank(password)) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }

        return userService.login(account, password);
    }

}
