/**
 * Student: Brian Casipit
 * Course: CS 11C - Data Structures and Algorithms
 */

package BinaryTreeProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class BinarySearchTreeTester {

    public static void main(String[] args) {
    //----------------------------------------------------------------
    // This main method accepts input from a text file and
    // uses the TreeNode, KeyedItem, Month, BinaryTreeBasis,
    // BinarySearchTree, and TreeException classes. The
    // output of this program is listed in the postconditions.
    // For this lab, the input file contains names of months,
    // separated by newlines.
    //
    // Preconditions: The unless the input file is in the same
    //                working directory, the full file path
    //                needs to be specified when entering in
    //                the file name.
    //
    //                The text file should contain names of
    //                months separated by newlines, and all
    //                months should follow the same format
    //                of spacing and capitalization in order
    //                to be sorted correctly.
    //
    // Postconditions: Stores the information in a Binary Search Tree.
    //
    //                 Displays the Binary Search Tree.
    //
    //                 Displays the items in alphabetical order.
    //----------------------------------------------------------------

        //*******************************************************//
        // Print the program name and prompt user for input file //
        //*******************************************************//
        System.out.println("\nTREE SORT PROGRAM\n");
        System.out.println("Enter the name of the input file below.");
        System.out.print("Please specify the full file path unless\n" +
                            "it's in the current working directory: ");

        try {

            Scanner input = new Scanner(System.in);
            String filename = input.nextLine();
            input.close();

            System.out.println();

            //**************************************************************//
            // Search for the input file and input the contents into a tree //
            //**************************************************************//
            BufferedReader fileIn = new BufferedReader(new FileReader(filename));
            String line;

            // build BST
            BinarySearchTree bst = new BinarySearchTree();

            // iterate through the file to insert items into the tree
            while ((line = fileIn.readLine()) != null) {
                Month m = new Month(line);
                bst.insert(m);
            } // end while

            fileIn.close();

            //********************************//
            // Display the binary search tree //
            //********************************//
            System.out.println("Binary Search Tree:\n");

            bst.display(bst.root);

            System.out.println();

            //**************************//
            // Display the sorted items //
            //**************************//
            System.out.println("Sorted items:\n");

            bst.printSortedOrder(bst.root);

        } catch (IOException | TreeException e) {
            System.out.println("An error has occurred. Rerun the program.");
        } // end try-catch block

    } // end main

} // end BinarySearchTreeTester
