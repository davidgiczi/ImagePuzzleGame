package com.david.giczi.imagepuzzlegame.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.david.giczi.imagepuzzlegame.exceptions.InvalidInputValueException;
import com.david.giczi.imagepuzzlegame.model.ImageMosaic;
import com.david.giczi.imagepuzzlegame.model.ImagePuzzleGame;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;

class ImagePuzzleGameClassTest {

	
	@Test
	public void testCreateObject() throws InvalidInputValueException  {
		
		assertNotNull(new ImagePuzzleGame(new ImageMosaic("Papucskavalkád", BoardSize.SMALL)));
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testConstructor() throws InvalidInputValueException {
		
		ImagePuzzleGame game = new ImagePuzzleGame(new ImageMosaic("Papucskavalkád", BoardSize.LARGE));
		
		assertNotNull(game.getMosaic());
		assertNotNull(game.getLogic());
		assertNotNull(game.getBoard());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
