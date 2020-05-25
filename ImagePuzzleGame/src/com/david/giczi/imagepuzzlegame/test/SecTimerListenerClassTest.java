package com.david.giczi.imagepuzzlegame.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.david.giczi.imagepuzzlegame.exceptions.InvalidInputValueException;
import com.david.giczi.imagepuzzlegame.model.ImageMosaic;
import com.david.giczi.imagepuzzlegame.model.ImagePuzzleGame;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;


class SecTimerListenerClassTest {

	@Test
	public void testSecTimer() throws InvalidInputValueException {
		
		ImageMosaic mosaic = new ImageMosaic("Papucskavalk�d", BoardSize.LARGE);
		ImagePuzzleGame game = new ImagePuzzleGame(mosaic);
		assertEquals(0, game.getSecondCounter());

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals("ImagePuzzle - Papucskavalk�d - 0:03", game.getBoard().getFrame().getTitle());
		assertEquals(4, game.getSecondCounter());
		
	}

}
