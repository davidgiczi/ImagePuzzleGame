package com.david.giczi.imagepuzzlegame.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.david.giczi.imagepuzzlegame.model.GameLogic;
import com.david.giczi.imagepuzzlegame.model.NumberSquare;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;

import static org.junit.Assert.*;

/**
 *
 * @author GicziD
 */
public class GameLogicClassTest {

    private GameLogic logic;
    
    @After
	public void clearBoardStore() {

		NumberSquare.clearBoardStore();
	}
    

    @Before
    public void createGameLogicObject() {

        logic = new GameLogic();
        logic.setBoardSideValue(BoardSize.MEDIUM);
    }

    @Test
    public void testCreateGameLogicObject() {

        assertNotNull(logic);
    }

    @Test
    public void testSizeOfGameBoardAfterInit() {

        logic.initGameBoard();

        int boardArea = logic.getBoardSideValue() * logic.getBoardSideValue();

        assertEquals(boardArea, logic.getBoard().size());

    }

    @Test
    public void testElementsOfGameBoardAfterInit() {

        logic.initGameBoard();
        
        for (int i = 1; i < logic.getBoard().size()-1; i++) {

            assertTrue(logic.getBoard().get(i-1)+1 == logic.getBoard().get(i));

        }
        
        assertTrue(0 == logic.getBoard().get(logic.getBoard().size()-1));
    }  
  
    @Test
    public void testIsTheEndOfTheGame() {

        List<Integer> board = new ArrayList<>();

        int boardArea = logic.getBoardSideValue() * logic.getBoardSideValue();

        for (int i = 1; i < boardArea; i++) {
            board.add(i);
        }

        board.add(0);

        logic.setBoard(board);

        assertTrue(logic.isTheEndOfTheGame());

    }

    @Test
    public void testIsNotTheEndOfTheGameBecauseOfNotAscendingOrder() {

        List<Integer> board = new ArrayList<>();

        int boardArea = logic.getBoardSideValue() * logic.getBoardSideValue();

        for (int i = 1; i < boardArea; i++) {
            board.add(i);
        }

        board.add(0);

        Collections.shuffle(board);

        logic.setBoard(board);

        assertFalse(logic.isTheEndOfTheGame());
    }

    @Test
    public void testIsNotTheEndOfTheGameBeacuseOfIsNotLastZeroValue() {

        List<Integer> board = new ArrayList<>();

        int boardArea = logic.getBoardSideValue() * logic.getBoardSideValue();

        for (int i = 1; i < boardArea; i++) {
            board.add(i);
        }

        logic.setBoard(board);

        assertFalse(logic.isTheEndOfTheGame());
    }

    @Test
    public void testEvaluateWhereThePlayerCanStepMethodCaseNo1() {

        List<Integer> board = new ArrayList<>();

        int boardArea = logic.getBoardSideValue() * logic.getBoardSideValue();

        for (int i = 1; i < boardArea; i++) {
            board.add(i);
        }

        board.add(0);

        logic.setBoard(board);

        int result = logic.evaluateWhereThePlayerCanStep(11);

        assertEquals(15, result);

        int zeroElement = logic.getBoard().get(11);

        assertEquals(0, zeroElement);

        int steppedElement = logic.getBoard().get(15);

        assertEquals(12, steppedElement);
    }

    @Test
    public void testEvaluateWhereThePlayerCanStepMethodCaseNo2() {

        List<Integer> board = new ArrayList<>();

        int boardArea = logic.getBoardSideValue() * logic.getBoardSideValue();

        for (int i = 1; i < boardArea; i++) {
            board.add(i);
        }

        board.add(0);

        logic.setBoard(board);

        int result = logic.evaluateWhereThePlayerCanStep(14);

        assertEquals(15, result);

        int zeroElement = logic.getBoard().get(14);

        assertEquals(0, zeroElement);

        int steppedElement = logic.getBoard().get(15);

        assertEquals(15, steppedElement);
    }

    @Test
    public void testEvaluateWhereThePlayerCanStepMethodCaseNo3() {

        List<Integer> board = new ArrayList<>();

        int boardArea = logic.getBoardSideValue() * logic.getBoardSideValue();

        for (int i = 1; i < boardArea; i++) {
            board.add(i);
        }

        board.add(0);

        logic.setBoard(board);

        int result = logic.evaluateWhereThePlayerCanStep(16);

        assertEquals(-1, result);

        int zeroElement = logic.getBoard().get(15);

        assertEquals(0, zeroElement);

    }

    @Test
    public void testEvaluateWhereThePlayerCanStepMethodCaseNo4() {

        List<Integer> board = new ArrayList<>();

        int boardArea = logic.getBoardSideValue() * logic.getBoardSideValue();

        for (int i = 1; i < boardArea; i++) {
            board.add(i);
        }

        board.add(0);

        logic.setBoard(board);

        int result = logic.evaluateWhereThePlayerCanStep(6);

        assertEquals(-1, result);

        int zeroElement = logic.getBoard().get(15);

        assertEquals(0, zeroElement);

        int steppedElement = logic.getBoard().get(6);

        assertEquals(7, steppedElement);

    }

    
    
}
