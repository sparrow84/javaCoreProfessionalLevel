package com.company.lesson06;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class Lesson06Test {

    @Test
    void afterLastFourEnd() {
        Lesson06 lesson06 = new Lesson06();
        int[] arr = {1,2,3,4};
        assertNull(lesson06.afterLastFour(arr));
    }

    @Test
    void afterLastFourNormal() {
        Lesson06 lesson06 = new Lesson06();
        int[] arr = {1,2,3,4,3,2,1};
        assertArrayEquals(new int[]{3,2,1},lesson06.afterLastFour(arr));
    }

    @Test
    void afterLastFourEmpty() {
        Lesson06 lesson06 = new Lesson06();
        int[] arr = {};
        assertThrows(ArrayEmptyEception.class, () -> {lesson06.afterLastFour(arr);});
    }

    @Test//(expected = ArrayEmptyEception.class)
    void afterLastFourNull() {
        Lesson06 lesson06 = new Lesson06();
        int[] arr = null;
        assertThrows(ArrayIsNotInitialized.class, () -> {lesson06.afterLastFour(arr);});
    }

    @Test
    void atLeastOneFourAndOne001() {
        Lesson06 lesson06 = new Lesson06();
        int[] arr = {1,1,1,1,1};
        assertFalse(lesson06.atLeastOneFourAndOne(arr));
    }

    @Test
    void atLeastOneFourAndOne002() {
        Lesson06 lesson06 = new Lesson06();
        int[] arr = {4,4,4,4,4};
        assertFalse(lesson06.atLeastOneFourAndOne(arr));
    }

    @Test
    void atLeastOneFourAndOne003() {
        Lesson06 lesson06 = new Lesson06();
        int[] arr = {1,4,4,4,4};
        assertTrue(lesson06.atLeastOneFourAndOne(arr));
    }

    @Test
    void atLeastOneFourAndOne004() {
        Lesson06 lesson06 = new Lesson06();
        int[] arr = {4,1,1,1,1};
        assertTrue(lesson06.atLeastOneFourAndOne(arr));
    }

}