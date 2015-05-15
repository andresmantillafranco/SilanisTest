package com.silanis.lottery.common.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author am
 * 
 *         Class that represents the lottery pot
 */
public class Pot implements DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Current balance of the pot
	 */
	private Money balance;

	/**
	 * Operations made with this pot
	 */
	private List<PotOperation> operations;

	public Pot(String currency) {
		super();
		balance = new Money(BigDecimal.ZERO, currency);
	}
	/*
	 * Getters and setters
	 */

	public Money getBalance() {
		return balance;
	}

	public void setBalance(Money balance) {
		this.balance = balance;
	}

	public List<PotOperation> getOperations() {
		return operations;
	}

	public void setOperations(List<PotOperation> operations) {
		this.operations = operations;
	}
	
	public void addOperation(PotOperation operation) {
		if ( this.operations == null ) {
			this.operations = new ArrayList<PotOperation>();
		}
		this.operations.add(operation);
	}
}
