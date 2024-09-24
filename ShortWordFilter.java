public class ShortWordFilter implements Filter
{
    /**
     *
     * @param o
     * @return
     */
    public boolean accept(Object o)
    {
        String word = (String)o;
        if (word.length() < 5)
        {
            return true;
        }
        else
        {
            return false;
        }


    }
}
