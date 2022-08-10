package com.samm.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.ReviewVo;

@Repository
@Mapper
public interface ReviewMapper {
    public void insert(ReviewVo v) throws Exception;
    public void delete(int k) throws Exception;
    public void update(ReviewVo v) throws Exception;
    public ReviewVo select(int k) throws Exception;
    public List<ReviewVo> selectAll() throws Exception;
    public List<ReviewVo> list(int page) throws Exception;
    public List<ReviewVo> selectByUid(String id) throws Exception;
    public List<ReviewVo> selectByContents(HashMap<String, Object> map) throws Exception;
    
    public List<ReviewVo> getfestivalreview(int fid) throws Exception;
    
    public int getCount(int fid) throws Exception;
}
