import java.util.ArrayList;

public class InheritanceDemo
{
    public static void main(String[] args)
    {
        Worker w1 = new Worker("Ian", "Wolfram", "000001","Mr.",2005, 15);
        Worker w2 = new Worker("Jane", "Doe", "000002", "Ms.", 1990, 20);
        Worker w3 = new Worker("John", "Smith", "000003", "Dr.", 1985, 25);

        SalaryWorker sw1 = new SalaryWorker("Alice", "Johnson", "000004", "Mrs.", 1995, 18, 50000);
        SalaryWorker sw2 = new SalaryWorker("Robert", "Williams", "000005", "Mr.", 1980, 22, 120000);
        SalaryWorker sw3 = new SalaryWorker("Emily", "Brown", "000006", "Ms.", 1992, 25, 150000);


        ArrayList <Worker> list;
        list = new ArrayList<Worker>();
        list.add(w1);
        list.add(w2);
        list.add(w3);
        list.add(sw1);
        list.add(sw2);
        list.add(sw3);

        for (int week = 1; week <= 3; week++)
        {
            System.out.println("Week #" + week + "------------------------------------------------\n");

            int hoursWorked;
            if (week ==2)
            {
                hoursWorked = 50;
            }
            else
            {
                hoursWorked = 40;
            }

            for (int i = 0; i < list.size(); i++)
            {
                Worker worker = list.get(i);
                System.out.println(worker.fullName());
                System.out.println(worker.displayWeeklyPay(hoursWorked));
            }
        }


    }









}
