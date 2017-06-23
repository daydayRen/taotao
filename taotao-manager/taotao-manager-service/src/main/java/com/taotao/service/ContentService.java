package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	EUDataGridResult getContentList(Long categoryId, Integer page, Integer rows);
	
	TaotaoResult  addContent(TbContent content);
}
