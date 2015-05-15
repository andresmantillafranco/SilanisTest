package com.silanis.lottery.common.raffle;

import java.util.List;

import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.WinnerTicket;
import com.silanis.lottery.common.exceptions.DrawException;

/**
 * @author am
 *
 * Interface to define how to the draw and distribute the prize
 */
public interface Raffler {
	/**
	 * Method that allows draw and distribute the prize
	 * @param draw
	 * @return
	 * @throws DrawException
	 */
	public List<WinnerTicket> rafflePrizes(Draw draw) throws DrawException;
}
