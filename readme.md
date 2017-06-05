# Problem Statement 
You are provided with short paragraphs of text from Wikipedia, about well known places,

persons, animals, flowers etc. For each paragraphs, there is a set of five simple questions,

which can be directly answered by reading the paragraph. You are also provided with the

answers to each of these questions, though they are jumbled up, and provided in no

specific order. Your task is to identify, which answer corresponds to which question.

# Input Format
  
  The first line contains a short paragraph of text from Wikipedia.
  
  Line Number 2 to 6 contain a group of 5 simple questions, expecting factual answers,
  
  which can be directly answered by reading the provided paragraph.
  
  Line Number 7 contains the five answers which have been jumbled up. The answers are
  
  grouped into one string and separated by semi-colons.
  
# Output Format
  
  Five lines - the first line should contain the answer to the first question, second line should
  
  contain the answer to the second question and so on.
  
  The answers should be entirely confined to one of the possible answers listed in the last
  
  line of the input file, i.e. Line Number 7.
  
# Sample Input
 
 Zebras are several species of African equids (horse family) united by their distinctive black
 
 and white stripes. Their stripes come in different patterns, unique to each individual. They
 
 are generally social animals that live in small harems to large herds. Unlike their closest
 
 relatives, horses and donkeys, zebras have never been truly domesticated. There are
 
 three species of zebras: the plains zebra, the Grévy's zebra and the mountain zebra. The
 
 plains zebra and the mountain zebra belong to the subgenus Hippotigris, but Grévy's
 
 zebra is the sole species of subgenus Dolichohippus. The latter resembles an ass, to
 
 which it is closely related, while the former two are more horse-like. All three belong to the
 
 genus Equus, along with other living equids. The unique stripes of zebras make them one
 
 of the animals most familiar to people. They occur in a variety of habitats, such as
 
 grasslands, savannas, woodlands, thorny scrublands, mountains, and coastal hills.
 
 However, various anthropogenic factors have had a severe impact on zebra populations,
 
 in particular hunting for skins and habitat destruction. Grévy's zebra and the mountain
 
 zebra are endangered. While plains zebras are much more plentiful, one subspecies, the
 
 quagga, became extinct in the late 19th century – though there is currently a plan, called
 
 the Quagga Project, that aims to breed zebras that are phenotypically similar to the
 
 quagga in a process called breeding back.
 
 Which Zebras are endangered?
 
 What is the aim of the Quagga Project?
 
 Which animals are some of their closest relatives?
 
 Which are the three species of zebras?
 
 Which subgenus do the plains zebra and the mountain zebra belong to?
 
 subgenus Hippotigris;the plains zebra, the Grévy's zebra and the mountain zebra;horses
 
 and donkeys;aims to breed zebras that are phenotypically similar to the quagga;Grévy's
 
 zebra and the mountain zebra
 
# Sample Output
 
 Grévy's zebra and the mountain zebra

 aims to breed zebras that are phenotypically similar to the quagga
 
 horses and donkeys
 
 the plains zebra, the Grévy's zebra and the mountain zebra
 
 subgenus Hippotigris
 
# Design
![alt text](https://github.com/saisundu/WikipediaSolution/blob/master/Design.jpg "Design")

# API
The API provides a simple interface to get an instance of the Wikipedia Answer Matcher to which the input text can be set .
The API also provides a method to get the desired output(ordered answers) with the method getOrderedAnswers().
Please have a look at the Unit Tests and the below code snippet to get started with using the API.

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

    private String outputListDelimiter = "\n";
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
            
    WikipediaAnswerMatcher.getInstance ( wikipediaMainTextTestString + questionsTestList + semicolonDelimitedInputAnswersTestList ).getOrderedAnswers ();
'

# Tests
Most of the implementation has been done using the Test Driven Development approach and hence covers 100% of the implemented functionality. 
Please refer to the unit tests for set of covered test scenarios.
## Test Cases
![alt text](https://github.com/saisundu/WikipediaSolution/blob/master/testCoverageResults/UnitTests.jpg "Test Cases")

## Test Case Code Coverage
![alt text](https://github.com/saisundu/WikipediaSolution/blob/master/testCoverageResults/UnitTestsCoverage.jpg "Test Cases Code Coverage")

 
