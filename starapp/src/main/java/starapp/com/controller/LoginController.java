package starapp.com.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;






@Controller/*此处直接返回的是一个界面*/
/*直接返回的是字符串，@RestController等同于@Controller注解+@ResponseBody*/
@RequestMapping("/starapp/")
public class LoginController {
		private Logger log=Logger.getLogger(this.getClass());
	
	@RequestMapping("/")
	protected String login() {
		log.info("[{}]"+"登陆到login.html");
		return "login";	
	}
	

	
	
	
	

}
