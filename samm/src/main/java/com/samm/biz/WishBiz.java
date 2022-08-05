package com.samm.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.WishMapper;
import com.samm.vo.WishVo;

@Service
public class WishBiz implements Biz<Integer,WishVo> {
    @Autowired
    WishMapper wishDao;
    
    @Override
    public void register(WishVo v) throws Exception {
        wishDao.insert(v);
    }

    @Override
    public void modify(WishVo v) throws Exception {
       throw new Exception("수정할 수 없습니다. 삭제 후 추가해 주십시오.");
    }

    @Override
    public void remove(Integer k) throws Exception {
        
    }

    @Override
    public WishVo get(Integer k) throws Exception {
        WishVo result = wishDao.select(k);
        return result;
    }

    @Override
    public List<WishVo> get() throws Exception {
        List<WishVo> result = wishDao.selectAll();
        return result;
    }
    
    public List<WishVo> getByUid(String uid) throws Exception {
        List<WishVo> result = wishDao.selectByUid(uid);
        return result;
    }

    public List<WishVo> getById2(int id2) throws Exception {
        List<WishVo> result = wishDao.selectById2(id2);
        return result;
    }
    public void deleteWish(WishVo v) throws Exception {
    	wishDao.delete(v);
    }
}
