package com.yjkwon.bookshop.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.yjkwon.bookshop.goods.service.GoodsService;
import com.yjkwon.bookshop.goods.vo.GoodsVO;

import net.sf.json.JSONObject;

@Controller("goodsController")
@RequestMapping(value = "/goods")
public class GoodsControllerImpl implements GoodsController {
	@Autowired
	GoodsService goodsService;

	@Override
	@RequestMapping(value="/goodsDetail", method = RequestMethod.GET)
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session = request.getSession();
		Map<String, Object>goodsMap = (Map<String, Object>)goodsService.goodsDetail(goods_id);
		mav.addObject("goodsMap",goodsMap);
		GoodsVO goodsVO = (GoodsVO)goodsMap.get("goodsVO");
		getQuickMenuData(goods_id, goodsVO, session);
		return mav;
	}

	@Override
	@RequestMapping(value="/keywordSearch",method=RequestMethod.GET,produces="application/json;charset=utf8")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String keywordSearch(@RequestParam("keyword") String keyword,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		if(keyword == null || keyword.equals("")) return null;
		keyword = keyword.toUpperCase();
		List<String>keywordList = goodsService.keywordSearch(keyword);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);
		String jsonInfo = jsonObject.toString();
		return jsonInfo;
	}

	@Override
	@RequestMapping(value="/searchGoods",method=RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		List<GoodsVO> goodsList = (ArrayList<GoodsVO>)goodsService.searchGoods(searchWord);
		mav.addObject("goodsList" ,goodsList);
		return mav;
	}

	protected void getQuickMenuData(String goods_id,GoodsVO goodsVO,HttpSession session) {
		List<GoodsVO> quickGoodsList=quickGoodsList=(ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");
		if (quickGoodsList!=null && quickGoodsList.size()<4) {
			int targtGoodsId=Integer.parseInt(goods_id);
			quickGoodsList=quickGoodsList.stream().filter(compare->compare.getGoods_id()!=targtGoodsId)
					.collect(Collectors.toList());
			quickGoodsList.add(goodsVO);
		} else {
			quickGoodsList=new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);
		}
		session.setAttribute("quickGoodsList",quickGoodsList);
		session.setAttribute("quickGoodsListNum",quickGoodsList.size());
	}
}
