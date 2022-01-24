package com.amazon.ata.maps.partsdiscovery;

import java.util.*;

/**
 * Helps expose key words from new editions of part catalogs.
 */
public class DevicePartDiscovery {

    // --- Part A ---
    /**
     * Calculate how often each word appears in a Catalog.
     * @param catalog The catalog to calculate word frequencies for.
     * @return A Map of words that appear in the catalog to the number of times they appear.
     */
    public Map<String, Integer> calculateWordCounts(PartCatalog catalog) {
        // PARTICIPANTS: Implement calculateWordCounts()
        Map<String, Integer> wordCounts = new HashMap<>();
        for ( String aWord : catalog.getCatalogWords()) {
            if (wordCounts.containsKey(aWord)) {
                int currentCount = wordCounts.get(aWord) + 1;
                wordCounts.put(aWord,currentCount);
            }
            else {
                wordCounts.put(aWord,1);
            }
        }
        return wordCounts;
    }

    // --- Part B ---
    /**
     * Removes a word from the provided word count map.
     * @param word the word to be removed
     * @param wordCounts the map to remove the word from
     */
    public void removeWord(String word, Map<String, Integer> wordCounts) {
        // PARTICIPANTS: Implement removeWord()
        wordCounts.remove(word);
        return;
    }

    // --- Part C ---
    /**
     * Find the word that appears most frequently based on the word counts from a catalog.
     * @param wordCounts an association between a word and the total number of times it appeared in a catalog
     * @return The word that appears most frequently in the catalog to the number of times they appear.
     */
    public String getMostFrequentWord(Map<String, Integer> wordCounts) {
        // PARTICIPANTS: Implement getMostFrequentWord()

        Map<String, Integer> sortedMapByValue = sortByValue(wordCounts);
        Map.Entry<String,Integer> entry = sortedMapByValue.entrySet().iterator().next();
        return entry.getKey();
    }

    // --- Part D ---
    /**
     * Calculates the TF-IDF score for each word in a catalog. The TF-IDF score for a word
     * is equal to the count * idf score. You can assume there will be an idfScore for each word
     * in wordCounts.
     * @param wordCounts - associates a count for each word from a catalog
     * @param idfScores - associates an IDF score for each word in the catalog
     * @return a map associating each word with its TF-IDF score.
     */
    public Map<String, Double> getTfIdfScores(Map<String, Integer> wordCounts, Map<String, Double> idfScores) {
        // PARTICIPANTS: Implement getTfIdfScores()
        Map<String, Double> TfIdScores = new TreeMap<String, Double>();
        Set<String> theKeys = wordCounts.keySet();
        for (String aKey : theKeys) {
            TfIdScores.put(aKey, wordCounts.get(aKey) * idfScores.get(aKey));
        }
        return TfIdScores;
    }

    // --- Extension 1 ---
    /**
     * Gets the 10 highest (TF-IDF) scored words for a catalog.
     *
     * @param tfIdfScores - associates a TF-IDF score for each word in a catalog
     * @return a list of the 10 highest scored words for a catalog.
     */
    public List<String> getBestScoredWords(Map<String, Double> tfIdfScores) {
        // PARTICIPANTS: Implement getBestScoredWords()
        return Collections.emptyList();
    }

    // --- Extension 2 ---
    /**
     * Calculates the IDF score for each word in a set of catalogs. The IDF score for a word
     * is equal to the inverse of the total number of times appears in all catalogs.
     * Assume df is the sum of the counts of a single word across all catalogs, then idf = 1.0/df.
     *
     * @param catalogWordCounts - a list of maps that associate a count for each word for each catalog
     * @return a map associating each word with its IDF score.
     */
    public Map<String, Double> calculateIdfScores(List<Map<String,Integer>> catalogWordCounts) {
        // PARTICIPANTS: Implement getIdfScores()
        return Collections.emptyMap();
    }
    /**
     * Sort hashmap by descending values
     *
     * @param aHashMap
     * @return result - the given HashMap sorted by descending values
     */
    public static Map<String, Integer> sortByValue(Map<String, Integer> aHashMap) {

        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(aHashMap.entrySet());

        // Sort the list by descending values using Collections class sort method
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return (entry2.getValue()).compareTo(entry1.getValue());
            }
        });

        // Add the sorted elements in the list to a LinkedHashMap (Map entries stored in entry sequence)
        HashMap<String, Integer> result = new LinkedHashMap<String, Integer>();  // Hold return object
        for (Map.Entry<String, Integer> anEntry : list) {                        // Loop through sorted list
            result.put(anEntry.getKey(), anEntry.getValue());                    // Add current entry from list to result Map
        }
        // return the LinkedHashMap with entries in sorted sequence
        return result;
    }
}
