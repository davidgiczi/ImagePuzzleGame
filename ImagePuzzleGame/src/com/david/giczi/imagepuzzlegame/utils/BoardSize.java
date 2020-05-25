package com.david.giczi.imagepuzzlegame.utils;

public enum BoardSize {

	
	SMALL(3), MEDIUM(4), LARGE(5);
	
	private final int index;

	private BoardSize(int index) {
		this.index = index;
	}
	
	public static int getBoardSizeValueByName(BoardSize boardSize) {
		return boardSize.index;
	}
	
}
