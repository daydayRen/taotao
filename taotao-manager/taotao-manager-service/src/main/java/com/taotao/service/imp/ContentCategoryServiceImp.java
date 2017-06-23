package com.taotao.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImp implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	//查询节点
	@Override
	public List<EUTreeNode> getCategoryList(Long parentId) throws Exception {
		//创建查询
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//查找
		List<TbContentCategory> list=contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList=new ArrayList<>();
		
		for(TbContentCategory tbContentCategory:list){
			//创建一个节点
			EUTreeNode node=new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		
		
		return resultList;
	}

	//添加节点
	@Override
	public TaotaoResult insertContenCategory(Long parentId, String name) throws Exception {
		TbContentCategory node=new TbContentCategory();
		node.setName(name);
		node.setIsParent(false);
		node.setStatus(1);
		node.setSortOrder(1);
		node.setCreated(new Date());
		node.setUpdated(new Date());
		node.setParentId(parentId);
		
		contentCategoryMapper.insert(node);
		
		TbContentCategory parentNode=contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()){
			parentNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		
		
		return TaotaoResult.ok(node);
	}
	
	/**
	 * 删除节点
	 */
	@Override
	public Long getParentId(Long id) {
		TbContentCategory parentNode=contentCategoryMapper.selectByPrimaryKey(id);
		Long pId= parentNode.getParentId();
		return pId;
	}
	@Override
	public TaotaoResult deleteNode(Long parentId,Long id) throws Exception {
	
		
		contentCategoryMapper.deleteByPrimaryKey(id);
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
	
		List<TbContentCategory> list=contentCategoryMapper.selectByExample(example);
		//判断是否还是父节点
		if(list==null){
			TbContentCategory parentNode=contentCategoryMapper.selectByPrimaryKey(parentId);
			parentNode.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateNode(Long id, String name) {
		TbContentCategory Node=contentCategoryMapper.selectByPrimaryKey(id);
		Node.setName(name);
		contentCategoryMapper.updateByPrimaryKey(Node);
		return TaotaoResult.ok();
	}

	

}
