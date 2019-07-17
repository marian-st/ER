package Generator;
import Entities.Recovery;
import State.StringCommand;
import System.Sistema;
public class DataGenerator {
    public static void main(String... args) {
        DataThread<StringCommand> data = new DataThread(Sistema.getInstance().getStore(), new Recovery());
        data.start();
    }
}