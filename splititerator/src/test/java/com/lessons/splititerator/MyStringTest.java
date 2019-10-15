package com.lessons.splititerator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MyStringTest {

    @Test
    void streamTest() {
        String[] array = {"one", "two", "three", "four", "five"};
        MyString ms = new MyString(array);

        assertEquals(ms.stream().count(), 5);
    }

    @Test
    void parallelStreamTest() {
        String[] array2 = {"one", "two", "three", "four", "five", "six"};
        MyString ms = new MyString(array2);
        assertEquals(ms.parallelStream().count(), 3);
    }

}
