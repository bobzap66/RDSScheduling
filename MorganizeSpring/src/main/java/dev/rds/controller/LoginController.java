package dev.rds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@Controller
public class LoginController 
{
	@Autowired
	AccountServices as;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login()
	{
		
	}
}
