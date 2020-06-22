package com.twschool.practice.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnswerGenerator {
    public GameAnswer generateAnswer() {
        List<String> sourceNumbers = IntStream.rangeClosed(0, 9).boxed().map(String::valueOf).collect(Collectors.toList());
        Collections.shuffle(sourceNumbers);
        
        List<String> answerNumbers = sourceNumbers.stream().limit(4).collect(Collectors.toList());
        return new GameAnswer(String.join(" ", answerNumbers));
    }
}
