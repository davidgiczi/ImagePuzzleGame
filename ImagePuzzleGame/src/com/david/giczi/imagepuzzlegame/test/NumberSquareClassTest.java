package com.david.giczi.imagepuzzlegame.test;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import com.david.giczi.imagepuzzlegame.exceptions.InvalidInputValueException;
import com.david.giczi.imagepuzzlegame.model.GameLogic;
import com.david.giczi.imagepuzzlegame.model.NumberSquare;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;

import static org.junit.Assert.*;

/**
 *
 * @author GicziD
 */
public class NumberSquareClassTest {

    @Test
    public void testGetNumberSquareFromLeftAndUpSide() {

        GameLogic logic = new GameLogic(BoardSize.MEDIUM);
        logic.initGameBoard();
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();
        List<Integer> leftAndUpNumberSquare = NumberSquare.getNumberSquareStore()
                .get(0)
                .getNumberSquare();

        assertEquals(Arrays.asList(11, 12, 0, 15), leftAndUpNumberSquare);

    }

    @Test
    public void testGetNumberSquareFromRightAndUpSide() {

        GameLogic logic = new GameLogic(BoardSize.MEDIUM);
        logic.initGameBoard();
        Collections.swap(logic.getBoard(), 10, 15);
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();
        List<Integer> rightAndUpNumberSquare = NumberSquare.getNumberSquareStore()
                .get(1)
                .getNumberSquare();

        assertEquals(Arrays.asList(7, 8, 12, 0), rightAndUpNumberSquare);

    }

    @Test
    public void testGetNumberSquareFromRightAndDownSide() {

        GameLogic logic = new GameLogic(BoardSize.MEDIUM);
        logic.initGameBoard();
        Collections.swap(logic.getBoard(), 5, 15);
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();
        List<Integer> rightAndDownNumberSquare = NumberSquare.getNumberSquareStore()
                .get(2)
                .getNumberSquare();

        assertEquals(Arrays.asList(0, 7, 11, 10), rightAndDownNumberSquare);

    }

    @Test
    public void testGetNumberSquareFromLeftAndDownSide() {

        GameLogic logic = new GameLogic(BoardSize.LARGE);
        logic.initGameBoard();
        Collections.swap(logic.getBoard(), 18, 24);
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();
        List<Integer> leftAndDownNumberSquare = NumberSquare.getNumberSquareStore()
                .get(3)
                .getNumberSquare();

        assertEquals(Arrays.asList(18, 0, 24, 23), leftAndDownNumberSquare);

    }

    @Test
    public void testGetNumberSquareFromEverySide() {

        GameLogic logic = new GameLogic(BoardSize.SMALL);
        logic.initGameBoard();
        Collections.swap(logic.getBoard(), 4, 8);
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();
        List<Integer> leftAndUpNumberSquare = NumberSquare.getNumberSquareStore()
                .get(0)
                .getNumberSquare();

        List<Integer> rightAndUpNumberSquare = NumberSquare.getNumberSquareStore()
                .get(1)
                .getNumberSquare();

        List<Integer> rightAndDownNumberSquare = NumberSquare.getNumberSquareStore()
                .get(2)
                .getNumberSquare();

        List<Integer> leftAndDownNumberSquare = NumberSquare.getNumberSquareStore()
                .get(3)
                .getNumberSquare();

        assertEquals(Arrays.asList(1, 2, 0, 4), leftAndUpNumberSquare);
        assertEquals(Arrays.asList(2, 3, 6, 0), rightAndUpNumberSquare);
        assertEquals(Arrays.asList(0, 6, 5, 8), rightAndDownNumberSquare);
        assertEquals(Arrays.asList(4, 0, 8, 7), leftAndDownNumberSquare);

    }

    @Test
    public void testGetNumberSquareIfItDoesNotExist() {

        GameLogic logic = new GameLogic(BoardSize.MEDIUM);
        logic.initGameBoard();
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();
        List<Integer> leftAndUpNumberSquare = NumberSquare.getNumberSquareStore()
                .get(0)
                .getNumberSquare();

        List<Integer> rightAndUpNumberSquare = NumberSquare.getNumberSquareStore()
                .get(1)
                .getNumberSquare();

        List<Integer> rightAndDownNumberSquare = NumberSquare.getNumberSquareStore()
                .get(2)
                .getNumberSquare();

        List<Integer> leftAndDownNumberSquare = NumberSquare.getNumberSquareStore()
                .get(3)
                .getNumberSquare();

        assertEquals(Arrays.asList(11, 12, 0, 15), leftAndUpNumberSquare);
        assertEquals(Arrays.asList(), rightAndUpNumberSquare);
        assertEquals(Arrays.asList(), rightAndDownNumberSquare);
        assertEquals(Arrays.asList(), leftAndDownNumberSquare);

    }

