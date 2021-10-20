import javax.swing.*;

// arg[0] = dbName
// arg[1] = dbPort
// arg[2] = user
// arg[3] = password
public class Main {
    public static void main(String[] args){
        // GUI Instance
		DatabaseGui dGui = new DatabaseGui();
		dGui.display();

		DatabaseInterface dbInter = new DatabaseInterface(args[0],
														  args[1],
														  args[2],
														  args[3]);

		dbInter.execStatement("SHOW TABLES");
    }
}
