import javax.swing.*;

public class List {
    String[] list;
    int size = 0;


    public List() {
        list = new String[0];
    }

    public String[] getList() {
        return list;
    }

    public void splitStrings(JTextField str)
    {
        list = str.getText().split("\\s+");
        size = list.length;
    }

    public String get(int index)
    {
        if (index >= 0 && index < size)
        {
            return list[index];
        }
        else
        {
            return null;
        }
    }

    public void sortList()
    {
        for (int i = 0; i < size - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < size; j++)
            {
                if (list[j].compareTo(list[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = list[minIndex];
            list[minIndex] = list[i];
            list[i] = temp;
        }
    }

    // Binary search method
    public int binarySearch(String newString)
    {
        int low = 0;
        int high = size - 1;

        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            int comparison = newString.compareTo(list[mid]);

            if (comparison == 0) {
                return mid; // Found the element
            } else if (comparison < 0) {
                high = mid - 1; // Search the left half
            } else {
                low = mid + 1; // Search the right half
            }
        }

        return low; // Return the insertion point
    }

    public void insert(String newString) {
        sortList();

        int insertIndex = binarySearch(newString);

        if (size < list.length)
        {
            for (int i = size; i > insertIndex; i--)
            {
                list[i] = list[i - 1];
            }

            list[insertIndex] = newString;
            size++;
        }
        else
        {
            System.out.println("List is full, cannot insert.");
        }
    }


}
