package Tools;

import javax.swing.*;
import java.awt.*;

public class GUITools {
    //获取当前系统的工具包
    static Toolkit kit = Toolkit.getDefaultToolkit();
    /**
     * 设置给定组件居中显示
     *
     * @param com
     */
    public static void setCenter(Component com) {
        int x = (kit.getScreenSize().width - com.getWidth()) / 2;
        int y = (kit.getScreenSize().height - com.getHeight()) / 2;
        com.setLocation(x, y);
    }

    /**
     * 设置给定窗体的图标
     * @param jFrame
     * @param path
     */
    public static void setTitleImage(JFrame jFrame, String path) {

        jFrame.setIconImage(kit.createImage(path));
    }
}
