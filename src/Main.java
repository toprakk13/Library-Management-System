import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//file paths
		String userFile=args[1];
		 String itemFile=args[0];
		 String commandFile=args[2];
		 String outputFile=args[3];
		
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
			// TODO Auto-generated method stub
		
			
			fileReader readd = new fileReader();//object from filereader
			readd.loadItemsFromFile(itemFile);
			 
			readd.loadUsersFromFile(userFile); 
			
			readd.processCommandsFromFile(commandFile, writer);
			writer.write("\n");
			writer.write("\n");
		 
		  

			//print the users
			for (Users user : readd.usersList) {
	            writer.write(user.toString()+"\n");  
	        }
			writer.write("\n");
	        
		   

		    //print the items
		    for (Items item : readd.itemsList) {
		    	writer.write(item.toString()+"\n");  
		     }
		
		


	}
		catch (IOException e) {
	        System.out.println("Komut dosyası okuma hatası: " + e.getMessage());
	    }

}
}
