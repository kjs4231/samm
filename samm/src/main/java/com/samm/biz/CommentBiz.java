   package com.samm.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samm.mapper.BoardMapper;
import com.samm.mapper.CommentMapper;
import com.samm.vo.CommentVo;

@Service
public class CommentBiz {
	@Autowired
    BoardMapper boardDao;
    @Autowired
    CommentMapper commentDao;

//    @Autowired
//    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
//        this.commentDao = commentDao;
//        this.boardDao = boardDao;
//    }

    public int getCount(Integer bno) throws Exception {
        return commentDao.count(bno);
    }


    public int remove(Integer cno) throws Exception {
        return commentDao.delete(cno);
    }

    
    public int write(CommentVo CommentVo) throws Exception {
        return commentDao.insert(CommentVo);
    }

    public List<CommentVo> getList(Integer bno) throws Exception {
//        throw new Exception("test");
        return commentDao.selectAll(bno);
    }


    public CommentVo read(Integer cno) throws Exception {
        return commentDao.select(cno);
    }


    public int modify(CommentVo CommentVo) throws Exception {
        return commentDao.update(CommentVo);
    }
}