package com.samm.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.BordVo;

@Repository
@Mapper
public interface BordMapper {
    public void insert(BordVo v) throws Exception;
    public void delete(int k) throws Exception;
    public void update(BordVo v) throws Exception;
    public BordVo select(int k) throws Exception;
    public List<BordVo> selectAll() throws Exception;
    public List<BordVo> list(int page) throws Exception;
    public void views(int k) throws Exception;
    public List<BordVo> selectByUid(HashMap<String, Object> map) throws Exception;
    public List<BordVo> selectByTitle(HashMap<String, Object> map) throws Exception;
    public List<BordVo> selectByContents(HashMap<String, Object> map) throws Exception;
}
