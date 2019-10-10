package com.capgemini.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="transaction_table")
public class Transaction {
			@Id
			@GeneratedValue(strategy=GenerationType.AUTO)
			private long transactionCount;
			private long transactionId;
			private String transactionMode;
			
			private String transactionDate;
			private double transactionAmount;
			private long senderMobNo;
			private long receiverMobNo;
			private double senderBal;
			private double receiverBal;
			
			@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
			@JoinColumn(name="mob_no")
			@JsonBackReference
			private AccountModel account;
			
			public Transaction() {
				
			}
			public Transaction(long transactionId, String transactionMode, String transactionDate,
					double transactionAmount, long senderMobNo, long receiverMobNo, double senderBal,
					double receiverBal) {
				super();
				this.transactionId = transactionId;
				this.transactionMode = transactionMode;
				this.transactionDate = transactionDate;
				this.transactionAmount = transactionAmount;
				this.senderMobNo = senderMobNo;
				this.receiverMobNo = receiverMobNo;
				this.senderBal = senderBal;
				this.receiverBal = receiverBal;
			}
			public long getTransactionId() {
				return transactionId;
			}
			public void setTransactionId(long transactionId) {
				this.transactionId = transactionId;
			}
			public String getTransactionMode() {
				return transactionMode;
			}
			public void setTransactionMode(String transactionMode) {
				this.transactionMode = transactionMode;
			}
			public String getTransactionDate() {
				return transactionDate;
			}
			public void setTransactionDate(String transactionDate) {
				this.transactionDate = transactionDate;
			}
			public double getTransactionAmount() {
				return transactionAmount;
			}
			public void setTransactionAmount(double transactionAmount) {
				this.transactionAmount = transactionAmount;
			}
			public long getSenderMobNo() {
				return senderMobNo;
			}
			public void setSenderMobNo(long senderMobNo) {
				this.senderMobNo = senderMobNo;
			}
			public long getReceiverMobNo() {
				return receiverMobNo;
			}
			public void setReceiverMobNo(long receiverMobNo) {
				this.receiverMobNo = receiverMobNo;
			}
			public double getSenderBal() {
				return senderBal;
			}
			public void setSenderBal(double senderBal) {
				this.senderBal = senderBal;
			}
			public double getReceiverBal() {
				return receiverBal;
			}
			public void setReceiverBal(double receiverBal) {
				this.receiverBal = receiverBal;
			}
			public AccountModel getAccount() {
				return account;
			}
			public void setAccount(AccountModel account) {
				this.account = account;
			}
			public long getTransactionCount() {
				return transactionCount;
			}
			public void setTransactionCount(long transactionCount) {
				this.transactionCount = transactionCount;
			}
			@Override
			public String toString() {
				return "Transaction [transactionId=" + transactionId + ", transactionMode=" + transactionMode
						+ ", transactionDate=" + transactionDate + ", transactionAmount=" + transactionAmount
						+ ", senderMobNo=" + senderMobNo + ", receiverMobNo=" + receiverMobNo + ", senderBal="
						+ senderBal + ", receiverBal=" + receiverBal + "]";
			}
			
			
			
			
}
