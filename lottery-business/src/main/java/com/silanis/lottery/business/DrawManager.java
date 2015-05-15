package com.silanis.lottery.business;

import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.exceptions.DrawException;

/**
 * @author am
 *
 *	Interface that defines operations related with a draw
 */
public interface DrawManager {
	/**
	 * Allows get the current draw information. It creates a draw if not exists
	 * @param createIfNotExist
	 * @return
	 */
	public Draw getCurrentDraw(boolean createIfNotExist);

	/**
	 * Creates a draw
	 * @return
	 */
	public Draw createDraw();
	
	/**
	 * Start a draw
	 * @param draw
	 * @return
	 * @throws DrawException
	 */
	public Draw startDraw(Draw draw) throws DrawException;
}
