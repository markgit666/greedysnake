import javax.swing.*;
import java.net.URL;

/**
 * @author yinxt
 * @version 1.0
 * @date 2020-05-23 21:30
 */
public class SnakeImage {

    public static void main(String[] args) {
        URL headerUrl = SnakeImage.class.getResource("/Images/body.png");
        ImageIcon imageIcon = new ImageIcon(headerUrl);
        System.out.println(imageIcon.getDescription());
        System.out.println(SnakeImage.class.getResource(""));

    }

    public static URL bodyURL = SnakeImage.class.getResource("/Images/body.png");
    public static ImageIcon bodyImg = new ImageIcon(bodyURL);
    public static URL downURL = SnakeImage.class.getResource("/Images/down.png");
    public static ImageIcon downImg = new ImageIcon(downURL);
    public static URL foodURL = SnakeImage.class.getResource("/Images/food.png");
    public static ImageIcon foodImg = new ImageIcon(foodURL);
    public static URL leftURL = SnakeImage.class.getResource("/Images/left.png");
    public static ImageIcon leftImg = new ImageIcon(leftURL);
    public static URL rightURL = SnakeImage.class.getResource("/Images/right.png");
    public static ImageIcon rightImg = new ImageIcon(rightURL);
    public static URL upURL = SnakeImage.class.getResource("/Images/up.png");
    public static ImageIcon upImg = new ImageIcon(upURL);

}
