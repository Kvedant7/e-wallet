package com.capgemini.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.capgemini.dao.AccountDaoImpl;
import com.capgemini.entity.AccountModel;
import com.capgemini.entity.Transaction;
import com.capgemini.exception.InsufficientFundException;
import com.capgemini.exception.UserExistsException;
import com.capgemini.exception.UserNotFoundException;
//Service Implementation By 189852
@Service
public class AccountServiceImpl implements AccountService{
	AccountDaoImpl dao= new AccountDaoImpl();
	@Override
	public AccountModel createAccount(AccountModel user) throws  UserExistsException{
		dao.beginTransaction();
		int result = dao.createAccount(user);
		dao.commitTransaction();
		if(result==1)
			return user;
		else
			return null;
	}
	//Show Balance
	@Override
	public AccountModel showBalance(long mob) {
		dao.beginTransaction();
		AccountModel result1=dao.printBalance(mob);
		dao.commitTransaction();
		return result1;
	}
	// Deposit
	@Override
	public AccountModel deposit(long mob,double dep){
			dao.beginTransaction();
			AccountModel result2=dao.deposit(mob,dep);
			dao.commitTransaction();
			return result2;
		}
	//Withdraw
	@Override
	public AccountModel withdraw(long mob,double wdn) throws InsufficientFundException{
		try {
			dao.beginTransaction();
			AccountModel result3=dao.withdraw(mob,wdn);
			dao.commitTransaction();
			return result3;
		} catch (InsufficientFundException e) {
			dao.commitTransaction();
			throw e;
		}
	}
	//Fund Transfer
	@Override
	public List<AccountModel> fundTransfer(long mob1,long mob2,double amt) throws InsufficientFundException, UserNotFoundException {
		try {
			dao.beginTransaction();
			List<AccountModel> result4=dao.fundTransfer(mob1,mob2,amt);
			dao.commitTransaction();
			return result4;
		}catch(InsufficientFundException e1){
			dao.commitTransaction();
			throw e1;
		}catch(UserNotFoundException e2) {
			dao.commitTransaction();
			throw e2;
		}
	}
	//Print Transactions 
	@Override
	public AccountModel printTransactions(long mob) {
		dao.beginTransaction();
		AccountModel result = dao.printTransactions(mob);
		dao.commitTransaction();
		return result;
	}
	@Override
	public boolean findSenderOrReceiver(long mob,Transaction transaction) {
		dao.beginTransaction();
		boolean result = dao.findSenderOrReceiver(mob,transaction);
		dao.commitTransaction();
		return result;
	}
	@Override
	public AccountModel updateAccount(AccountModel acc) {
		dao.beginTransaction();
		AccountModel result = dao.updateUser(acc);
		dao.commitTransaction();
		return result;
	}
	
	// Validations By 189852
	@Override
	public AccountModel validateLogin(long mob,String pass) {
		AccountModel result=dao.validateLogin(mob,pass);
		return result;
	}
	
		
}
