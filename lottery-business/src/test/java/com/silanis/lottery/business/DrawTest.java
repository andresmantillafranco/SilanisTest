package com.silanis.lottery.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import junit.framework.TestCase;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.silanis.lottery.business.impl.PotManagerImpl;
import com.silanis.lottery.business.numeration.RandomBallNumerator;
import com.silanis.lottery.business.rafflers.BasicRaffler;
import com.silanis.lottery.common.constants.DrawStatusEnum;
import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.DrawConfig;
import com.silanis.lottery.common.dto.Money;
import com.silanis.lottery.common.dto.Person;
import com.silanis.lottery.common.dto.Pot;
import com.silanis.lottery.common.dto.Ticket;
import com.silanis.lottery.common.dto.WinnerTicket;
import com.silanis.lottery.common.exceptions.DrawException;
import com.silanis.lottery.repository.Repository;

public class DrawTest extends TestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// Reading spring context
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		AutowireCapableBeanFactory acbFactory = context
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);
	}

	/**
	 * Checks that the number generator works
	 */
	public void testRandomBallNumerator() {
		Calendar today = GregorianCalendar.getInstance();
		DrawConfig config = new DrawConfig();
		config.setNumberOfTickets(50);
		config.setPotPercentage(50f);
		config.setTicketPrice(new Money(new BigDecimal(10), "CAD"));
		Draw draw = new Draw(UUID.randomUUID().toString(), today.getTime(),
				today.getTime(), DrawStatusEnum.CREATED, config,
				Repository.getPot());

		RandomBallNumerator rbn = new RandomBallNumerator();
		try {
			int number = rbn.getNextNumber(draw);
			System.out.println(number);
			assertTrue(number > 0 && number <= 50);
		} catch (DrawException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks that the number generator doesn't repeat any number
	 */
	public void testDifferentNumbers() {
		Calendar today = GregorianCalendar.getInstance();
		DrawConfig config = new DrawConfig();
		config.setNumberOfTickets(1000);
		config.setPotPercentage(50f);
		config.setTicketPrice(new Money(new BigDecimal(10), "CAD"));
		Draw draw = new Draw(UUID.randomUUID().toString(), today.getTime(),
				today.getTime(), DrawStatusEnum.CREATED, config,
				Repository.getPot());

		RandomBallNumerator rbn = new RandomBallNumerator();
		try {
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			for (int i = 0; i < 1000; i++) {
				int number = rbn.getNextNumber(draw);
				System.out.println(number);
				assertTrue(number > 0 && number <= 1000);
				assertTrue(!numbers.contains(number));
				Person person = new Person("John");
				Ticket ticket = new Ticket(person, number);

				// Save the ticket
				draw.getSoldTickets().put(number, ticket);
				numbers.add(number);
			}
		} catch (DrawException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Checks that the basic raffler works properly
	 */
	public void testBasicRaffler() {
		Pot pot = new PotManagerImpl().createPot(new Money(new BigDecimal(500),
				"CAD"));
		Repository.setPot(pot);
		Calendar today = GregorianCalendar.getInstance();
		DrawConfig config = new DrawConfig();
		config.setNumberOfTickets(50);
		config.setPotPercentage(50f);
		config.setTicketPrice(new Money(new BigDecimal(10), "CAD"));
		Draw draw = new Draw(UUID.randomUUID().toString(), today.getTime(),
				today.getTime(), DrawStatusEnum.CREATED, config,
				Repository.getPot());
		
		RandomBallNumerator rbn = new RandomBallNumerator();
		try {
			for (int i = 0; i < 50; i++) {
				int number = rbn.getNextNumber(draw);
				Person person = new Person("John");
				Ticket ticket = new Ticket(person, number);

				// Save the ticket
				draw.getSoldTickets().put(number, ticket);
			}
			BasicRaffler raffler = new BasicRaffler();
			List<WinnerTicket> winners = raffler.rafflePrizes(draw);
			assertEquals(3, winners.size());
			
			BigDecimal sum = BigDecimal.ZERO; 
			for ( WinnerTicket winner : winners ) {
				sum = sum.add(winner.getPrize().getAmount());
			}
			assertTrue(sum.compareTo(new BigDecimal(250)) <= 0 );
		} catch (DrawException e) {
			fail(e.getMessage());
		}
	}
}
