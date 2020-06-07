import javax.swing.*;

/**
 * @author yinxt
 * @version 1.0
 * @date 2020-05-23 21:35
 */
public class GameStater {


    public static void main(String[] args) {
        JFrame snakeJFrame = new JFrame();
        //设置jframe属性
        snakeJFrame.setTitle("快乐贪吃蛇");
        snakeJFrame.setSize(750, 795);
        snakeJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        snakeJFrame.setResizable(false);
        snakeJFrame.setLocationRelativeTo(null);

        //添加面板
        snakeJFrame.add(new SnakePanel());

        snakeJFrame.setVisible(true);

        System.out.println(GameStater.class.getResource("/"));
        System.out.println(GameStater.class.getResource(""));

    }

}
