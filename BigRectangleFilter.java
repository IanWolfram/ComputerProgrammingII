import java.awt.*;

public class BigRectangleFilter implements Filter
{
    public boolean accept(Object o)
    {
        Rectangle r = (Rectangle)o;
        int perimeter = 2 * (r.width + r.height);
        if (perimeter > 10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
