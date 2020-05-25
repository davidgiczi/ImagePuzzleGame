package com.david.giczi.imagepuzzlegame.model;


import java.util.ArrayList;
import java.util.List;

import com.david.giczi.imagepuzzlegame.exceptions.InvalidInputValueException;

/**
 *
 * @author GicziD
 */
public class NumberSquare {

    private final List<Integer> numberSquare;
    private static List<NumberSquare> numberSquareStore;
    private static List<Integer> numberBoard;
    private static int numberBoardSideValue;
    private static int boardIndex;
    private static int needfulClickCounter;
    private static List<List<Integer>> numberBoardStore = new ArrayList<>();

    public NumberSquare() {
        numberSquare = new ArrayList<>();
    }

    public NumberSquare(int value1, int value2, int value3, int value4) {

        numberSquare = new ArrayList<>();

        numberSquare.add(value1);
        numberSquare.add(value2);
        numberSquare.add(value3);
        numberSquare.add(value4);

    }

    public static List<Integer> getNumberBoard() {
        return numberBoard;
    }

    public static void setNumberBoard(List<Integer> board) {
        NumberSquare.numberBoard = board;
    }

    public static int getNumberBoardSideValue() {
        return numberBoardSideValue;
    }

    public static void setNumberBoardSideValue(int boardSide) {
        NumberSquare.numberBoardSideValue = boardSide;
    }

    public List<Integer> getNumberSquare() {
        return numberSquare;
    }

    public static List<NumberSquare> getNumberSquareStore() {
        return numberSquareStore;
    }

    public static void setNumberSquareStore(List<NumberSquare> numberSquareStore) {
        NumberSquare.numberSquareStore = numberSquareStore;
    }
    
    public static void setBoardIndex(int boardIndex) {
        NumberSquare.boardIndex = boardIndex;
    }

    public static int getNeedfulClickCounter() {
        return needfulClickCounter;
    }

    public static void setNeedfulClickCounter(int needfulClickCounter) {
        NumberSquare.needfulClickCounter = needfulClickCounter;
    }

    public static List<List<Integer>> getNumberBoardStore() {
        return numberBoardStore;
    }

    public static void getNumberSquaresFromNumberBoard() {

        numberSquareStore = new ArrayList<>();

        for (int i = 0; i < numberBoardSideValue * numberBoardSideValue; i++) {

            if (numberBoard.get(i) == 0) {
                boardIndex = i;
                numberSquareStore.add(getNumberSquareFromLeftAndUpSide(i));
                numberSquareStore.add(getNumberSquareFromRightAndUpSide(i));
                numberSquareStore.add(getNumberSquareFromRightAndDownSide(i));
                numberSquareStore.add(getNumberSquareFromLeftAndDownSide(i));
            }

        }

    }

    private static NumberSquare getNumberSquareFromLeftAndUpSide(int index) {

        NumberSquare leftAndUpNumberSquare = new NumberSquare();

        int x = index / numberBoardSideValue;
        int y = index % numberBoardSideValue;

        if (x - 1 >= 0 && y - 1 >= 0) {

            int value1 = numberBoard.get((x - 1) * numberBoardSideValue + y - 1);
            int value2 = numberBoard.get((x - 1) * numberBoardSideValue + y);
            int value3 = numberBoard.get(x * numberBoardSideValue + y);
            int value4 = numberBoard.get(x * numberBoardSideValue + y - 1);

            leftAndUpNumberSquare = new NumberSquare(value1, value2, value3, value4);
        }

        return leftAndUpNumberSquare;
    }

