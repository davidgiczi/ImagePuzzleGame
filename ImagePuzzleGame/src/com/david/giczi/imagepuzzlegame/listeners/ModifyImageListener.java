package com.david.giczi.imagepuzzlegame.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.david.giczi.imagepuzzlegame.exceptions.InvalidInputValueException;
import com.david.giczi.imagepuzzlegame.model.ImageMosaic;
import com.david.giczi.imagepuzzlegame.model.ImagePuzzleGame;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;
import com.david.giczi.imagepuzzlegame.utils.Option;

public class ModifyImageListener implements ActionListener {

	private final ImagePuzzleGame game;
	private int imageNameIndex = -1;
	private final Option option;

	public ModifyImageListener(ImagePuzzleGame game, Option option) {
		this.game = game;
		this.option = option;
	}

	public ModifyImageListener(ImagePuzzleGame game, Option option, int imageNameIndex) {
		this.game = game;
		this.option = option;
		this.imageNameIndex = imageNameIndex;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		game.getBoard().destroyFrame();
		game.stopTimers();
		game.clearMixedBoardsStore();

		if (option == Option.NEW_IMAGE) {

			createNewImageMosaicWithNewImage();

		} else {

			createNewSizeImageMosaic();
		}

		try {
			game.launch();
		} catch (InvalidInputValueException e1) {

			e1.printStackTrace();
		}

	}

	private void createNewSizeImageMosaic() {

		game.setMosaic(new ImageMosaic(game.getMosaic().getImageFileName(), getNewBoardSize()));
	}

	private void createNewImageMosaicWithNewImage() {

		game.setMosaic(new ImageMosaic(game.getMosaic().getImageNameStore().get(imageNameIndex),
				game.getMosaic().getBoardSize()));

	}

	private BoardSize getNewBoardSize() {

		BoardSize boardSize = game.getMosaic().getBoardSize();

		if (option == Option.NEW_GAME) {

			return boardSize;
		} else if (option == Option.BIGGER && boardSize == BoardSize.SMALL) {

			return BoardSize.MEDIUM;
		} else if (option == Option.BIGGER && boardSize == BoardSize.MEDIUM) {

			return BoardSize.LARGE;
		} else if (option == Option.SMALLER && boardSize == BoardSize.LARGE) {

			return BoardSize.MEDIUM;
		} else if (option == Option.SMALLER && boardSize == BoardSize.MEDIUM) {

			return BoardSize.SMALL;
		} else

			return boardSize;
	}

}
