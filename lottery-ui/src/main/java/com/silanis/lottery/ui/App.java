package com.silanis.lottery.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.silanis.lottery.business.DrawManager;
import com.silanis.lottery.business.PotManager;
import com.silanis.lottery.business.SalesManager;
import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.Money;
import com.silanis.lottery.common.dto.Person;
import com.silanis.lottery.common.dto.Ticket;
import com.silanis.lottery.common.dto.WinnerTicket;
import com.silanis.lottery.common.exceptions.DrawException;

/**
 * @author am
 * 
 * Main class to manage UI
 */
@Component
public class App {
	@Autowired
	private PotManager potManager;

	@Autowired
	private DrawManager drawManager;

	@Autowired
	private SalesManager salesManager;

	/**
	 * Reference to current draw
	 */
	private Draw currentDraw;

	/**
	 * Constructor of the app
	 */
	public App() {
		//Reading spring context
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		AutowireCapableBeanFactory acbFactory = context
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);
	}
	
	/**
	 * Starts application defaults
	 * @param args
	 * @throws IOException
	 */
	private void start(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		PrintWriter writer = new PrintWriter(new PrintWriter(System.out), true);

		BigDecimal potAmount = BigDecimal.ZERO;
		if (args.length == 0) {
			System.err.println("--Launching without pot");
		} else {
			try {
				potAmount = new BigDecimal(Double.parseDouble(args[0]));
			} catch (NumberFormatException nfe) {
				writer.println("--Invalid argument");
				writer.println("--Launching without pot");
			}
		}

		Money potMoney = new Money(potAmount, "CAD");
		potManager.createPot(potMoney);

		showMenu(reader, writer);
	}

	/**
	 * Prints menu and read selections
	 * @param reader
	 * @param writer
	 * @throws IOException
	 */
	private void showMenu(BufferedReader reader, PrintWriter writer)
			throws IOException {
		writer.println("Select one of the next options:");
		writer.println("1 - Purchase");
		writer.println("2 - Draw");
		writer.println("3 - Winners");
		writer.println("4 - Exit");
		
		//Check selection
		try {
			int selection = Integer.parseInt(reader.readLine());
			writer.println("You select -> " + selection);
			switch (selection) {
			case 1:
				sellTicket(reader, writer);
				break;
			case 2:
				draw(reader, writer);
				break;
			case 3:
				showWinners(reader, writer);
				break;
			case 4:
				writer.println("Thank you!");
				System.exit(0);
				break;
			default:
				writer.println("Invalid selection!");
				break;
			}
		} catch (NumberFormatException nfe) {
			writer.println("Invalid selection!");
		}
		//Show the menu again
		showMenu(reader, writer);
	}

	/**
	 * Prints winners of the draw
	 * @param reader
	 * @param writer
	 */
	private void showWinners(BufferedReader reader, PrintWriter writer) {
		StringBuilder header = new StringBuilder();
		StringBuilder info = new StringBuilder();
		writer.println("------Winners for the last draw------");
		int counter = 1;
		for (WinnerTicket wt : currentDraw.getWinners()) {
			header.append("Ball ");
			header.append(counter);
			header.append("\t\t\t");
			info.append( (wt.getTicket() == null) ? "void" : wt.getTicket().getPerson().getFirstName() );
			info.append(" ");
			info.append(wt.getPrize().getCurrency());
			info.append(wt.getPrize().getAmount());
			info.append("\t\t");
			counter++;
		}
		writer.println(header);
		writer.println(info);
	}

	/**
	 * Draw the balls and show the winners
	 * @param reader
	 * @param writer
	 */
	private void draw(BufferedReader reader, PrintWriter writer) {
		try {
			drawManager.startDraw(currentDraw);
			writer.print("Starting the draw");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			writer.print("...\n");
			writer.print("Mixing the balls");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			writer.print("...\n");
			writer.print("And the winner is");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			writer.print("...\n");
			
			showWinners(reader, writer);
			printPotInformation(writer);
		} catch (DrawException e) {
			printDrawException(writer, e);
		}
	}

	/**
	 * Prints exception information
	 * @param writer
	 * @param e
	 */
	private void printDrawException(PrintWriter writer, DrawException e) {
		writer.println("-- Error -- " + e.getMessage());
	}

	/**
	 * Request selling information and read inputs
	 * @param reader
	 * @param writer
	 * @throws IOException
	 */
	private void sellTicket(BufferedReader reader, PrintWriter writer)
			throws IOException {
		currentDraw = drawManager.getCurrentDraw(true);
		Money price = salesManager.getTicketPrice(currentDraw);
		writer.println(String.format("The ticket price is: %s %.2f",
				price.getCurrency(), price.getAmount()));
		writer.println("Client first name: ");
		
		String firstName = reader.readLine();
		
		Person person = new Person(firstName);
		Ticket ticket;
		try {
			ticket = salesManager.saleTicket(currentDraw, person);
			printPotInformation(writer);
			writer.println(String.format("Your ticket number is: %d",
					ticket.getNumber()));
		} catch (DrawException e) {
			printDrawException(writer, e);
		}
	}

	/**
	 * Prints pot information
	 * @param writer
	 */
	private void printPotInformation(PrintWriter writer) {
		writer.println(String.format("The new pot is: %s %.2f", currentDraw
				.getPot().getBalance().getCurrency(), currentDraw.getPot()
				.getBalance().getAmount()));
	}

	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		new App().start(args);
	}
}
