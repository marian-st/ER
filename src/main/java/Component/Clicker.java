package Component;

import Main.Main;
import State.Command;
import State.State;
import State.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class Clicker extends Component<State, Command> {
    private UUID id = UUID.randomUUID();
    private JFrame view;
    private JLabel lab;
    public Clicker(Store store) {
        super(store);
    }
    @Override
    void eventHook(State state) {

    }

    @Override
    State getState() {
        return null;
    }

    @Override
    void draw(State state) {
        view = new JFrame("Button value");
        view.setSize(400, 300); view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ottieni il riferimento al Content Pane
        Container frmContentPane = view.getContentPane();
        // Usa frmContentPane per aggiungere elementi grafici
        JButton button = new JButton("Click me");
        button.addActionListener();
        frmContentPane.add(button);
        view.setVisible(true);

    }

    @Override
    void initialization(State state) {
        draw(state);
    }

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            store.update(new Main.MyString("INC", id));
        }
    }
}
