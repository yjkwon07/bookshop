package com.yjkwon.bookshop.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yjkwon.bookshop.goods.service.GoodsService;
import com.yjkwon.bookshop.goods.vo.GoodsVO;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController{
	@Autowired 
	private GoodsService goodsService;
	
	@RequestMapping(value = "/main/main", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletRequest response) throws Exception{
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		Map<String, List<GoodsVO>> goodsMap = goodsService.listGoods();
		mav.addObject("goodsMap",goodsMap);
		if(session.isNew()) {
			session.setAttribute("create", session.getCreationTime());
		}
		return mav; 
	}
}