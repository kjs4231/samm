   package com.samm.biz;

import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.BoardMapper;
import com.samm.vo.BoardVo;

@Service
public class BoardBiz implements Biz<Integer, BoardVo> {
    @Autowired
    BoardMapper boardDao;

    public int getCount() throws Exception {
        return boardDao.count();
    }

    public void remove(int k) throws Exception {
    	boardDao.delete(k);
    }

    public void write(BoardVo v) throws Exception {
    	
       boardDao.insert(v);
    }

    public List<BoardVo> getList() throws Exception {
        return boardDao.selectAll();
    }

    public BoardVo read(int k) throws Exception {
        BoardVo BoardVo = boardDao.select(k);
        boardDao.increaseViewCnt(k);

        return BoardVo;
    }

    public List<BoardVo> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }

    @Override
    public void modify(BoardVo v) throws Exception {
        boardDao.update(v);
    }

	@Override
	public void register(BoardVo v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVo get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.select(k);
	}

	@Override
	public List<BoardVo> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

    /*@Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return boardDao.searchResultCnt(sc);
    }

    @Override
    public List<BoardVo> getSearchResultPage(SearchCondition sc) throws Exception {
        return boardDao.searchSelectPage(sc);
    }*/
}
