package com.saisundu.wikipediachallenge;

import com.saisundu.wikipediachallenge.api.WikipediaAnswerMatcher;
import com.saisundu.wikipediachallenge.exception.WikipediaAnswerMatcherException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by i033118 on 6/3/17.
 */
public class WikipediaAnswerMatcherTest {
    private String outputListDelimiter = "\n";
    private String wikipediaMainTextTestString = "Zebras are several species of African equids (horse family) united by their distinctive black and white stripes. " +
            "Their stripes come in different patterns, unique to each individual. " +
            "They are generally social animals that live in small harems to large herds. " + "" +
            "Unlike their closest relatives, horses and donkeys, zebras have never been truly domesticated. " +
            "There are three species of zebras: the plains zebra, the Grévy's zebra and the mountain zebra. " +
            "The plains zebra and the mountain zebra belong to the subgenus Hippotigris, but Grévy's zebra is the sole species of subgenus Dolichohippus. " +
            "The latter resembles an ass, to which it is closely related, while the former two are more horse-like. " +
            "All three belong to the genus Equus, along with other living equids. The unique stripes of zebras make them one of the animals most familiar to people. " +
            "They occur in a variety of habitats, such as grasslands, savannas, woodlands, thorny scrublands, mountains, and coastal hills. " +
            "However, various anthropogenic factors have had a severe impact on zebra populations, in particular hunting for skins and habitat destruction. " +
            "Grévy's zebra and the mountain zebra are endangered. " +
            "While plains zebras are much more plentiful, one subspecies, the quagga, became extinct in the late 19th century – " +
            "though there is currently a plan, called the Quagga Project, that aims to breed zebras that are phenotypically similar to the quagga in a process called breeding back.\n";

    private String questionsTestList = "Which Zebras are endangered?\n" +
            "What is the aim of the Quagga Project?\n" +
            "Which animals are some of their closest relatives?\n" +
            "Which are the three species of zebras?\n" +
            "Which subgenus do the plains zebra and the mountain zebra belong to?\n";

    private String semicolonDelimitedInputAnswersTestList = "subgenus Hippotigris;" +
            "the plains zebra, the Grévy's zebra and the mountain zebra;" +
            "horses and donkeys;" +
            "aims to breed zebras that are phenotypically similar to the quagga;" +
            "Grévy's zebra and the mountain zebra";

    private String delimitedExpectedAnswersTestList = "Grévy's zebra and the mountain zebra"+outputListDelimiter +
            "aims to breed zebras that are phenotypically similar to the quagga"+outputListDelimiter +
            "horses and donkeys"+outputListDelimiter +
            "the plains zebra, the Grévy's zebra and the mountain zebra"+outputListDelimiter +
            "subgenus Hippotigris";


    private String wikipediaMainTextTestString_APJ = "Avul Pakir Jainulabdeen Abdul Kalam better known as A.P.J. Abdul Kalam (15 October 1931 – 27 July 2015) was the 11th President of India from 2002 to 2007. " +
            "A career scientist turned statesman, Kalam was born and raised in Rameswaram, Tamil Nadu, and studied physics and aerospace engineering. " +
            "He spent the next four decades as a scientist and science administrator, mainly at the Defence Research and Development Organisation (DRDO) and Indian Space Research Organisation (ISRO) and was intimately involved in India's civilian space programme and military missile development efforts. " +
            "He thus came to be known as the Missile Man of India for his work on the development of ballistic missile and launch vehicle technology. " +
            "He also played a pivotal organisational, technical, and political role in India's Pokhran-II nuclear tests in 1998, the first since the original nuclear test by India in 1974. " +
            "Kalam was elected as the 11th President of India in 2002 with the support of both the ruling Bharatiya Janata Party and the then-opposition Indian National Congress. " + "" +
            "Widely referred to as the People's President, he returned to his civilian life of education, writing and public service after a single term. He was a recipient of several prestigious awards, including the Bharat Ratna, India's highest civilian honour. " +
            "While delivering a lecture at the Indian Institute of Management Shillong, Kalam collapsed and died from an apparent cardiac arrest on 27 July 2015, aged 83. " +
            "Thousands including national-level dignitaries attended the funeral ceremony held in his hometown of Rameshwaram, where he was buried with full state honours. \n";

