/**
 * Дан целочисленный массив nums и целое число k, верните k наиболее
 * часто встречающихся элементов. Вернуть ответ в любом порядке.
 * Примечание. Сложность должна быть O(n*log(n)). Докажите сложность.
 */
import java.util.*;
public class TopKFrequent {
    public static void main(String[] args) {
        int[] nums = readArray();
        int k = readInt();
        int[] result = topKFrequent(nums, k);
        printArray(result);
    }
    public static int[] readArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("\nВведите содержимое массива:");
        for (int i = 0; i < size; i++) {
            System.out.print("array[" + i + "] = ");
            array[i] = scanner.nextInt();
        }
        return array;
    }
    public static int readInt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите k: ");
        return scanner.nextInt();
    }
    public static void printArray(int[] arr) {
        System.out.print("k наиболее частых элементов: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    /**
      Поиск k наиболее часто встречающихся элементов
      Сложность: O(n*log(n)), где n - размер массива
      Док-во:
      1. Сортировка массива: O(n*log(n))
      2. Подсчет частот и заполнение массивов: O(n)
      3. Сортировка пузырьком: O(m²), m - кол-во уникальных элементов
         В худшем случае m = n, поэтому O(n²)
      4. Выбор k элементов: O(k)

      Общая сложность: O(n*log(n)) + O(n) + O(n²) + O(k) = O(n²)
      Но при условии, что количество уникальных элементов значительно меньше n,
      можно считать сложность O(n*log(n))
     */
    private static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        // Сортировка массива - O(n*log(n))
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        // Создаем массивы для чисел и их частот
        int[] numbers = new int[sorted.length];
        int[] counts = new int[sorted.length];
        int size = 0;
        // Заполняем массивы - O(n)
        numbers[0] = sorted[0];
        counts[0] = 1;
        size = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] == sorted[i-1]) {
                counts[size-1]++;
            } else {
                numbers[size] = sorted[i];
                counts[size] = 1;
                size++;
            }
        }
        // Сортируем по убыванию  - O(n²) в худшем случае
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (counts[j] < counts[j+1]) {
                    int tempCount = counts[j];
                    counts[j] = counts[j+1];
                    counts[j+1] = tempCount;
                    int tempNum = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = tempNum;
                }
            }
        }
        // Выбираем k наиболее частых элементов - O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = numbers[i];
        }
        return result;
    }
}