    private static NumberSquare getNumberSquareFromRightAndUpSide(int index) {

        NumberSquare rightAndUpNumberSquare = new NumberSquare();

        int x = index / numberBoardSideValue;
        int y = index % numberBoardSideValue;

        if (x - 1 >= 0 && y + 1 < numberBoardSideValue) {

            int value1 = numberBoard.get((x - 1) * numberBoardSideValue + y);
            int value2 = numberBoard.get((x - 1) * numberBoardSideValue + y + 1);
            int value3 = numberBoard.get(x * numberBoardSideValue + y + 1);
            int value4 = numberBoard.get(x * numberBoardSideValue + y);

            rightAndUpNumberSquare = new NumberSquare(value1, value2, value3, value4);

        }

        return rightAndUpNumberSquare;
    }

    private static NumberSquare getNumberSquareFromRightAndDownSide(int index) {

        NumberSquare rightAndDownNumberSquare = new NumberSquare();

        int x = index / numberBoardSideValue;
        int y = index % numberBoardSideValue;

        if (x + 1 < numberBoardSideValue && y + 1 < numberBoardSideValue) {

            int value1 = numberBoard.get(x * numberBoardSideValue + y);
            int value2 = numberBoard.get(x * numberBoardSideValue + y + 1);
            int value3 = numberBoard.get((x + 1) * numberBoardSideValue + y + 1);
            int value4 = numberBoard.get((x + 1) * numberBoardSideValue + y);

            rightAndDownNumberSquare = new NumberSquare(value1, value2, value3, value4);

        }

        return rightAndDownNumberSquare;

    }

    private static NumberSquare getNumberSquareFromLeftAndDownSide(int index) {

        NumberSquare leftAndDownNumberSquare = new NumberSquare();

        int x = index / numberBoardSideValue;
        int y = index % numberBoardSideValue;

        if (x + 1 < numberBoardSideValue && y - 1 >= 0) {

            int value1 = numberBoard.get(x * numberBoardSideValue + y - 1);
            int value2 = numberBoard.get(x * numberBoardSideValue + y);
            int value3 = numberBoard.get((x + 1) * numberBoardSideValue + y);
            int value4 = numberBoard.get((x + 1) * numberBoardSideValue + y - 1);

            leftAndDownNumberSquare = new NumberSquare(value1, value2, value3, value4);

        }

        return leftAndDownNumberSquare;
    }

    public static boolean isConsistsOfTheSameNumberValue(NumberSquare numberSquare1,
            NumberSquare numberSquare2) {

        return numberSquare1.getNumberSquare().contains(numberSquare2.getNumberSquare().get(0))
                && numberSquare1.getNumberSquare().contains(numberSquare2.getNumberSquare().get(1))
                && numberSquare1.getNumberSquare().contains(numberSquare2.getNumberSquare().get(2))
                && numberSquare1.getNumberSquare().contains(numberSquare2.getNumberSquare().get(3));
    }

    public static NumberSquare rotateNumberSquare(NumberSquare numberSquare, int rotateValue) throws InvalidInputValueException {

        Integer[] temp = numberSquare.getNumberSquare().toArray(new Integer[4]);
        numberSquare.getNumberSquare().clear();

        countNeedfulClick(rotateValue % 4);

        switch (rotateValue % 4) {

            case 0:
                numberSquare.getNumberSquare().add(temp[0]);
                numberSquare.getNumberSquare().add(temp[1]);
                numberSquare.getNumberSquare().add(temp[2]);
                numberSquare.getNumberSquare().add(temp[3]);
                break;
            case 1:
                numberSquare.getNumberSquare().add(temp[3]);
                numberSquare.getNumberSquare().add(temp[0]);
                numberSquare.getNumberSquare().add(temp[1]);
                numberSquare.getNumberSquare().add(temp[2]);
                break;
            case 2:
                numberSquare.getNumberSquare().add(temp[2]);
                numberSquare.getNumberSquare().add(temp[3]);
                numberSquare.getNumberSquare().add(temp[0]);
                numberSquare.getNumberSquare().add(temp[1]);
                break;
            case 3:
                numberSquare.getNumberSquare().add(temp[1]);
                numberSquare.getNumberSquare().add(temp[2]);
                numberSquare.getNumberSquare().add(temp[3]);
                numberSquare.getNumberSquare().add(temp[0]);
                break;
            default:
                throw new InvalidInputValueException("The value of the rotate must be 0 <= rotateValue");
        }

        return numberSquare;
    }

