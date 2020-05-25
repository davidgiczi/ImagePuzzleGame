package com.david.giczi.imagepuzzlegame.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.david.giczi.imagepuzzlegame.utils.BoardSize;

class BoardSizeTest {

	@Test
	public void testSmallSize() {
		
		assertEquals(3, BoardSize.getBoardSizeValueByName(BoardSize.SMALL));
		
	}

	
	@Test
	public void testMediumSize() {
		
		assertEquals(4, BoardSize.getBoardSizeValueByName(BoardSize.MEDIUM));
		
	}
	
	@Test
	public void testLargeSize() {
		
		assertEquals(5, BoardSize.getBoardSizeValueByName(BoardSize.LARGE));
		
	}
	
}
