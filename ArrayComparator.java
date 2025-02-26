import java.util.Arrays;
import java.util.Comparator;

public class ArrayComparator implements Comparator<int[]> 
{
    public int compare(int[] array1, int[] array2) {
        // Compare arrays directly using Arrays.equals
        if (Arrays.equals(array1, array2)) {
            return 1;
        }
        return 0; // Arrays are not equal
    }
}