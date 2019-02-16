/**
 * AIM:- TO DEVELOPE AN ACCOUNTING SOFTWARE CONSISTING OF SIMPLE ACCOUNTING TRANSACTIONS LIKE INCOME AND EXPENSES AND ULTIMATELY FOUND OUT THE SAVINGS
 * PROGRAM DEVELOPED BY:-PROF. RAHUL DHAR 
 * VERSION:-1.01.09.07.2018  Base Program
 *          1.02.11.07.2018  Bugs fixes        
 */
import java.io.*; 
class Finance
{
    // Declaration of global variables   
    double inc,exp,sal,savings,iF,oF;
    String name,cn;
    double ia[]=new double[50];String in[]=new String[50];int pos=0;
    double ea[]=new double[50];String en[]=new String[50];
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    // Method to input the data entered by the user
    void getData()throws IOException
    {
        System.out.println("Enter your name");
        name=br.readLine();
        System.out.println("Enter your main salary");
        sal=Double.parseDouble(br.readLine());
        System.out.println("Enter currency acronym (like INR,USD) ");
        cn=br.readLine();
    }

    // Method to input and calculate the cash inflow items
    double  getDataCalcInf()throws IOException
    {
        double n=0;int x;String ch;
        System.out.println("Do you have any other source of income?(Y/N)");// To check whether the user has any other inflow items
        ch=br.readLine();
        if(ch.equals("Y"))
        {
            do
            {
                System.out.println("Enter the account name ");
                in[pos]=br.readLine();// To store the account name
                System.out.println("Enter the amount");
                ia[pos]=Double.parseDouble(br.readLine());//To store the account amount
                System.out.println("Do you want to enter any more(0/1)");
                x=Integer.parseInt(br.readLine());// To check whether the user has any other source of income
                n+=ia[pos];
                if(x==1)        
                    ++pos;
                else   
                    break;// To break out of the do-while loop
            }
            while(true); 
            iF=n+sal;// To store the value net inflow
            return (n+sal);// To return total income to the caller fx.
        }
        else
        {   iF=sal;
            return(sal);// To return total income to the caller fx.
        }
    }

    // Method to display the inflow items
    void displayInf()throws IOException
    {
        System.out.println("Total net income ="+cn+". "+getDataCalcInf());//To display the net income
        System.out.println("Enter 1 for more details............................");
        int choice=Integer.parseInt(br.readLine());
        if(choice ==1)//Condition to check whether the user wants to view the details of it
        {
            System.out.println("Account Name"+"\t\t\t"+"Account Amount");
            for (int i=0;i<=pos;i++)
                System.out.println(in[i]+"\t\t\t"+ia[i]);//To display the value of the account name and account amount
        }
        pos=0;//To reintialize the value of pos back to 0
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
    }

    // Method to input and calculate the cash outflow items
        double  getDataCalcOuf()throws IOException
    {
        double exp=0;int x;String ch;
        System.out.println("Enter your expenses");
        // Loop to enter the expense items
        do
        {
            System.out.println("Enter expense account");
            en[pos]=br.readLine();
            System.out.println("Enter expenses amount");
            ea[pos]=Double.parseDouble(br.readLine());
            exp+=ea[pos];
            System.out.println("Do you have any other expenses(0/1)");
            x=Integer.parseInt(br.readLine());
            if(x==0)
                break;
            else  
                pos++;
        }
        while(true);
        oF=exp;
        return exp;
    }

    // Method to display the inflow items
    void displayOuf()throws IOException
    {  
        int i,choice;
        System.out.println("Total Expenses :-"+cn+"."+getDataCalcOuf());//To display the net expenses
        System.out.println("Enter 1 for more details............................................................................................");
        choice=Integer.parseInt(br.readLine());
        //Condition to check whether user wants to view the items and amount of items 
        if(choice==1)
        {
            System.out.println("Account Name \t\t\t\t\t\tAccount Amount");
            for(i=0;i<=pos;i++)
                System.out.println(en[i]+"\t\t\t\t\t\t"+cn+"."+ea[i]);
        }
        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
        pos=0;
    }

    // To calculate the savings method
    void  getDataCalcSav() throws IOException
    { 
        savings=iF-oF; 
    }

    // To display all the items
    void displayMain()throws IOException
    {   
        int choice;
        do{
            getData();
            System.out.println("Name : - "+name);
            displayInf();
            displayOuf();
            getDataCalcSav();
            System.out.println(" Total Savings :-"+cn+"."+savings);
            System.out.println("Enter 1 to enter again");
            choice=Integer.parseInt(br.readLine());
            if (choice!=1)
                break;
        }
        while(true);
    }
}

/**
 * AIM:-  To receive data from class Finance and then modify the savings by asking the user to create a fund and spend it
 * PROGRAM DEVELOPED BY:-PROF. RAHUL DHAR 
 * VERSION:-1.01.11.07.2018
 */
import java.io.*;
class Savings extends Finance
{   
    BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
    public  void main()throws IOException
    {
        double fund=0.0;int choice=-1;
        String n;
        super.displayMain();
        do
        {
            System.out.println("Do you want to spend or transfer your savings to a seperate fund or spend it?\n Enter 1 to spend or transfer ");
            choice=Integer.parseInt(br.readLine());
            if( choice==1)
            {
                System.out.println("Enter fund name");
                n=br.readLine();
                System.out.println("Enter the amount required");
                fund=Double.parseDouble(br.readLine());
                if(super.savings>fund)
                {
                    System.out.println("You have excess fund availabe");
                    System.out.println("Enter 1 to confirm");
                    choice=Integer.parseInt(br.readLine());
                    if(choice==1)
                    {
                        System.out.println("You have successfully spend your savings \n");
                        System.out.println(fund+" you have spend for "+n);
                    }
                    System.out.println(super.savings-fund+"is the remaining fund availabe");
                    super.savings=super.savings-fund;
                }
                else if( super.savings==fund)
                {
                    System.out.println("Your required fund is equal to your savings");
                    System.out.println("Enter 1 to confirm");
                    choice=Integer.parseInt(br.readLine());
                    if(choice==1)
                    {
                        System.out.println("You have successfully spend your savings \n You have no funds available");
                        System.out.println(fund+" you have spend for "+n);
                        super.savings=0;
                    }
                }
                else 
                {
                    System.out.println("ERROR!!!!!!!\n Insufficinent fund");
                    System.out.println("You need "+super.cn+". "+(fund-super.savings)+"more");
                }
            }
            System.out.println("Enter 1 to EXIT ");
            choice=Integer.parseInt(br.readLine());
            if (choice==1)
                break;
        }
        while(true);
        System.out.println("Thank you for using the application ");
    }
}