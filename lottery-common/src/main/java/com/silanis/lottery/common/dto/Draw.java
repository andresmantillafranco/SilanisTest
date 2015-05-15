package com.silanis.lottery.common.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silanis.lottery.common.constants.DrawStatusEnum;

/**
 * @author am
 * 
 *         Class that represents a draw of the lottery
 */
public class Draw implements DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Draw unique identifier
	 */
	private String id;

	/**
	 * Date of creation
	 */
	private Date creationDate;

	/**
	 * Date when the draw took the last status
	 */
	private Date lastStatusDate;

	/**
	 * Draw's status
	 */
	private DrawStatusEnum status;

	/**
	 * Configuration of the draw
	 */
	private DrawConfig config;

	/**
	 * Pot assigned to the draw
	 */
	private Pot pot;

	/**
	 * Tickets sold to the draw
	 */
	private Map<Integer, Ticket> soldTickets;

	/**
	 * Winners of the draw
	 */
	private List<WinnerTicket> winners;

	
	public Draw(String id, Date creationDate, Date lastStatusDate,
			DrawStatusEnum status, DrawConfig config, Pot pot) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.lastStatusDate = lastStatusDate;
		this.status = status;
		this.config = config;
		this.pot = pot;
		this.soldTickets = new HashMap<Integer, Ticket>();
	}

	/*
	 * Getters and setters
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastStatusDate() {
		return lastStatusDate;
	}

	public void setLastStatusDate(Date lastStatusDate) {
		this.lastStatusDate = lastStatusDate;
	}

	public DrawStatusEnum getStatus() {
		return status;
	}

	public void setStatus(DrawStatusEnum status) {
		this.status = status;
	}

	public DrawConfig getConfig() {
		return config;
	}

	public void setConfig(DrawConfig config) {
		this.config = config;
	}

	public Pot getPot() {
		return pot;
	}

	public void setPot(Pot pot) {
		this.pot = pot;
	}

	public Map<Integer, Ticket> getSoldTickets() {
		return soldTickets;
	}

	public void setSoldTickets(Map<Integer, Ticket> soldTickets) {
		this.soldTickets = soldTickets;
	}

	public List<WinnerTicket> getWinners() {
		return winners;
	}

	public void setWinners(List<WinnerTicket> winners) {
		this.winners = winners;
	}
}
