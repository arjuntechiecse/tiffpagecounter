import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import java.io.File;
import java.io.IOException;

public class TiffPageCounter {
    public static void main(String[] args) {
        // Specify the directory containing the TIFF files
        String directoryPath = "C:\\Users\\srarj\\Desktop\\tiff_files";

        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".tiff") || name.toLowerCase().endsWith(".tif"));
            if (files != null) {
                for (File file : files) {
                    try {
                        int pageCount = countTiffPages(file);
                        System.out.println(file.getName() + " - Page Count: " + pageCount);
                    } catch (IOException e) {
                        System.out.println("Error reading TIFF file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("Invalid directory path: " + directoryPath);
        }
    }

    private static int countTiffPages(File file) throws IOException {
        // Read the TIFF image using ImageIO
        ImageReader reader = ImageIO.getImageReadersByFormatName("TIFF").next();
        reader.setInput(ImageIO.createImageInputStream(file));

        // Get the number of pages in the TIFF image
        int pageCount = reader.getNumImages(true);

        // Dispose the reader
        reader.dispose();

        return pageCount;
    }
}
