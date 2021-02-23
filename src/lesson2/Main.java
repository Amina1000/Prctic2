package lesson2;

public class Main {
    /**
     * homework lesson 2
     *
     * @author Isaeva Amina
     * date 22.02.2021
     */

    public static void main(String[] args) {
        /*Задача 3
        В методе main() вызвать полученный метод,
        обработать возможные исключения MyArraySizeException и MyArrayDataException и вывести результат расчета
        (сумму элементов, при условии что подали на вход корректный массив).
        */
        String[][] myArray= {
                {"","","",""},
                {"","","",""},
                {"","","",""},
                {"","","",""}
        };

        try {
            array_elements(myArray);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }
        try {
            sum_array_elements(myArray);
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    /*Задача 1
      Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
      При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
     */
    public static void array_elements(String[][] myArray) throws MyArraySizeException {
        throw new MyArraySizeException("Не верный формат");
    }

    /*Задача 2
     Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
      Если в каком-то элементе массива преобразование не удалось
      (например, в ячейке лежит символ или текст вместо числа),
      должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
     */
    public static void sum_array_elements(String[][] myArray) throws MyArrayDataException {
        int sum =0;
        for (String[] strings : myArray) {
            for (String string : strings) {
                try {
                    int a = Integer.parseInt(string);
                    sum = +a;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Не удалось преобразовать, не верный формат ячейки", e);
                }
            }
        }
        System.out.println("Сумма элементов массива = "+sum);
    }
}
