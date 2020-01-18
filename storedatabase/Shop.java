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

import java.util.ArrayList;
import java.util.Scanner;

class Shop {
    private Food Food = new Food();                             //Create an instance of a food object
    private Toy Toy = new Toy();                                //Create an instance of a toy object
    private Book Book = new Book();                             //Create an instance of a book object
    private ShoppingList shoppingList = new ShoppingList();     //Create a new instance of the shopping list
    private boolean finished = false;                           //Boolean for when the user is finished shopping and is checked out
    private boolean basketFull = false;                         //Boolean for when the basket is full
    private int currentAmount;                                  //Variable use to keep track of the quantity of each good currently available
    private int input;                                          //Variable for what button the user pressed

    Shop() {
        System.out.println("Welcome to the Costco!");
        while (!finished) {                                     //While the user has not checked out yet, the user can go shopping
            shopping();
        }
        System.out.println("Please come again!");
    }

    private void shopping() {
        options();                                              //Display what the store has available for sale
        shoppingList();                                         //Display what is currently in the users basket

        Scanner scanner = new Scanner(System.in);

        try {
            input = scanner.nextInt();                          //Take user input
        } catch (Exception e) {
        }

        if (shoppingList.getItems().size() == 10) {                                             //When the basket reaches 10 items
            System.out.println("Your basket is full, please go check out your items.");
            basketFull = true;                                                                  //Basket becomes full
        }

        if (input == 1) {                                                                       //If the user presses 1 and the basket is not full
            if (!basketFull) {
                if (shoppingList.getItems().size() < 10) {
                    if (Food.quantity > 0) {
                        shoppingList.addItem(Food);                                             //Add the food item to the basket
                        System.out.println("\nYou added a food item to the basket.\n");
                        Food.decreaseQuantity();                                                //Decrease the quantity of food available
                        currentAmount = Food.getQuantity();
                        Food = new Food();                                                      //Create a new food object
                        Food.setQuantity(currentAmount);
                    } else {
                        System.out.println("\nThe store is currently out of food.\n");          //If the quantity of food is 0
                    }
                }

            }
        } else if (input == 2) {                                                                //If the user presses 2 and the basket is not full
            if (!basketFull) {
                if (shoppingList.getItems().size() < 10) {
                    if (Toy.quantity > 0) {
                        shoppingList.addItem(Toy);                                              //Add the toy item to the basket
                        System.out.println("\nYou added a toy to the basket.\n");
                        Toy.decreaseQuantity();                                                 //Decrease the quantity of toys available
                        currentAmount = Toy.getQuantity();
                        Toy = new Toy();                                                        //Create a new toy object
                        Toy.setQuantity(currentAmount);
                    } else {
                        System.out.println("\nThe store is currently out of toys.\n");          //If the quantity of toys is 0
                    }
                }
            }
        } else if(input == 3) {                                                                 //If the user presses 3 and the basket is not full
            if (!basketFull) {
                if (shoppingList.getItems().size() < 10) {
                    if (Book.quantity > 0) {
                        shoppingList.addItem(Book);                                             //Add the book item to the basket
                        System.out.println("\nYou added a book to the basket.\n");
                        Book.decreaseQuantity();                                                //Decrease the quantity of books available
                        currentAmount = Book.getQuantity();
                        Book = new Book();                                                      //Create a new book object
                        Book.setQuantity(currentAmount);
                    } else {
                        System.out.println("\nThe store is currently out of books.\n");         //If the quantity of books is 0
                    }
                }
            }
        } else if(input == 4) {                                                                 //If the user presses 4
            removeItem();                                                                       //Remove the item that the user selects
        } else if(input == 5) {                                                                 //If the user presses 5
            checkOut();                                                                         //Start the checkout function
        } else {
            System.out.println("That is not a correct command, please try again!\n");           //If the user presses the wrong button
        }
    }

