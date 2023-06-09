import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class TiffPageCountAndRename {
    public static void main(String[] args) {
        // Specify the directory containing the TIFF files
        String directoryPath = "C:\\Users\\srarj\\Desktop\\tiff_files";

        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".tiff") || name.toLowerCase().endsWith(".tif"));
            if (files != null) {
                for (File file : files) {
                    try {
                        int pageCount = countTiffPages(file);
                        System.out.println(file.getName() + " - Page Count: " + pageCount);

                        // Rename the file with the page count
                        renameFileWithPageCount(file, pageCount);
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
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);

        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
        if (!imageReaders.hasNext()) {
            throw new IOException("No TIFF image readers found");
        }

        ImageReader imageReader = imageReaders.next();
        imageReader.setInput(imageInputStream);

        int pageCount = imageReader.getNumImages(true);

        imageReader.dispose();
        imageInputStream.close();

        return pageCount;
    }

    private static void renameFileWithPageCount(File file, int pageCount) {
        String filePath = file.getAbsolutePath();
        String fileName = file.getName();

        // Remove the file extension
        String extension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = fileName.substring(dotIndex);
            fileName = fileName.substring(0, dotIndex);
        }
// remove last two characters
        String removedFileName = fileName.substring(0, fileName.length() - 2);

        // Create the new file name with the page count
        String newFileName = removedFileName + pageCount + extension;
        String newFilePath = filePath.replace(file.getName(), newFileName);

        // Rename the file
        File newFile = new File(newFilePath);
        if (file.renameTo(newFile)) {
            System.out.println("File renamed to: " + newFileName);
        } else {
            System.out.println("Error renaming file: " + file.getName());
        }
    }
}
