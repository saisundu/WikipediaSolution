package com.saisundu.wikipediachallenge.api;


import com.saisundu.wikipediachallenge.exception.WikipediaAnswerMatcherException;

import java.util.HashMap;
import java.util.Map;


/**
 * WikipediaAnswerMatcherImpl.java
 * Implementation Public Interface WikipediaAnswerMatcher for Wikipedia Answer Matching Problem.
 * Implements the methods required to use the Wikipedia Answer Matcher Implementation
 *
 * @author Sundaresan Krishnamurthy
 * @since 06-04-2017
 */


public class WikipediaAnswerMatcherImpl implements WikipediaAnswerMatcher {

    private String textInput;
    private String[] jumbledAnswers;
    private String[] listOfInputQuestionsTrimmed = new String[EXPECTED_INPUT_QUESTIONS];
    private String[] listOfInputQuestions = new String[EXPECTED_INPUT_QUESTIONS];
    private String[] mainTextSentences;
    private Map<String, String> mapOfQuestionsWithMatchingAnswers = new HashMap<String, String> ();
    private Map<String, String> mapOfQuestionsWithSentences = new HashMap<String, String> ();
    private Map<String, String[]> mapOfSentensesWithMatchingAnswers = new HashMap<String, String[]> ();

    /**
     * Checks the input text for conformance to the input guidelines. Parses the string to derive the required output.
     *
     * @param textInput the String containing the main text in the first sentence followed by 5 questions in each line and the jumbled answers delimited by ";"
     * @return <tt>WikipediaAnswerMatcher</tt> if the input String is a valid string in the required format
     * @throws WikipediaAnswerMatcherException if the <tt>textInput</tt> is invalid
     */
    private void checkAndParseInput(String textInput) throws WikipediaAnswerMatcherException {
        if ( textInput.length () > INPUT_LENGTH_UPPER_LIMIT ) {
            throw new WikipediaAnswerMatcherException ( "Input text length restricted to 5000 characters" );
        }
        if ( textInput == "" || textInput == null ) {
            throw new WikipediaAnswerMatcherException ( "Input text cannot be empty" );
        }
        this.textInput = textInput;
        parseInputText ();
    }

    /**
     * Constructor implementation with textInput. Calls method checkAndParseInput internally.
     *
     * @param textInput the String containing the main text in the first sentence followed by 5 questions in each line and the jumbled answers delimited by ";"
     * @throws WikipediaAnswerMatcherException if the <tt>textInput</tt> is invalid
     */
    WikipediaAnswerMatcherImpl(String textInput) throws WikipediaAnswerMatcherException {
        checkAndParseInput ( textInput );

    }

    /**
     * Default Constructor implementation .
     */
    WikipediaAnswerMatcherImpl() {

    }

    /**
     * Implementation of method setInputText
     * Sets the input text on an existing instance of WikipediaAnswerMatcher.
     *
     * @param textInput the String containing the main text in the first sentence followed by 5 questions in each line and the jumbled answers delimited by ";"
     * @throws WikipediaAnswerMatcherException if the <tt>inputText</tt> is invalid
     */
    public void setInputText(String textInput) throws WikipediaAnswerMatcherException {
        checkAndParseInput ( textInput );

    }

    /**
     * Parses the input text and prepares the internal state .
     *
     * @throws WikipediaAnswerMatcherException if the <tt>inputText</tt> is invalid
     */
    private void parseInputText() throws WikipediaAnswerMatcherException {
        //spilt the input text with new line character to differentiate main text, questions and jumbled answers
        String lines[] = textInput.split ( "\\r?\\n" );

        //check if each of the input parts are of the write length
        checkInputIsOfCorrectLength ( lines );
        //split the main text into individual sentences
        mainTextSentences = lines[0].split ( "\\." );

        //check if the questions part of the input contains valid question
        checkInputContainsValidQuestions ( lines );
        //split the answers part of the input to get a list of jumbled answers
        jumbledAnswers = lines[6].split ( ";" );
        //check each answer is contained part of atleast one of the sentences of the main text
        checkInputContainsValidAnswers ();
        //(1)map each sentence of the main text one or more matching answers
        mapSentencesWithMatchingAnswers ();
        //(2)map each of the questions to one of the sentences of the main text based on occurence of question parts
        mapQuestionsToSentences ();
        //Use the maps in (1) and (2) to map each question with a matching answer.
        mapQuestionsToAnswers ();


    }

    /**
     * Iterates through the list of main text sentences and maps them to one or more answers which are contained in the main text sentence
     */
    private void mapSentencesWithMatchingAnswers() {
        for (String mainTextSentence : mainTextSentences) {
            boolean foundAnswerAsSubstring = false;
            String matchingAnswers = "";

            for (String jumbledAnswer : jumbledAnswers) {
                if ( mainTextSentence.contains ( jumbledAnswer ) ) {
                    foundAnswerAsSubstring = true;
                    matchingAnswers += jumbledAnswer + "\n";

                }

            }
            if ( matchingAnswers.length () != 0 ) {
                String[] matchingAnswersAsArray = matchingAnswers.split ( "\n" );
                mapOfSentensesWithMatchingAnswers.put ( mainTextSentence, matchingAnswersAsArray );
            }
            /*if (foundAnswerAsSubstring == false) {
                throw new IllegalArgumentException("Each answer must be part of atleast one sentence of the main text");
            }*/


        }

    }

