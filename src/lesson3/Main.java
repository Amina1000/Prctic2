package lesson3;

import java.util.*;

public class Main {
    /**
     * homework lesson 3
     *
     * @author Isaeva Amina
     * date 24.02.2021
     */
    public static void main(String[] args) {

        //Задача 1
        songAboutBears();
        //Задача 2
        telephoneBook();

    }

    /*Задача 1
      Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
      Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
      Посчитать, сколько раз встречается каждое слово.
       */
    public  static void songAboutBears(){
        String[] words = {"где", "то", "на", "белом", "свете", "Там", "где", "всегда", "мороз",
                "Трутся", "об", "ось", "медведи", "О земную", "ось", "Мимо", "плывут", "столетья",
                "Спят", "подо", "льдом", "моря", "Трутся", "об", "ось", "медведи", "Вертится", "земля"};
        Map<String,Integer> songMap = new LinkedHashMap<>();
        for (String word:words) {
            Integer count = songMap.getOrDefault(word,0);
            songMap.put(word,count+1);
        }
        System.out.println(songMap);
    }
    /*Задача 2
    Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
    В этот телефонный справочник с помощью метода add() можно добавлять записи,
    а с помощью метода get() искать номер телефона по фамилии.
    Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
    тогда при запросе такой фамилии должны выводиться все телефоны
     */
    public  static void telephoneBook(){
        TelephoneBook telephoneBook = new TelephoneBook();
        String names = "Иванов,Петров,Сидоров,Ягудин,Иванов";
        String[] namesArray = names.split(",");

        for (String name:namesArray) {
            telephoneBook.add(name,new Random().nextInt(800000000));
        }

        for (String name:new HashSet<>(Arrays.asList(namesArray))) {
            System.out.println("Телефон "+ name + ": "+ telephoneBook.get(name));
        }
    }
}
