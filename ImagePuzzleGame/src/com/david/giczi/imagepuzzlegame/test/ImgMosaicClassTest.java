package com.david.giczi.imagepuzzlegame.test;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.ImageIcon;
import org.junit.jupiter.api.Test;
import com.david.giczi.imagepuzzlegame.model.ImgMosaic;

class ImgMosaicClassTest {

	
	@Test
	public void testCreateObject() {
		assertNotNull(new ImgMosaic(new ImageIcon(), 100));
	}

	@Test
	public void testOjectImageIconVariable() {
		
		ImageIcon icon = new ImageIcon();
		ImgMosaic img = new ImgMosaic(icon, 10);
		
		assertEquals(icon, img.getImage());
	}
	
	@Test
	public void testOjectIdVariable() {
		
		ImageIcon icon = new ImageIcon();
		ImgMosaic img = new ImgMosaic(icon, 123456);
		
		assertEquals(123456, img.getId());
	}
	
	
}
