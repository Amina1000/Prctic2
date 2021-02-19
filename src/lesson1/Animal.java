package lesson1;

public class Animal implements Contributors{
    String type;
    String name;

    int maxRunDistance;
    int maxJumpDistance;
    int maxSwimDistance;

    boolean onDistance;

    public Animal(String type, String name, int maxRunDistance, int maxJumpDistance, int maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpDistance = maxJumpDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    @Override
    public void run(int distance) {
        if (distance<maxRunDistance){
            System.out.println(type + " " + name + " справился с кросом");
        }else {
            System.out.println(type + " " + name + " провалил крос");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height<maxJumpDistance){
            System.out.println(type + " " + name + " справился с прыжком");
        }else {
            System.out.println(type + " " + name + " провалил прыжок");
            onDistance = false;
        }
    }

    @Override
    public void swim(int distance) {
        if (maxSwimDistance ==0) {
            System.out.println(type + " " + name + " плавать не умеет");
            onDistance = false;
            return;
        }
        if (distance<maxSwimDistance){
            System.out.println(type + " " + name + " проплыл удачно");
        }else {
            System.out.println(type + " " + name + " не смог проплыть, сняли с дистанции");
            onDistance = false;
        }
    }

    @Override
    public boolean isDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        System.out.println(type + " " + name + " на дистанции: " + onDistance);
    }
}