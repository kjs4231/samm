package com.samm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.UsersVo;

@Repository
@Mapper
public interface UsersMapper {
    public void insert(UsersVo v) throws Exception;
    public void delete(String k) throws Exception;
    public void update(UsersVo v) throws Exception;
    public UsersVo select(String k) throws Exception;
    public List<UsersVo> selectAll() throws Exception;
}
