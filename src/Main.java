import javax.swing.*;

public class Main {
    public static void main(String[] args){
        // GUI Instance

		if(args.length == 0) {
			DatabaseGui dGui = new DatabaseGui("group10", "3307", "root", "comp6120");
			dGui.display();
		} else {
			
			// arg[0] = dbName
			// arg[1] = dbPort
			// arg[2] = username
			// arg[3] = password
			DatabaseGui dGui = new DatabaseGui(args[0],
											   args[1],
											   args[2],
											   args[3]);
		    dGui.display();
		}
		
    }
}
