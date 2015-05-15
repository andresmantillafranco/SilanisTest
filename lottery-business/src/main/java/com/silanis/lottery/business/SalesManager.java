package com.silanis.lottery.business;

import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.Money;
import com.silanis.lottery.common.dto.Person;
import com.silanis.lottery.common.dto.Ticket;
import com.silanis.lottery.common.exceptions.DrawException;

/**
 * @author am
 *
 * Interface that defines operations related with the sale of tickets
 */
public interface SalesManager {
	/**
	 * Allows obtain ticket price for an specific draw
	 * @param draw
	 * @return
	 */
	public Money getTicketPrice(Draw draw);
	
	/**
	 * Allows sell a ticket to a person
	 * @param draw
	 * @param person
	 * @return
	 * @throws DrawException
	 */
	public Ticket saleTicket(Draw draw, Person person) throws DrawException;
}
