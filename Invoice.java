import java.util.ArrayList;

public class Invoice
{
    private ArrayList<LineItem> items;
    private double total;

    public Invoice()
    {
        items = new ArrayList<LineItem>();
    }

    public void addLineItem(LineItem item)
    {
        items.add(item);
    }

    public double getTotal()
    {
        for (int i = 0; i < items.size(); i++)
        {
            total += items.get(i).getTotal();
        }
        return total;
    }

    public ArrayList<LineItem> getLineItems() {
        return items;
    }

}