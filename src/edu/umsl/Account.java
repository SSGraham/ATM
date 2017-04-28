package edu.umsl;

import java.io.*;
import java.text.*;
import java.util.*;


public class Account implements Serializable
{
    protected double balance = 100, rate; 
    protected int date1, date2;
    protected Calendar cal1 = new GregorianCalendar();
    protected Calendar cal2 = new GregorianCalendar();
    protected Boolean dateflag = false; //field
    //private String acctName; 
    static Scanner sc = new Scanner(System.in); //don't need to create new scanner each time
    
    //public Account(){}  //constructor (always has same name as class & doesnt have a return type)
   
    public void menu() throws IOException //only method that needs to be public
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        do
        {
            //Scanner sc = new Scanner(System.in);
            System.out.println("***********************\n"
                                + "Main Menu\n"
                                +"**********************\n"
                                +"1. Deposit\n"
                                +"2. Withdraw\n"
                                +"3. Balance Inquiry\n"
                                +"4. Exit");
            choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    System.out.println("\nCurrent balance: $" + calcBalance() );
                    if (dateflag == true)
                        {
                            getDate2();
                            calcInterest();
                            deposit();
                        }
                        else
                        {
                            getDate1();
                            deposit();
                        }                      
                        break;
                case 2:
                    System.out.println("Current balance: $" + calcBalance() );
                    if (dateflag == true)
                        {
                            getDate2();
                            calcInterest();                
                        }
                        else
                        {
                            getDate1();
                        }
                       withdraw();
                        break;
                case 3:
                    System.out.println("Your current balance is: $" + calcBalance() );
                    //if (dateflag == true)
                        //{
                            //getDate2();
                            //calcInterest();                
                        //}
                        //else
                        //{
                            //getDate1(); 
                        //}
                       //calcBalance();
                        break;
                case 4:
                    System.out.println("Goodbye.");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection. Please try again.");
                    menu();
        
            }
        }while (choice >=1 && choice<= 4);
    }
    
    public void deposit() throws IOException
    {
        calcInterest();
        double deposit;
        //Scanner sc = new Scanner(System.in);

        System.out.println("Enter the amount you would like to deposit: ");
        deposit = sc.nextDouble();

        balance = calcBalance() + deposit; 
        System.out.println("$" + deposit + " has been desposited into your account.\n"
                            + "Your current balance is: " + balance);
    }
    
    public void withdraw() throws IOException
    {
        double withdraw;
        //Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter amount to withdraw:\n"
                            + "(must be in multiples of $10.00)");
        withdraw = sc.nextDouble();

        balance = calcBalance() - withdraw;
        System.out.println("$" + withdraw + " has been deducted from your account.\n"
                            + "Your current balance is: " + balance);
    }
    
    //moved this to switch case
    //public void checkBalance() 
    //{
        //display current balance for user
        //display as US currency 
        //System.out.println("Your current balance is: " + balance);

    //}
    
    public double calcBalance() throws IOException
    {
        NumberFormat currencyFormatter;
        String strBalance;
        
        currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        strBalance = currencyFormatter.format(balance);
                
        return balance;
    }

    public void calcInterest()
    {
        int datediff = date1 - date2;           
        rate = .05/365;           
        double ratetime = Math.pow(1+rate,datediff);//1 lets it compound itself           
        balance = balance * ratetime;           
        date1 = date2;       
    }

    private void getDate1()throws IOException
    {
        System.out.print("Enter todays date(mm/dd/yyyy): ");
        String inputText = sc.next( );
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        ParsePosition pos = new ParsePosition(0);
        //Date date= new Date();
        Date date = formatter.parse(inputText, pos);
        cal1.setTime(date);
        date1 = cal1.get(Calendar.DAY_OF_YEAR);
        dateflag = true;
    }

    private void getDate2() throws IOException
    {      
        System.out.print("Enter todays date(mm/dd/yyyy): ");
        String inputText = sc.next( );
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        ParsePosition pos = new ParsePosition(0);
        //Date date= new Date();
        Date date = formatter.parse(inputText, pos);
        cal2.setTime(date);
        date2 = cal2.get(Calendar.DAY_OF_YEAR);
        dateflag = true;
      
        if(date1 > date2)
        {
            System.out.println("Nice try. Enter a valid date.");
            getDate2();
        }
    }
}






