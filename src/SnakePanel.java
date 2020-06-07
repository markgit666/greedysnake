import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.Timer;

/**
 * @author yinxt
 * @version 1.0
 * @date 2020-05-23 22:02
 */
public class SnakePanel extends JPanel {

    int length;
    int[] snakeX = new int[500];
    int[] snakeY = new int[500];
    //定义一个变量来判断小蛇的方向：
    String direction = "R";//默认情况下小蛇是向右的

    volatile boolean isStart = false;

    int score;

    int foodX;
    int foodY;

    Timer timer;

    volatile boolean isDead = false;

    public SnakePanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_SPACE) {
                    if (isDead) {
                        init();
                        isDead = false;
                    } else {
                        isStart = !isStart;
                        repaint();
                    }
                }
                if (code == KeyEvent.VK_UP && !direction.equals("D")) {
                    direction = "U";
                }
                if (code == KeyEvent.VK_DOWN && !direction.equals("U")) {
                    direction = "D";
                }
                if (code == KeyEvent.VK_LEFT && !direction.equals("R")) {
                    direction = "L";
                }
                if (code == KeyEvent.VK_RIGHT && !direction.equals("L")) {
                    direction = "R";
                }
            }
        });
    }

    public void init() {
        length = 3;

        direction = "R";

        //蛇头
        snakeX[0] = 200;
        snakeY[0] = 375;
        //第一节身子的信息：
        snakeX[1] = 175;
        snakeY[1] = 375;
        //第二节身子的信息：
        snakeX[2] = 150;
        snakeY[2] = 375;

        foodX = 325;
        foodY = 125;

        timer = new Timer(150, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (isStart && isDead == false) {
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }

                    switch (direction) {
                        case "U":
                            snakeY[0] -= 25;
                            break;
                        case "D":
                            snakeY[0] += 25;
                            break;
                        case "L":
                            snakeX[0] -= 25;
                            break;
                        case "R":
                            snakeX[0] += 25;
                            break;
                    }

                    if (snakeX[0] > 725) {
                        snakeX[0] = 0;
                    }
                    if (snakeX[0] < 0) {
                        snakeX[0] = 725;
                    }
                    if (snakeY[0] > 750) {
                        snakeY[0] = 25;
                    }
                    if (snakeY[0] < 25) {
                        snakeY[0] = 750;
                    }

                    if (snakeX[0] == foodX && snakeY[0] == foodY) {
                        length++;
                        score = (length - 3) * 10;
                        foodX = 25 * (new Random().nextInt(25));
                        foodY = 25 + 25 * (new Random().nextInt(25));
                    }

                    for (int i = 1; i < length; i++) {
                        if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]) {
                            isDead = true;
                            break;
                        }
                    }
                    repaint();
                }

            }
        });

        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //设置面板背景色
        this.setBackground(new Color(255, 255, 255));
        //画header
//        SnakeImage.headerImg.paintIcon(this, g, 10, 10);

        //画正方形
        g.setColor(new Color(125, 112, 158));
        g.fillRect(0, 25, 750, 750);

        //画格子
        g.setColor(new Color(182, 237, 114));
        for (int i = 0; i <= 775; i = i + 25) {
            g.drawLine(0, i, 750, i);
        }
        for (int j = 25; j <= 750; j = j + 25) {
            g.drawLine(j, 25, j, 775);
        }

        //根据小蛇的方向来画蛇头：
        switch (direction) {
            case "L":
                SnakeImage.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "R":
                SnakeImage.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "U":
                SnakeImage.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                SnakeImage.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }
        //画蛇身
        for (int i = 1; i < length; i++) {
            SnakeImage.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //画食物
        SnakeImage.foodImg.paintIcon(this, g, foodX, foodY);

        //中断或者开始游戏
        if (isStart == false) {
            g.setColor(new Color(130, 191, 51));
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按空格键开始游戏", 220, 300);
        }

        //中断或者开始游戏
        if (isDead) {
            g.setColor(new Color(130, 191, 51));
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏结束，按空格键重新开始游戏", 80, 300);
            timer.stop();
        }

        g.setColor(new Color(239, 223, 255));
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("积分：" + score, 0, 20);

    }
}
