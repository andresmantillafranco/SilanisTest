package com.silanis.lottery.common.numeration;

import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.exceptions.DrawException;

/**
 * @author am
 * Interface that defines the abstract behavior of an ball numerator
 */
public interface BallNumerator {

	/**
	 * Gets the next available ball
	 * @param draw
	 * @return
	 * @throws DrawException
	 */
	public Integer getNextNumber(Draw draw) throws DrawException;
}
