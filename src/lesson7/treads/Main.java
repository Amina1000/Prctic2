package lesson7.treads;

/**
 * homework lesson7.treades
 *
 * @author Amina
 * 10.05.2021
 */
public class Main {
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet();
        Thread threadA = new Thread(alphabet::printA);
        Thread threadB = new Thread(alphabet::printB);
        Thread threadC = new Thread(alphabet::printC);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
