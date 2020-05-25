package com.david.giczi.imagepuzzlegame.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;
import com.david.giczi.imagepuzzlegame.model.ImageMosaic;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;
import com.david.giczi.imagepuzzlegame.view.ImagePuzzleBoard;

class ImagePuzzleBoardClassTest {

	@Test
	public void testCreateImagePuzzleBoardObject() {
		assertNotNull(new ImagePuzzleBoard());
	}

	@Test
	public void testCreateFrame() throws FileNotFoundException {
		ImagePuzzleBoard board = new ImagePuzzleBoard();
		ImageMosaic mosaic = new ImageMosaic("Anna&Dave", BoardSize.SMALL);
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		board.createBoard(mosaic);

		assertEquals("Anna&Dave - 0:00", board.getFrame().getTitle());
		assertEquals(JFrame.EXIT_ON_CLOSE, board.getFrame().getDefaultCloseOperation());
		assertFalse(board.getFrame().isResizable());
		assertEquals(
				new Dimension(BoardSize.getBoardSizeValueByName(mosaic.getBoardSize()) * 100 + 10,
						BoardSize.getBoardSizeValueByName(mosaic.getBoardSize()) * 100 + 50),
				board.getFrame().getSize());

	}

	@Test
	public void testAddMenuToframe() throws FileNotFoundException {
		ImagePuzzleBoard board = new ImagePuzzleBoard();
		ImageMosaic mosaic = new ImageMosaic("Anna&Dave", BoardSize.SMALL);
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		board.createBoard(mosaic);

		assertNotNull(board.getMixOption());
		assertEquals("Keverés", board.getMixOption().getText());
		assertNotNull(board.getAgainOption());
		assertEquals("Újra", board.getAgainOption().getText());
		assertNotNull(board.getNewGameOption());
		assertEquals("Új játék", board.getNewGameOption().getText());
		assertNotNull(board.getSmallerBoardOption());
		assertEquals("Kisebbre", board.getSmallerBoardOption().getText());
		assertNotNull(board.getBiggerBoardOption());
		assertEquals("Nagyobbra", board.getBiggerBoardOption().getText());
		assertNotNull(board.getExitOption());
		assertEquals("Kilépés", board.getExitOption().getText());
		assertNotNull(board.getImageNames());
		assertEquals("Anna&Dave", board.getImageNames().get(0).getText());
		assertEquals("Papucskavalkád", board.getImageNames().get(1).getText());
	}

	@Test
	public void testAddImageMosaicsToFrame() throws FileNotFoundException {
		ImagePuzzleBoard board = new ImagePuzzleBoard();
		ImageMosaic mosaic = new ImageMosaic("Anna&Dave", BoardSize.SMALL);
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		board.createBoard(mosaic);

		board.getImageMosaics().forEach(element -> assertNotNull(element));
		board.getImageMosaics().forEach(element -> assertNotNull(element.getIcon()));
		assertFalse(board.getImageMosaics().get(board.getImageMosaics().size() - 1).isVisible());

	}

	@Test
	public void testDestroyFrame() throws FileNotFoundException {
		ImagePuzzleBoard board = new ImagePuzzleBoard();
		ImageMosaic mosaic = new ImageMosaic("Anna&Dave", BoardSize.SMALL);
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		board.createBoard(mosaic);
		board.destroyFrame();

		assertNull(board.getFrame());

	}

	@Test
	public void testModifyInvisibleImageMosaicToVisible() throws FileNotFoundException {
		
		ImagePuzzleBoard board = new ImagePuzzleBoard();
		ImageMosaic mosaic = new ImageMosaic("Papucskavalkád", BoardSize.MEDIUM);
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		board.createBoard(mosaic);
		board.modifyInvisibleImageMosaicToVisible(mosaic);
		
		board.getImageMosaics().forEach(img -> assertTrue(img.isVisible()));
		board.getImageMosaics().forEach(img -> assertNotNull(img.getIcon()));
		
	}
	
	@Test
	public void getImageMosaicIndex() throws FileNotFoundException {
		
		ImagePuzzleBoard board = new ImagePuzzleBoard();
		ImageMosaic mosaic = new ImageMosaic("Anna&Dave", BoardSize.SMALL);
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		board.createBoard(mosaic);
		
		for(int i = 0; i < board.getImageMosaics().size(); i++) {
			assertEquals(i, board.getClickedImageMosaicIndex(board.getImageMosaics().get(i)));
		}
		
	}
	
	
	@Test
    public void testAdjustSlider() {

		ImagePuzzleBoard board = new ImagePuzzleBoard();
		
        board.adjustSlider();

        assertNotNull(board.getSlider());
        assertNotNull(board.getSliderFrame());
        assertNotNull(board.getSliderOkButton());
        assertEquals("Ok", board.getSliderOkButton().getText());
        assertEquals(1, board.getSlider().getMinorTickSpacing());
        assertEquals(50, board.getSlider().getMajorTickSpacing());
        assertTrue(board.getSlider().getPaintTicks());
        assertTrue(board.getSlider().getPaintLabels());
        assertEquals(JFrame.HIDE_ON_CLOSE, board.getSliderFrame().getDefaultCloseOperation());
        assertFalse(board.getSliderFrame().isResizable());
        assertEquals(new Dimension(250, 115), board.getSliderFrame().getSize());
        assertTrue(board.getSliderFrame().isVisible());
    }
	
	@Test
	public void testSwapImageMosaicOnTheBoard() throws FileNotFoundException {
		
		ImagePuzzleBoard board = new ImagePuzzleBoard();
		ImageMosaic mosaic = new ImageMosaic("Papucskavalkád", BoardSize.LARGE);
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		board.createBoard(mosaic);
		
		assertTrue(board.getImageMosaics().get(0).isVisible());
		assertFalse(board.getImageMosaics().get(24).isVisible());
		ImageIcon icon = (ImageIcon) board.getImageMosaics().get(0).getIcon();
		
		board.swapImageMosaicsOnTheBoard(0, 24);
		
		assertTrue(board.getImageMosaics().get(24).isVisible());
		assertFalse(board.getImageMosaics().get(0).isVisible());
		assertEquals(icon, board.getImageMosaics().get(24).getIcon());
	}
	
}
