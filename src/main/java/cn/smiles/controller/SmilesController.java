package cn.smiles.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.smiles.constants.CommonStatus;
import cn.smiles.constants.ResultJson;
import cn.smiles.domain.Page;
import cn.smiles.domain.Smiles;
import cn.smiles.log.CommonLogger;
import cn.smiles.service.SmilesService;

@RestController
@CrossOrigin
@RequestMapping("/smiles")
public class SmilesController {
    
    @Resource
    private SmilesService smilesService;
    
    //1、新增分子
    @PostMapping("/add")
    public ResultJson addSmiles(HttpServletRequest request, @RequestBody JSONObject param) {
        if (param == null) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        CommonLogger.info("add smiles, param:{}", param);
        // param check
        String smiles = param.getString("smiles");
        if (StringUtils.isBlank(smiles)) {
            return new ResultJson(CommonStatus.USER_PHONE_ERROR);
        }
        String jme = (String) param.getOrDefault("jme", StringUtils.EMPTY);
        String description = (String) param.getOrDefault("description", StringUtils.EMPTY);
        String pngStr = (String) param.getOrDefault("pngStr", StringUtils.EMPTY);
        
        Smiles smilesBean = new Smiles();
        smilesBean.setJme(jme);
        smilesBean.setSmiles(smiles);
        smilesBean.setDescription(description);
        smilesBean.setPngStr(pngStr);
        
        smilesService.addSmiles(smilesBean);
        return new ResultJson();
    }
    
    //删除分子
    @PostMapping("/remove")
    public ResultJson removeSmiles(HttpServletRequest request, @RequestBody JSONObject param){
        if (param == null) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        int id = param.getIntValue("id");
        if (id <= 0) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        smilesService.removeSmiles(id);
        return new ResultJson();
    }
    
    //修改分子
    @PostMapping("/modify")
    public ResultJson modifySmiles(HttpServletRequest request, @RequestBody JSONObject param){
        if (param == null) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        CommonLogger.info("modify smiles, param:{}", param);
        // param check
        int id = param.getIntValue("id");
        if(id <= 0){
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        String smiles = param.getString("smiles");
        if (StringUtils.isBlank(smiles)) {
            return new ResultJson(CommonStatus.USER_PHONE_ERROR);
        }
        String jme = (String) param.getOrDefault("jme", StringUtils.EMPTY);
        String pngStr = (String) param.getOrDefault("pngStr", StringUtils.EMPTY);
        
        Smiles smilesBean = new Smiles();
        smilesBean.setJme(jme);
        smilesBean.setSmiles(smiles);
        smilesBean.setPngStr(pngStr);
        
        smilesService.updateSmiles(smilesBean);
        return new ResultJson();
    }
    
    //添加分子描述
    @PostMapping("/description")
    public ResultJson smilesDescription(HttpServletRequest request, @RequestBody JSONObject param){
        if (param == null) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        CommonLogger.info("modify smiles description, param:{}", param);
        // param check
        int id = param.getIntValue("id");
        if(id <= 0){
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        String description = (String) param.getOrDefault("description", StringUtils.EMPTY);
        smilesService.updateSmilesDescription(id, description);
        return new ResultJson();
    }
    
    //分子列表 带翻页
    @GetMapping("/list")
    public ResultJson getSmilesList(HttpServletRequest request, 
            @RequestParam(value="page",defaultValue="1")int page, 
            @RequestParam(value="pageSize",defaultValue="10")int pageSize,
            @RequestParam(value="userId")int userId){
        if (userId <= 0) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        Page<Smiles> result = smilesService.getSmilesList(page, pageSize, userId);
        return new ResultJson(CommonStatus.SUCCESS, result);
    }
    
    //获取分子详情
    @GetMapping("/detail")
    public ResultJson getSmilesDetail(HttpServletRequest request, @RequestParam("id") int id){
        Smiles smiles = smilesService.getDetail(id);
        return new ResultJson(CommonStatus.SUCCESS, smiles);
    }
    
    //收藏/取消收藏分子
    @PostMapping("/collect")
    public ResultJson collectSmiles(HttpServletRequest request, @RequestBody JSONObject param){
        if (param == null) {
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        CommonLogger.info("collect smiles, param:{}", param);
        // param check
        int userId = param.getIntValue("userId");
        if(userId <= 0){
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        int smilesId = param.getIntValue("smilesId");
        if(userId <= 0){
            return new ResultJson(CommonStatus.PARAM_ERROR);
        }
        int collect = param.getIntValue("collect");

        smilesService.collect(userId, smilesId, collect);
        return new ResultJson();
    }
    
}
