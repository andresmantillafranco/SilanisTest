package com.silanis.lottery.business.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author am
 *
 *	Utility class to manage some basic calculations
 */
public class CalculationUtils {

	/**
	 * Calculates a percentage
	 * 
	 * @param number
	 * @param percentage
	 * @return
	 */
	public static BigDecimal getPercentageValue(BigDecimal number,
			Float percentage) {
		return number.multiply(new BigDecimal(percentage)).divide(
				new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
	}

	/**
	 * Rounds downs a number to the nearest
	 * 
	 * @param number
	 * @return
	 */
	public static BigDecimal round(BigDecimal number) {
		return number.setScale(0, RoundingMode.FLOOR);
	}
}
