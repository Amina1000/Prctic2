package lesson1;

public enum DayOfWeek {
    MONDAY(8), TUESDAY(8), WEDNESDAY(8),
    THURSDAY(8), FRIDAY(8), SATURDAY(0), SUNDAY(0);

    private int hours;

    DayOfWeek(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }
}