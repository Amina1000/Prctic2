package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * homework lesson4
 *
 * @author Admin
 * 17.03.2021
 */
public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 500, 500);
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));

        JTextField textField = new JTextField();
        jp.add(textField);
        JButton sendMassage = new JButton("Send massage",
                new ImageIcon("C:\\JavaPractic\\Prctic2\\src\\img\\send.png"));
        jp.add(sendMassage);

        JTextArea jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);

        add(jScrollPane);
        add(jp, BorderLayout.SOUTH);

        ActionListener send = e -> {
            jTextArea.append(textField.getText() + "\n");
            textField.setText("");
        };

        textField.addActionListener(send);
        sendMassage.addActionListener(send);

        setVisible(true);
    }

}
