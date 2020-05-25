package com.david.giczi.imagepuzzlegame.app;

import com.david.giczi.imagepuzzlegame.exceptions.InvalidInputValueException;
import com.david.giczi.imagepuzzlegame.model.ImageMosaic;
import com.david.giczi.imagepuzzlegame.model.ImagePuzzleGame;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;

public class ImagePuzzleGameApp {
	
	public static void main(String[] args) throws InvalidInputValueException {
		
		
		new ImagePuzzleGame(new ImageMosaic("Anna&Dave", BoardSize.MEDIUM));
	}

}
