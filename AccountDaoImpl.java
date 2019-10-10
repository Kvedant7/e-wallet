package com.capgemini.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.capgemini.entity.AccountModel;
import com.capgemini.entity.Transaction;
import com.capgemini.exception.InsufficientFundException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.utils.JPAUtils;

//Dao implementation by 189852
@Repository
public class AccountDaoImpl implements AccountDao{
	JPAUtils jpaUtil = new JPAUtils();
	private static EntityManager em;
	private int transactionId = 0;
	private String transMode = null;
	private String transDate = null;
	private static Transaction transaction;
	private List<AccountModel> list;
	
	public AccountDaoImpl(){
			em = jpaUtil.getEntityManager();
	}
   	@Override
	public int createAccount(AccountModel user){
   		AccountModel userTemp = em.find(AccountModel.class,user.getMobNumber());
   		if(userTemp==null){
		em.persist(user);
		return 1;
   		}else{
   			return 0;
   		}
	}
	@Override
	public void beginTransaction() {
			em.getTransaction().begin();
	}
	@Override
	public void commitTransaction() {
		em.getTransaction().commit();
	}
	@Override
	public AccountModel validateLogin(long mob, String pass) {
		AccountModel userTemp = em.find(AccountModel.class,mob);
		if(userTemp!=null&&userTemp.getPassword().equals(pass))
			return userTemp;
		else
			return null;
	}
	@Override
	public AccountModel printBalance(long mob) {
		AccountModel userTemp = em.find(AccountModel.class,mob);
		return userTemp;
	}
	@Override
	public AccountModel deposit(long mob, double dep) {
		transactionId = new Random().nextInt(900000) + 100000;
		transMode = "Deposit";
		transDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		AccountModel userTemp = em.find(AccountModel.class,mob);
		userTemp.setBalance(userTemp.getBalance()+dep);
		em.merge(userTemp);
		transaction = new Transaction();
		transaction.setAccount(userTemp);
		transaction.setTransactionMode(transMode);
		transaction.setReceiverMobNo(mob);
		transaction.setTransactionId(transactionId);
		transaction.setSenderMobNo(mob);
		transaction.setTransactionAmount(dep);
		transaction.setTransactionDate(transDate);
		transaction.setSenderBal(userTemp.getBalance());
		transaction.setReceiverBal(userTemp.getBalance());
		userTemp.addTransaction(transaction);
		em.persist(transaction);
		return userTemp;
	}
	@Override
	public AccountModel withdraw(long mob, double wdn) throws InsufficientFundException {
		transactionId = new Random().nextInt(900000) + 100000;
		transMode = "Withdraw";
		transDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		AccountModel userTemp = em.find(AccountModel.class,mob);
		if(userTemp.getBalance()<wdn) {
			throw new InsufficientFundException();
		}else {
			userTemp.setBalance(userTemp.getBalance()-wdn);
			em.merge(userTemp);
			transaction = new Transaction();
			transaction.setAccount(userTemp);
			transaction.setTransactionMode(transMode);
			transaction.setReceiverMobNo(mob);
			transaction.setTransactionId(transactionId);
			transaction.setSenderMobNo(mob);
			transaction.setTransactionAmount(wdn);
			transaction.setTransactionDate(transDate);
			transaction.setSenderBal(userTemp.getBalance());
			transaction.setReceiverBal(userTemp.getBalance());
			userTemp.addTransaction(transaction);
			em.persist(transaction);
			return userTemp;
		}
	}
	@Override
	public List<AccountModel> fundTransfer(long mob1, long mob2, double amt) throws InsufficientFundException, UserNotFoundException {
		AccountModel userTemp1 = em.find(AccountModel.class, mob1);
		AccountModel userTemp2 = em.find(AccountModel.class, mob2);
		transactionId = new Random().nextInt(900000) + 100000;
		transMode = "Fund transfer";
		transDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		if(userTemp2!=null) {
			if(userTemp1.getBalance()<amt) {
				list = new ArrayList<AccountModel>();
				list.add(userTemp1);
				return list;
			}else {
				userTemp1.setBalance(userTemp1.getBalance()-amt);
				em.merge(userTemp1);
				userTemp2.setBalance(userTemp2.getBalance()+amt);
				em.merge(userTemp2);
				transaction = new Transaction();
				transaction.setTransactionMode(transMode);
				transaction.setReceiverMobNo(mob2);
				transaction.setTransactionId(transactionId);
				transaction.setSenderMobNo(mob1);
				transaction.setTransactionAmount(amt);
				transaction.setTransactionDate(transDate);
				transaction.setSenderBal(userTemp1.getBalance());
				transaction.setReceiverBal(userTemp2.getBalance());
				userTemp1.addTransaction(transaction);
				
				Transaction transaction1 = new Transaction();
				transaction1.setTransactionMode(transMode);
				transaction1.setReceiverMobNo(mob2);
				transaction1.setTransactionId(transactionId);
				transaction1.setSenderMobNo(mob1);
				transaction1.setTransactionAmount(amt);
				transaction1.setTransactionDate(transDate);
				transaction1.setSenderBal(userTemp1.getBalance());
				transaction1.setReceiverBal(userTemp2.getBalance());
				userTemp2.addTransaction(transaction1);
				em.persist(transaction);
				list = new ArrayList<AccountModel>();
				list.add(userTemp1);
				list.add(userTemp2);
				return list;
			}
		}else {
			throw new UserNotFoundException();
		}
	}
	@Override
	public AccountModel printTransactions(long mob) {
		AccountModel userTemp = em.find(AccountModel.class, mob);
		return userTemp;
	}
	@Override
	public boolean findSenderOrReceiver(long mob,Transaction transaction) {
		if(mob==transaction.getSenderMobNo())
			return true;
		else
			return false;
		}
	public void deleteAccount(long mob) {
		AccountModel user = em.find(AccountModel.class,mob);
		em.remove(user);
	}
	@Override
	public AccountModel updateUser(AccountModel acc) {
		AccountModel userTemp = em.find(AccountModel.class, acc.getMobNumber());
		userTemp.setName(acc.getName());
		userTemp.setAccNumber(acc.getAccNumber());
		userTemp.setAge(acc.getAge());
		userTemp.setBalance(acc.getBalance());
		userTemp.setEmail(acc.getEmail());
		userTemp.setMobNumber(acc.getMobNumber());
		userTemp.setPassword(acc.getPassword());
		return userTemp;
	}
	
}

	
	
	

