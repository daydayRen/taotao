package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId)
			throws Exception {
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}

	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult addNode(Long parentId, String name) throws Exception {
		TaotaoResult result = contentCategoryService.insertContenCategory(parentId, name);
		return result;
	}

	//删除节点
	@RequestMapping("delete")
	public TaotaoResult deleteNode(Long parentId,Long id) throws Exception {
		//bug  parentId获取不到 id可以获取 同过id获取parentId
		Long pId=contentCategoryService.getParentId(id);
		TaotaoResult result = contentCategoryService.deleteNode(pId,id);
		
		return result;
	}
	
	//更新节点
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateNode(Long id,String name){
		TaotaoResult result=contentCategoryService.updateNode(id, name);
		return result;
	}

}
