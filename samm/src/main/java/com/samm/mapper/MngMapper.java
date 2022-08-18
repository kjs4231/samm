package com.samm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.AdmintblVo;
import com.samm.vo.BoardVo;
import com.samm.vo.CommentVo;
import com.samm.vo.FestivalVo;
import com.samm.vo.UsersVo;
import com.samm.vo.imgallowVo;


@Mapper
@Repository
public interface MngMapper {
	/**********************************/
	
    public void ainsert(AdmintblVo v) throws Exception;
    public void adelete(String k) throws Exception;
    public void aupdate(AdmintblVo v) throws Exception;
    public AdmintblVo aselect(String k) throws Exception;
    public List<AdmintblVo> aselectAll() throws Exception;
    
	/**********************************/   
	
	public List<AdmintblVo> aselectlist(Map<String, Integer> m) throws Exception;
	public List<AdmintblVo> asearch(Map<String, String> m) throws Exception;
	public int agetTotalNum() throws Exception;
	
	/**********************************/	
 
	public String idCheck(String k) throws Exception;
	public void mdelete(String k) throws Exception;
	public int mgetTotalNum() throws Exception; 
	public List<UsersVo> mselectlist(Map<String, Integer> m) throws Exception;
	public List<UsersVo> msearch(Map<String, String> m) throws Exception;
	
	/**********************************/
	public void iupdateNwhy(int iid, String whytext) throws Exception;
	
	public void iupdateY(Integer k) throws Exception;
	public void ideleteNew(int k) throws Exception;
	public imgallowVo iselectNew(int k) throws Exception;
	public List<imgallowVo> iselectAllNew() throws Exception;
	
	/**********************************/
	
	 public BoardVo bselect(int k) throws Exception;
	 public void deleteComment(Integer cno) throws Exception; 
	 public List<BoardVo> bsearch(Map<String, String> map) throws Exception;
	 public List<BoardVo> bget(Map<String, Integer> m) throws Exception;
	 
	 
	 
	 
	public UsersVo mselect(String k) throws Exception;
	public void minsert(UsersVo v) throws Exception;
	public void mupdate(UsersVo v) throws Exception;
	public List<UsersVo> mselectAll() throws Exception;
	
	public void bdelete(Integer k) throws Exception;//
	public List<BoardVo> bselectAll() throws Exception;//
	public int bcount() throws Exception;//
 
	
	
    public List<CommentVo> cgetList(Integer bno) throws Exception;
	public CommentVo cget(int cno) throws Exception;
	public void iupdateNwhy(imgallowVo vo) throws Exception;
	public List<FestivalVo> fnowget() throws Exception;
 

			
	 /**********************************/
	
	
}
