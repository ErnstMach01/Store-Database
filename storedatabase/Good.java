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

abstract class Good {
    String description;                                   //Description of the good
    float price;                                          //Price of the good
    int quantity = (int)(Math.random() * 10);             //Quantity of the good currently available, randomizes the quantity

    Good(){                                               //Default constructor
        float cents = ((float) (Math.random() * 99)/100);
        float dollar = ((float) (Math.random() * 5) + 1);
        price = dollar + cents;                            //Randomize the price of the good
    }

    float getPrice() {                                     //Function that will return the current price of a good
        return price;
    }

    int getQuantity() {                                    //Function that will return the current quantity amount
        return quantity;
    }

    void setQuantity(int amount) {                         //Function to set the quantity of a good
        quantity = amount;
    }

    void decreaseQuantity() {                              //Function that will decrease the quantity of a good by 1
        quantity--;
    }

    void increaseQuantity() {                              //Function that will increase the quantity of a good by 1
            quantity++;
    }

    void list() {                                          //Function to show the good in the store
        System.out.printf("%-20s Price: $%-10.2f Quantity available: %-10d ",description,price, quantity);
    }
}
