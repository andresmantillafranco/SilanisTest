package com.silanis.lottery.business.rafflers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.silanis.lottery.business.utils.CalculationUtils;
import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.DrawConfig;
import com.silanis.lottery.common.dto.Money;
import com.silanis.lottery.common.dto.WinnerTicket;
import com.silanis.lottery.common.exceptions.DrawException;
import com.silanis.lottery.common.raffle.Raffler;

/**
 * @author am
 *
 * Class that implements a basic raffler, with just three possible winners
 */
public class BasicRaffler implements Raffler {

	/**
	 * Indicates the way to distribute the prize
	 */
	private final static float[] prizesPlan = {75f, 15f, 10f};
	
	/* (non-Javadoc)
	 * @see com.silanis.lottery.common.raffle.Raffler#rafflePrizes(com.silanis.lottery.common.dto.Draw)
	 */
	public List<WinnerTicket> rafflePrizes(Draw draw) throws DrawException {
		if ( draw.getSoldTickets() == null || draw.getSoldTickets().size() == 0 ) {
			throw new DrawException("No tickets sold");
		}
		
		DrawConfig config = draw.getConfig();
		Float percentage = config.getPotPercentage();
		
		//Calculates the percentage of the pot to distribute
		BigDecimal totalPrize = CalculationUtils.getPercentageValue(draw.getPot().getBalance().getAmount(), percentage);
		
		List<WinnerTicket> winners = new ArrayList<WinnerTicket>();
		Random randomGenerator = new Random();

		//Generates the winners based on the prizes plan
		for ( float prizePlan : prizesPlan ) {
			//Calculates the specific prize to the ball
			BigDecimal prize = CalculationUtils.getPercentageValue(totalPrize, prizePlan);
			prize = CalculationUtils.round(prize);

			// generates a random number between 1 and the number of the tickets available
			int ballNumber = randomGenerator.nextInt(config.getNumberOfTickets()) + 1;
			
			//Add the winner to the array, notice that is possible not to have a winner if the ball is not associated with a ticket 
			winners.add(new WinnerTicket(draw.getSoldTickets().get(ballNumber), new Money(prize, config.getTicketPrice().getCurrency())));
		}
		return winners;
	}
}
