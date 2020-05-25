package com.david.giczi.imagepuzzlegame.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import com.david.giczi.imagepuzzlegame.model.ImageMosaic;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;





class ImageMosaicClassTest {

	
	@Test
	public void testCollectImageFileName() throws FileNotFoundException {
		
	ImageMosaic mosaic = new ImageMosaic("", null);
	
	mosaic.collectImageMosaicFileName();
		
    assertEquals(System.getProperty("user.dir")+"/img", mosaic.getPath());
    assertEquals("Anna&Dave", mosaic.getImageNameStore().get(0));
		
	}
	
	@Test
	public void testCollectImageMosaic() throws FileNotFoundException {
		
		ImageMosaic mosaic = new ImageMosaic("Anna&Dave", BoardSize.MEDIUM);
		
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		
		for(int i = 0; i < mosaic.getImageNameStore().size(); i++) {
			
			assertNotNull(mosaic.getImageMosaicStore().get(i));
		}
		
		assertEquals(16, mosaic.getImageMosaicStore().size());	
	}
	
	@Test
	public void testImgIdInImageMosaicStore() throws FileNotFoundException {
		
		ImageMosaic mosaic = new ImageMosaic("Anna&Dave", BoardSize.LARGE);
		
		mosaic.collectImageMosaicFileName();
		mosaic.collectImageMosaic();
		
		int i;
		
		for(i = 1 ; i < mosaic.getImageMosaicStore().size(); i++) {
			
		assertEquals(i , mosaic.getImageMosaicStore().get(i-1).getId());	
		
		}
		
		assertEquals(0, mosaic.getImageMosaicStore().get(i-1).getId());	
	}

}
