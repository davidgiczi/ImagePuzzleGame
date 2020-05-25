package com.david.giczi.imagepuzzlegame.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;


public class ImageMosaic {

	private List<ImgMosaic> imageMosaicStore;
	private final String imageFileName;
	private final BoardSize boardSize;
	private String path = System.getProperty("user.dir");
	private List<String> imageNameStore;
	
	
	public ImageMosaic(String imageFileName, BoardSize boardSize) {

		this.imageFileName = imageFileName;
		this.boardSize = boardSize;

	}

	
	public List<ImgMosaic> getImageMosaicStore() {
		return imageMosaicStore;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public BoardSize getBoardSize() {
		return boardSize;
	}

	public String getPath() {
		return path;
	}

	public List<String> getImageNameStore() {
		return imageNameStore;
	}

	public void collectImageMosaicFileName() throws FileNotFoundException {

		path += "/img";
		File file = new File(path);

		if (file.exists()) {

			imageNameStore = Arrays.asList(file.list());

		} else {

			throw new FileNotFoundException("\'" + path + "\' folder doesn't exist.");
		}

	}

	public void collectImageMosaic() throws FileNotFoundException {

		createPath();

		File file = new File(path);
		
		if (file.exists()) {

			imageMosaicStore = new ArrayList<>();

			for (int i = 1; i <= getMosaicIndex(); i++) {
				
				ImageIcon icon = new ImageIcon(path + addMosaicSizeNameForPath()+ i +".jpg");
				
				if( i < getMosaicIndex() ) {
				imageMosaicStore.add(new ImgMosaic(icon, i));	
				}
				else {	
				imageMosaicStore.add(new ImgMosaic(icon, 0));		
				}
			}			

		} else {

			throw new FileNotFoundException("\'" + path + "\' folder doesn't exist.");
		}

	}

	private void createPath() {
		
		path += "/" + imageNameStore.stream().filter(name -> name.equals(imageFileName)).findAny().get();
		
		path += addMosaicSizeNameForPath();
	}
	
	private String addMosaicSizeNameForPath() {

		switch (boardSize) {

		case SMALL:
			return "/S";	
		case MEDIUM:
			return "/M";
		case LARGE:
			return "/L";
		default:
			return "/";
		}

	}

	public int getMosaicIndex() {
	
	return BoardSize.getBoardSizeValueByName(boardSize) 
			* BoardSize.getBoardSizeValueByName(boardSize);
		
	}

}