    /**
     * Checks if the answer list provided contains valid answers which are sub strings of the main text sentences
     */
    private void checkInputContainsValidAnswers() throws WikipediaAnswerMatcherException {
        if ( jumbledAnswers == null || jumbledAnswers.length < EXPECTED_INPUT_ANSWERS ) {
            throw new WikipediaAnswerMatcherException ( "There must be five answers deleimited by ;" );
        }
    }

    /**
     * Checks if the answer list provided contains valid questions.
     */
    private void checkInputContainsValidQuestions(String[] lines) throws WikipediaAnswerMatcherException {
        for (int i = 1; i <= 5; i++) {
            if ( !isValidQuestion ( lines[i] ) ) {
                throw new WikipediaAnswerMatcherException ( "Input text must contain 5 lines of questions" );
            } else {
                listOfInputQuestions[i - 1] = lines[i];
                listOfInputQuestionsTrimmed[i - 1] = lines[i].replace ( "?", "" );
            }
        }
    }

    /**
     * Checks if the input text contains exactly 7 lines.
     */
    private void checkInputIsOfCorrectLength(String[] lines) throws WikipediaAnswerMatcherException {

        if ( lines.length != INPUT_LINES ) {
            throw new WikipediaAnswerMatcherException ( "Input text must contain 7 lines" );
        }

    }

    /**
     * Checks that each of the question string is a valid question.
     */
    private boolean isValidQuestion(String questionString) {

        return questionString.matches ( "([^.?!]*)\\?" );

    }


    public String getOrderedAnswers() throws WikipediaAnswerMatcherException {
        if ( textInput != null ) {
            StringBuffer delimitedListOfOutputAnswers = new StringBuffer ();
            for (String inputQuestion : listOfInputQuestions
                    ) {

                if ( delimitedListOfOutputAnswers.length () != 0 ) {
                    delimitedListOfOutputAnswers.append ( "\n" );
                }
                delimitedListOfOutputAnswers.append ( mapOfQuestionsWithMatchingAnswers.get ( inputQuestion ) );

            }
            return delimitedListOfOutputAnswers.toString ();
        } else {
            throw new WikipediaAnswerMatcherException ( "Input text has not been set" );
        }

    }


    private Map<String, String> mapQuestionsToSentences() {

        for (String question : listOfInputQuestions) {
            String questionTrimmed = question.toLowerCase ().replace ( "?", "" ).trim ();
            if ( questionTrimmed.toLowerCase ().matches ( "which (.*)" ) )
                questionTrimmed = questionTrimmed.replaceFirst ( "which", "" ).trim ();
            if ( questionTrimmed.toLowerCase ().matches ( "what (.*)" ) )
                questionTrimmed = questionTrimmed.replaceFirst ( "what", "" ).trim ();
            if ( questionTrimmed.toLowerCase ().matches ( "when (.*)" ) )
                questionTrimmed = questionTrimmed.replaceFirst ( "when", "" ).trim ();
            if ( questionTrimmed.toLowerCase ().matches ( "where (.*)" ) )
                questionTrimmed = questionTrimmed.replaceFirst ( "where", "" ).trim ();

            for (String questionPart : getArrayOfQuestionParts ( questionTrimmed )
                    ) {
                String matchStringInMainText = matchQuestionPartInMainText ( questionPart );
                if ( matchStringInMainText != null ) {
                    mapOfQuestionsWithSentences.put ( question, matchStringInMainText );
                    break;
                }

            }
        }
        return mapOfQuestionsWithSentences;
    }

    private Map<String, String> mapQuestionsToAnswers() {

        for (String inputQuestion : listOfInputQuestions) {
            String[] matchingAnswers = mapOfSentensesWithMatchingAnswers.get ( mapOfQuestionsWithSentences.get ( inputQuestion ) );
            if ( matchingAnswers != null )
                mapOfQuestionsWithMatchingAnswers.put ( inputQuestion, matchingAnswers[0] );
            else
                mapOfQuestionsWithMatchingAnswers.put ( inputQuestion, "<ANSWER COULD NOT BE FOUND>" );
        }

        return mapOfQuestionsWithMatchingAnswers;

    }


    private String matchQuestionPartInMainText(String questionPart) {

        if ( questionPart.length () != 0 ) {
            for (int i = 0; i < mainTextSentences.length; i++) {
                if ( mainTextSentences[i].toLowerCase ().contains ( questionPart.toLowerCase () ) ) {
                    return mainTextSentences[i];
                }
            }
        }
        return null;
    }

    private String[] getArrayOfQuestionParts(String questionTrimmed) {
        String[] wordsOfQuestion = questionTrimmed.split ( " " );
        String[] arrayOfQuestionParts = new String[wordsOfQuestion.length];
        arrayOfQuestionParts[0] = questionTrimmed;
        int arrayOfQuestionPartsIndex = 0;
        for (String word : wordsOfQuestion
                ) {
            String questionPart = questionTrimmed.replaceFirst ( word, "" ).trim ();
            questionTrimmed = questionPart;
            arrayOfQuestionParts[arrayOfQuestionPartsIndex] = questionPart;
            arrayOfQuestionPartsIndex++;

        }

        return arrayOfQuestionParts;

    }
}

