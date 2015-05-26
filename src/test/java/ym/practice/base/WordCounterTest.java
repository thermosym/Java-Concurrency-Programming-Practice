package ym.practice.base;

import org.junit.Test;

public class WordCounterTest {
    private static final String TEXT1 = "one two three one two one";
    private static final String TEXT2 = "five six\tseven#five";
    private static final String TEXT3 = "eight; nine\t?!ten<eleven...eight";
    private static final WordCount COUNTS1 = new WordCount();
    private static final WordCount COUNTS2 = new WordCount();
    private static final WordCount COUNTS3 = new WordCount();

    static {
        COUNTS1.add("one", 3).add("two", 2).add("three", 1);
        COUNTS2.add("five", 2).add("six", 1).add("seven", 1);
        COUNTS3.add("eight", 2).add("nine", 1).add("ten", 1).add("eleven", 1);
    }

    @Test
    public void testCountWordsString() {
//        WordCounts result = WordUtils.countWords(createText(), Character::isAlphabetic);
//        assertEquals(combineCounts(), result);
    }

}
