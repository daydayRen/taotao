package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

public interface ContentCategoryService {
	List<EUTreeNode> getCategoryList(Long parentId)throws Exception;
	TaotaoResult insertContenCategory(Long parentId,String name)throws Exception;
	TaotaoResult deleteNode(Long parentId,Long id)throws Exception;
	Long getParentId(Long id);
	TaotaoResult updateNode(Long id,String name);
}
