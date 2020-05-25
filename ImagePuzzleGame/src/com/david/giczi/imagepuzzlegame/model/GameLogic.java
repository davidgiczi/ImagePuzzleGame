package com.david.giczi.imagepuzzlegame.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.david.giczi.imagepuzzlegame.exceptions.InvalidInputValueException;
import com.david.giczi.imagepuzzlegame.utils.BoardSize;


/**
 *
 * @author GicziD
 */
public class GameLogic {

    private int boardSideValue;
    private List<Integer> board;
    private List<Integer> savedBoard;
    private int numberOfMix = 10;

    public GameLogic(BoardSize boardSize) {
        
    	boardSideValue = BoardSize.getBoardSizeValueByName(boardSize);
    	
    }

      
	public int getBoardSideValue() {
		return boardSideValue;
	}

	public void setBoardSideValue(BoardSize boardSize) {
		this.boardSideValue = BoardSize.getBoardSizeValueByName(boardSize);
	}


	public List<Integer> getBoard() {
        return board;
    }

    public void setBoard(List<Integer> board) {
        this.board = new ArrayList<>(board);
    }

    public int getNumberOfMix() {
        return numberOfMix;
    }

    public void setNumberOfMix(int numberOfMix) {
        if (numberOfMix == 0) {
            this.numberOfMix = 1;
        } else {
            this.numberOfMix = numberOfMix;
        }
    }

    public List<Integer> getSavedBoard() {
        return savedBoard;
    }

    public void setSavedBoard(List<Integer> savedBoard) {
        this.savedBoard = new ArrayList<>(savedBoard);
    }

    public void initGameBoard() {

        board = Stream
                .iterate(1, element -> element + 1)
                .limit(boardSideValue * boardSideValue - 1)
                .collect(Collectors.toList());

        board.add(0);

    }

    public void mixGameBoardNumber() throws InvalidInputValueException {

        NumberSquare previousNumberSquare = new NumberSquare(-1, -1, -1, -1);
        NumberSquare.setNumberBoardSideValue(boardSideValue);
        NumberSquare.setNumberBoard(board);
        NumberSquare.setNeedfulClickCounter(0);

        for (int i = 0; i < numberOfMix; i++) {

            NumberSquare.getNumberSquaresFromNumberBoard();
            NumberSquare numberSquare = NumberSquare
                    .getOneOfNumberSquareFromNumberSquareStore(previousNumberSquare);
            numberSquare = NumberSquare.rotateNumberSquare(numberSquare, (int) (Math.random() * 100));
            previousNumberSquare = numberSquare;
            NumberSquare.setNumberSquareIntoNumberBoard(numberSquare);
            NumberSquare.storeNumberBoard();
        }

        setBoard(NumberSquare.getNumberBoard());
        setSavedBoard(NumberSquare.getNumberBoard());
    }

    public int evaluateWhereThePlayerCanStep(int playerSteppedIndex) {

        int whereThePlayerCanStepIndex = -1;
        int x = playerSteppedIndex / boardSideValue;
        int y = playerSteppedIndex % boardSideValue;

        if (boardSideValue <= x || boardSideValue <= y) {

            return whereThePlayerCanStepIndex;
        } else if (x - 1 >= 0 && board.get((x - 1) * boardSideValue + y) == 0) {

            whereThePlayerCanStepIndex = (x - 1) * boardSideValue + y;
            Collections.swap(board, playerSteppedIndex, whereThePlayerCanStepIndex);
        } else if (y + 1 < boardSideValue && board.get(x * boardSideValue + y + 1) == 0) {

            whereThePlayerCanStepIndex = x * boardSideValue + y + 1;
            Collections.swap(board, playerSteppedIndex, whereThePlayerCanStepIndex);
        } else if (x + 1 < boardSideValue && board.get((x + 1) * boardSideValue + y) == 0) {

            whereThePlayerCanStepIndex = (x + 1) * boardSideValue + y;
            Collections.swap(board, playerSteppedIndex, whereThePlayerCanStepIndex);
        } else if (y - 1 >= 0 && board.get(x * boardSideValue + y - 1) == 0) {

            whereThePlayerCanStepIndex = x * boardSideValue + y - 1;
            Collections.swap(board, playerSteppedIndex, whereThePlayerCanStepIndex);
        }

        return whereThePlayerCanStepIndex;
    }

    
    
    public boolean isTheEndOfTheGame() {

        int i = 0;

        while (i < board.size() - 2 && board.get(i + 1) - board.get(i) == 1) {

            i++;
        }

        return i == board.size() - 2 && board.get(board.size() - 1) == 0;

    }
    

}
