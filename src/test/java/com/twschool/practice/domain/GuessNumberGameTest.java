package com.twschool.practice.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GuessNumberGameTest {

    private GuessNumberGame guessNumberGame;
    
    @Before
    public void setUp() throws Exception {
        GameAnswer gameAnswer = new GameAnswer("1 2 3 4");

        AnswerGenerator answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generateAnswer()).thenReturn(gameAnswer);
        guessNumberGame = new GuessNumberGame(answerGenerator);
    }

    @Test
    public void should_return_4A0B_when_input_1234_given_game_with_answer_1234() {
        String result = guessNumberGame.guess("1 2 3 4");

        Assert.assertEquals("4A0B", result);
    }

    @Test
    public void should_return_2A0B_when_input_1256_given_game_with_answer_1234() {
        String result = guessNumberGame.guess("1 2 5 6");

        Assert.assertEquals("2A0B", result);
    }

    @Test
    public void should_return_succeed_when_get_status_after_input_1234_given_game_with_answer_1234() {
        guessNumberGame.guess("1 2 3 4");

        GameStatus gameStatus = guessNumberGame.getStatus();

        Assert.assertEquals(GameStatus.SUCCEED, gameStatus);
    }

    @Test
    public void should_return_continued_when_get_status_after_input_1256_given_game_with_answer_1234() {
        guessNumberGame.guess("1 2 5 6");

        GameStatus gameStatus = guessNumberGame.getStatus();

        Assert.assertEquals(GameStatus.CONTINUED, gameStatus);
    }

    @Test
    public void should_return_failed_when_get_status_after_input_1256_6_times_given_game_with_answer_1234() {
        guessNumberGame.guess("1 2 5 6");
        guessNumberGame.guess("1 2 5 6");
        guessNumberGame.guess("1 2 5 6");
        guessNumberGame.guess("1 2 5 6");
        guessNumberGame.guess("1 2 5 6");
        guessNumberGame.guess("1 2 5 6");

        GameStatus gameStatus = guessNumberGame.getStatus();

        Assert.assertEquals(GameStatus.FAILED, gameStatus);
    }

}
