package com.david.giczi.imagepuzzlegame.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.david.giczi.imagepuzzlegame.exceptions.InvalidInputValueException;
import com.david.giczi.imagepuzzlegame.listeners.ClickOnMosaicListener;
import com.david.giczi.imagepuzzlegame.listeners.MixMosaicsListener;
import com.david.giczi.imagepuzzlegame.listeners.ModifyImageListener;
import com.david.giczi.imagepuzzlegame.listeners.SecTimerListener;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;
import com.david.giczi.imagepuzzlegame.utils.Option;
import com.david.giczi.imagepuzzlegame.view.ImagePuzzleBoard;

public class ImagePuzzleGame {

	private ImageMosaic mosaic;
	private GameLogic logic;
	private ImagePuzzleBoard board;
	private Timer timerForSec = new Timer(1000, new SecTimerListener(this));
	private Timer timerForMixMosaics = new Timer(200, new MixMosaicsListener(this));
	private ClickOnMosaicListener clickOnMosaicListener = new ClickOnMosaicListener(this);
	private int secondCounter;
	private int clickOnMosaicCounter;

	public ImageMosaic getMosaic() {
		return mosaic;
	}

	public void setMosaic(ImageMosaic mosaic) {
		this.mosaic = mosaic;
	}

	public GameLogic getLogic() {
		return logic;
	}

	public ImagePuzzleBoard getBoard() {
		return board;
	}

	public Timer getTimerForSec() {
		return timerForSec;
	}

	public Timer getTimerForMixMosaics() {
		return timerForMixMosaics;
	}

	public int getSecondCounter() {
		return secondCounter;
	}

	public void setSecondCounter(int secondCounter) {
		this.secondCounter = secondCounter;
	}
	
	public int getClickOnMosaicCounter() {
		return clickOnMosaicCounter;
	}

	public void setClickOnMosaicCounter(int clickOnMosaicCounter) {
		this.clickOnMosaicCounter = clickOnMosaicCounter;
	}

	public ImagePuzzleGame(ImageMosaic mosaic) throws InvalidInputValueException {

		this.mosaic = mosaic;
		logic = new GameLogic();
		board = new ImagePuzzleBoard();
		
		launch();
	}

	public void launch() throws InvalidInputValueException {
		
		loadImageMosaics();
		board.createBoard(mosaic);
		mixImageMosaics();
		initCounters();
		
	}

	private void loadImageMosaics() {

		try {

			mosaic.collectImageMosaicFileName();
			mosaic.collectImageMosaic();
		} catch (FileNotFoundException | NoSuchElementException e) {

			board.infoWindow("Nem található \"" + mosaic.getImageFileName() + "\" nevû képfájl a "
					+ System.getProperty("user.dir") + "\\img mappában.");
			System.exit(-1);
		}

	}
	
	public void mixImageMosaics() throws InvalidInputValueException {
		
		logic.setBoardSideValue(mosaic.getBoardSize());
		logic.initGameBoard();
		logic.mixGameBoardNumber();

		while (logic.isTheEndOfTheGame()) {
			clearMixedBoardsStore();
			logic.mixGameBoardNumber();
		}
		timerForMixMosaics.start();
	}

	private void initCounters() {
		setSecondCounter(0);
		setClickOnMosaicCounter(0);
	}
	
	public void addListeners() {
		
		addMixOptionListener();
		AddSliderChangeListener();
		addSliderOkButtonListener();
		addAgainOptionListener();
		board.getNewGameOption().addActionListener(new ModifyImageListener(this, Option.NEW_GAME));
		board.getSmallerBoardOption().addActionListener(new ModifyImageListener(this, Option.SMALLER));
		board.getBiggerBoardOption().addActionListener(new ModifyImageListener(this, Option.BIGGER));
		addClickOnMosaicListener();
		addExitOptionListener();
		addNewImageListener();

	}
	
	private void addAgainOptionListener() {
		
		board.getAgainOption().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				timerForSec.stop();
				initCounters();
				logic.setBoard(logic.getSavedBoard());
				mixImageMosaicsListBasedOnLogicBoard(logic.getSavedBoard());
				displayMixedMosaics();
				removeClickOnMosaicListener();
				addClickOnMosaicListener();
				timerForSec.start();
			}
		});
		
	}
	
	private void addMixOptionListener() {
		
		board.getMixOption().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				board.adjustSlider();
				
				String disp = board.getSlider().getValue() == 0
		                ? String.valueOf(1) : String.valueOf(board.getSlider().getValue());

		        board.getSliderFrame().setTitle(String.valueOf(disp));
			}
		});
		
	}
	
	private void AddSliderChangeListener() {
		
		board.getSlider().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				

		         String disp = board.getSlider().getValue() == 0 ?
		                String.valueOf(1) : String.valueOf(board.getSlider().getValue());
		        
		         board.getSliderFrame().setTitle(disp);
				
				
			}
		});
	}
	
	
	private void addSliderOkButtonListener() {
		
		board.getSliderOkButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 logic.setNumberOfMix(board.getSlider().getValue());
				 board.getNewGameOption().doClick();
			     board.getSliderFrame().setVisible(false);
					
			}
		});
	}

	private void addExitOptionListener() {

		board.getExitOption().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (board.questionWindow("Szeretnél kilépni a játékból?")) {
					System.exit(0);
				}

			}
		});

	}

	private void addNewImageListener() {

		for (int imageNameIndex = 0; imageNameIndex < board.getImageNames().size(); imageNameIndex++) {
			board.getImageNames().get(imageNameIndex)
			.addActionListener(new ModifyImageListener(this, Option.NEW_IMAGE, imageNameIndex));
		}
	}
	
	private void addClickOnMosaicListener() {
		
		board.getImageMosaics().forEach(moasic -> moasic.addActionListener(clickOnMosaicListener));
		
	}
	
	public void removeClickOnMosaicListener() {
		
		board.getImageMosaics().forEach(moasic -> moasic.removeActionListener(clickOnMosaicListener));
	}
	
	public void mixImageMosaicsListBasedOnLogicBoard(List<Integer> logicBoard) {

		for (int i = 0; i < logicBoard.size(); i++) {

			for (int j = 0; j < mosaic.getImageMosaicStore().size(); j++) {

				if (logicBoard.get(i) == mosaic.getImageMosaicStore().get(j).getId() && i != j) {

					Collections.swap(mosaic.getImageMosaicStore(), i, j);

				}

			}

		}
	}
	
	public void displayMixedMosaics() {

		board.getImageMosaics().forEach(element -> element.setVisible(true));

		for (int i = 0; i < board.getImageMosaics().size(); i++) {

			board.getImageMosaics().get(i).setIcon(mosaic.getImageMosaicStore().get(i).getImage());

			if (mosaic.getImageMosaicStore().get(i).getId() == 0) {

				board.getImageMosaics().get(i).setVisible(false);
			}

		}

	}

	public void stopTimers() {
		timerForSec.stop();
		timerForMixMosaics.stop();
	}
	
	public void clearMixedBoardsStore() {

		NumberSquare.clearBoardStore();
	}
	

	public void getFrameTitle() {
		board.getFrame().setTitle(mosaic.getBoardSize() == BoardSize.SMALL
				? mosaic.getImageFileName() + " - " + formatSecondCounterValue()
				: "ImagePuzzle - " + mosaic.getImageFileName() + " - " + formatSecondCounterValue());
	}
	
	public String formatSecondCounterValue() {

		int sec = secondCounter % 60;
		int min = secondCounter / 60;

		return sec < 10 ? min + ":0" + sec : min + ":" + sec;
	}
	
}
