package System.HP;

import State.Command;

public class HPMonitoring<C extends Command> implements HPInterface{
    private String file;

    public HPMonitoring() {
        this.file = "/A_HP_Monitoring.fxml";
    }

    public String getFile() {
        return this.file;
    }
}