    private String questionsTestList_APJ = "Where was Kalam born and raised?\n" +
            "Which year was Kalam elected as the 11th President of India?\n" +
            "What was Kalam came to be known as?\n" +
            "Which is India's highest civilian honour he received?\n"+
            "When was the first nuclear test by India?\n";

    private String semicolonDelimitedInputAnswersTestList_APJ = "1974;" +
            "Rameswaram, Tamil Nadu;" +
            "2002;" +
            "Missile Man of India;" +
            "Bharat Ratna" ;


    private String delimitedExpectedAnswersTestList_APJ = "Rameswaram, Tamil Nadu"+outputListDelimiter +
            "2002"+outputListDelimiter +
            "Missile Man of India"+outputListDelimiter +
            "Bharat Ratna"+outputListDelimiter +
            "1974";


    @Test
    public void testInputWithNoText() throws Exception {
        String wikipediaTestEmptyString = "";
        try {
            WikipediaAnswerMatcher.getInstance ( wikipediaTestEmptyString );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Input text cannot be empty", excep.getMessage () );
        }


    }

    @Test
    public void testInputMustContain7Lines() throws Exception {
        //Pass only the main text while creating an object of the WikipediaAnswerMatcherImpl.Must lead to an exception
        try {
            WikipediaAnswerMatcher.getInstance ( wikipediaMainTextTestString );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Input text must contain 7 lines", excep.getMessage () );
        }


    }

    @Test
    public void testInputMustContain5Questions() throws Exception {
        String wikipediaTest = wikipediaMainTextTestString;
        wikipediaTest += "Which Zebras are endangered?\n";

        wikipediaTest += "What is the aim of the Quagga Project?\n";

        wikipediaTest += "Which animals are some of their closest relatives?\n";

        // wikipediaTest +=  "Which are the three species of zebras?\n";

        // wikipediaTest +=  "Which subgenus do the plains zebra and the mountain zebra belong to?\n";

        wikipediaTest += "subgenus Hippotigris;the plains zebra, the Grévy's zebra and the mountain zebra;horses and donkeys;aims to breed zebras that are phenotypically similar to the quagga;Grévy's zebra and the mountain zebra";

        String expectedOutput = "Grévy's zebra and the mountain zebra\n"
                + "aims to breed zebras that are phenotypically similar to the quagga\n"
                + "horses and donkeys\n"
                + "the plains zebra, the Grévy's zebra and the mountain zebra\n"
                + "subgenus Hippotigris";

        try {
            WikipediaAnswerMatcher.getInstance ( wikipediaTest );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Input text must contain 7 lines", excep.getMessage () );
        }
    }


