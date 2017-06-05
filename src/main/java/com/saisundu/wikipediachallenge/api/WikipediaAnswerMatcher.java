package com.saisundu.wikipediachallenge.api;

import com.saisundu.wikipediachallenge.exception.WikipediaAnswerMatcherException;

/**
 *
 * WikipediaAnswerMatcher.java
 * Public Interface for Wikipedia Answer Matching Problem.
 * Exposes the methods required to use the Wikipedia Answer Matcher Implementation
 *
 * @author Sundaresan Krishnamurthy
 * @since 06-04-2017
 */

public interface WikipediaAnswerMatcher {

    public final int INPUT_LINES = 7;
    public final int INPUT_LENGTH_UPPER_LIMIT = 5000;
    public final int EXPECTED_INPUT_QUESTIONS = 5;
    public final int EXPECTED_INPUT_ANSWERS = 5;

    public String getOrderedAnswers() throws WikipediaAnswerMatcherException;
    /**
     * Returns an instance of <tt>WikipediaAnswerMatcher</tt> for processing the input text and derive the ordered answers list
     * @param textInput the String containing the main text in the first sentence followed by 5 questions in each line and the jumbled answers delimited by ";"
     * @return <tt>WikipediaAnswerMatcher</tt> if the input String is a valid string in the required format
     * @throws WikipediaAnswerMatcherException if the <tt>textInput</tt> is invalid
     */
    public static WikipediaAnswerMatcher getInstance(String textInput) throws WikipediaAnswerMatcherException{
        return new WikipediaAnswerMatcherImpl ( textInput );
    }
    /**
     * Returns an instance of <tt>WikipediaAnswerMatcher</tt> for processing the input text and derive the ordered answers list.
     * @return <tt>WikipediaAnswerMatcher</tt> if the input String is a valid string in the required format
     */
    public static WikipediaAnswerMatcherImpl getInstance() {
        return new WikipediaAnswerMatcherImpl (  );
    }
    /**
     * Sets the input text on an existing instance of WikipediaAnswerMatcher.
     * @param inputText the String containing the main text in the first sentence followed by 5 questions in each line and the jumbled answers delimited by ";"
     * @throws WikipediaAnswerMatcherException if the <tt>inputText</tt> is invalid
     */
    public void setInputText(String inputText) throws WikipediaAnswerMatcherException;

}
