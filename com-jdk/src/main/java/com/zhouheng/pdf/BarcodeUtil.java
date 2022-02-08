package com.zhouheng.pdf;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


/**
 * 生成条形码
 *
 * @author huxingbin
 */

public class BarcodeUtil {

    /**
     * 二维码参数
     */
    private static final Map<EncodeHintType, Object> HINTS = new HashMap<>();

    static {
        // 字符编码
        HINTS.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 二维码与图片边距
        HINTS.put(EncodeHintType.MARGIN, 0);
    }

    /**
     * 生成条形码
     *
     * @param msg  数据
     * @param path 路径
     *
     * @return 文件
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generateBarCode128(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成条形码 -顺丰
     *
     * @param msg  数据
     * @param path 路径
     *
     * @return 文件
     */
    public static File generateFileSF(String msg, String path) {
        File file = new File(path);
        try {
            generateBarCode128Sf(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成二维码
     *
     * @param text     数据
     * @param width    宽
     * @param height   高
     * @param filePath 路径
     *
     * @return 二维码文件
     */
    public static File generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        File file = new File(filePath);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, HINTS);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        return file;
    }

    /**
     * 生成字节
     *
     * @param msg
     *
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }

    /**
     * 生成Code39Bean条形码
     *
     * @param msg
     * @param ous
     */
    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }
        Code39Bean bean = new Code39Bean();
        // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
        // 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.setWideFactor(3);
        bean.doQuietZone(false);
        /*  bean.setBarHeight((double) ObjectUtils.defaultIfNull(5.0, 9.0D));*/

        String format = "image/png";
        try {
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
            // 生成条形码
            bean.generateBarcode(canvas, msg);
            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成code128条形码
     *
     * @param message 要生成的文本
     *
     * @return 图片对应的字节码
     */
    public static void generateBarCode128(String message, OutputStream ous) {
        if (StringUtils.isEmpty(message) || ous == null) {
            return;
        }
        Code128Bean bean = new Code128Bean();
        // 分辨率
        int dpi = 150;
        final double moduleWidth = UnitConv.in2mm(2.0f / dpi);
        // 设置两侧是否留白
        bean.doQuietZone(false);

        // 设置条形码高度和宽度
        bean.setBarHeight(10);

        bean.setModuleWidth(moduleWidth);

        // 设置文本位置（包括是否显示）
        if (false) {
            bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
        }
        // 设置图片类型
        String format = "image/png";

        BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                BufferedImage.TYPE_BYTE_BINARY, false, 0);

        // 生产条形码
        bean.generateBarcode(canvas, message);
        try {
            canvas.finish();
        } catch (IOException e) {
            //ByteArrayOutputStream won't happen
        }
    }

    /**
     * 生成code128条形码 - 顺丰
     *
     * @param message 要生成的文本
     *
     * @return 图片对应的字节码
     */
    public static void generateBarCode128Sf(String message, OutputStream ous) {
        if (StringUtils.isEmpty(message) || ous == null) {
            return;
        }
        Code128Bean bean = new Code128Bean();
        // 分辨率
        int dpi = 150;
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
        // 设置两侧是否留白
        bean.doQuietZone(false);

        // 设置条形码高度和宽度
        bean.setBarHeight(10);

        bean.setModuleWidth(moduleWidth);

        // 设置文本位置（包括是否显示）
      /*  if (false) {
            bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
        }*/
        // 设置图片类型
        String format = "image/png";

        BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                BufferedImage.TYPE_BYTE_BINARY, false, 0);

        // 生产条形码
        bean.generateBarcode(canvas, message);
        try {
            canvas.finish();
        } catch (IOException e) {
            //ByteArrayOutputStream won't happen
        }
    }


}
