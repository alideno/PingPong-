import java.awt.Dimension;

import javax.swing.JFrame;

public class PlayFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new PingPong());
        frame.setPreferredSize(new Dimension(800,500));
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
