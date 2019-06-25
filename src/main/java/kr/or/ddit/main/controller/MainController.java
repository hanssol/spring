package kr.or.ddit.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
  servlet 
   - extends HttpServlet
   - servlet-mapping(web.xml, @WebServlet)
   - sevice -> doXXX
   
  spring  
   - pojo (Plain Old Java Object), @Controller  
   - @RequestMapping(class, method)
   - @requestMapping에 설정한 url method 호출
 */

@Controller
public class MainController {
	
	@RequestMapping("/main")
//	public String mainView(HttpServletRequest request) {
	public String mainView(Model model) {
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		
		// prefix + viewName + suffix
		// /WEB-INF/views/main.jsp
		
		// 아래 문장은 다음과 동일하다
//		 request.setAttribute("mainUserId","brown");
		model.addAttribute("mainUserId", "brown");
		
		// viewName
		return "main";
	}
	
}
