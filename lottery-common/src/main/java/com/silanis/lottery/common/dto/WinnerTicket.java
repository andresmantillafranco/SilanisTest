package com.silanis.lottery.common.dto;

/**
 * @author am
 * 
 *         Class that represent a winner lottery ticket
 */
public class WinnerTicket implements DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Original ticket
	 */
	private Ticket ticket;

	/**
	 * Prize won
	 */
	private Money prize;

	/**
	 * @param ticket
	 * @param prize
	 */
	public WinnerTicket(Ticket ticket, Money prize) {
		super();
		this.ticket = ticket;
		this.prize = prize;
	}

	/*
	 * Getters and setters
	 */
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Money getPrize() {
		return prize;
	}

	public void setPrize(Money prize) {
		this.prize = prize;
	}
}
