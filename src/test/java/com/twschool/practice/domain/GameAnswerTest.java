package com.twschool.practice.domain;

import com.twschool.practice.domain.GameAnswer;
import org.junit.Assert;
import org.junit.Test;

public class GameAnswerTest {
    @Test
    public void should_return_4A0B_when_compare_with_1234_given_answer_1234() {
        GameAnswer answer = new GameAnswer("1 2 3 4");
        
        String result = answer.check("1 2 3 4");

        Assert.assertEquals("4A0B", result);
    }

    @Test
    public void should_return_4A0B_when_compare_with_1256_given_answer_1234() {
        GameAnswer answer = new GameAnswer("1 2 3 4");

        String result = answer.check("1 2 5 6");

        Assert.assertEquals("2A0B", result);
    }

    @Test
    public void should_return_0A4B_when_compare_with_4321_given_answer_1234() {
        GameAnswer answer = new GameAnswer("1 2 3 4");

        String result = answer.check("4 3 2 1");

        Assert.assertEquals("0A4B", result);
    }

    @Test
    public void should_return_true_when_check_is_valid_format_given_answer_1234() {
        GameAnswer answer = new GameAnswer("1 2 3 4");

        boolean isValidFormat = answer.isValidFormat();

        Assert.assertTrue(isValidFormat);
    }

    @Test
    public void should_return_false_when_check_is_valid_format_given_answer_12345() {
        GameAnswer answer = new GameAnswer("1 2 3 4 5");

        boolean isValidFormat = answer.isValidFormat();

        Assert.assertFalse(isValidFormat);
    }

    @Test
    public void should_return_false_when_check_is_valid_format_given_answer_1123() {
        GameAnswer answer = new GameAnswer("1 1 2 3");

        boolean isValidFormat = answer.isValidFormat();

        Assert.assertFalse(isValidFormat);
    }

    @Test
    public void should_return_false_when_check_is_valid_format_given_answer_abcd() {
        GameAnswer answer = new GameAnswer("a b c d");

        boolean isValidFormat = answer.isValidFormat();

        Assert.assertFalse(isValidFormat);
    }

    @Test
    public void should_return_false_when_check_is_valid_format_given_answer_abcd1234() {
        GameAnswer answer = new GameAnswer("a b c d 1 2 3 4");

        boolean isValidFormat = answer.isValidFormat();

        Assert.assertFalse(isValidFormat);
    }

}
