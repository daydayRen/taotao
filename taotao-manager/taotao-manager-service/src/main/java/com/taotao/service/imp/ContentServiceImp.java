package com.taotao.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImp implements com.taotao.service.ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	//查询分类列表
	@Override
	public EUDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
		
		TbContentExample example=new TbContentExample();
		Criteria criteria=example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		PageHelper.startPage(page, rows);
		
		List<TbContent> list=contentMapper.selectByExampleWithBLOBs(example);
		//取分页信息
	
		PageInfo<TbContent> pageInfo=new PageInfo<>(list);
		EUDataGridResult result=new EUDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//添加分类列表
	@Override
	public TaotaoResult addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
	
		//添加缓存同步逻辑
		
		try {
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return TaotaoResult.ok();
	}

}
