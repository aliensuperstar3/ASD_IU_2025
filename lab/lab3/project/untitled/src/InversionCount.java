/**
 * Дан массив arr из N элементов. Назовем инверсией пару индексов (i, j),
 * таких что i < j и arr[i] > arr[j]. Требуется определить количество инверсий
 * в данном массиве и вывести их. Дать комментарии. Вычислить сложность.
 */
import java.util.*;
public class InversionCount {
    public static void main(String[] args) {
        int[] array = readArray();
        int inversionCount = countInversions(array);
        System.out.println("Количество инверсий: " + inversionCount);
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
    /**
      Подсчет инверсий в массиве
      Сложность: O(n²), где n - размер массива
      Док-во:
      1. Циклы
         - Внешний цикл: for (int i = 0; i < arr.length - 1; i++)
           Количество итераций: (n-1)
         - Внутренний цикл: for (int j = i + 1; j < arr.length; j++)
           Количество итераций для каждого i: (n - i - 1)
      2. Суммарное колличество операций
         T(n) = (n - i - 1) =
               = (n-1) + (n-2) + (n-3) + ... + 1
      3. Сумма
        T(n) = (n-1) + (n-2) + ... + 1 = (n-1)*n/2
      4. Оценка
         T(n) = (n² - n)/2 = O(n²)
     */
    private static int countInversions(int[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    counter++;
                }
            }
        }
        return counter;
    }
}