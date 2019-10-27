package dev.rds.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.rds.entities.Account;
import dev.rds.services.AccountService;

@RestController
@CrossOrigin
public class AccountController 
{
	@Autowired
	AccountService as;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Account login(@RequestBody Account temp)
	{	
		temp = as.login(temp);	
		return temp;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public Account createAccount(@RequestBody Account account) {
		account = as.createAccount(account);
		return account;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@ResponseBody
	public Account updateAccount(@RequestBody Account account) {
		account = as.updateAccount(account);
		return account;
	}
}
