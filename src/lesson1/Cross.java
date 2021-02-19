package lesson1;

public class Cross extends Obstacle{
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doit(Contributors contributors) {
        contributors.run(length);
    }
}
