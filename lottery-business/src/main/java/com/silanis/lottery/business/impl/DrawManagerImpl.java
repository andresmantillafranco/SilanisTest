package com.silanis.lottery.business.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.silanis.lottery.business.DrawManager;
import com.silanis.lottery.business.PotManager;
import com.silanis.lottery.common.constants.DrawStatusEnum;
import com.silanis.lottery.common.dto.Draw;
import com.silanis.lottery.common.dto.DrawConfig;
import com.silanis.lottery.common.dto.WinnerTicket;
import com.silanis.lottery.common.exceptions.DrawException;
import com.silanis.lottery.common.exceptions.PotException;
import com.silanis.lottery.repository.Repository;

/**
 * @author am
 * Class that implements the operations related with a draw
 */
@Component
public class DrawManagerImpl implements DrawManager {
	
	/**
	 * Reference to the pot manager
	 */
	@Autowired
	private PotManager potManager;
	
	/* (non-Javadoc)
	 * @see com.silanis.lottery.business.DrawManager#getCurrentDraw(boolean)
	 */
	public Draw getCurrentDraw(boolean createIfNotExist) {
		Draw draw = Repository.getDraw();

		//If the draw not exist
		if ( draw == null && createIfNotExist ) {
			draw = createDraw();
		} else if ( draw.getStatus().equals(DrawStatusEnum.FINISHED) ) { //If the draw has finished
			draw = createDraw();
		}
		return draw;
	}

	/* (non-Javadoc)
	 * @see com.silanis.lottery.business.DrawManager#createDraw()
	 */
	public Draw createDraw() {
		Calendar today = GregorianCalendar.getInstance();
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		DrawConfig config = (DrawConfig) context.getBean("drawConfig");
		Draw draw = new Draw(UUID.randomUUID().toString(), today.getTime(), today.getTime(), DrawStatusEnum.CREATED, config, Repository.getPot());

		Repository.setDraw(draw);
		return draw;
	}

	/* (non-Javadoc)
	 * @see com.silanis.lottery.business.DrawManager#startDraw(com.silanis.lottery.common.dto.Draw)
	 */
	public Draw startDraw(Draw draw) throws DrawException {
		if ( draw == null ) {
			throw new DrawException("Not tickets sold");
		}
		
		//If the status is different to created
		if ( ! draw.getStatus().equals(DrawStatusEnum.CREATED) ) {
			throw new DrawException("Draw is finished. Sell tickets before draw again.");
		}

		draw.setStatus(DrawStatusEnum.STARTED);
		Repository.setDraw(draw);
		DrawConfig config = draw.getConfig();
		
		//Make the raffle
		List<WinnerTicket> winners = config.getRaffler().rafflePrizes(draw);
		draw.setWinners(winners);
		
		for ( WinnerTicket winner : winners ) {
			//No winner associated with the ball
			if ( winner.getTicket() == null ) {
				continue;
			}
			
			try {
				potManager.debitPot(Repository.getPot(), winner.getPrize());
			} catch (PotException e) {
				throw new DrawException("Error getting money from the pot");
			}
		}
		
		draw.setStatus(DrawStatusEnum.FINISHED);
		return draw;
	}
}
