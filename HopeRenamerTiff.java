import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class MultiPageTiffRenamer {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\srarj\\Desktop\\tiff_files";
        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".tif") || name.toLowerCase().endsWith(".tiff"));

            if (files != null) {
                for (File file : files) {
                    try {
                        int pageCount = getTiffPageCount(file);
                        String newFileName = getNewFileName(file.getName(), pageCount);
                        renameFile(file, newFileName);
                    } catch (IOException e) {
                        System.out.println("Error reading TIFF file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("Invalid directory path: " + folderPath);
        }
    }

    private static int getTiffPageCount(File file) throws IOException {
        ImageInputStream input = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> readers = ImageIO.getImageReaders(input);

        if (readers.hasNext()) {
            ImageReader reader = readers.next();
            reader.setInput(input);

            int pageCount = reader.getNumImages(true);

            reader.dispose();
            input.close();

            return pageCount;
        } else {
            input.close();
            throw new IOException("No TIFF ImageReader found for file: " + file.getName());
        }
    }

    private static String getNewFileName(String fileName, int pageCount) {
        int dotIndex = fileName.lastIndexOf('.');
        String baseName = fileName.substring(0, dotIndex);
        String extension = fileName.substring(dotIndex);

        return baseName + "-" + pageCount + extension;
    }

    private static void renameFile(File file, String newFileName) {
        String folderPath = file.getParent();
        File newFile = new File(folderPath + File.separator + newFileName);

        if (file.renameTo(newFile)) {
            System.out.println("File renamed to: " + newFileName);
        } else {
            System.out.println("Error renaming file: " + file.getName());
        }
    }
}
