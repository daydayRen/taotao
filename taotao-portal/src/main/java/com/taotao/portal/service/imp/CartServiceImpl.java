package com.taotao.portal.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITME_INFO_URL}")
	private String ITME_INFO_URL;
	

	/**
	 * 添加购物车商品
	 */
	@Override
	public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		CartItem cartItem = null;
		// 获得商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 如果有商品数量加一
		for (CartItem item : itemList) {
			if (item.getId() == itemId) {
				item.setNum(item.getNum() + num);
				cartItem = item;
				break;
			}

		}
		// 没有商品从缓存中添加商品
		// 添加商品到购物车
		if (cartItem == null) {
			cartItem = new CartItem();
			String itemJson = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL + itemId);
			System.out.println(itemJson+"===================");
			TaotaoResult result = TaotaoResult.formatToPojo(itemJson,TbItem.class);
			if (result.getStatus() == 200) {
				TbItem item = (TbItem) result.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			itemList.add(cartItem);
		}
		// 存储到cookie中 true表示字符需要编码
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}

	/**
	 * 获取商品列表
	 */
	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> list = getCartItemList(request);
		return list;
	}

	private List<CartItem> getCartItemList(HttpServletRequest request) {
		String cartjson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (cartjson == null) {
			return new ArrayList<>();
		}
		// 把json装换为商品列表
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartjson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	
	/**
	 * 删除商品
	 */
	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 获得商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 如果有商品删除
		for (CartItem item : itemList) {
			if (item.getId() == itemId) {
				itemList.remove(item);
				break;
			}

		}
		// 存储到cookie中 true表示字符需要编码
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}
}
