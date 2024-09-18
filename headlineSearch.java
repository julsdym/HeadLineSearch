package headlinesearch;

import java.util.ArrayList;
import java.util.Scanner;

/*************************************************************************
 * Compilation: javac headlineSearch.java
 * Execution: java headLineSearch
 * 
 * The headlineSearch class represents real news headlines from 2012 to 2022
 * from a given database provided by HuffPost. This class is used to
 * represent a search engine in order to search for and return
 * headlines, keywords, and phrases.
 *
 * @author Julia Dymnicki
 *
 *************************************************************************/

public class headlineSearch {

    /*
     * The database ArrayList keeps track of headlineObjects that
     * are loaded from a database file.
     * Each index corresponds to 1 headlineObject from the database.
     */
    private ArrayList<headlineObject> database;

    /*
     * This ArrayList keeps track of the categories that
     * currently exist in the database, taken from headlineObjects.
     */
    private ArrayList<String> arrayListOfCategories;

    /*
     * Constructor creates and initializes the @database and
     * 
     * @arrayListOfCategories ArrayLists.
     */
    public headlineSearch() {
        database = new ArrayList<>();
        arrayListOfCategories = new ArrayList<>();
    }

    /*
     * Getter method for the database and category ArrayLists.
     * *** DO NOT REMOVE ****
     */
    public ArrayList<headlineObject> getDatabase() {
        return database;
    }

    public ArrayList<String> getArrayListOfCategories() {
        return arrayListOfCategories;
    }

    /*
     * This method reads the information about a headline
     * from an input database file and populates the database ArrayList.
     * 
     * Each headLineObject is a line in the input database file.
     * 
     * @param dataFile
     */
    public void readFile(String dataFile) {

        StdIn.setFile(dataFile); // opens the file

        while (StdIn.hasNextLine()) {
            String[] line = StdIn.readLine().split("link|headline|category|short_description|authors|date");

            String link = line[1].replaceAll("\"|,|:", "");
            String headline = line[2].replaceAll("\"|,|:", "");
            String category = line[3].replaceAll("\"|,|:", "");
            String description = line[4].replaceAll("\"|,|:", "");
            String authors = line[5].replaceAll("\"|:", "");
            String date = line[6].replaceAll("\"|,|:|}", "");

            headlineObject object = new headlineObject(link, headline, category, description, authors, date);
            database.add(object);
        }
    }

    /*
     * This method returns the number of times a specific phrase
     * occurs in a headline.
     * 
     * @param givenPhrase the phrase we are interested in finding in the headline
     * 
     * @param target the target headline we are searching in
     * 
     * @return the number of times the given phrase occurs in the target headline
     * 
     */
    public int hasPhrase(String givenPhrase, String target) {
        int count = 0;
        String[] first = givenPhrase.split(" ");
        for (int i = 0; i < first.length; i++) {
            if (first[i].equals(target)) {
                count++;
            }
        }
        return count;

    }

    /*
     * This method returns the number of times a specific phrase
     * occurs in the database ArrayList.
     * 
     * @param phrase the phrase we are interested in finding in the database
     * 
     * @return the number of times the given phrase occurs in the database
     * 
     */
    public int mostPopularPhraseInDatabase(String phrase) {
        int c = 0;
        for (int i = 0; i < database.size(); i++) {
            int x = hasPhrase(database.get(i).getHeadLine(), phrase);
            c += x;
        }
        return c;
    }

    /*
     * This method returns the number of headlines in a given category.
     * 
     * @param chosenCategory the category we are interested in finding the number of
     * headlines for
     * 
     * @return the number of headlines in the chosen category
     * 
     */
    public int numberOfHeadLinesInCategory(String chosenCategory) {
        int popularity = 0;
        for (int i = 0; i < database.size(); i++) {
            if ((database.get(i).getCategory()).equals(chosenCategory)) {
                popularity++;
            }
        }
        return popularity;
    }

    /*
     * This method populates the ArrayList "arrayListOfCategories" with
     * the categories that occur in the database.
     * 
     * Each index in the ArrayList corresponds to one category.
     */
    public void categoriesArrayListCreator() {
        for (int i = 0; i < database.size(); i++) {
            if (!arrayListOfCategories.contains(database.get(i).getCategory())) {
                arrayListOfCategories.add(database.get(i).getCategory());
            }
        }
    }

    /*
     * This method returns the category with the most headlines in it.
     * 
     * @return the category with the most headlineObjects in it
     */
    public String mostPopularCategory() {
        ArrayList<Integer> categoryCounter = new ArrayList<>();
        // counts number of headlines in each category
        for (int i = 0; i < database.size(); i++) {
            for (int j = 0; j < arrayListOfCategories.size(); j++) {
                if (database.get(i).getCategory().equals(arrayListOfCategories.get(j))) {
                    categoryCounter.add(j, 1);
                }
            }
        }

        // finds category with the most headlines in it
        int index = 0;
        int p = 0;
        for (int x = 1; x < categoryCounter.size() - 1; x++) {
            if (categoryCounter.get(x) > categoryCounter.get(p)) {
                index = x;
            }
            p++;
        }
        return arrayListOfCategories.get(index);
    }

    /*
     * This method finds the most common word in a headline.
     * 
     * @param givenHeadline the headline that you are interested in finding
     * the most popular word for
     * 
     * @return the word that occurs most commonly in the headline
     * 
     */
    public String mostCommonKeyWordInHeadLine(String givenHeadLine) {
        givenHeadLine.replaceAll("[^a-zA-Z ]", "");
        String[] headlinearr = givenHeadLine.split(" ");
        String key = "";
        int freq = 0;
        for (int i = 0; i < headlinearr.length; i++) {
            int count = 0;
            for (int j = i + 1; j < headlinearr.length; j++) {
                if (headlinearr[j].equals(headlinearr[i])) {
                    count++;
                }
            }
            if (count >= freq) {
                key = headlinearr[i];
                freq = count;
            }
        }
        return key;
    }

