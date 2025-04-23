import java.awt.Color;

/**
 * An image filter to apply a blue tint to the image.
 * 
 * @author Alejandro Olea
 * @version 04.22.2025
 */
public class BlueTintFilter extends Filter
{
    /**
     * Constructor for objects of class BlueTintFilter.
     * @param name The name of the filter.
     */
    public BlueTintFilter(String name)
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pix = image.getPixel(x, y);
                int r = pix.getRed();
                int g = pix.getGreen();
                int b = pix.getBlue();
                int newB = Math.min(255, b + 50);
                image.setPixel(x, y, new Color(r, g, newB));
            }
        }
    }
}
