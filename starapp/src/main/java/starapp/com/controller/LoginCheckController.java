package starapp.com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController//直接将字符串返回界面
@RequestMapping("/starapp/")
public class LoginCheckController {
	private Logger log=Logger.getLogger(this.getClass());
	
	/**
	 * 登陆验证
	 * @author 周聪
	 * @param reques
	 * @return
	 */
	@RequestMapping("/login_check")
	protected String login_check(HttpServletRequest request) {		
		String _account=request.getParameter("user");
		log.info(_account);
		log.info("[{/login_check}]"+"登陆成功");
		HashMap<String,String> hashMap=new HashMap<String,String>();
		hashMap.put("message", "success");

	String jsonData=JSON.toJSONString(hashMap);
		log.info(jsonData);
		return 	jsonData;
	}
	

}
