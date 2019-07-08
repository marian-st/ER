package Component;


import State.Command;
import State.State;
import State.StateEvent;
import State.Store;
import State.MyString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class Clicker extends Component<Command> {
    private UUID id = UUID.randomUUID();


    public Clicker(Store store) {
        super(store);
    }
    @Override
    protected void eventHook(StateEvent se) {

    }

    @Override
    protected State getState() {
        return null;
    }

    @Override
    protected void draw(State state) {
        JFrame view = new JFrame("Button value");
        view.setSize(400, 300); view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ottieni il riferimento al Content Pane
        Container frmContentPane = view.getContentPane();
        // Usa frmContentPane per aggiungere elementi grafici
        JButton button = new JButton("Click me");
        button.addActionListener(new ButtonActionListener());
        frmContentPane.add(button);
        view.setVisible(true);

    }

    @Override
    protected void initialization(State state) {
        draw(state);
    }

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sendCommand(new MyString("INC", id));
        }
    }
}
