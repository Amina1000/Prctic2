package lesson1;

public class Wall extends Obstacle {

    int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doit(Contributors contributors) {
        contributors.jump(height);
    }
}
