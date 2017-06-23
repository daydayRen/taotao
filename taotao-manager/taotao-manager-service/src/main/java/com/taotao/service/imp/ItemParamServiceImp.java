package com.taotao.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;


@Service
public class ItemParamServiceImp implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	public TaotaoResult getItemParamByCid(Long cid) {
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria=example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list=itemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null&& list.size()>0){
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}
	
	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {
		//查询商品列表
		TbItemParamExample example=new TbItemParamExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItemParam> list=itemParamMapper.selectByExampleWithBLOBs(example);
		//创建一个返回值对象
		EUDataGridResult result=new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}
