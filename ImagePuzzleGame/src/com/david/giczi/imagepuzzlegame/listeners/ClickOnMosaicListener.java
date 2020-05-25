package com.david.giczi.imagepuzzlegame.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import com.david.giczi.imagepuzzlegame.model.ImagePuzzleGame;
import com.david.giczi.imagepuzzlegame.model.NumberSquare;


public class ClickOnMosaicListener implements ActionListener {

	private final ImagePuzzleGame game;

	public ClickOnMosaicListener(ImagePuzzleGame game) {

		this.game = game;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		countClick();
		game.getFrameTitle();
		int clickedMosaicIndex = game.getBoard().getClickedImageMosaicIndex((JButton)e.getSource());
		int resultIndex = game.getLogic().evaluateWhereThePlayerCanStep(clickedMosaicIndex);
		if(isInvalidClickedGameField(resultIndex)) {
			return;
		}
		
		game.getBoard().swapImageMosaicsOnTheBoard(clickedMosaicIndex, resultIndex);
		closeOrContinueTheGame();
	}

	private void countClick() {

		int clickPcs = game.getClickOnMosaicCounter();
		clickPcs++;
		game.setClickOnMosaicCounter(clickPcs);

	}

	 private boolean isInvalidClickedGameField(int clickedFieldIndex) {

	        if (clickedFieldIndex == -1) {

	            game.getBoard().infoWindow("Nem léptethetõ mezõ.");
	            return true;
	        }
	        return false;
	    }
	 
	 
	 private void closeOrContinueTheGame() {

	        if (game.getLogic().isTheEndOfTheGame()) {

	        	game.getBoard().modifyInvisibleImageMosaicToVisible(game.getMosaic());
	            game.getTimerForSec().stop();
	            game.getBoard().infoWindow("Az idõeredményed: "
	            + game.formatSecondCounterValue().split(":")[0]+" perc "
	            + game.formatSecondCounterValue().split(":")[1]+" mperc\n"
	            + "Maximálisan szükséges kattintások száma: "+NumberSquare.getNeedfulClickCounter()+" db\n"
	            + "Kattintásaid száma: " + game.getClickOnMosaicCounter() + " db\n\n" + getJudgedResultText());

	            if (game.getBoard().questionWindow("Szeretnél új játékot játszani?")) {

	                game.getBoard().getNewGameOption().doClick();
	            } else {

	                if (game.getBoard().questionWindow("Szeretnél kilépni a játékból?")) {

	                   System.exit(0);
	                    
	                } else {

	                 game.removeClickOnMosaicListener();
	                }

	            }

	        }

	    }
	
	private String getJudgedResultText() {

		String msg = (game.getClickOnMosaicCounter() <= NumberSquare.getNeedfulClickCounter()) ? "Gratulálunk!".toUpperCase()
				: "Sebaj, legközelebb!";

		return msg;
	}

}
