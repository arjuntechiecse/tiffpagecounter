import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
public class renameTiff{

    public static void main(String[] args) {



// Code for fetching the fax name
        System.setProperty("webdriver.edge.driver", "driver Path her");//web driver path here

        WebDriver driver = new EdgeDriver();

        driver.manage().window().maximize();

        driver.get("ProvideWebURL");// Provide web url
        //String FaxName= driver.findElement(By.xpath("//body")).getText();
        //rename code
        String folderPath = ""; // Specify the folder path where the files are located

        File folder = new File(folderPath);
        File[] files = folder.listFiles(); // Get a list of files in the folder

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String oldName = file.getName();
                    //String newName = "new_" + oldName; // Specify the new name here
                    String FaxName= driver.findElement(By.xpath("//body")).getText();

                    String newName = FaxName;

                    File newFile = new File(folderPath, newName);

                    if (file.renameTo(newFile)) {
                       // String FaxName= driver.findElement(By.xpath("//body")).getText();
                        System.out.println("File renamed successfully: " + oldName + " -> " + newName);
                        driver.navigate().refresh();
                    } else {
                        System.out.println("Failed to rename file: " + oldName);
                        driver.navigate().refresh();
                    }

                }

            }
        }

        else {
            System.out.println("No files found in the folder.");
        }

        driver.quit();

    }

}
