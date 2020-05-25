package com.david.giczi.imagepuzzlegame.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.david.giczi.imagepuzzlegame.model.ImagePuzzleGame;
import com.david.giczi.imagepuzzlegame.model.NumberSquare;

public class MixMosaicsListener implements ActionListener {

	private final ImagePuzzleGame game;
	private int numberBoardCounter;

	public MixMosaicsListener(ImagePuzzleGame game) {

		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		game.mixImageMosaicsListBasedOnLogicBoard(NumberSquare.getNumberBoardStore().get(numberBoardCounter++));
		game.displayMixedMosaics();

		if (numberBoardCounter == NumberSquare.getNumberBoardStore().size()) {

			game.clearMixedBoardsStore();
			game.getTimerForMixMosaics().stop();
			game.getTimerForSec().start();
			game.addListeners();
			numberBoardCounter = 0;
		}

	}

	

}

	


