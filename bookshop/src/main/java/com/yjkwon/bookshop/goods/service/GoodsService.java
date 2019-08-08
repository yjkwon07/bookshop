package com.yjkwon.bookshop.goods.service;

import java.util.List;
import java.util.Map;

import com.yjkwon.bookshop.goods.vo.GoodsVO;

public interface GoodsService {
	public Map<String , List<GoodsVO>> listGoods() throws Exception;
	public Map<String, Object> goodsDetail(String _good_id) throws Exception;
	public List<String> keywordSearch(String keyword) throws Exception;
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;
}
