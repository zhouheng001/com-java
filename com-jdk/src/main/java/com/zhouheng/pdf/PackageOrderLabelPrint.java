package com.zhouheng.pdf;

import com.itextpdf.text.pdf.BaseFont;
import lombok.Data;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.FileInputStream;

/**
 * 装箱单 打签
 *
 * @author huxingbin
 */

@Data
public class PackageOrderLabelPrint extends JPanel {

    /**
     * 商标
     */
    private Image liXiang;

    /**
     * 发货方代码
     */
    private String fromWarehouseCode;

    /**
     * 收货方代码
     */
    private String toWarehouseCode;

    /**
     * 打印时间
     */
    private String[] printDataTime;

    /**
     * 订单号
     */
    private String packageOrderCode;

    /**
     * 订单号条形码
     */
    private Image packageOrderCodeImage;

    /**
     * 箱号
     */
    private String packageOrderBoxCode;

    /**
     * 箱号条形码
     */
    private Image packageOrderBoxCodeImage;

    /**
     * 收货人
     */
    private String toUser;

    /**
     * 收货人地址
     */
    private String toUserAddress;

    /**
     * 收货人电话
     */
    private String toUserPhone;

    /**
     * 纸箱尺寸
     */
    private String measure;

    /**
     * 重量
     */
    private String weight;


    @SneakyThrows
    public void paint(Graphics graphics) {
        //此 Graphics2D 类扩展 Graphics 类，以提供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制。
        //它是用于在 Java(tm) 平台上呈现二维形状、文本和图像的基础类。
        Graphics2D g2 = (Graphics2D) graphics;
        //设置打印颜色为黑色
        g2.setColor(Color.black);

        //Font.PLAIN： 普通样式常量  	Font.ITALIC 斜体样式常量	Font.BOLD 粗体样式常量。
        //根据指定名称、样式和磅值大小，创建一个新 Font。
        Font font = new Font("宋体", com.itextpdf.text.Font.BOLD, 25);
        Font font1 = new Font("宋体", com.itextpdf.text.Font.BOLD, 10);
        //        Font font2 = new Font("宋体", com.itextpdf.text.Font.BOLD, 8);
        //Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
        Font font2 = new Font("Segoe Print", Font.BOLD, 8);
        //设置标题打印字体25
        g2.setFont(font);
        //设置打印模板
        //边框
        g2.drawLine((int) 10, (int) 10, (int) 10, (int) 270);
        g2.drawLine((int) 410, (int) 10, (int) 410, (int) 270);
        g2.drawLine((int) 10, (int) 10, (int) 410, (int) 10);
        g2.drawLine((int) 10, (int) 270, (int) 410, (int) 270);

        g2.drawLine((int) 10, (int) 50, (int) 410, (int) 50);
        g2.drawLine((int) 10, (int) 90, (int) 410, (int) 90);
        g2.drawLine((int) 10, (int) 135, (int) 410, (int) 135);
        g2.drawLine((int) 10, (int) 175, (int) 410, (int) 175);
        g2.drawLine((int) 10, (int) 225, (int) 410, (int) 225);

        g2.drawLine((int) 143, (int) 10, (int) 143, (int) 90);
        g2.drawLine((int) 193, (int) 10, (int) 193, (int) 90);
        g2.drawLine((int) 276, (int) 10, (int) 276, (int) 90);
        g2.drawLine((int) 326, (int) 10, (int) 326, (int) 90);
        g2.drawLine((int) 60, (int) 50, (int) 60, (int) 270);
        g2.drawLine((int) 276, (int) 135, (int) 276, (int) 175);
        g2.drawLine((int) 326, (int) 135, (int) 326, (int) 175);

        //设置小票的标题标题
        Component c = null;
        //商标
        g2.drawImage(liXiang, (int) 20, (int) 20, 105, 21, c);
        g2.setFont(font2);
        g2.drawString("订单号", (float) 23, (float) 65);
        g2.drawString("Order", (float) 24, (float) 75);
        g2.drawString("No.", (float) 30, (float) 85);
        g2.drawString("订单号", (float) 23, (float) 103);
        g2.drawString("条形码", (float) 23, (float) 113);
        g2.drawString("Order", (float) 24, (float) 123);
        g2.drawString("No.", (float) 30, (float) 133);
        g2.drawString("纸箱尺寸", (float) 19, (float) 144);
        g2.drawString("（mm）", (float) 25, (float) 154);
        g2.drawString("Package", (float) 20, (float) 164);
        g2.drawString("Size", (float) 25, (float) 174);
        g2.drawString("目的地", (float) 23, (float) 195);
        g2.drawString("Destina", (float) 20, (float) 205);
        g2.drawString("tion", (float) 28, (float) 215);
        g2.drawString("箱号", (float) 27, (float) 238);
        g2.drawString("条形码", (float) 23, (float) 248);
        g2.drawString("Carton", (float) 18, (float) 258);
        g2.drawString("No.", (float) 30, (float) 268);

        g2.drawString("发货方代码", (float) 148, (float) 25);
        g2.drawString("Shipper", (float) 150, (float) 35);
        g2.drawString("Code", (float) 155, (float) 45);
        g2.drawString("收货方代码", (float) 281, (float) 25);
        g2.drawString("Consignee", (float) 281, (float) 35);
        g2.drawString("Code", (float) 288, (float) 45);
        g2.drawString("箱号", (float) 160, (float) 65);
        g2.drawString("Carton", (float) 158, (float) 75);
        g2.drawString("No.", (float) 162, (float) 85);
        g2.drawString("打印时间", (float) 285, (float) 65);
        g2.drawString("Issue", (float) 288, (float) 75);
        g2.drawString("Date", (float) 288, (float) 85);
        g2.drawString("重量(kg)", (float) 285, (float) 150);
        g2.drawString("Gross", (float) 290, (float) 160);
        g2.drawString("Weight", (float) 290, (float) 170);

        //设置打印数据
        g2.setFont(font1);

        //发货方代码
        g2.drawString(fromWarehouseCode, (float) 200, (float) 35);
        //收货方代码
        g2.drawString(toWarehouseCode, (float) 330, (float) 35);
        //订单号
        g2.drawString(packageOrderCode, (float) 65, (float) 75);
        //箱号
        g2.drawString(packageOrderBoxCode, (float) 200, (float) 75);

        //打印时间
        g2.drawString(printDataTime[0], (float) 330, (float) 70);
        if (null != printDataTime[1]) {
            g2.drawString(printDataTime[1], (float) 330, (float) 80);
        }
        //订单号条形码
        g2.drawImage(packageOrderCodeImage, (int) 150, (int) 91, 150, 43, c);
        //纸箱尺寸
        g2.drawString(measure, (float) 100, (float) 160);
        //重量
        g2.drawString(weight, (float) 340, (float) 160);
        //收货人地址
        g2.drawString(toUserAddress, (float) 70, (float) 195);
        //收货人
        g2.drawString("联系人：" + toUser, (float) 70, (float) 205);
        //收货人电话
        g2.drawString("电话：" + toUserPhone, (float) 70, (float) 215);
        //箱号条形码
        g2.drawImage(packageOrderBoxCodeImage, (int) 160, (int) 226, 130, 43, c);
    }
}