    @Test
    public void testIsConsistsOfTheSameNumberValueInTwoNumberSquare() {

        NumberSquare numberSquare1 = new NumberSquare(1, 2, 3, 4);
        NumberSquare numberSquare2 = new NumberSquare(4, 1, 2, 3);
        NumberSquare numberSquare3 = new NumberSquare(3, 4, 1, 2);
        NumberSquare numberSquare4 = new NumberSquare(2, 3, 4, 1);

        assertTrue(NumberSquare.isConsistsOfTheSameNumberValue(numberSquare1, numberSquare2));
        assertTrue(NumberSquare.isConsistsOfTheSameNumberValue(numberSquare1, numberSquare3));
        assertTrue(NumberSquare.isConsistsOfTheSameNumberValue(numberSquare1, numberSquare4));
    }

    @Test
    public void testIsNotConsistsOfTheSameNumberValueInTwoNumberSquare() {

        NumberSquare numberSquare1 = new NumberSquare(1, 2, 3, 4);
        NumberSquare numberSquare2 = new NumberSquare(1, 2, 3, 5);
        NumberSquare numberSquare4 = new NumberSquare(6, 7, 8, 9);

        assertFalse(NumberSquare.isConsistsOfTheSameNumberValue(numberSquare1, numberSquare2));
        assertFalse(NumberSquare.isConsistsOfTheSameNumberValue(numberSquare1, numberSquare4));
    }

    @Test
    public void testRotateNumberSquareByZeroValue() throws InvalidInputValueException {

        NumberSquare numberSquare = new NumberSquare(1, 2, 3, 4);

        NumberSquare.rotateNumberSquare(numberSquare, 0);

        assertTrue(numberSquare.getNumberSquare().get(0) == 1
                && numberSquare.getNumberSquare().get(1) == 2
                && numberSquare.getNumberSquare().get(2) == 3
                && numberSquare.getNumberSquare().get(3) == 4);

    }

    @Test
    public void testRotateNumberSquareByOneValue() throws InvalidInputValueException {

        NumberSquare numberSquare = new NumberSquare(1, 2, 3, 4);

        NumberSquare.rotateNumberSquare(numberSquare, 1);

        assertTrue(numberSquare.getNumberSquare().get(0) == 4
                && numberSquare.getNumberSquare().get(1) == 1
                && numberSquare.getNumberSquare().get(2) == 2
                && numberSquare.getNumberSquare().get(3) == 3);

    }

    @Test
    public void testRotateNumberSquareByTwoValue() throws InvalidInputValueException {

        NumberSquare numberSquare = new NumberSquare(1, 2, 3, 4);

        NumberSquare.rotateNumberSquare(numberSquare, 2);

        assertTrue(numberSquare.getNumberSquare().get(0) == 3
                && numberSquare.getNumberSquare().get(1) == 4
                && numberSquare.getNumberSquare().get(2) == 1
                && numberSquare.getNumberSquare().get(3) == 2);

    }

    @Test
    public void testRotateNumberSquareByThreeValue() throws InvalidInputValueException {

        NumberSquare numberSquare = new NumberSquare(1, 2, 3, 4);

        NumberSquare.rotateNumberSquare(numberSquare, 3);

        assertTrue(numberSquare.getNumberSquare().get(0) == 2
                && numberSquare.getNumberSquare().get(1) == 3
                && numberSquare.getNumberSquare().get(2) == 4
                && numberSquare.getNumberSquare().get(3) == 1);

    }

    @Test
    public void testRotateNumberSquareMethodThrowsInvalidInputValueException() {

        boolean throwException = false;

        try {
            NumberSquare.rotateNumberSquare(new NumberSquare(0, 0, 0, 0), -1);
        } catch (InvalidInputValueException ex) {

            throwException = true;

        }
        assertTrue(throwException);
    }

    @Test
    public void testGetOneOfNumberSquareFromNumberSquareStoreButNotThisNumberSquare() {

        GameLogic logic = new GameLogic(BoardSize.SMALL);
        logic.initGameBoard();
        Collections.swap(logic.getBoard(), 4, 8);
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();

        NumberSquare thisNumberSquare = new NumberSquare(1, 2, 0, 4);

        NumberSquare gotNumberSquare = NumberSquare.getOneOfNumberSquareFromNumberSquareStore(thisNumberSquare);

        assertTrue((gotNumberSquare.getNumberSquare().get(0) == 2
                && gotNumberSquare.getNumberSquare().get(1) == 3
                && gotNumberSquare.getNumberSquare().get(2) == 6
                && gotNumberSquare.getNumberSquare().get(3) == 0)
                || (gotNumberSquare.getNumberSquare().get(0) == 0
                && gotNumberSquare.getNumberSquare().get(1) == 6
                && gotNumberSquare.getNumberSquare().get(2) == 5
                && gotNumberSquare.getNumberSquare().get(3) == 8)
                || (gotNumberSquare.getNumberSquare().get(0) == 4
                && gotNumberSquare.getNumberSquare().get(1) == 0
                && gotNumberSquare.getNumberSquare().get(2) == 8
                && gotNumberSquare.getNumberSquare().get(3) == 7)
        );
    }

