package com.twschool.practice;

import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;

import java.util.Scanner;

public class GuessNumberGameConsoleApp {
    
    public static void main(String[] args) {
        GuessNumberGame guessNumberGame = new GuessNumberGame(new AnswerGenerator());
        System.out.println("please guess number: eg, 1 2 3 4");
        while (guessNumberGame.getStatus() == GameStatus.CONTINUED) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            
            String result = guessNumberGame.guess(userInput);
            
            System.out.println(String.format("guess result: %s", result));
        }
        
        if (guessNumberGame.getStatus() == GameStatus.SUCCEED) {
            System.out.println("Congratulations, you guess succeed");
        }
        if (guessNumberGame.getStatus() == GameStatus.FAILED) {
            System.out.println("Sorry, you guess failed");
        }
    }
}
