package com.silanis.lottery.business;

import com.silanis.lottery.common.dto.Money;
import com.silanis.lottery.common.dto.Pot;
import com.silanis.lottery.common.exceptions.PotException;

/**
 * @author am
 * Interface that defines operations related with the pot
 */
public interface PotManager {
	/**
	 * Allows create a new pot
	 * @param amount
	 * @return
	 */
	public Pot createPot(Money amount); 
	
	/**
	 * Credit the pot with money
	 * @param pot
	 * @param amount
	 */
	public void creditPot(Pot pot, Money amount);

	/**
	 * Debt the pot with money
	 * @param pot
	 * @param amount
	 * @throws PotException 
	 */
	public void debitPot(Pot pot, Money amount) throws PotException;
}
