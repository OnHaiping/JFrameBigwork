package Frame;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DigitalClockFrame extends JPanel {
    private JLabel label;

    public DigitalClockFrame() {
        label = new JLabel();
        label.setFont(new Font("宋体", Font.PLAIN, 24)); // Set font to 宋体, plain style, size 24
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
        add(label);

        // 创建一个线程对象
        Thread thread = new Thread(this::updateTime);

        // 启动线程
        thread.start();
    }

    private void updateTime() {
        while (true) {
            // 获取当前日期和时间
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String dateTime = dtf.format(now);
            // 更新标签组件的内容
            label.setText(dateTime);
            try {
                // 暂停1秒钟
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
