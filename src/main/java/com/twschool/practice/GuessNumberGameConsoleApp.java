package com.twschool.practice;

import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GameAnswer;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;

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

    public GuessNumberGameConsoleApp(String answerString) {
        GuessNumberGameConsoleApp.answerNumbers = Arrays.asList(answerString.split(" "));
    }

    public GuessNumberGameConsoleApp() {
        //this.answerNumbers = Arrays.asList("1", "2", "3", "4");
    }

    public static void main(String args[]) {
        int gameTimes = 0;//单局游戏次数
        String userAnswer1 = null;
        GuessNumberGameConsoleApp answer = new GuessNumberGameConsoleApp();
      //  GuessNumberGameConsoleApp.answerNumbers = Arrays.asList("1", "2", "3", "4");
        AnswerGenerator generateAnswer = new AnswerGenerator();
        GuessNumberGameConsoleApp.answerNumbers = generateAnswer.generateAnswer1();
        ArrayList<String> userAnswer = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (!GuessNumberGameConsoleApp.answerNumbers.toString().equals(userAnswer.toString())) {
            userAnswer.clear();
            valueAndPositionCorrectCount = 0;
            positionCorrectCount = 0;
            System.out.println("Please guess number:eg,1 2 3 4");
            for (int i = 0; i < 4; i++) {
                userAnswer.add(sc.next());
            }
            if(answer.isRepeat(userAnswer)){
                userAnswer.clear();
            }
            else{
                userAnswer1 = userAnswer.toString().replace("[", "").replace("]", "").replace(",", "");
                String result = answer.check(userAnswer1);
                if (result.equals("4A0B")) {
                    System.out.println(result + "        Congratulations，you win！");
                    System.exit(0);
                } else if (gameTimes < 5) {
                    System.out.println(result + "        Sorry,please try again! The left game times：" + (5-gameTimes));
                } else {
                    System.out.println("Sorry，you have failed！The right answer is:" +answerNumbers.toString() );
                    System.exit(0);
                }
                gameTimes++;
            }
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
    public boolean isRepeat( List<String> list){
        long count = list.stream().distinct().count();
        if(count < list.size()){
            System.out.println("Warning! There exsit some repeat number,please input diffierent number:");
            return true;
        }
        return false;
    }









}
