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
        DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;
        System.out.println("Задача 1 (перечисления)");
        System.out.println("Остаток часов: "+getWorkingHours(dayOfWeek));

        //Задача 2:
        System.out.println("Задача 2 (Препядствия)");
        competitions();

        //Задача 3
        System.out.println("Задача 3 (Команды)");
        CourseTeam();
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
     * * У препятствий есть длина (для дорожки) или высота (для стены),
     * а участников ограничения на бег и прыжки. Если участник не смог пройти одно из препятствий,
     * то дальше по списку он препятствий не идет.
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
    /** Задача 3
     * 2. Добавить класс Team, который будет содержать: название команды,
     * массив из 4-х участников (т.е. в конструкторе можно сразу всех участников указывать),
     * метод для вывода информации о членах команды прошедших дистанцию,
     * метод вывода информации обо всех членах команды.
     * 3. Добавить класс Course (полоса препятствий), в котором будут находиться: массив препятствий,
     * метод который будет просить команду пройти всю полосу;
     */
    public static void CourseTeam(){
        Teams teams = new Teams("Dream",
                new Cat("Kokos"), new Human("Max"), new Dog("Boby"));
        Obstacle[] obstacle = {new Cross(80), new Wall(3), new Water(12)};
        Course course = new Course(obstacle);
        course.doIt(teams);
        teams.showWinners();
        teams.showContributors();
    }

}