    @Test
    public void testGetOneOfNumberSquareFromNumberSquareStoreIfStoreIsEmpty() {

        GameLogic logic = new GameLogic(BoardSize.MEDIUM);
        logic.initGameBoard();
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();

        NumberSquare thisNumberSquare = new NumberSquare(11, 12, 0, 15);

        NumberSquare gotNumberSquare = NumberSquare.getOneOfNumberSquareFromNumberSquareStore(thisNumberSquare);

        assertTrue(gotNumberSquare.getNumberSquare().get(0) == 11
                && gotNumberSquare.getNumberSquare().get(1) == 12
                && gotNumberSquare.getNumberSquare().get(2) == 0
                && gotNumberSquare.getNumberSquare().get(3) == 15);

    }

    @Test
    public void testSetNumberSquareIntoNumberBoard() throws InvalidInputValueException {

        GameLogic logic = new GameLogic(BoardSize.SMALL);
        logic.initGameBoard();
        NumberSquare.setNumberBoardSideValue(logic.getBoardSideValue());
        NumberSquare.setNumberBoard(logic.getBoard());
        NumberSquare.getNumberSquaresFromNumberBoard();
        NumberSquare numberSquare = NumberSquare.getOneOfNumberSquareFromNumberSquareStore(new NumberSquare(0, 8, 5, 6));
        NumberSquare.setBoardIndex(8);
        NumberSquare.setNumberSquareIntoNumberBoard(numberSquare);

        assertEquals(Integer.valueOf(1), logic.getBoard().get(0));
        assertEquals(Integer.valueOf(2), logic.getBoard().get(1));
        assertEquals(Integer.valueOf(3), logic.getBoard().get(2));

        assertEquals(Integer.valueOf(4), logic.getBoard().get(3));
        assertEquals(Integer.valueOf(0), logic.getBoard().get(4));
        assertEquals(Integer.valueOf(8), logic.getBoard().get(5));

        assertEquals(Integer.valueOf(7), logic.getBoard().get(6));
        assertEquals(Integer.valueOf(6), logic.getBoard().get(7));
        assertEquals(Integer.valueOf(5), logic.getBoard().get(8));
    }

    @Test
    public void
            testSetNumberSquareIntoNumberBoardMethodThrowsInvalidInputValueException() {

        NumberSquare.getNumberSquareStore().add(new NumberSquare(1, 2, 3, 4));

        boolean throwException = false;

        try {
            NumberSquare.setNumberSquareIntoNumberBoard(new NumberSquare(4, 1, 2, 3));
        } catch (InvalidInputValueException ex) {

            throwException = true;

        }
        assertTrue(throwException);
    }

    @Test
    public void testCountNeedfulClickMethod() throws InvalidInputValueException {

        NumberSquare.setNeedfulClickCounter(0);
        NumberSquare.rotateNumberSquare(new NumberSquare(0, 0, 0, 0), 0);

        assertEquals(0, NumberSquare.getNeedfulClickCounter());

        NumberSquare.rotateNumberSquare(new NumberSquare(0, 0, 0, 0), 1);

        assertEquals(3, NumberSquare.getNeedfulClickCounter());

        NumberSquare.rotateNumberSquare(new NumberSquare(0, 0, 0, 0), 2);

        assertEquals(9, NumberSquare.getNeedfulClickCounter());

        NumberSquare.rotateNumberSquare(new NumberSquare(0, 0, 0, 0), 3);

        assertEquals(12, NumberSquare.getNeedfulClickCounter());
    }

    @Test
    public void testStoreNumberBoard() throws InvalidInputValueException {

        GameLogic logic = new GameLogic(BoardSize.SMALL);
        logic.initGameBoard();
        logic.mixGameBoardNumber();

        assertEquals(logic.getNumberOfMix(), NumberSquare.getNumberBoardStore().size());
        assertEquals(logic.getBoard(), NumberSquare.getNumberBoardStore().get(logic.getNumberOfMix() - 1));

    }

    @Test
    public void testClearBoardStore() throws InvalidInputValueException {

        NumberSquare.clearBoardStore();
        
        GameLogic logic = new GameLogic(BoardSize.SMALL);
        logic.initGameBoard();
        logic.mixGameBoardNumber();

        assertEquals(logic.getNumberOfMix(), NumberSquare.getNumberBoardStore().size());
        assertEquals(logic.getBoard(), NumberSquare.getNumberBoardStore().get(logic.getNumberOfMix() - 1));

        NumberSquare.clearBoardStore();

        assertEquals(0, NumberSquare.getNumberBoardStore().size());

    }

}
