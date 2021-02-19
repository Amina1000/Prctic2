package lesson1;

public class Main {

    public static void main(String[] args) {
	// write your code here
       DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
       System.out.println(getWorkingHours(dayOfWeek));
    }

    public static int getWorkingHours(DayOfWeek dayOfWeek) {
        int workHours;
        workHours = dayOfWeek.getHours();
        if (workHours == 0) {
            System.out.println("Сегодня выходной");
        } else {
            for (DayOfWeek day : DayOfWeek.values()) {
                if (day.ordinal() > dayOfWeek.ordinal()) {
                    workHours+= day.getHours();
                }
            }
        }
        return workHours;
    }
}
