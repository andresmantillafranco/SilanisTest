package com.silanis.lottery.common.dto;

/**
 * @author am
 * 
 *         Class that represent a lottery ticket
 */
public class Ticket implements DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Owner of the ticket
	 */
	private Person person;

	/**
	 * Number of the ball
	 */
	private Integer number;

	/**
	 * @param person
	 * @param number
	 */
	public Ticket(Person person, Integer number) {
		super();
		this.person = person;
		this.number = number;
	}

	/*
	 * Getters and setters
	 */
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
