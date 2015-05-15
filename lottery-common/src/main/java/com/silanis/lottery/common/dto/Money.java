package com.silanis.lottery.common.dto;

import java.math.BigDecimal;

/**
 * @author am
 * 
 *         Class that represents an amount of money in a specific currency
 */
public class Money implements DataTransferObject {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Amount of money
	 */
	private BigDecimal amount;

	/**
	 * Currency of the money
	 */
	private String currency;

	/**
	 * Constructor of the money
	 * 
	 * @param amount
	 * @param currency
	 */
	public Money(BigDecimal amount, String currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	/**
	 * Allows add money to the current object
	 * 
	 * @param money
	 */
	public void add(Money money) {
		if (money == null) {
			throw new NullPointerException();
		}

		this.setAmount(this.getAmount().add(money.getAmount()));
	}

	/**
	 * Allows add money to the current object
	 * 
	 * @param money
	 */
	public void substract(Money money) {
		if (money == null) {
			throw new NullPointerException();
		}

		this.setAmount(this.getAmount().subtract(money.getAmount()));
	}

	/*
	 * Getters and setters
	 */

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
