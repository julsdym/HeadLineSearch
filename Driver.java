package headlinesearch;
import java.util.ArrayList;

/**
 * This class is designed to test each method in the headLineSearch file
 * interactively
 * 
 * @author Julia Dymnicki
 * @author Tanvi Yamarthy
 */

public class Driver {
    private static headlineSearch article = new headlineSearch();

    public static void main(String[] args){
        String[] methods = {"readFile", "mostPopularPhraseInDatabase", 
        "numberOfHeadLinesInCategory","categoriesArrayListCreator" ,"mostPopularCategory", "mostCommonKeyWordInHeadLine", 
        "mostPopularWordsInHeadLine", "mostPopularKeyWordInCategory", "findHeadLinesGivenKeyWord"
        , "distanceBetweenKeywords", "mostRelevantHeadLine", "headlineRank"};
        String[] options = {"Test a new database file.", "Test another method on the same database file.", "Quit."};

        int option = 1;
        
        StdOut.println("\nWhich method would you like to test?");
        for (int i = 0; i < methods.length; i++){
            StdOut.printf("%d. %s\n", i + 1, methods[i]);
        }

        StdOut.print("Enter a number -> ");
        int choice = Integer.parseInt(StdIn.readLine());


        do{
            do{
        switch (choice){
            //read the file
            case 1:
                StdOut.println("Enter a database input file -> ");
                String input = StdIn.readAll();
                testReadFile(input);
                break;

        //mostPopularPhraseInDatabase
            case 2:
                StdOut.println("Enter phrase to test the number of times it occurs in the database -> ");
                String inputPhrase = StdIn.readLine();
                testMostPopularPhraseInDatabase(inputPhrase);
                break;

        //numberOfHeadLinesInCategory
            case 3:
            StdOut.println("Enter a category to test the number of headlines in the category -> ");
            String inputCategory = StdIn.readLine();
            testNumberOfHeadLinesInCategory(inputCategory);
            break;

        //categoriesArrayListCreator
            case 4:
            testCategoriesArrayListCreator();
            break;

        //mostPopularCategory
            case 5:
            testMostPopularCategory();
            break;

        //mostCommonKeyWordInHeadLine
            case 6:
            StdOut.println("Enter a headline to return the most common word in that headline -> ");
            String inputHeadLine = StdIn.readLine();
            testMostCommonKeyWordInHeadLine(inputHeadLine);
            break;

        //mostPopularWordsInHeadLine
            case 7:
            testMostPopularWordsInHeadLine();
            break;

        //mostPopularKeyWordInCategory
            case 8:
            StdOut.println("Enter a category to return the most common keyword from that category -> ");
            String inputCat = StdIn.readLine();
            testMostPopularKeyWordInCategory(inputCat);
            break;

        //findHeadLinesGivenKeyWord
            case 9:
            StdOut.println("Enter first keyword -> ");
            String in1 = StdIn.readLine();
            StdOut.println("Enter second keyword -> ");
            String in2 = StdIn.readLine();
            testFindHeadLinesGivenKeyWord(in1, in2);
            break;

        //distanceBetweenKeywords
            case 10:
            StdOut.println("Enter headline -> ");
            String h = StdIn.readLine();
            StdOut.println("Enter first keyword -> ");
            String one = StdIn.readLine();
            StdOut.println("Enter second keyword -> ");
            String two = StdIn.readLine();
            testDistanceBetweenKeywords(h, one, two);
            break;

        //mostRelevantHeadLine
            case 11:
            StdOut.println("Enter first keyword -> ");
            String o = StdIn.readLine();
            StdOut.println("Enter second keyword -> ");
            String t = StdIn.readLine();
            testMostRelevantHeadLine(o, t);
            break;

        //headlineRank
            case 12:
            StdOut.println("Enter first keyword -> ");
            String uno = StdIn.readLine();
            StdOut.println("Enter second keyword -> ");
            String dos = StdIn.readLine();
            testHeadlineRank(uno, dos);
            break;

            default:
                StdOut.println("Not a valid option!");

        }
        StdOut.println("\nWhat would you like to do now?");

        for(int i=0; i< options.length;i++){
            StdOut.printf("%d. %s\n", i + 1, options[i]);
        }
        StdOut.print("Enter a number -> ");
        option = Integer.parseInt(StdIn.readLine());
    }while (option == 2);
    }while (option ==1);
    }


    private static headlineSearch testReadFile(String inputFile){
        article.readFile(inputFile);
        return article;
    }

    private static void testMostPopularPhraseInDatabase(String testPhrase){
        int x = article.mostPopularPhraseInDatabase(testPhrase);
        StdOut.println("The number of times the phrase " + testPhrase + " occurs is " + x + " times.");
    }

    private static void testNumberOfHeadLinesInCategory(String testCategory){
        int x = article.numberOfHeadLinesInCategory(testCategory);
        StdOut.println("The number of headlines in the category " + testCategory + " is " + x + " headlines.");
    }

    private static headlineSearch testCategoriesArrayListCreator(){
        article.categoriesArrayListCreator();
        return article;
    }

    private static void testMostPopularCategory(){
        StdOut.println("The most popular category in this database is: " + article.mostPopularCategory()+ ".");
    }

    private static void testMostCommonKeyWordInHeadLine(String testHeadLine){
        StdOut.println("The most common word in this headline is: " + article.mostCommonKeyWordInHeadLine(testHeadLine)+ ".");
    }

    private static headlineSearch testMostPopularWordsInHeadLine(){
        article.mostPopularWordsInHeadLine();
        return article;
    }

    private static void testMostPopularKeyWordInCategory(String testCat){
        StdOut.println("The most common keyword in this category is: " + article.mostPopularKeyWordInCategory(testCat)+ ".");
    }

    private static void testFindHeadLinesGivenKeyWord(String k1, String k2){
        ArrayList<String> x = article.findHeadLinesGivenKeyWord(k1, k2);
        StdOut.println("The headlines found given those keywords are: ");
        for(int i=0; i< x.size();i++){
            StdOut.println(x.get(i));
        }
    }

    private static void testDistanceBetweenKeywords(String head, String k1, String k2){
        StdOut.println("The distance between those two keywords given that headline is "+ article.distanceBetweenKeywords(head, k1, k2) + " words.");
    }

    private static void testMostRelevantHeadLine(String first, String second){
        StdOut.println("The most relevant headline given those keywords is: " + article.mostRelevantHeadLine(first, second) + ".");
    }

    private static void testHeadlineRank(String k1, String k2){
        StdOut.println("The ranking of most relevant headlines given those keywords is: ");
        ArrayList<String> x = article.headlineRank(k1, k2);
        for(int i =0; i< x.size();i++){
            StdOut.println(x.get(i));
        }
    }
}
