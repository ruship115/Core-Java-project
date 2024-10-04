public class RailwayReservation
{
    private int availableSeat=1;
    private int wantedSeat;

    public RailwayReservation(int wantedSeat)
    {
        this.wantedSeat = wantedSeat;
    }

    public static void main(String[] args) //throws InterruptedException
    {
        RailwayReservation ticket = new RailwayReservation(1);

        Runnable rr = () ->
        {
            String name=null;
            if(ticket.availableSeat>=ticket.wantedSeat)
            {
                name=Thread.currentThread().getName();
                System.out.println(ticket.wantedSeat+" berth reserved for :"+name);
                ticket.availableSeat-=ticket.wantedSeat;
            }
            else
            {
                System.out.println(name+" Sorry Berth is not available");
            }
        };
        Thread t1 = new Thread(rr,"Rushi");
        Thread t2 = new Thread(rr,"Abhi");
        Thread t3 = new Thread(rr,"Shubham");

        t1.start();
        //t1.join();
        t2.start();
        t3.start();

    }
}