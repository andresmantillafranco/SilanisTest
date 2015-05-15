package com.silanis.lottery.business.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.silanis.lottery.business.PotManager;
import com.silanis.lottery.common.constants.OperationTypeEnum;
import com.silanis.lottery.common.dto.Money;
import com.silanis.lottery.common.dto.Pot;
import com.silanis.lottery.common.dto.PotOperation;
import com.silanis.lottery.common.exceptions.PotException;
import com.silanis.lottery.repository.Repository;

/**
 * @author am
 * 
 * Class that implements operations related with the pot
 */
@Component
public class PotManagerImpl implements PotManager {

	/* (non-Javadoc)
	 * @see com.silanis.lottery.business.PotManager#createPot(com.silanis.lottery.common.dto.Money)
	 */
	public Pot createPot(Money amount) {
		//The pot starts with 0
		Pot pot = new Pot(amount.getCurrency());

		//Credit the pot
		if ( ! amount.getAmount().equals(BigDecimal.ZERO) ) {			
			creditPot(pot, amount);
		}
		Repository.setPot(pot);
		return pot;
	}

	/* (non-Javadoc)
	 * @see com.silanis.lottery.business.PotManager#creditPot(com.silanis.lottery.common.dto.Pot, com.silanis.lottery.common.dto.Money)
	 */
	public void creditPot(Pot pot, Money amount) {
		if ( pot == null ) {
			return;
		}
		
		//Save the operation to audit the pot
		pot.addOperation(new PotOperation(OperationTypeEnum.CREDIT, amount));
		pot.getBalance().add(amount);
	}

	/* (non-Javadoc)
	 * @see com.silanis.lottery.business.PotManager#debitPot(com.silanis.lottery.common.dto.Pot, com.silanis.lottery.common.dto.Money)
	 */
	public void debitPot(Pot pot, Money amount) throws PotException {
		if ( pot == null ) {
			return;
		}
		
		//Save the operation to audit the pot
		pot.addOperation(new PotOperation(OperationTypeEnum.DEBIT, amount));
		
		//Checks if the pot has enough money
		if ( pot.getBalance().getAmount().compareTo(amount.getAmount()) > 0 ) {			
			pot.getBalance().substract(amount);
		} else {
			pot.setBalance(new Money(BigDecimal.ZERO, amount.getCurrency()));
			throw new PotException("Opps!! Not enough money in the pot");
		}
	}
}
