package com.david.giczi.imagepuzzlegame.model;

import javax.swing.ImageIcon;

public class ImgMosaic {

	
	private final ImageIcon image;
	private final int id;
	
	
	public ImgMosaic(ImageIcon image, int id) {
		
		this.image = image;
		this.id = id;
	}


	public ImageIcon getImage() {
		return image;
	}


	public int getId() {
		return id;
	}
	
	
	
}