    private void shoppingList() {                                                       //Function that will show the list of items in the basket

        ArrayList<Good> items = shoppingList.getItems();
        if (items.size() > 0) {
            System.out.format("%-30s%-25s%-20s\n", "Item", "Cost", "Tax\n");
            float totalCost = 0;                                                        //Used to calculate the total cost of the shopping list
            float totalTax = 0;                                                         //Used to calculate the amount of tax that needs to be payed
            float subTotal = 0;                                                         //Used to calculate the total cost of all the items not including tax
            int i = 1;                                                                  //Acts as a listing mechanism

            for (Good item : items) {
                float price = item.getPrice();                                          //Gets the price of that specific item
                float taxValue = 0;                                                     //Used to calculate the amount of tax on an item
                String tax = "No Tax";                                                  //Used to indicated if the item is taxable or not

                if (item instanceof Taxable) {                                          //If the item is taxable
                    taxValue = ((Taxable) item).calculateTax();                         //Calculate the amount of tax that needs to be payed
                    tax = "Tax";                                                        //Indicated that the item is taxable
                }

                float total = price + taxValue;                                          //Calculate the total cost of the single item
                System.out.format("%2s. %-25s$%-25.2f%-20s\n", i, item.description, price, tax);
                totalCost += total;                                                      //Add everything to the total cost of the shopping list
                subTotal += price;                                                       //Add price to the sub total
                totalTax += taxValue;                                                    //Add the tax to the total tax amount
                i++;                                                                     //Increase the listing to the next number
            }

            System.out.printf("\nSubtotal: $%.2f", subTotal);
            System.out.printf("\nTotal tax: $%.2f\n", totalTax);
            System.out.printf("\nTotal cost: $%.2f\n\n", totalCost);
        }
    }

    private void options() {                                                //Function that will show the current available items that the store is selling and the instructions
        System.out.println("Available Goods:\n");
        System.out.print("1. ");
        Food.view();                                                        //Shows the food object that is currently being sold
        System.out.print("2. ");
        Toy.view();                                                         //Shows the toy object that is currently being sold
        System.out.print("3. ");
        Book.view();                                                        //Shows the book object that is currently being sold
        System.out.println("\nSelect the Food (1)");
        System.out.println("Select the Toy (2)");
        System.out.println("Select the Book (3)");
        System.out.println("Remove an Item (4)");
        System.out.println("Go to checkout (5)\n");
        System.out.println("Press a button to select an option.");
        System.out.println("Your basket can only hold a max of 10 items.\n");
    }

    private void removeItem() {                                                     //Function to remove an item from the array list
        if (shoppingList.getItems().size() == 0) {                                  //Nothing will occur when the array list is empty
            System.out.println("\nYou currently have no items in your basket.\n");
            return;
        }
        shoppingList();                                                             //Show the items currently on the list
        System.out.println("Enter a number on the list to remove that item.");

        Scanner scanner = new Scanner(System.in);                                   //Take user input
        int input = scanner.nextInt();

        ArrayList<Good> items = shoppingList.getItems();
        if (input > 0 && input <= items.size()) {                                   //Remove the item selected by the user
            Good item = items.get(input - 1);
            shoppingList.removeItem(item);
            System.out.println("You removed a " + item.description + ".");
            basketFull = false;                                                     //The basket is not full

            if(item instanceof Food) {                                              //If the item that was removed was a food item
                Food.increaseQuantity();                                            //Increase the quantity of food in the store
            }

            if(item instanceof Toy) {                                               //If the item that was removed was a toy
                Toy.increaseQuantity();                                             //Increase the quantity of toys in the store
            }

            if(item instanceof Book) {                                              //If the item that was removed was a book
                Book.increaseQuantity();                                            //Increase the quantity of books in the store
            }
        } else {
            System.out.println("\nInvalid input.\n");
        }
    }

    private void checkOut() {                                                       //Function for when the user wants to check out items
        System.out.println("----------------------------------------------------------------");
        System.out.println("\nCheck out: Receipt\n");
        shoppingList();
        finished = true;                                                             //The customer is done shopping and has checked out all of their items
    }
}
