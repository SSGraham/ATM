package edu.umsl;

import java.io.*;
import java.util.*;

public class ATM 
{
    //set up array of 3 accounts
    Account myAccounts[] = new Account[3];   
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String args[]) throws IOException
    {

        ATM atm = new ATM();
        //auto-populate 3 accounts
        atm.populateAccts();        
        atm.loadAccts();
        atm.welcomeMenu();        
    }

    public void welcomeMenu() throws IOException
    {
        int choice;
        
        do{
            System.out.println("\n**************WELCOME MENU************");
            System.out.println("Please choose from the following menu:");
            System.out.println("***************************************\n");
            //System.out.println("     1 - Populate new accounts");
            System.out.println("     1 - Select an account");
            System.out.println("     2 - Exit\n");
            System.out.println("\n***************************************\n");
            choice = sc.nextInt();
          
            switch (choice)
            {
//                case 1:
//                    populateAccts();
//                    break;
                case 1:
                    selectAcct();
                    break;
                case 2:
                    System.out.println("\n\n\n\n***Thank you***");
                    System.out.println("****Goodbye****\n\n\n\n");
                    System.exit(0);
                default:
                    System.out.println("Invalid. Try again.");
                    welcomeMenu();
            }
        
        }while (choice >= 1 && choice<=2);
    }
    
    public void populateAccts() throws IOException
    {
        for(int i = 0; i < myAccounts.length; i++)
        {
            myAccounts[i] = new Account(100.00);
            System.out.println(myAccounts[i]);
        }

    }

    public void loadAccts()
    {
    } 

    public void saveAcct()
    {
    }

    public void selectAcct() throws IOException
    {           
        int choice;
        
        do
        {              
            System.out.println("\n*************ACCOUNTS MENU*************");
            System.out.println("Select an account: ");
            System.out.println("***************************************\n");
            System.out.println("     0 - Account #0");
            System.out.println("     1 - Account #1");
            System.out.println("     2 - Account #2");
            System.out.println("     3 - Exit to welcome menu\n");
            System.out.println("\n***************************************\n");
                                  
            choice = sc.nextInt();
            
            switch(choice)
            {
                case 0:
                {
                    System.out.println(myAccounts[choice]);
                    myAccounts[choice].menu();
                    break;
                }
                case 1: 
                {
                    //myAccounts[choice].thisAcct = this;
                    System.out.println(myAccounts[choice]);
                    myAccounts[choice].menu();
                    break;
                }
                case 2:
                {
                    //myAccounts[choice].thisAcct = this;
                    System.out.println(myAccounts[choice]);
                    myAccounts[choice].menu();
                    break;
                }
                case 3:
                {
                    System.out.println("\nExiting to welcome menu....\n");
                    welcomeMenu();
                }
                default:
                    System.out.println("Invalid. Try again.");
                    selectAcct();
            }   
        }while (choice >= 0 && choice <= 3);        
                       
    }

}