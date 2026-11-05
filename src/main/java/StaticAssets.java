import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import javax.swing.ImageIcon;

public class StaticAssets {
	
	public static Font loadCustomFont(String path, float size) {
	    try {
	        InputStream inputStream = StaticAssets.class.getClassLoader().getResourceAsStream(path);
	        if (inputStream == null) {
	            throw new RuntimeException("Resource not found: " + path);
	        }
	        Font customFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	        
	        // Register it so the JVM can handle it
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(customFont);
	        
	        // Return the font at the specific size you want
	        return customFont.deriveFont(size);
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Fallback font if the file is missing
	        return new Font("Arial", Font.BOLD, (int)size);
	    }
	}
	
	public static ImageIcon loadImage(String path) {
	    try {
	        InputStream inputStream = StaticAssets.class.getClassLoader().getResourceAsStream(path);
	        if (inputStream == null) {
	            throw new RuntimeException("Resource not found: " + path);
	        }
	        return new ImageIcon(inputStream.readAllBytes());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	static Font jersey10 = loadCustomFont("fonts/Jersey10-Regular.ttf", 20);
	
	static Font jersey25 = loadCustomFont("fonts/Jersey25-Regular.ttf", 50);
}
