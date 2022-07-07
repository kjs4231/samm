package com.samm.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.UsersMapper;
import com.samm.vo.UsersVo;

@Service
public class UsersBiz implements Biz<String,UsersVo> {
    @Autowired
    UsersMapper usersDao;

    @Override
    public void register(UsersVo v) throws Exception {
        usersDao.insert(v);
    }

    @Override
    public void modify(UsersVo v) throws Exception {
        usersDao.update(v);
    }

    @Override
    public void remove(String k) throws Exception {
        usersDao.delete(k);
    }

    @Override
    public UsersVo get(String k) throws Exception {
        UsersVo result = usersDao.select(k);
        return result;
    }

    @Override
    public List<UsersVo> get() throws Exception {
        List<UsersVo> result = usersDao.selectAll();
        return result;
    }
    
}
