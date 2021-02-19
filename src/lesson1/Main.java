package lesson1;

public class Main {
    /**
     * homework lesson 1
     *
     * @author Isaeva Amina
     * date 19.02.2021
     */
    public static void main(String[] args) {
        // Задача 1:
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        System.out.println(getWorkingHours(dayOfWeek));

        //Задача 2:
        competitions();
    }
    /**
     * задача 1: Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
     * С его помощью необходимо решить задачу определения кол-ва рабочих
     * часов до конца недели по заднному текущему дню.
     */
    public static int getWorkingHours(DayOfWeek dayOfWeek) {
        int workHours;
        workHours = dayOfWeek.getHours();
        if (workHours == 0) {
            System.out.println("Сегодня выходной");
        } else {
            for (DayOfWeek day : DayOfWeek.values()) {
                if (day.ordinal() > dayOfWeek.ordinal()) {
                    workHours += day.getHours();
                }
            }
        }
        return workHours;
    }
    /**
     * задача 2
     * Создайте три класса Человек, Кот, Собака.
     * Эти классы должны уметь бегать прыгать и плавать.
     * Создайте два класса: беговая дорожка, стена и вода,
     * при прохождении через которые, участники должны выполнять соответствующие действия,
     * результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
     * Создайте два массива: с участниками и препятствиями,
     * и заставьте всех участников пройти этот набор препятствий.
     */
    public static void competitions(){
        Contributors[] contributors ={
                new Human("Bob"),
                new Cat("Bars"),
                new Dog("Jack"),
                new Dog("Roby") };

        Obstacle[] obstacles ={
                new Cross(80),
                new Wall(2),
                new Wall(1),
                new Water(10) };
        for (Contributors c:contributors) {
            for (Obstacle o:obstacles) {
                o.doit(c);
                if (!c.isDistance()) break;
            }

        }
        for (Contributors c:contributors) {
            c.info();
        }
     }
 }
