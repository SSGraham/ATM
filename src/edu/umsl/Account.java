package edu.umsl;

import java.io.*;
import java.text.*;
import java.util.*;

public class Account //implements Serializable
{

    private double balance, rate;  
    private int date1, date2;
    private boolean dateflag;    
    private final Calendar cal1 = new GregorianCalendar();
    private final Calendar cal2 = new GregorianCalendar();       
    static Scanner sc = new Scanner(System.in);
    
        //account constructor
	public Account (double beginBalance) throws IOException 
        {
		balance = beginBalance;
	 
        }
        
        public void menu()throws IOException
        {
            int choice;
            
            do
            {
                System.out.println("\n***************MAIN MENU***************");
                System.out.println("Please choose from the following menu:");
                System.out.println("***************************************");
                System.out.println("     1 - Deposit");
                System.out.println("     2 - Withdraw");
                System.out.println("     3 - Balance Inquiry");
                System.out.println("     4 - Select a Different Account or Exit");
                System.out.println("***************************************");
                
                choice = sc.nextInt();
                
                switch(choice)
                {
                    case 1:
                    {
                        //System.out.println();
                        if(dateflag == true)
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
                    }
                    case 2:
                    {
                        //System.out.println();
                        if(dateflag == true)
                        {
                            getDate2();
                            calcInterest();
                            withdraw();
                        }
                        else
                        {
                            getDate1();
                            withdraw();
                        }
                        break;
                    }
                    case 3:
    //I can take out the if/else here since they're doing the same thing
    //leaving it for now to make sure interest is calculating correctly for bal inquiry
                    {
                        //System.out.println();
                        if(dateflag == true)
                        {
                            getDate2();
                            calcInterest();
                            System.out.println("\nYour current balance is: " + getBalance());
                        }
                        else
                        {
                            //getDate1();
                            getDate2();
                            calcInterest();
                            System.out.println("\nYour current balance is: " + getBalance());
                        }
                        break;
                    }
                    case 4:                  
                        return;                    
                    default:
                    {
                        System.out.println("Invalid. Try again.");
                        menu();
                    }
                        
                }
                
            }while(choice >= 1 && choice <= 4);
            
        }

	public String getBalance()
        {
		NumberFormat currencyFormatter;
		String strBalance;
		
		currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
		strBalance = currencyFormatter.format(balance);

                return strBalance;
	}

	private void deposit() throws IOException 
        {		
		String deposit;

                System.out.println("\n**********MAKE A DEPOSIT**********");
                System.out.println("Your current balance is " + getBalance());
                System.out.print("Enter the amount you would like to deposit: ");
                
		deposit = sc.next();
		double depositAmount = Double.parseDouble(deposit);
                
		balance = balance + depositAmount;
                
                System.out.println("\n $" + depositAmount + " has been deposited to your account.");
                System.out.println("Your new balance is " + getBalance());   
                //menu();
	} 	

	private void withdraw() throws IOException 
        {
            //for something extra: add an if to make sure enough funds available for wdraw
            String withdraw;

            System.out.println("\n*********MAKE A WITHDRAWAL*********");
            System.out.println("Your current balance is " + getBalance());
            System.out.print("Enter the amount you would like to withdraw: ");

            withdraw = sc.next();
            double wdrawAmount = Double.parseDouble(withdraw);
            
            

            balance = balance - wdrawAmount;

            System.out.println("\n $" + wdrawAmount + " has been deducted from your account.");
            System.out.println("Your new balance is " + getBalance());
        }
                
        
        private void calcInterest()
        {
            int datediff = date2 - date1;
            rate = .05/365;
            double ratetime = Math.pow(1+rate,datediff);
            balance = balance * ratetime;
            date1 = date2;
        }

        
        private void getDate1() throws IOException
        {
            System.out.print("\nEnter today's date (mm/dd/yyyy): ");          
            String inputText = sc.next();
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(inputText, pos);
            
            cal1.setTime(date);
            date1 = cal1.get(Calendar.DAY_OF_YEAR);
            dateflag = true;
        }
        
        
        private void getDate2() throws IOException
        {
            System.out.print("\nEnter today's date (mm/dd/yyyy): ");
            String inputText = sc.next();

            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(inputText, pos);

            cal2.setTime(date);
            date2 = cal2.get(Calendar.DAY_OF_YEAR);
            
            if (date1 > date2)
            {
                System.out.println("Invalid entry. Please enter a valid date.");
                getDate2();
            }    
        }
    
}