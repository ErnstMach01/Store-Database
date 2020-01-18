package ics4x.storedatabase;

/**
 *  Assignment: Store Database
 *  Author: Ernst Mach
 *  Date: Saturday November 24th, 2018
 *  Description: A simple database program for a store that sells 3 types of goods to customers.
 *  The 3 types of goods are foods, toys, and books. Each instance of each good will contain a unique
 *  description and price. All food objects will also include a calorie property. All toy objects will
 *  include a minimum age requirement property. All book objects will include an author property.
 *  The program will simulate a different quantity of each type good so that the program will be different
 *  each time it is run. The user can interact with the store through the console allowing them
 *  to select which item they wish to add to their basket. The user can also choose to remove an
 *  item from their basket if they do not want that item as well as go to the check out and pay
 *  for their items at anytime throughout the program. The basket can carry a maximum of 10 items.
 *  Once the user reaches 10 items in their basket, they will be prompted to go to the checkout
 *  so they can pay for their items. The program will then show the user a receipt of all the
 *  items that they have bought as well as show the user which items have been taxed, the
 *  subtotal cost of all the items, the total tax cost of all the items, and the final total.
 */

class Book extends Good implements Taxable{              //Subclass of good
    private String author;                               //The author of the book
    private String[] nameList = {"Arthur Conan Doyle", "Charles Dickens", "Ezra Pound", "F. Scott Fitzgerald", "George Orwell"};            //List of author names
    private String[] bookList = {"A Study in Scarlet", "Great Expectations", "Pisan Cantos", "The Great Gatsby", "1984"};                   //List of books
    private int randomize;                               //Used to store a random number, so that the book will match the correct author

    Book() {
        super();
        randomize = (int) (Math.random() * nameList.length);           //Generate a random number
        author = nameList[randomize];                                  //Sets the random number for both the author and book title
        description = bookList[randomize];                             //so that the book title will match the correct author
    }

    @Override
    public float calculateTax() {                          //Used to calculate the cost of tax, since book is a taxable item
        return price * taxRate;
    }

    void view() {                    //Function to show the book good in store, shows properties from good as well as showing the author
        super.list();
        System.out.println("Author: " + author);
    }
}

