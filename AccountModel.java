package com.capgemini.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


//Model Class by 189852
@Entity
@Table(name="account_table")
public class AccountModel{
	private String Password;
	private String Name;
	private long AccNumber;
	private double Balance=0.0;
	private String Email;
	@Id
	private long MobNumber;
	private int Age;
	
	@OneToMany(mappedBy="account",cascade=CascadeType.ALL)
	 @JsonManagedReference
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	public AccountModel() {
		
	}
	
	public AccountModel(String password, String name, long accNumber, double balance, String email, long mobNumber,
			int age) {
		super();
		Password = password;
		Name = name;
		AccNumber = accNumber;
		Balance = balance;
		Email = email;
		MobNumber = mobNumber;
		Age = age;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getAccNumber() {
		return AccNumber;
	}
	public void setAccNumber(long accNumber) {
		AccNumber = accNumber;
	}
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public long getMobNumber() {
		return MobNumber;
	}
	public void setMobNumber(long mobNumber) {
		MobNumber = mobNumber;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (AccNumber ^ (AccNumber >>> 32));
		result = prime * result + Age;
		long temp;
		temp = Double.doubleToLongBits(Balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + (int) (MobNumber ^ (MobNumber >>> 32));
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((Password == null) ? 0 : Password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountModel other = (AccountModel) obj;
		if (AccNumber != other.AccNumber)
			return false;
		if (Age != other.Age)
			return false;
		if (Double.doubleToLongBits(Balance) != Double.doubleToLongBits(other.Balance))
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (MobNumber != other.MobNumber)
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AccountModel [Password=" + Password + ", Name=" + Name + ", AccNumber=" + AccNumber + ", Balance="
				+ Balance + ", Email=" + Email + ", MobNumber=" + MobNumber + ", Age=" + Age + "]";
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void addTransaction(Transaction transaction) {
		transaction.setAccount(this);			
		this.getTransactions().add(transaction);
	}
		
	
}
