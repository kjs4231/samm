package com.samm.frame;

import java.util.List; 

import org.springframework.transaction.annotation.Transactional;

import com.samm.vo.BoardVo;
import com.samm.vo.SearchCondition;

public interface Biz<K,V> {
	@Transactional
	public void register(V v) throws Exception;
	public void modify(V v) throws Exception;
	public void remove(K k) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
	
}