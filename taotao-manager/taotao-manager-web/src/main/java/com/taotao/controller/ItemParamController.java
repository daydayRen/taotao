package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/query/itemcatid/{itemcatid}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemcatid) {
		TaotaoResult result = itemParamService.getItemParamByCid(itemcatid);
		return result;
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		System.out.println("ok");
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}

	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		TbItemParam itemPatam = new TbItemParam();
		itemPatam.setItemCatId(cid);
		itemPatam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemPatam);
		return result;
	}
}
