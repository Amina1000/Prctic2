package lesson7.serialize;

import java.io.Serializable;

/**
 * homework lesson7.serialize
 *
 * @author Amina
 * 09.05.2021
 */
public class SerializeEx implements Serializable {
    private final String caption;
    private final int count;

    public SerializeEx(String caption, int count) {
        this.caption = caption;
        this.count = count;
    }

    @Override
    public String toString() {
        return "SerializeEx{" +
                "caption='" + caption + '\'' +
                ", count=" + count +
                '}';
    }
}
