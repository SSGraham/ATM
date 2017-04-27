package edu.umsl;

import java.io.*;
import java.util.*;


public class ATM 
{
    //create array of 3 accounts
    Account[] accounts = new Account[3]; 
    
    
    public static void main(String[] args) throws IOException
    {
        ATM myATM = new ATM();
        myATM.accountsMenu();
        
        myATM.saveAccount();
    }

    public void accountsMenu() throws IOException
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("*****************WELCOME*****************"
                                +"\nPlease choose from the following menu:"
                                +"\n*****************************************"
                                + "\n1 - Load Accounts"
                                + "\n2 - Populate Accounts"
                                + "\n3 - Select an Account"                  
                                + "\n4 - Exit");
                choice = sc.nextInt(); 
                
                switch (choice)
                {
                    case 1: 
                        loadAcct();
                        break;
                    case 2:
                        populateAccts();
                        break;
                    case 3:
                        selectAcct();
                        break;                
                    case 4:
                        System.out.println("Goodbye.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid. Try again.");
                        accountsMenu();
                }
            } while (choice >= 1 && choice <=4);
        
    }                
   

    public void loadAcct()
    {
        
        try
        {
            FileInputStream inStream = new FileInputStream("C:/test.txt");
            ObjectInputStream is = new ObjectInputStream(inStream);
            accounts = (Account[]) is.readObject();
            is.close();
        }
        catch(Exception ioe)
        {
            System.out.println(ioe.getMessage());
        }
    }

    public void populateAccts() throws IOException
    {
        //loop through array creating 3 accounts
        for(int i =0; i < accounts.length; i++)
            {  
                accounts[i] = new Account();
                System.out.println(accounts[i]);
                
            }
    
    }

    public void selectAcct() throws IOException
    {
        int choice; 
        Scanner sc = new Scanner(System.in);
        
        do
        {
            System.out.println("*******ACCOUNTS MENU*******\n"
                                +"    Select an account:\n"
                                +"***************************\n"
                                +"0: Account 0\n"
                                +"1: Account 1\n"
                                +"2: Account 2\n"
                                +"3: Exit");
            choice = sc.nextInt();
            
            switch(choice)
            {
                case 0:
                    accounts[choice].menu();
                    break;
                case 1:
                    accounts[choice].menu();
                    break;
                case 2:
                    accounts[choice].menu();
                    break;
                case 3:
                    System.out.println("Goodbye.");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }while (choice >=1 && choice<= 4);
    }

    public void saveAccount()
    {
        System.out.println("\nSave account");
        try
        {
            FileOutputStream outStream = new FileOutputStream("test.txt");
            ObjectOutputStream os = new ObjectOutputStream(outStream);
            os.writeObject(accounts);
            os.flush();
            os.close();
        }
        catch(IOException ioe)
        {
            System.out.print("Exception");
        }
} 