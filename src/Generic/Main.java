package Generic;

public class Main {
    /**
     *
     * @author Isaeva Amina.
     * Дополнительный курс.
     */

    public static void main(String[] args) {
        Body body = new Body();

        SmallHead smallHead = new SmallHead();
        //MediumHead mediumHead = new MediumHead();
        BigHead bigHead = new BigHead();

        Robot<SmallHead> robot = new Robot<>(body,smallHead);
        Robot<BigHead> robot1 = new Robot<>(body,bigHead);

        robot.getHead().burn();
        robot1.getHead().turn();

        //Robot robot2 = new Robot(body, mediumHead);

    }
}
