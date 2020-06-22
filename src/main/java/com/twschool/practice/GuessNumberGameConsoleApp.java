package com.twschool.practice;


import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GameAnswer;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import jdk.nashorn.internal.ir.ContinueNode;

import java.text.BreakIterator;
import java.util.*;

public class GuessNumberGameConsoleApp {

//    public static void main(String[] args) {
//        GuessNumberGame guessNumberGame = new GuessNumberGame(new AnswerGenerator());
//        System.out.println("please guess number: eg, 1 2 3 4");
//        while (guessNumberGame.getStatus() == GameStatus.CONTINUED) {
//            Scanner scanner = new Scanner(System.in);
//            String userInput = scanner.nextLine();
//
//            String result = guessNumberGame.guess(userInput);
//
//            System.out.println(String.format("guess result: %s", result));
//        }
//
//        if (guessNumberGame.getStatus() == GameStatus.SUCCEED) {
//            System.out.println("Congratulations, you guess succeed");
//        }
//        if (guessNumberGame.getStatus() == GameStatus.FAILED) {
//            System.out.println("Sorry, you guess failed");
//        }
//    }

    private static List<String> answerNumbers;
    private static int valueAndPositionCorrectCount;
    private static int positionCorrectCount;
    private static int gameTimesInOneMatch;
    private static List<String> resultOfWinAndLoseForOneUser;

    public GuessNumberGameConsoleApp(String answerString) {
        GuessNumberGameConsoleApp.answerNumbers = Arrays.asList(answerString.split(" "));
    }

    public GuessNumberGameConsoleApp() {

    }

    public static void main(String args[]) {
        gameTimesInOneMatch = 0;
        String userAnswer = null;
        GuessNumberGameConsoleApp answer = new GuessNumberGameConsoleApp();
        AnswerGenerator generateAnswer = new AnswerGenerator();
        GuessNumberGameConsoleApp.answerNumbers = generateAnswer.generateAnswerForListType();
        ArrayList<String> userInput = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (!GuessNumberGameConsoleApp.answerNumbers.toString().equals(userInput.toString())) {
            userInput.clear();
            valueAndPositionCorrectCount = 0;
            positionCorrectCount = 0;
            System.out.println("Please guess number:eg,1 2 3 4");
            for (int i = 0; i < 4; i++) {
                userInput.add(sc.next());
            }
            if (answer.isRepeat(userInput)) {
                userInput.clear();
            } else {
                userAnswer = getUserInputForString(userInput);
                String result = answer.check(userAnswer);
                resultCompareInOneMatch(result);
                gameTimesInOneMatch++;
            }
        }
    }

    public static String getUserInputForString(ArrayList<String> userInput) {
        String userAnswer;
        userAnswer = userInput.toString().replace("[", "").replace("]", "").replace(",", "");
        return userAnswer;
    }

    public static void resultCompareInOneMatch(String result) {
        if (result.equals("4A0B")) {
            System.out.println(result + "        Congratulations，you win！");
             //resultOfWinAndLoseForOneUser.add("win");

            System.exit(0);

        } else if (gameTimesInOneMatch < 5) {
            System.out.println(result + "        Sorry,please try again! The left game times：" + (5 - gameTimesInOneMatch));
        } else {
            System.out.println("Sorry，you have failed！The right answer is:" + answerNumbers.toString());
            //resultOfWinAndLoseForOneUser.add("lose");

            System.exit(0);

        }
    }

    public String check(String userAnswerString) {
        List<String> userAnswerNumbers = Arrays.asList(userAnswerString.split(" "));
        valueAndPositionCorrectCount = 0;
        positionCorrectCount = 0;
        for (int index = 0; index < answerNumbers.size(); index++) {
            if (answerNumbers.get(index).equals(userAnswerNumbers.get(index))) {
                valueAndPositionCorrectCount++;
            } else if (answerNumbers.contains(userAnswerNumbers.get(index))) {
                positionCorrectCount++;
            }
        }
        return valueAndPositionCorrectCount + "A" + positionCorrectCount + "B";
    }

    public boolean isRepeat(List<String> list) {
        long count = list.stream().distinct().count();
        if (count < list.size()) {
            System.out.println("Warning! There exsit some repeat number,please input diffierent number:");
            return true;
        }
        return false;
    }


}
