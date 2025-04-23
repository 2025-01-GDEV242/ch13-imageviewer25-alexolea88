import java.awt.Color;

/**
 * An image filter to create a 2x2 grid with the original image along with
 * mirrored red, green, and blue tinted versions.
 * 
 * @author Alejandro Olea
 * @version 04.22.2025
 */
public class FlippedWarholFilter extends Filter
{
    /**
     * Constructor for objects of class FlippedWarholFilter.
     * @param name The name of the filter.
     */
    public FlippedWarholFilter(String name)
    {
        super(name);
    }

    /**
     * Apply this filter to an image.
     * 
     * @param image The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        // Create a temporary copy of the original image
        OFImage tempImage = new OFImage(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tempImage.setPixel(x, y, image.getPixel(x, y));
            }
        }
        
        // Create new image for 2x2 grid
        OFImage newImage = new OFImage(width * 2, height * 2);
        
        // Top-left: Original image scaled to width x height
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Scale by sampling from tempImage
                int sourceX = x * tempImage.getWidth() / width;
                int sourceY = y * tempImage.getHeight() / height;
                Color pix = tempImage.getPixel(sourceX, sourceY);
                newImage.setPixel(x, y, pix);
            }
        }
        
        // Top-right: Red tint, mirrored horizontally
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pix = tempImage.getPixel(x, y);
                int r = pix.getRed();
                int g = pix.getGreen();
                int b = pix.getBlue();
                int newR = Math.min(255, r + 50);
                newImage.setPixel((width - 1 - x) + width, y, new Color(newR, g, b));
            }
        }
        
        // Bottom-left: Green tint, mirrored vertically
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pix = tempImage.getPixel(x, y);
                int r = pix.getRed();
                int g = pix.getGreen();
                int b = pix.getBlue();
                int newG = Math.min(255, g + 50);
                newImage.setPixel(x, (height - 1 - y) + height, new Color(r, newG, b));
            }
        }
        
        // Bottom-right: Blue tint, mirrored horizontally and vertically
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pix = tempImage.getPixel(x, y);
                int r = pix.getRed();
                int g = pix.getGreen();
                int b = pix.getBlue();
                int newB = Math.min(255, b + 50);
                newImage.setPixel((width - 1 - x) + width, (height - 1 - y) + height, new Color(r, g, newB));
            }
        }
        
        // Scale down to original size
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pix = newImage.getPixel(x * 2, y * 2);
                image.setPixel(x, y, pix);
            }
        }
    }
}
