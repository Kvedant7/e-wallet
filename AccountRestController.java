package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.AccountModel;
import com.capgemini.exception.InsufficientFundException;
import com.capgemini.exception.UserExistsException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.service.AccountService;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping(value="ewallet")
public class AccountRestController {
	@Autowired
	private AccountService service;
	// http://localhost:9090/ewallet/hello
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public String helloWorld(){
		return "Hello Class From REST service";
	}
	// http://localhost:9090/ewallet/deposit/{mob}/{amt}
	@RequestMapping(value="/deposit/{mob}/{amt}", method=RequestMethod.PUT)
		public AccountModel deposit(@PathVariable("mob") long mob,@PathVariable ("amt") double amt){
		return service.deposit(mob,amt);
	}
	// http://localhost:9090/ewallet/balance/{mob}
	@RequestMapping(value="/balance/{mob}", method=RequestMethod.GET)
	public AccountModel printBalance(@PathVariable("mob")long mob){
		AccountModel user = service.showBalance(mob);
		return user;
	}
	// http://localhost:9090/ewallet/withdraw/{mob}/{amt}
		@RequestMapping(value="/withdraw/{mob}/{amt}", method=RequestMethod.PUT)
			public AccountModel withdraw(@PathVariable("mob") long mob,@PathVariable ("amt") double amt) {
			try{
				return service.withdraw(mob,amt);
			}catch(InsufficientFundException e)
			{
				System.out.println(e.toString());
				return null;
			}
		}
		// http://localhost:9090/ewallet/login/{mob}/{password}
		@RequestMapping(value="/login/{mob}/{password}", method=RequestMethod.GET)
		public AccountModel logIn(@PathVariable("mob") long mob,@PathVariable ("password") String password) {
					return service.validateLogin(mob, password);
		}
		// http://localhost:9090/ewallet/fundtransfer/{mob1}/{mob2}/{amt}
		@RequestMapping(value="/fundtransfer/{mob1}/{mob2}/{amt}", method=RequestMethod.PUT)
		public List<AccountModel> fundTransfer(@PathVariable("mob1") long mob1,@PathVariable("mob2") long mob2,@PathVariable ("amt") double amt) {
							try {
								return service.fundTransfer(mob1, mob2, amt);
							} catch (InsufficientFundException | UserNotFoundException e) {
								e.printStackTrace();
							}
							return null;
		}
		// http://localhost:9090/ewallet/printtransaction/{mob}
		@RequestMapping(value="/printtransaction/{mob}", method=RequestMethod.GET)
		public AccountModel printTransactions(@PathVariable("mob") long mob) {
							return service.printTransactions(mob);
						
				}
		// http://localhost:9090/ewallet/add
		@RequestMapping(value="/add", method=RequestMethod.POST)
		public AccountModel addAccount(@RequestBody AccountModel user) {
							try {
								return service.createAccount(user);
							} catch (UserExistsException e) {
								e.printStackTrace();
							}
							return null;
						
				}
		// http://localhost:9090/ewallet/update
		@RequestMapping(value="/update", method=RequestMethod.PUT)
		public AccountModel updateAccount(@RequestBody AccountModel user){
			return service.updateAccount(user);
		}
				
	/*
	 * //200 "Successful" operation,related to 400 "not found"
	 * 
	 * @ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="Id not present")
	 * 
	 * @ExceptionHandler({StudentNotFoundException.class}) public void
	 * handleException() {
	 * 
	 * }
	 */
}
