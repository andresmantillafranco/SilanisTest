package com.silanis.lottery.common.dto;

/**
 * @author am
 * 
 *         Class that represents a person
 */
public class Person implements DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * First name of the person
	 */
	private String firstName;

	
	/**
	 * @param firstName
	 */
	public Person(String firstName) {
		super();
		this.firstName = firstName;
	}

	/*
	 * Getters and setters
	 */

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
