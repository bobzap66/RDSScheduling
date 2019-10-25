package dev.rds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.rds.entities.Account;

@Component
@Controller
public class LoginController 
{
	@Autowired
	AccountServices as;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody Account account)
	{
		HttpServletRequest request;	
		HttpServletResponse response;
		
		account = as.login(account);
		
		if(account != null)
		{
			return "Login Successful";
		}
		else
		{
			return "Login Successful";
		}
		
	}
}
