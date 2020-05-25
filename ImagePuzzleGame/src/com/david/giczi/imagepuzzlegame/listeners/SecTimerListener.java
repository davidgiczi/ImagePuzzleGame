package com.david.giczi.imagepuzzlegame.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.david.giczi.imagepuzzlegame.model.ImagePuzzleGame;

public class SecTimerListener implements ActionListener {

	
	private final ImagePuzzleGame game;
	

	public SecTimerListener(ImagePuzzleGame game) {
		this.game = game;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		game.getFrameTitle();
		int secondCounter = game.getSecondCounter();
		game.setSecondCounter(++secondCounter);
		
	}

}
