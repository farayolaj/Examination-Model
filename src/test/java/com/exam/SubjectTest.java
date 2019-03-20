package com.exam;

import com.exam.examiner.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SubjectTest {

    @Test
    public void setup(){
        Subject sub = Subject.parse("file2.json");
        sub.add("Is there life?");
        String stringifyResult = sub.stringify();
        System.out.println(stringifyResult);
        Assert.assertTrue(stringifyResult.contains("16."));
    }
}
