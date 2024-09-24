import java.awt.*;
import java.util.ArrayList;

public class BigRectLister
{
    public static void main(String[] args)
    {
        Rectangle r1, r2, r3, r4, r5, r6, r7, r8, r9, r10;
        ArrayList<Rectangle> list = new ArrayList<Rectangle>();

        list.add(r1 = new Rectangle(2, 3));
        list.add(r2 = new Rectangle(3, 6));
        list.add(r3 = new Rectangle(6, 7));
        list.add(r4 = new Rectangle(1, 2));
        list.add(r5 = new Rectangle(4, 5));
        list.add(r6 = new Rectangle(7, 8));
        list.add(r7 = new Rectangle(8, 9));
        list.add(r8 = new Rectangle(3, 1));
        list.add(r9 = new Rectangle(2, 1));
        list.add(r10 = new Rectangle(1, 3));

        BigRectangleFilter filter = new BigRectangleFilter();
        System.out.println("The rectangles with perimeter > 10 are: ");

        for (Rectangle rect : list)
        {
            boolean b;
            b = filter.accept(rect);
            if (b)
            {
                System.out.println("r" + list.indexOf(rect) + " " + rect);
            }


        }
    }
}
