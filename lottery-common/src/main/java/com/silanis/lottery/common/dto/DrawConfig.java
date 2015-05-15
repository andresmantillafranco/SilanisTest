package com.silanis.lottery.common.dto;

import com.silanis.lottery.common.numeration.BallNumerator;
import com.silanis.lottery.common.raffle.Raffler;

/**
 * @author am
 * 
 *         Class that represents the configuration that a draw can take
 */
public class DrawConfig implements DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Number of tickets to sell
	 */
	private Integer numberOfTickets;

	/**
	 * Price of each ticket
	 */
	private Money ticketPrice;

	/**
	 * Percentage of the pot that the draw could distribute
	 */
	private Float potPercentage;

	/**
	 * Class that defines the logic to distribute the prize
	 */
	private Raffler raffler;

	/**
	 * Class that defines how to associate the ticket with the balls
	 */
	private BallNumerator ballNumerator;

	/*
	 * Getters and setters
	 */

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public Money getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Money ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Float getPotPercentage() {
		return potPercentage;
	}

	public void setPotPercentage(Float potPercentage) {
		this.potPercentage = potPercentage;
	}

	public Raffler getRaffler() {
		return raffler;
	}

	public void setRaffler(Raffler raffler) {
		this.raffler = raffler;
	}

	public BallNumerator getBallNumerator() {
		return ballNumerator;
	}

	public void setBallNumerator(BallNumerator ballNumerator) {
		this.ballNumerator = ballNumerator;
	}
}
