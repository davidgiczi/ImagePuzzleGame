package com.david.giczi.imagepuzzlegame.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import com.david.giczi.imagepuzzlegame.model.ImageMosaic;
import com.david.giczi.imagepuzzlegame.model.ImgMosaic;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;

public class ImagePuzzleBoard {

	private JFrame frame;
	private List<JButton> imageMosaics;
	private List<JMenuItem> imageNames;
	private JMenuItem mixOption;
	private JMenuItem againOption;
	private JMenuItem newGameOption;
	private JMenuItem smallerBoardOption;
	private JMenuItem biggerBoardOption;
	private JMenuItem exitOption;
	private JFrame sliderFrame;
	private JSlider slider;
	private JButton sliderOkButton;

	public JFrame getFrame() {
		return frame;
	}

	public List<JButton> getImageMosaics() {
		return imageMosaics;
	}

	public List<JMenuItem> getImageNames() {
		return imageNames;
	}

	public JMenuItem getMixOption() {
		return mixOption;
	}

	public JMenuItem getAgainOption() {
		return againOption;
	}

	public JMenuItem getNewGameOption() {
		return newGameOption;
	}

	public JMenuItem getSmallerBoardOption() {
		return smallerBoardOption;
	}

	public JMenuItem getBiggerBoardOption() {
		return biggerBoardOption;
	}

	public JMenuItem getExitOption() {
		return exitOption;
	}
		
	public JFrame getSliderFrame() {
		return sliderFrame;
	}

	public JSlider getSlider() {
		return slider;
	}

	public JButton getSliderOkButton() {
		return sliderOkButton;
	}

	public ImagePuzzleBoard() {
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sliderOkButton = new JButton("Ok");
	}
	
	public void createBoard(ImageMosaic mosaic) {
		createBoardFrame(mosaic);
		addMenuToFrame(mosaic);
		addImageMosaicsToFrame(mosaic);
		showBoard(true);
	}

	private void createBoardFrame(ImageMosaic mosaic) {

		frame = new JFrame(mosaic.getBoardSize() == BoardSize.SMALL ? mosaic.getImageFileName() + " - 0:00"
				: "ImagePuzzle - " + mosaic.getImageFileName() + " - 0:00");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(BoardSize.getBoardSizeValueByName(mosaic.getBoardSize()) * 100 + 10,
				BoardSize.getBoardSizeValueByName(mosaic.getBoardSize()) * 100 + 50);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(BoardSize.getBoardSizeValueByName(mosaic.getBoardSize()),
				BoardSize.getBoardSizeValueByName(mosaic.getBoardSize())));

	}

	private void addMenuToFrame(ImageMosaic mosaic) {

		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Opciók");
		JMenu images = new JMenu("Képek");

		mixOption = new JMenuItem("Keverés");
		againOption = new JMenuItem("Újra");
		newGameOption = new JMenuItem("Új játék");
		smallerBoardOption = new JMenuItem("Kisebbre");
		biggerBoardOption = new JMenuItem("Nagyobbra");
		exitOption = new JMenuItem("Kilépés");

		options.add(mixOption);
		options.addSeparator();
		options.add(againOption);
		options.add(newGameOption);
		options.add(smallerBoardOption);
		options.add(biggerBoardOption);
		options.addSeparator();
		options.add(exitOption);

		imageNames = new ArrayList<>();

		for (String name : mosaic.getImageNameStore()) {

			JMenuItem imageName = new JMenuItem(name);
			imageNames.add(imageName);
			images.add(imageName);
		}

		menuBar.add(options);
		menuBar.add(images);

		options.setForeground(Color.GRAY);
		images.setForeground(Color.GRAY);

		frame.setJMenuBar(menuBar);
	}

	private void addImageMosaicsToFrame(ImageMosaic mosaic) {

		imageMosaics = new ArrayList<>();

		for (int i = 0; i < mosaic.getImageMosaicStore().size(); i++) {

			imageMosaics.add(new JButton());
			imageMosaics.get(i).setBorder(new LineBorder(Color.GRAY));
			imageMosaics.get(i).setIcon(mosaic.getImageMosaicStore().get(i).getImage());
			if (mosaic.getImageMosaicStore().get(i).getId() == 0) {
				imageMosaics.get(i).setVisible(false);
			}
			frame.add(imageMosaics.get(i));
		}

	}

	private void showBoard(boolean show) {
		frame.setVisible(show);
	}

	public void destroyFrame() {

		showBoard(false);
		frame = null;

	}
	
	
	public void modifyInvisibleImageMosaicToVisible(ImageMosaic mosaic) {
		
		for (ImgMosaic imgMosaic : mosaic.getImageMosaicStore()) {
			
			if(imgMosaic.getId() == 0) {
				
				imageMosaics.get(imageMosaics.size()-1).setVisible(true);
				imageMosaics.get(imageMosaics.size()-1).setIcon(imgMosaic.getImage());
			}
			
		}
	}
	
	public int getClickedImageMosaicIndex(JButton imageMosaic) {
		
		return imageMosaics.indexOf(imageMosaic);
	}
	
	public void adjustSlider() {
		
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
       
        sliderFrame = new JFrame();
        sliderFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        sliderFrame.setResizable(false);
        sliderFrame.setSize(250, 115);
        sliderFrame.setLocationRelativeTo(null);

        JPanel sliderPanel = new JPanel();
        sliderPanel.add(slider);
        sliderPanel.add(sliderOkButton);

        sliderFrame.add(sliderPanel);
        sliderFrame.setVisible(true);
    }
	
	
	public void swapImageMosaicsOnTheBoard(int clickedMosaicIndex, int resultIndex){
		
		ImageIcon icon = (ImageIcon) imageMosaics.get(clickedMosaicIndex).getIcon();
		imageMosaics.get(clickedMosaicIndex).setVisible(false);
		imageMosaics.get(resultIndex).setVisible(true);
		imageMosaics.get(resultIndex).setIcon(icon);
	}
	
	public boolean questionWindow(String text) {

		Object[] options = { "Igen", "Nem" };

		return JOptionPane.YES_OPTION == JOptionPane.showOptionDialog(this.frame, text, "ImagePuzzle",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

	}

	public void infoWindow(String text) {

		JOptionPane.showMessageDialog(this.frame, text, "ImagePuzzle", JOptionPane.INFORMATION_MESSAGE);

	}

}
