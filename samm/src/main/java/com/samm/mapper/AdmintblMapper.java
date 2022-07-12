package com.samm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.AdmintblVo;


@Mapper
@Repository
public interface AdmintblMapper {
    public void insert(AdmintblVo v) throws Exception;
    public void delete(String k) throws Exception;
    public void update(AdmintblVo v) throws Exception;
    public AdmintblVo select(String k) throws Exception;
    public List<AdmintblVo> selectAll() throws Exception;
}
