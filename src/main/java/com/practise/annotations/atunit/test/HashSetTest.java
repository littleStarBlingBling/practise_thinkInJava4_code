package com.practise.annotations.atunit.test;

import java.util.HashSet;

public class HashSetTest {
    HashSet<String> testObject = new HashSet<>();

    @Test
    void initialization(){
        assert testObject.isEmpty();
    }

    @Test
    void _Contains(){
        testObject.add("one");
        assert testObject.contains("one");
    }

    @Test
    void _Remove(){
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }
}
