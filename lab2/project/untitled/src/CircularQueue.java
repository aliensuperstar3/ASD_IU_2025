/*
2 Реализовать «Циклическую очередь» (Circular Queue). Прокомментировать
логику.
 */
import java.util.Scanner;
public class CircularQueue {
    private int[] queue;    // массив для хранения элементов очереди
    private int front;      // индекс начала очереди
    private int rear;       // индекс конца очереди
    private int capacity;   // максимальный размер очереди
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = -1;  //  что очередь пуста
        rear = -1;   // что очередь пуста
    }
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Очередь переполнена. Перезаписываем самый старый элемент.");
            // В настоящей циклической очереди при переполнении
            // перезаписывается самый старый элемент
            front = (front + 1) % capacity; // сдвиг
        }
        if (isEmpty()) {
            front = 0; // если очередь была пуста, инициализируем начало
        }
        rear = (rear + 1) % capacity;
        queue[rear] = value;
        System.out.println("Добавлен элемент: " + value);
    }
    // Удаление элемента из очереди
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста, удалить нечего");
            return -1;
        }
        int value = queue[front];
        // Если изначально ток 1 элемент
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        System.out.println("Удален элемент: " + value);
        return value;
    }
    // Получение элемента без удаления
    public int peek() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return -1;
        }
        return queue[front];
    }
    public boolean isEmpty() {
        return front == -1;
    }
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }
        System.out.println("Содержимое очереди: ");
        if (rear >= front) {
            // rear after front
            for (int i = front; i <= rear; i++) {
                System.out.print(queue[i] + " ");
            }
        } else {
            // rear before front
            for (int i = front; i < capacity; i++) {
                System.out.print(queue[i] + " ");
            }
            for (int i = 0; i <= rear; i++) {
                System.out.print(queue[i] + " ");
            }
        }
        System.out.println();
    }
    public void fillQueue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Введите элемент " + (i + 1) + ": ");
            enqueue(sc.nextInt());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер очереди: ");
        int size = sc.nextInt();
        CircularQueue queue = new CircularQueue(size);
        queue.fillQueue();
        queue.printQueue();
        System.out.println("Первый элемент: " + queue.peek());
        queue.dequeue();
        queue.printQueue();
        System.out.println("Введите число, которое необходимо добавить в очередь:");
        int value = sc.nextInt();
        queue.enqueue(value);
        queue.printQueue();
        System.out.println("Текущий первый элемент: " + queue.peek());
        // Демонстрация
        System.out.println("\nДемонстрация цикличности:");
        CircularQueue circularDemo = new CircularQueue(3);
        circularDemo.enqueue(1);
        circularDemo.enqueue(2);
        circularDemo.enqueue(3);
        circularDemo.printQueue();
        // Четвертый элемент при добавлении перезапишет старый
        circularDemo.enqueue(4);
        circularDemo.printQueue();
    }
}
