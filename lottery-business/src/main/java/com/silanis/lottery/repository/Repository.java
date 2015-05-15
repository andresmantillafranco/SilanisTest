package com.silanis.lottery.repository;

import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.Pot;

/**
 * @author am
 * Class that provides persistence to the main entities
 */
public class Repository {
	/**
	 * Main pot
	 */
	private static Pot pot;

	/**
	 * Current draw
	 */
	private static Draw draw;

	/*
	 * Getters and setters
	 */
	public static Pot getPot() {
		return pot;
	}

	public static void setPot(Pot pot) {
		Repository.pot = pot;
	}

	public static Draw getDraw() {
		return draw;
	}

	public static void setDraw(Draw draw) {
		Repository.draw = draw;
	}
}
