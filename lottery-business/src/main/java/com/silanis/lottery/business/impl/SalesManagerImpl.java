package com.silanis.lottery.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silanis.lottery.business.PotManager;
import com.silanis.lottery.business.SalesManager;
import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.Money;
import com.silanis.lottery.common.dto.Person;
import com.silanis.lottery.common.dto.Ticket;
import com.silanis.lottery.common.exceptions.DrawException;
import com.silanis.lottery.repository.Repository;

/**
 * @author am
 * 
 * Class that implement operations related with the sale of tickets
 */
@Component
public class SalesManagerImpl implements SalesManager {

	/**
	 * Reference to the pot manager
	 */
	@Autowired
	private PotManager potManager;

	/* (non-Javadoc)
	 * @see com.silanis.lottery.business.SalesManager#saleTicket(com.silanis.lottery.common.dto.Draw, com.silanis.lottery.common.dto.Person)
	 */
	public Ticket saleTicket(Draw draw, Person person) throws DrawException {
		//Checks if it's possible sell more tickets
		if ( draw.getSoldTickets().size() == draw.getConfig().getNumberOfTickets() ) {
			throw new DrawException("All tickets have been sold");
		}
		
		//Saves operation into the pot
		potManager.creditPot(Repository.getPot(), getTicketPrice(draw));
		
		//Gets ball number
		Integer ballNumber = draw.getConfig().getBallNumerator().getNextNumber(draw);
		Ticket ticket = new Ticket(person, ballNumber);
		
		//Save the ticket
		draw.getSoldTickets().put(ballNumber, ticket);
		return ticket;
	}

	/* (non-Javadoc)
	 * @see com.silanis.lottery.business.SalesManager#getTicketPrice(com.silanis.lottery.common.dto.Draw)
	 */
	public Money getTicketPrice(Draw draw) {
		return draw.getConfig().getTicketPrice();
	}
}
