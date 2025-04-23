import java.awt.Color;

/**
 * An image filter to display the blue channel as a grayscale image.
 * 
 * @author Alejandro Olea
 * @version 04.22.2025
 */
public class BlueChannelFilter extends Filter
{
    /**
     * Constructor for objects of class BlueChannelFilter.
     * @param name The name of the filter.
     */
    public BlueChannelFilter(String name)
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
                int b = pix.getBlue();
                image.setPixel(x, y, new Color(b, b, b));
            }
        }
    }
}