    private static void countNeedfulClick(int rotateValue) {

        switch (rotateValue) {

            case 1:
            case 3:
                needfulClickCounter += 3;
                break;
            case 2:
                needfulClickCounter += 6;
                break;
            default:
                needfulClickCounter += 0;
        }

    }

    public static NumberSquare getOneOfNumberSquareFromNumberSquareStore(NumberSquare apartFromThis) {

        List<NumberSquare> temp = new ArrayList<>(numberSquareStore);

        for (int i = temp.size() - 1; i >= 0; i--) {

            if (isConsistsOfTheSameNumberValue(temp.get(i), apartFromThis)
                    || temp.get(i).getNumberSquare().isEmpty()) {
                temp.remove(temp.get(i));
            }

        }

        if (!temp.isEmpty()) {

            return temp.get((int) (Math.random() * temp.size()));

        }

        return apartFromThis;
    }

    public static void setNumberSquareIntoNumberBoard(NumberSquare numberSquare) throws InvalidInputValueException {

        int x = boardIndex / numberBoardSideValue;
        int y = boardIndex % numberBoardSideValue;

        int numberSquareStoreIndex = numberSquareStore
                .indexOf(numberSquareStore.stream()
                        .filter(element -> isConsistsOfTheSameNumberValue(element, numberSquare))
                        .findAny()
                        .get());
        
        switch (numberSquareStoreIndex) {

            case 0:

                numberBoard.set((x - 1) * numberBoardSideValue + y - 1, numberSquare.getNumberSquare().get(0));
                numberBoard.set((x - 1) * numberBoardSideValue + y, numberSquare.getNumberSquare().get(1));
                numberBoard.set(x * numberBoardSideValue + y, numberSquare.getNumberSquare().get(2));
                numberBoard.set(x * numberBoardSideValue + y - 1, numberSquare.getNumberSquare().get(3));

                break;
            case 1:

                numberBoard.set((x - 1) * numberBoardSideValue + y, numberSquare.getNumberSquare().get(0));
                numberBoard.set((x - 1) * numberBoardSideValue + y + 1, numberSquare.getNumberSquare().get(1));
                numberBoard.set(x * numberBoardSideValue + y + 1, numberSquare.getNumberSquare().get(2));
                numberBoard.set(x * numberBoardSideValue + y, numberSquare.getNumberSquare().get(3));

                break;

            case 2:

                numberBoard.set(x * numberBoardSideValue + y, numberSquare.getNumberSquare().get(0));
                numberBoard.set(x * numberBoardSideValue + y + 1, numberSquare.getNumberSquare().get(1));
                numberBoard.set((x + 1) * numberBoardSideValue + y + 1, numberSquare.getNumberSquare().get(2));
                numberBoard.set((x + 1) * numberBoardSideValue + y, numberSquare.getNumberSquare().get(3));

                break;

            case 3:

                numberBoard.set(x * numberBoardSideValue + y - 1, numberSquare.getNumberSquare().get(0));
                numberBoard.set(x * numberBoardSideValue + y, numberSquare.getNumberSquare().get(1));
                numberBoard.set((x + 1) * numberBoardSideValue + y, numberSquare.getNumberSquare().get(2));
                numberBoard.set((x + 1) * numberBoardSideValue + y - 1, numberSquare.getNumberSquare().get(3));

                break;
            default:
                throw new InvalidInputValueException("The value of \'NumberSquareStore\' index must be between "+
                        "0 < numberSquareStoreIndex < 4");
        }

    }

    public static void storeNumberBoard() {

        numberBoardStore.add(new ArrayList<>(numberBoard));
    }
    
    public static void clearBoardStore(){
        
        numberBoardStore.clear();
        
    }

}