    @Test
    public void testInpuMustContainJumbledAnswersDelimitedBySemicolon() throws Exception {
        String wikipediaTest = wikipediaMainTextTestString;
        wikipediaTest += questionsTestList;


        wikipediaTest += "subgenus Hippotigris;" + "" +
                "the plains zebra, the Grévy's zebra and the mountain zebra horses and donkeys;" +
                "aims to breed zebras that are phenotypically similar to the quagga;" +
                "Grévy's zebra and the mountain zebra";


        try {
            WikipediaAnswerMatcher.getInstance ( wikipediaTest );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "There must be five answers deleimited by ;", excep.getMessage () );
        }

    }

    @Test
    public void testAllAnswersMustBeSubStringOfMainText() throws Exception {

        String wikipediaTest = wikipediaMainTextTestString;
        wikipediaTest += questionsTestList;


        wikipediaTest += "horses Hippotigris;" + //=>Not a valid substring
                "the plains zebra, the Grévy's zebra and the mountain zebra;" +
                "horses and donkeys;" +
                "aims to breed zebras that are phenotypically similar to the quagga;" +
                "Grévy's zebra and the mountain zebra";


        try {
            WikipediaAnswerMatcher.getInstance ( wikipediaTest );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Each answer must be part of atleast one sentence of the main text", excep.getMessage () );
        }


    }

    @Test
    public void testOutputContainsAnswereInCorrectOrder_BasedOnSampleInput() throws Exception {
        assertEquals ( delimitedExpectedAnswersTestList,
                WikipediaAnswerMatcher.getInstance ( wikipediaMainTextTestString + questionsTestList + semicolonDelimitedInputAnswersTestList ).getOrderedAnswers () );
    }
    @Test
    public void testOutputContainsAnswereInCorrectOrder_APJ_Input() throws Exception {
        //This test is failing. The implementation does not have the intelligence to match different sentences that are semantcially related . For example "die" and "died"
        //This needs further handling.
        assertEquals ( delimitedExpectedAnswersTestList_APJ ,WikipediaAnswerMatcher.getInstance ( wikipediaMainTextTestString_APJ + questionsTestList_APJ + semicolonDelimitedInputAnswersTestList_APJ ).getOrderedAnswers ());
    }

    @Test
    public void testSetInputText() throws Exception {
        String wikipediaTest = wikipediaMainTextTestString;
        wikipediaTest += questionsTestList;


        wikipediaTest += "horses Hippotigris;" + //=>Not a valid substring
                "the plains zebra, the Grévy's zebra and the mountain zebra;" +
                "horses and donkeys;" +
                "aims to breed zebras that are phenotypically similar to the quagga;" +
                "Grévy's zebra and the mountain zebra";


        try {
            WikipediaAnswerMatcher.getInstance ().setInputText ( wikipediaTest );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Each answer must be part of atleast one sentence of the main text", excep.getMessage () );
        }

    }


    @Test
    public void testMaxLimitOnInputLength() throws Exception {
        try{
        WikipediaAnswerMatcher.getInstance ().setInputText ( wikipediaMainTextTestString+wikipediaMainTextTestString+wikipediaMainTextTestString+wikipediaMainTextTestString );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Input text length restricted to 5000 characters", excep.getMessage () );
        }
    }
    @Test
    public void testInputMustBeSetBeforeFetchingResults() throws Exception{
        try{
            WikipediaAnswerMatcher.getInstance ().getOrderedAnswers () ;
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Input text has not been set", excep.getMessage () );
        }

    }

    @Test
    public void testInputMustContain5ValidQuestions() throws Exception {
        String wikipediaTest = wikipediaMainTextTestString;
        wikipediaTest += "Which Zebras are endangered?\n";

        wikipediaTest += "What is the aim of the Quagga Project?\n";

        wikipediaTest += "Which animals are some of their closest relatives?\n";

        wikipediaTest +=  "Which are the three species of zebras\n";

        wikipediaTest +=  "Which subgenus do the plains zebra and the mountain zebra belong to\n";

        wikipediaTest += "subgenus Hippotigris;the plains zebra, the Grévy's zebra and the mountain zebra;horses and donkeys;aims to breed zebras that are phenotypically similar to the quagga;Grévy's zebra and the mountain zebra";

        String expectedOutput = "Grévy's zebra and the mountain zebra\n"
                + "aims to breed zebras that are phenotypically similar to the quagga\n"
                + "horses and donkeys\n"
                + "the plains zebra, the Grévy's zebra and the mountain zebra\n"
                + "subgenus Hippotigris";

        try {
            WikipediaAnswerMatcher.getInstance ( wikipediaTest );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Input text must contain 5 lines of questions", excep.getMessage () );
        }
    }

    @Test
    public void testInputCannotBeEmpty() throws Exception {

        try {
            WikipediaAnswerMatcher.getInstance ( "" );
        } catch (WikipediaAnswerMatcherException excep) {
            assertEquals ( "Input text cannot be empty", excep.getMessage () );
        }
    }


}