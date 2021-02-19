package lesson1;

public class Human implements Contributors{
    String name;

    int maxRunDistance;
    int maxJumpDistance;
    int maxSwimDistance;

    boolean active;

    public Human(String name) {
        this.name = name;
        this.maxRunDistance = 3000;
        this.maxJumpDistance = 2;
        this.maxSwimDistance = 1;
        this.active = true;
    }

    @Override
    public void run(int distance) {
        if (distance<maxRunDistance){
            System.out.println(name + " справился с кросом");
        }else {
            System.out.println(name + " провалил крос");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height<maxJumpDistance){
            System.out.println(name + " справился с прыжком");
        }else {
            System.out.println(name + " провалил прыжок");
            active = false;
        }
    }

    @Override
    public void swim(int distance) {
       if (distance<maxSwimDistance){
            System.out.println(name + " проплыл удачно");
        }else {
            System.out.println(name + " не смог проплыть, сняли с дистанции");
            active = false;
        }
    }

    @Override
    public boolean isDistance() {
        return active;
    }

    @Override
    public void info() {
        System.out.println(name + " активность: " + active);
    }
}
