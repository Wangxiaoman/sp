package cn.smiles.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;

import cn.smiles.constants.CommonStatus;
import cn.smiles.constants.ResultJson;
import cn.smiles.domain.User;
import cn.smiles.mapper.UserMapper;
import cn.smiles.tools.MD5;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    
    public CommonStatus register(String account, String phone, String password){
        User existUser = userMapper.getByAccountOrPhone(account, phone);
        if(existUser != null){
            return CommonStatus.USER_PHONE_ACCOUNT_EXIST;
        }
        
        String passwordMd5 = MD5.getMD5Code(password);
        User user = new User();
        user.setAccount(account);
        user.setPassword(passwordMd5);
        user.setPhone(phone);
        userMapper.insert(user);
        
        return CommonStatus.SUCCESS;
    }
    
    public ResultJson login(String account, String password){
        User user = userMapper.getByAccountOrPhone(account, password);
        if(user == null){
            return new ResultJson(CommonStatus.USER_NOT_EXIST);
        }
        if(!Objects.equal(user.getPassword(),MD5.getMD5Code(password))){
            return new ResultJson(CommonStatus.USER_PASSWORD_ERROR);
        }
        JSONObject jo = new JSONObject();
        jo.put("userId", user.getId());
        return new ResultJson(CommonStatus.SUCCESS, jo);
    }
    
}
