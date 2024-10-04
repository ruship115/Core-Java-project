import java.util.Scanner;
public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Scanner sc = new Scanner(System.in);
        //try(sc){}finally {}
        System.out.print("Enter initial balance :");
        BankApp b = new BankApp(sc.nextDouble());
        Runnable r = () ->
        {
            String name = Thread.currentThread().getName();

            if(name.equals("Balance Enqurity"))
            {
                System.err.println("\t"+name);
                System.out.println("availabel balance "+b.getBalance());
            }

            if(name.equals("Withdraw"))
            {
                System.err.println("\t"+name);
                try
                {
                    System.out.print("Enter withdraw amount :");
                    b.withdraw(sc.nextDouble());
                } catch (Insufficent_Balance e)
                {
                    System.err.println(e.getMessage());
                }
            }

            if(name.equals("Deposit"))
            {
                System.err.println("\t"+name);
                try
                {
                    System.out.print("Enter deposit amount :");
                    b.deposit(sc.nextDouble());
                } catch (Invalid_Amount e)
                {
                    System.err.println(e.getMessage());
                }
            }

        };
        Thread t1 = new Thread(r,"Balance Enqurity");
        Thread t2 = new Thread(r,"Withdraw");
        Thread t3 = new Thread(r,"Deposit");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        //sc.close();
        //try(sc){} finally {}
    }

}

class BankApp
{
    private double balance;
    public BankApp(double balance)
    {
        this.balance = balance;
    }
    public double getBalance()
    {
        return balance;
    }
    public void deposit(double amt) throws Invalid_Amount
    {
        if(amt>0)
        {
            balance+=amt;
            System.out.println("Deposit Successful Your current Balance :"+balance);
        }
        else
            throw new Invalid_Amount("Invalid amount");
    }

    public void withdraw(double amt) throws Insufficent_Balance
    {
        if(amt<=balance)
        {
            balance-=amt;
            System.out.println("Withdraw successful Your current balance :"+balance);
        }
        else
            throw new Insufficent_Balance("Insufficent balance");
    }
}

class Insufficent_Balance extends Exception
{
    public Insufficent_Balance() {}

    public Insufficent_Balance(String msg)
    {
        super(msg);
    }
}

class Invalid_Amount extends Exception
{
    public Invalid_Amount() {}

    public Invalid_Amount(String msg)
    {
        super(msg);
    }
}