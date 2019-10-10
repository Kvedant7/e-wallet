package com.capgemini.dao;

import java.util.List;

import com.capgemini.entity.AccountModel;
import com.capgemini.entity.Transaction;
import com.capgemini.exception.InsufficientFundException;
import com.capgemini.exception.UserExistsException;
import com.capgemini.exception.UserNotFoundException;

public interface AccountDao {
	public int createAccount(AccountModel acc) throws UserExistsException;
	public AccountModel validateLogin(long mob,String pass);
	public AccountModel printBalance(long mob);
	public AccountModel deposit(long mob,double dep);
	public AccountModel withdraw(long mob,double wdn) throws InsufficientFundException;
	public List<AccountModel> fundTransfer(long mob1,long mob2,double amt) throws InsufficientFundException,UserNotFoundException;
	public AccountModel printTransactions(long mob);
	public void beginTransaction();
	public void commitTransaction();
	public boolean findSenderOrReceiver(long mob,Transaction transaction);
	public void deleteAccount(long mob);
	public AccountModel updateUser(AccountModel acc);
}
