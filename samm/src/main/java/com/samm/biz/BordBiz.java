package com.samm.biz;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.BordMapper;
import com.samm.vo.BordVo;

@Service
public class BordBiz implements Biz<Integer, BordVo> {
    @Autowired
    BordMapper bordDao;

    @Override
    public void register(BordVo v) throws Exception {
        bordDao.insert(v);
    }

    @Override
    public void modify(BordVo v) throws Exception {
        bordDao.update(v);
    }

    @Override
    public void remove(Integer k) throws Exception {
        bordDao.delete(k);
    }

    @Override
    public BordVo get(Integer k) throws Exception {
        BordVo result = bordDao.select(k);
        return result;
    }

    @Override
    public List<BordVo> get() throws Exception {
        List<BordVo> result = bordDao.selectAll();
        return result;
    }

    public List<BordVo> list(int page) throws Exception {
        List<BordVo> result = bordDao.list(page);
        return result;
    }

    public void views(int k) throws Exception {
        bordDao.views(k);
    }

    public List<BordVo> getByUid(String uid, int page) throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put("uid", uid);
        param.put("page", page);
        List<BordVo> result = bordDao.selectByUid(param);
        return result;
    }

    public List<BordVo> getByTitle(String title, int page) throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put("title", title);
        param.put("page", page);
        List<BordVo> result = bordDao.selectByTitle(param);
        return result;
    }

    public List<BordVo> getByContents(String contents, int page) throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put("contents", contents);
        param.put("page", page);
        List<BordVo> result = bordDao.selectByContents(param);
        return result;
    }
}
