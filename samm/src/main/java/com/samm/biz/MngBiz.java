package com.samm.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.mapper.MngMapper;
import com.samm.vo.AdmintblVo;
import com.samm.vo.BoardVo;
import com.samm.vo.CommentVo;
import com.samm.vo.FestivalVo;
import com.samm.vo.UsersVo;
import com.samm.vo.imgallowVo;

@Service
public class MngBiz {
    
	@Autowired
    MngMapper dao;
	
	
	/********************************* admin */
	
    
    public void aregister(AdmintblVo v) throws Exception {
        dao.ainsert(v);
    }

    public void amodify(AdmintblVo v) throws Exception {
        dao.aupdate(v);
    }
    
    public void aremove(String k) throws Exception {
        dao.adelete(k);
    }
    
    public AdmintblVo aget(String k) throws Exception {
        return dao.aselect(k);
    }

    public List<AdmintblVo> aget() throws Exception {
        return dao.aselectAll();
    }

	public String idCheck(String k) throws Exception {
		return dao.idCheck(k);
	}
    
	public List<AdmintblVo> get(Map<String, Integer> m) throws Exception {
		return dao.aselectlist(m);
	}
	
	public List<AdmintblVo> asearch(Map<String, String> m) throws Exception {
		return dao.asearch(m);
	}	
		
	public int agetTotalNum() throws Exception {
		return dao.agetTotalNum();
	}
	
	/********************************* members */
	    
    public void mregister(UsersVo v) throws Exception {
        dao.minsert(v);
    }
    
    public void mmodify(UsersVo v) throws Exception {
        dao.mupdate(v);
    }
    
    public void mremove(String k) throws Exception {
        dao.mdelete(k);
    }
    
    public UsersVo mget(String k) throws Exception {
        return dao.mselect(k);
    }
    
    public List<UsersVo> mget() throws Exception {
        return dao.mselectAll();
    }
  
	public int mgetTotalNum() throws Exception {		
		return dao.mgetTotalNum();
	}

	public List<UsersVo> mselectlist(Map<String, Integer> Map) throws Exception {
		return dao.mselectlist(Map);
	}

	public List<UsersVo> msearch(Map<String, String> map) throws Exception {
		return dao.msearch(map);
	}
 
	
	/********************************* festival photo */
 
	public void iremove(Integer k) throws Exception {
		dao.ideleteNew(k);
	}
 
	public imgallowVo iselectNew(Integer k) throws Exception {
		return dao.iselectNew(k);
	}

	public List<imgallowVo> iselectAllNew() throws Exception {
		return dao.iselectAllNew();
	}

	public void iupdateY(Integer k) throws Exception {
		dao.iupdateY(k);
	}
	
	public void iupdateNwhy(int iid, String whytext) throws Exception {
		dao.iupdateNwhy(iid, whytext); 
	}
	
	/********************************* board */
	
	public List<BoardVo> bget(Map<String, Integer> m) throws Exception {
		return dao.bget(m);
	}
	
	public BoardVo bget(int k) throws Exception {
		return dao.bselect(k);
	}
	
	public List<BoardVo> bsearch(Map<String, String> map) throws Exception {
		return dao.bsearch(map);
	}
	
	public void bremove(Integer k) throws Exception {
		dao.bdelete(k);		
	}
	
    public List<BoardVo> bgetList() throws Exception {
        return dao.bselectAll();
    }
    
    public int getbCount() throws Exception {
        return dao.bcount();
    }
    
    
    
    public List<CommentVo> getList(Integer bno) throws Exception{
    	return dao.cgetList(bno);
    }

	public void deleteComment(int cno) throws Exception {
		dao.deleteComment(cno);
	}

	public CommentVo cget(int cno) throws Exception {
		return dao.cget(cno);
	}

	public void iupdateNwhy(imgallowVo vo) throws Exception {
		dao.iupdateNwhy(vo);
		
	}
	
	public List<FestivalVo> fnowget() throws Exception {
		return dao.fnowget();
	}
 
	
	
	
}
