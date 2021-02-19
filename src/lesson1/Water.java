package lesson1;

public class Water extends Obstacle {

    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doit(Contributors contributors) {
        contributors.swim(length);
    }
}
