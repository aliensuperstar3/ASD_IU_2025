import java.util.*;
//Демонстрация коллизии в HashMap - показываем что все элементы
//находятся в 1 и том же bucket и объясняем коллизию
public class HashMapCollision {
    public static void main(String[] args){
        HashMap<BadKey, String> collisionMap = new HashMap<>(1000);//Создаем хэш-мап для коллизии
        BadKey keys[] = new BadKey[1000];//Создаем массив для удобного хранения ключей класса BadKey
        for(int i = 0; i < 1000; i++){//Заполняем коллизионный хэш-мап
            keys[i] = new BadKey(i);//В классе BadKey зафиксирован метод, возвращающий хэш-код равный 1 для любых входных данных
            collisionMap.put(keys[i], "Объект №" + String.valueOf(i));
        }
        int keyHash;
        int bucketIndex;
        for(int i = 100; i < 500; i += 50){ //Выведем несколько объектов и проверим их хэш-коды и бакеты где они находятся
            keyHash = keys[i].hashCode();
            bucketIndex = keyHash & (1000 - 1);
            System.out.print(collisionMap.get(keys[i]) + " ");
            System.out.print(keyHash + " ");
            System.out.println(bucketIndex);
        }
        System.out.println();
        //Объект №100 1 1
        //Объект №150 1 1
        //Объект №200 1 1
        //Объект №250 1 1
        //Объект №300 1 1
        //Объект №350 1 1
        //Объект №400 1 1
        //Объект №450 1 1
        //Как видим, все объекты имеют одинаковый хеш-код и номер бакета - происходит коллизия
        //В случае коллизии хэшмап изменяет хранящийся в бакете элемент-пару ключ-значение
        //в узел связанного списка ключ-значение-след.элемент.
        //В нашем случае в первом бакете находится связанный список из 1000 объектов
        //Для сравнения создадим вариант без намеренной коллизии
        HashMap<Long, String> normalMap = new HashMap<>(1000);//обычный хэш-мап с корректным методом получения хэш-кода
        for(int i = 0; i < 1000; i++){
            normalMap.put((long)i, "Тестовый Объект №" + String.valueOf(i));
        }
        Long normalKey;
        for(int i = 100; i < 500; i += 50){ //Так же смотрим несколько объектов что бы проверить их бакеты и хэш-коды
            normalKey = (long)i;
            keyHash = normalKey.hashCode();
            bucketIndex = keyHash & (1000 - 1);
            System.out.print(normalMap.get((long)i) + " ");
            System.out.print(keyHash + " ");
            System.out.println(bucketIndex);
        }
        System.out.println();
        //Тестовый Объект №100 100 100
        //Тестовый Объект №150 150 150
        //Тестовый Объект №200 200 200
        //Тестовый Объект №250 250 250
        //Тестовый Объект №300 300 300
        //Тестовый Объект №350 350 350
        //Тестовый Объект №400 400 400
        //Тестовый Объект №450 450 450

        //Разные объекты с разными хэш-кодами в разных бакетах -> коллизии нет, все отлично
        //Каждый объект сидит в своем бакете, необходимости в создании связанных списков у
        //хэш-мапа нет
        int iterations = 100000;//Проведем проверку времени получения последнего объекта из обоих хэш-мапов
        long collisionTime = 0;
        long normalTime = 0;
        for (int i = 0; i < iterations; i++) {
            // Коллизия
            long start = System.nanoTime();
            collisionMap.get(keys[999]);
            collisionTime += System.nanoTime() - start;

            // Нет коллизии
            start = System.nanoTime();
            normalMap.get((long)999);
            normalTime += System.nanoTime() - start;
        }

        System.out.println("Коллизии: " + (collisionTime / iterations) + " нс");
        System.out.println("Без коллизий: " + (normalTime / iterations) + " нс");
        System.out.println("Коллизии медленнее в " +
                String.format("%.2f", (double)collisionTime / normalTime) + " раз");
    }
    //Коллизии: 974 нс
    //Без коллизий: 195 нс
    //Коллизии медленнее в 4.99 раз ->
    //Наглядно видим, как коллизии замедляют работу с хэш-мапом, убивая все его преимущества

    //В хэшмапе без коллизий искомый элемент находится с временной сложностью О(1), тк входящий ключ(999)
    //сразу корректно переводится в уникальный хэш-ключ, по которому сразу находится бакет где сидит искомый объект

    //В коллизионном хэш-мапе входной ключ(999) получает от некорректной хэш-функции хэш-код равный 1, как и у любого
    //другого входного ключа. Бакет для хэш-кода 1 - первый, первая пара в нем с ключом = 1. Хэш мап сравнивает
    //значения ключей, понимает что это не искомый объект, и идет дальше, старательно проверяя каждый новый ключ,
    //тем самым проходя подряд все 1000 элементов списка - сложность по времени, как для цикла, О(n). Таким образом,
    //производительность хэш-мапа резко снижается. Коллизия показана.
}
class BadKey {
    private int id;
    public BadKey(int id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        return 1; //Фиксированный хэш-код для создания коллизии
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BadKey badKey = (BadKey) obj;
        return id == badKey.id;
    }
}