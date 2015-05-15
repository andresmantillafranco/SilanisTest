package com.silanis.lottery.common.dto;

import com.silanis.lottery.common.constants.OperationTypeEnum;

/**
 * @author am
 * 
 *         Class that represents an money operation made into the pot
 */
public class PotOperation implements DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Type of operation
	 */
	private OperationTypeEnum type;

	/**
	 * Amount of the operation
	 */
	private Money amount;

	
	/**
	 * @param type
	 * @param amount
	 */
	public PotOperation(OperationTypeEnum type, Money amount) {
		super();
		this.type = type;
		this.amount = amount;
	}

	/*
	 * Getters and setters
	 */

	public OperationTypeEnum getType() {
		return type;
	}

	public void setType(OperationTypeEnum type) {
		this.type = type;
	}

	public Money getAmount() {
		return amount;
	}

	public void setAmount(Money amount) {
		this.amount = amount;
	}
}
