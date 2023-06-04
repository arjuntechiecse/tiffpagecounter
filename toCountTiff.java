import com.twelvemonkeys.imageio.ImageReaderBase;

	import javax.imageio.ImageIO;
	import javax.imageio.ImageReader;
	import javax.imageio.stream.ImageInputStream;
	import java.io.File;
	import java.io.IOException;
	import java.util.Iterator;
	
public class toCountTiff {

	 public static void main(String[] args) {
	       File tiffFile = new File("C:\\Users\\srarj\\Desktop\\Sample tiff.tiff");
		 //File tiffFile = new File("https://drive.google.com/file/d/1sphq2vjAeYjjcAhzFCmxJ0FDIKosUAd8/view?usp=sharing");
		 

	        try (ImageInputStream input = ImageIO.createImageInputStream(tiffFile)) {
	            // Locate the TIFF image reader
	            Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
	            ImageReader reader = null;

	            while (readers.hasNext()) {
	                ImageReader candidate = readers.next();
	                if (candidate instanceof ImageReaderBase && candidate.getFormatName().equalsIgnoreCase("TIFF")) {
	                    reader = candidate;
	                    break;
	                }
	            }

	            // Get the page count using the TIFF image reader
	            if (reader != null) {
	                reader.setInput(input);
	                int pageCount = reader.getNumImages(true);

	                System.out.println("Page count: " + pageCount);
	            } else {
	                System.err.println("TIFF image reader not found.");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	


}
