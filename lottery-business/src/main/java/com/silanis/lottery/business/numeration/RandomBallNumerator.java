package com.silanis.lottery.business.numeration;

import java.util.Random;

import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.DrawConfig;
import com.silanis.lottery.common.exceptions.DrawException;
import com.silanis.lottery.common.numeration.BallNumerator;

/**
 * @author am
 * Class that implements a ticket numerator using a random algorithm
 */
public class RandomBallNumerator implements BallNumerator {

	/* (non-Javadoc)
	 * @see com.silanis.lottery.common.numeration.TicketNumerator#getNextNumber(com.silanis.lottery.common.dto.Draw)
	 */
	public Integer getNextNumber(Draw draw) throws DrawException {
		
		DrawConfig config = draw.getConfig();
		
		//Checks if exists numbers available
		if ( config.getNumberOfTickets() == draw.getSoldTickets().size() ) {
			throw new DrawException("Not numbers available");
		}
		
		//Creates a array to store available numbers
		int[] availableNumbers = new int[config.getNumberOfTickets() - draw.getSoldTickets().size()];
		
		int availableNumbersIndex = 0;
		
		//Iterates to find available ticket numbers
		for ( int i = 1; i <= config.getNumberOfTickets(); i++ ) {
			//Checks if the number isn't already in use
			if ( draw.getSoldTickets().get(i) == null ) {
				availableNumbers[availableNumbersIndex] = i;
				availableNumbersIndex++;
			}
		}
		
		Random randomGenerator = new Random();
		// generates a random number between 1 and the number of the tickets available
		int ticketIndex = randomGenerator.nextInt(availableNumbersIndex);
		// returns the available number
		return availableNumbers[ticketIndex];
	}
}
