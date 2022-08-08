package com.samm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.WishVo;

@Repository
@Mapper
public interface WishMapper {
    public void insert(WishVo v) throws Exception;
    public void delete(WishVo v) throws Exception;
    // public void update(WishVo v) throws Exception; 
    public WishVo select(int k) throws Exception;
    public List<WishVo> selectAll() throws Exception;
    public List<WishVo> selectByUid(String uid) throws Exception;
    public List<WishVo> selectById2(int id2) throws Exception;
}