    /*
     * This method creates an ArrayList of keywords that occur most commonly in the
     * database
     * 
     * Each index in the ArrayList corresponds to one keyword.
     * 
     * @return an ArrayList of Strings of the most common keywords that occur in
     * each headline in the database
     */
    public ArrayList<String> mostPopularWordsInHeadLine() {
        ArrayList<String> popularKeys = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            if (!popularKeys.contains(mostCommonKeyWordInHeadLine(database.get(i).getHeadLine())))
                popularKeys.add(mostCommonKeyWordInHeadLine(database.get(i).getHeadLine()));
        }
        return popularKeys;
    }

    /*
     * This method returns the most common keyword in a given category in the
     * database.
     * 
     * @param givenCategory the category that you are interested in finding the most
     * common word for
     * 
     * @return the word that occurs most commonly in that category
     */
    public String mostPopularKeyWordInCategory(String givenCategory) {
        ArrayList<String> categoryKeys = new ArrayList<>();
        ArrayList<String> databaseKeys = mostPopularWordsInHeadLine();
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getCategory().equals(givenCategory)) {
                categoryKeys.add(databaseKeys.get(i));
            }
        }
        int freq = 0;
        String word = "";
        for (int x = 0; x < categoryKeys.size(); x++) {
            int count = 0;
            for (int j = x + 1; j < categoryKeys.size(); j++) {
                if (categoryKeys.get(j).equals(categoryKeys.get(x))) {
                    count++;
                }
            }
            if (count >= freq) {
                word = categoryKeys.get(x);
                freq = count;
            }
        }
        return word;
    }

    /*
     * This method returns an ArrayList of headlines that contain two
     * given keywords.
     * 
     * @param keyword1 first keyword to search for in a headline
     * 
     * @param keyword2 second keyword to search for in a headline
     * 
     * @return an ArrayList of headlines that contain the given keywords
     */
    public ArrayList<String> findHeadLinesGivenKeyWord(String keyword1, String keyword2) {
        ArrayList<String> headlinesFound = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            if (hasPhrase(database.get(i).getHeadLine(), keyword1) > 0) {
                headlinesFound.add(database.get(i).getHeadLine());
            }
            if (hasPhrase(database.get(i).getHeadLine(), keyword2) > 0
                    && (!headlinesFound.contains(database.get(i).getHeadLine()))) {
                headlinesFound.add(database.get(i).getHeadLine());
            }
        }
        return headlinesFound;
    }

    /*
     * This method returns the distance between two given keywords in a given
     * headline.
     * 
     * @param givenHeadline provided headline to search within
     * 
     * @param keyword1 first keyword to search for in the headline
     * 
     * @param keyword2 second keyword to search for in the headline
     * 
     * @return how far the first keyword is from the second in the given headline
     */
    public int distanceBetweenKeywords(String givenHeadLine, String keyword1, String keyword2) {
        givenHeadLine.replaceAll("[^a-zA-Z ]", "");
        int index1 = givenHeadLine.indexOf(keyword1);
        int index2 = givenHeadLine.indexOf(keyword2);
        int distance = 0;
        if (index1 != -1 && index2 != -1) {
            distance = index2 - index1;
        }
        return distance;
    }

    /*
     * This method returns the most relevant headline the database,
     * given two keywords. This can be done by finding the distance between
     * those two keywords in each headline of the database.
     * 
     * @param key first keyword to search for in the headline
     * 
     * @param key second keyword to search for in the headline
     * 
     * @return the headline that contains the shortest distance between those two
     * keywords
     */
    public String mostRelevantHeadLine(String key1, String key2) {
        ArrayList<String> relevantHeadlines = findHeadLinesGivenKeyWord(key1, key2);
        ArrayList<Integer> relevantHeadlineCount = new ArrayList<>();
        for (int i = 0; i < relevantHeadlines.size(); i++) {
            relevantHeadlineCount.add(distanceBetweenKeywords(relevantHeadlines.get(i), key1, key2));
        }
        int highest = 0;
        int x = 0;
        for (int j = 1; j < relevantHeadlineCount.size() - 1; j++) {
            if (relevantHeadlineCount.get(j) > relevantHeadlineCount.get(x)) {
                highest = j;
            }
            x++;
        }
        return relevantHeadlines.get(highest);
    }

    /*
     * This method ranks the most relevant headlines given two keywords, from
     * the most relevant at index 0 to the least relevant at the last index of the
     * ArrayList.
     * 
     * 
     * @param key1 first keyword to search for in the headline
     * 
     * @param key2 second keyword to search for in the headline
     * 
     * @return an ArrayList of headlines in order from most relevant to least
     * relevant
     * given those two keywords
     * 
     */
    public ArrayList<String> headlineRank(String key1, String key2) {
        ArrayList<String> relevantHeadlines = findHeadLinesGivenKeyWord(key1, key2);
        ArrayList<Integer> relevantHeadlineCount = new ArrayList<>();
        for (int i = 0; i < relevantHeadlines.size(); i++) {
            relevantHeadlineCount.add(distanceBetweenKeywords(relevantHeadlines.get(i), key1, key2));
        }
        ArrayList<String> rank = new ArrayList<>();
        int x = 0;
        for (int j = 1; j < relevantHeadlineCount.size() - 1; j++) {
            if (relevantHeadlineCount.get(j) > relevantHeadlineCount.get(x)
                    && !rank.contains(relevantHeadlines.get(j))) {
                rank.add(relevantHeadlines.get(j));
            }
            x++;
        }
        return rank;
    }
}