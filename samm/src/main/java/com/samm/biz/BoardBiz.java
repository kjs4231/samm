   package com.samm.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.BoardMapper;
import com.samm.vo.BoardVo;
import com.samm.vo.SearchCondition;

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
		boardDao.insert(v);		
	}

	@Override
	public void remove(Integer k) throws Exception {
		boardDao.delete(k);		
	}

	@Override
	public BoardVo get(Integer k) throws Exception {
		return boardDao.select(k);
	}

	@Override
	public List<BoardVo> get() throws Exception {
		return boardDao.selectAll();
	}

   
	public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return boardDao.searchResultCnt(sc);
    }

    public List<BoardVo> getSearchResultPage(SearchCondition sc) throws Exception {
        return boardDao.searchSelectPage(sc);
    }
    
    public List<BoardVo> selectByUid(String uid) throws Exception{
    	return boardDao.selectByUid(uid);
    }

	public List<BoardVo> get(Map<String, Integer> m) throws Exception {
		return boardDao.get(m);
	}
}
