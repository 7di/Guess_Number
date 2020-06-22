package com.twschool.practice.domain;

import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GameAnswer;
import org.junit.Assert;
import org.junit.Test;

public class AnswerGeneratorTest {
    @Test
    public void should_generate_valid_answer() {
        AnswerGenerator answerGenerator = new AnswerGenerator();
        
        GameAnswer gameAnswer = answerGenerator.generateAnswer();

        Assert.assertTrue(gameAnswer.isValidFormat());
    }
}
