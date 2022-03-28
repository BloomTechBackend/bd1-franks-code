package com.amazon.ata.introthreads.classroom;

import com.google.common.collect.Lists;  // Contains the Google Lists methods
import com.google.common.collect.Maps;   // Contains the Google Maps methods
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class to pre-compute hashes for all common passwords to speed up cracking the hacked database.
 *
 * Passwords are downloaded from https://github.com/danielmiessler/SecLists/tree/master/Passwords/Common-Credentials
 */
public class PasswordHasher {
    // should create the file in your workspace directory
    private static final String PASSWORDS_AND_HASHES_FILE = "./passwordsAndHashesOutput.csv";
    // a "salt" is a value used to generate hard to de-hash values
    //          used in the generation of the hash code for a vlaue, usually if hashing sensitive data
    //          typically unique to a user and very large to make it hard to guess or derive
    private static final String DISCOVERED_SALT = "salt";

    /**
     * Generates hashes for all of the given passwords.
     * <p>
     * Changed to use 4 threads to process the passwords
     *
     * @param passwords List of passwords to hash
     * @return map of password to hash
     * @throws InterruptedException
     */
    public static Map<String, String> generateAllHashes(List<String> passwords) throws InterruptedException {
        // Added final to Map to make immutable due to the requirement for concurrency
        //    Map<String, String> passwordToHashes = Maps.newConcurrentMap();
        // Prior to adding threading this Map was populated by the one call to BatchPasswordHasher
        // With threading implemented we need to add the result from each thread to this Map
        final Map<String, String> passwordToHashes = Maps.newConcurrentMap(); // hold the return value for method

        // Split the list of passwords into 4 sub lists so we can give one to each thread
        // Each sublist is a List<String> so set of sublists is a List<List<String>>
        //                                                spread the elements across 4 sublists
        List<List<String>> passwordSublists = Lists.partition(passwords, passwords.size() / 4);

        // List to hold the result from each call to BatchPasswordHasher so we can give that to a thread to process
        //      and copy the results to the Map we are returning
        List<BatchPasswordHasher> batchHashers = new ArrayList<>();

        // List to hold the thread we are running so we can reference them later
        List<Thread> theThreads = new ArrayList<>();

        // Convert the passwords in the List to hashcodes
        // Loop throuoh the List of sublists of passwords
        // Changed to use a current sublist of passwords rather the original big list of passwords
        for (int i = 0; i < passwordSublists.size(); i++) {
            BatchPasswordHasher batchHasher = new BatchPasswordHasher(passwordSublists.get(i), DISCOVERED_SALT);
            batchHashers.add(batchHasher);  // add the hashed passwords from the current list to List of batchHashers
            // Changed define and start a thread to process the current sublist of hashed password
            //    batchHasher.hashPasswords();
            Thread aThread = new Thread(batchHasher);  // define a thread to run the current batchHasher
            aThread.start();                           // Start the thread to run the current batchHasher
            theThreads.add(aThread);                   // Store the thread in our Thread List
        } // End of for loop to define, start and store threads

        // Need to wait until all the threads have completed before can copy the results
        //      from BatchPasswordHasher to our returned Map
        // Call a method to wait for all threads to be completed before we continue
        waitForThreadsToComplete(theThreads);

        // Since we have multiple BatchPasswordHashers that have run,
        //       we need to copy the result from each one into our returned Map
        for(BatchPasswordHasher aBatchHasher : batchHashers) {
            passwordToHashes.putAll(aBatchHasher.getPasswordToHashes());  // copied hashed passwords to returned Map
        }

        return passwordToHashes;
    }

    /**
     * Makes the thread calling this method wait until passed in threads are done executing before proceeding.
     * We will not return from this until all threads have completed
     *
     * @param threads to wait on
     * @throws InterruptedException
     */
    public static void waitForThreadsToComplete(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {  // Loop through the List of threads we started
            thread.join();               // .join() - wait for the thread passed to complete
        }
    }

    /**
     * Writes pairs of password and its hash to a file.
     */
    static void writePasswordsAndHashes(Map<String, String> passwordToHashes) {
        File file = new File(PASSWORDS_AND_HASHES_FILE);
        try (
            BufferedWriter writer = Files.newBufferedWriter(file.toPath());
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)
        ) {
            for (Map.Entry<String, String> passwordToHash : passwordToHashes.entrySet()) {
                final String password = passwordToHash.getKey();
                final String hash = passwordToHash.getValue();

                csvPrinter.printRecord(password, hash);
            }
            System.out.println("Wrote output of batch hashing to " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
