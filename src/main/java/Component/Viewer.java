package Component;

import State.State;
import State.Store;
import State.StateEvent;
import State.Command;

import javax.swing.*;
import java.awt.*;

public class Viewer extends Component<Command> {
    private JFrame view;
    private JLabel lab;
    public Viewer(Store store) {
        super(store);
    }
    @Override
    void eventHook(StateEvent se) {
        view.remove(lab);
        lab = new JLabel(String.format("%d", se.state().getCounter()));
        view.add(lab);
        view.repaint();
        view.revalidate();
    }

    @Override
    State getState() {
        return null;
    }

    @Override
    void draw(State state) {
        view = new JFrame("Button value");
        view.setSize(80, 80); view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ottieni il riferimento al Content Pane
        Container frmContentPane = view.getContentPane();
        // Usa frmContentPane per aggiungere elementi grafici
        int nlikes = state.getCounter();
        lab = new JLabel(String.format("%d", nlikes));
        frmContentPane.add(lab);
        view.setVisible(true);

    }

    @Override
    void initialization(State state) {
        draw(state);
    }
}
