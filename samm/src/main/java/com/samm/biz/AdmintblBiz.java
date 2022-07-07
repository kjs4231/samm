package com.samm.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.AdmintblMapper;
import com.samm.vo.AdmintblVo;

@Service
public class AdmintblBiz implements Biz<String,AdmintblVo> {
    @Autowired
    AdmintblMapper admintblDao;

    @Override
    public void register(AdmintblVo v) throws Exception {
        admintblDao.insert(v);
    }

    @Override
    public void modify(AdmintblVo v) throws Exception {
        admintblDao.update(v);
    }

    @Override
    public void remove(String k) throws Exception {
        admintblDao.delete(k);
    }

    @Override
    public AdmintblVo get(String k) throws Exception {
        AdmintblVo result = admintblDao.select(k);
        return result;
    }

    @Override
    public List<AdmintblVo> get() throws Exception {
        List<AdmintblVo> result = admintblDao.selectAll();
        return result;
    }
    
}
