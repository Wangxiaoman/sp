package cn.smiles.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import cn.smiles.domain.Page;
import cn.smiles.domain.Smiles;
import cn.smiles.domain.SmilesCollect;
import cn.smiles.mapper.SmilesCollectMapper;
import cn.smiles.mapper.SmilesMapper;

@Service
public class SmilesService {
    
    @Resource
    private SmilesMapper smilesMapper;
    @Resource
    private SmilesCollectMapper smilesCollectMapper;

    public int addSmiles(Smiles smiles){
        return smilesMapper.insert(smiles);
    }
    
    public Smiles getDetail(int id){
        return smilesMapper.getById(id);
    }
    
    public int removeSmiles(int id){
        return smilesMapper.remove(id);
    }
    
    public int updateSmiles(Smiles smiles){
        return smilesMapper.updateSmiles(smiles);
    }
    
    public int updateSmilesDescription(int id, String description){
        return smilesMapper.updateDescription(id, description);
    }
    
    public Page<Smiles> getSmilesList(int page, int pageSize, int userId){
        Page<Smiles> result = new Page<>(page, pageSize);
        List<Smiles> list = smilesMapper.queryList((page-1)*pageSize, pageSize);
        int count = smilesMapper.getCount();
        List<SmilesCollect> smilesCollectList = smilesCollectMapper.queryList(userId);
        if(CollectionUtils.isNotEmpty(smilesCollectList)){
            Set<Integer> smilesCollectSet = smilesCollectList.stream().map(sc -> sc.getSmilesId()).collect(Collectors.toSet());
            for(Smiles smiles : list){
                if(smilesCollectSet.contains(smiles.getId())){
                    smiles.setUserCollect(true);
                }
            }
        }
        
        result.setAllCount(count);
        result.setList(list);
        return result;
    }
    
    public int collect(int userId, int smilesId, int collect){
        if(Objects.equal(collect, 1)){
            return smilesCollectMapper.insert(userId, smilesId);
        } else if(Objects.equal(collect, 0)){
            return smilesCollectMapper.delete(userId, smilesId);
        }
        return 1;
    }
    
}
