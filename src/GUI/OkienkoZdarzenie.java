package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkienkoZdarzenie extends JDialog {

    public OkienkoZdarzenie(String komunikat)
    {
        setTitle("Zdarzenie");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400,150);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel label = new JLabel("<html>"+komunikat+"</html>");
        label.setFont(new Font("Monospaced", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createRigidArea(new Dimension(0,10)));

        JButton button = new JButton("Ok");
        button.setFocusable(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Monospaced", Font.BOLD, 12));
        button.setBackground(Color.decode("#eee3da"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OkienkoZdarzenie.this.dispose();
            }
        });
        panel.add(button);

        add(panel);

        setVisible(true);
    }

}
