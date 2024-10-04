public class Theatre
{
    public static void main(String[] args) //throws InterruptedException
    {
        Runnable r1 = ()->
        {
            for(int i=1;i<=5;i++)
            {
                String name = Thread.currentThread().getName();
                System.out.println(name+" : "+i);
                try
                {
                    Thread.sleep(100);
                }
                catch (Exception e)
                {
                    System.err.println(e);
                }
            }
        };

        Thread t1 = new Thread(r1,"booking");
        Thread t2 = new Thread(r1,"Allocating");
        t1.start();
        //t1.join();
        t2.start();
    }
}