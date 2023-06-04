import java.io.File;
public class rename_tifffiles {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		
String folderPath = "C:\\Users\\srarj\\Desktop\\tiff_files"; // Specify the folder path where the files are located
        
        File folder = new File(folderPath);
        File[] files = folder.listFiles(); // Get a list of files in the folder
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String oldName = file.getName();
                    String newName = "new_" + oldName; // Specify the new name here
                    
                    File newFile = new File(folderPath, newName);
                    
                    if (file.renameTo(newFile)) {
                        System.out.println("File renamed successfully: " + oldName + " -> " + newName);
                    } else {
                        System.out.println("Failed to rename file: " + oldName);
                    }
                }
            }
        } 
        
        else {
            System.out.println("No files found in the folder.");
        }

	}

}
