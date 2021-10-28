import javax.swing.*;

// arg[0] = dbName
// arg[1] = dbPort
// arg[2] = user
// arg[3] = password
public class Main {
    public static void main(String[] args){
        // GUI Instance
		DatabaseGui dGui = new DatabaseGui("group10", "3307", "root", "comp6120");
		dGui.display();
    }
}
