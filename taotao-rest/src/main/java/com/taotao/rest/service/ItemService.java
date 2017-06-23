package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
	TaotaoResult getItemBaseInfo(Long itemId);
	TaotaoResult getItemDesc(long itemId);
	TaotaoResult getItemParam(long itemId);
}
