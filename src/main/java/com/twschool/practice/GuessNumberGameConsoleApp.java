package com.twschool.practice;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GuessNumberGameConsoleApp {
    public static final String SUCCESS_5 = "11111";
    public static final String SUCCESS_3 = "111";

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        boolean controller = true;
        Scanner scanner = new Scanner(System.in);

        while (controller) {
            GuessNumberGame guessNumberGame = new GuessNumberGame(new AnswerGenerator());
            System.out.println("please guess number: eg, 1 2 3 4");
            while (guessNumberGame.getStatus() == GameStatus.CONTINUED) {
                String userInput = scanner.nextLine();
                String result = guessNumberGame.guess(userInput);
                System.out.println(String.format("guess result: %s", result));
            }

            if (guessNumberGame.getStatus() == GameStatus.SUCCEED) {
                list.add(1);
                System.out.println("Congratulations, you guess succeed");
                controller = isContine(controller, scanner);
            }

            if (guessNumberGame.getStatus() == GameStatus.FAILED) {
                list.add(0);
                System.out.println("Sorry, you guess failed");
                controller = isContine(controller, scanner);
            }
        }
        calculatePoints(list);
    }

    private static boolean isContine(boolean flag, Scanner scanner) {
        System.out.println("--------是否继续游戏？ Y：继续    N：退出并显示积分-------- ");
        String s = scanner.nextLine();
        if ("n".equals(s.toLowerCase())) {
            flag = false;
        }
        return flag;
    }

    private static void calculatePoints(List<Integer> list) {
        long score = 0;
        //计算积分
        long winNum = list.stream().filter(p -> p == 1).count();
        long loseNum = list.stream().filter(p -> p == 0).count();
        score = (winNum - loseNum) * 3;

        String strScore = list.stream().map(r -> r.toString()).collect(Collectors.joining(""));

        int length = strScore.split(SUCCESS_5).length;
        if (length > 1) {
            score = score + (length - 1) * 5;
        } else {
            length = strScore.split(SUCCESS_3).length;
            score = score + (length - 1) * 2;
        }
        System.out.println("您的最终得分为：" + score);

    }
}
