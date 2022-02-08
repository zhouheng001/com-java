package com.zhouheng.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 创建PDF文件
 *
 * @author zhouheng
 * @create 2022-01-11 9:39 上午
 */
public class CreatePDF {


    public static void main(String[] args) throws IOException, DocumentException {

        String fileUrl = writeExampaperPdf();
        // 文件
        File file = new File(fileUrl);
    }

    /**
     * 将需要打印的单子写到指定的位置为pdf
     *
     * @throws IOException DocumentException
     */
    public static String writeExampaperPdf() throws IOException, DocumentException {

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //一级标题
        Font firsetTitleFont = new Font(bfChinese, 20, Font.BOLD);
        //加粗
        Font boldFont = new Font(bfChinese, 15, Font.BOLD);
        //输出路径
        String outPath = "/Users/zhouheng/chj/data/pdf/索赔卡.pdf";
        //设置横向
        //设置纸张 创建文档实例
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        //设置横向
        Rectangle pageSize = new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth());
        document.setPageSize(pageSize);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(outPath)));

        //        //创建输出流
        document.open();
//        document.add(new Chunk(""));
//        PdfContentByte canvas = writer.getDirectContent();
//        PackageOrderLabelPrint packageOrderLabelPrint = new PackageOrderLabelPrint();
//        packageOrderLabelPrint.setLiXiang(Toolkit.getDefaultToolkit().getImage(CreatePDF.class.getResource("/img/lixiang.png")));
//        packageOrderLabelPrint.setPrintDataTime(new String[]{"2022-01-14", "2022-01-14"});
//        packageOrderLabelPrint.setFromWarehouseCode("PDC001");
//        packageOrderLabelPrint.setToWarehouseCode("PDC001");
//        packageOrderLabelPrint.setToUserAddress("北京");
//        packageOrderLabelPrint.setToUser("周恒");
//        packageOrderLabelPrint.setToUserPhone("2574055113");
//        packageOrderLabelPrint.setPackageOrderCode("fjsdlakfjlkasjfsd");
//        for (int i = 0; i < 2; i++) {
//            packageOrderLabelPrint.setMeasure(String.valueOf(i));
//            packageOrderLabelPrint.setWeight(String.valueOf(i));
//            packageOrderLabelPrint.setPackageOrderBoxCode("123");
//            String path1 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test1.png");
//            BarcodeUtil.generateFile("dfjasdkfjasf", path1);
//            packageOrderLabelPrint.setPackageOrderCodeImage(Toolkit.getDefaultToolkit().getImage(path1));
//            String path2 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test2.png");
//            BarcodeUtil.generateFile("fkasdjfkasdjf", path2);
//            packageOrderLabelPrint.setPackageOrderBoxCodeImage(Toolkit.getDefaultToolkit().getImage(path2));
//            Graphics2D g2 = canvas.createGraphics(420, 280);
//            packageOrderLabelPrint.paint(g2);
//            g2.dispose();
//            document.newPage();
//        }
                //段落
                Paragraph pt = new Paragraph();
                //短语
                Phrase ph1 = new Phrase();
                // 4.向文档中添加内容
                // 通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
                //设置logo
                Image png = Image.getInstance(CreatePDF.class.getResource("/img/lixiang.png"));
                png.scaleToFit(160, 40);
                png.setAbsolutePosition(30, 520);

                document.add(png);

                ph1 = createParagraph("索赔号:" + "C90215XXX", boldFont, Element.ALIGN_RIGHT, 50, 50, 40, 0, BaseColor.WHITE, 0);
                document.add(ph1);

                // 将标题写进去
                ph1 = createParagraph("理想汽车索赔卡", firsetTitleFont, Element.ALIGN_LEFT, 50, 0, 20, 0, BaseColor.WHITE, 680);
                document.add(ph1);

                // 索赔内容
                ph1 = createParagraph("索赔内容", boldFont, Element.ALIGN_LEFT, 50, 0, 20, 0, BaseColor.GRAY, 680);
                document.add(ph1);
                //插入空白行
                pt = new Paragraph("             ");
                document.add(pt);

                ph1 = createPhrase("       ", boldFont);
                document.add(ph1);
                ph1 = createPhrase("索 赔 号：", boldFont);
                document.add(ph1);
                ph1 = createPhrase("C90215XXX", boldFont);
                document.add(ph1);
                //插入空白行
                pt = new Paragraph("             ");
                document.add(pt);

                ph1 = createPhrase("       ", boldFont);
                document.add(ph1);
                ph1 = createPhrase("索赔类型：", boldFont);
                document.add(ph1);
                ph1 = createPhrase("制造商缺陷", boldFont);
                document.add(ph1);
                //插入空白行
                pt = new Paragraph("             ");
                document.add(pt);

                ph1 = createPhrase("       ", boldFont);
                document.add(ph1);
                ph1 = createPhrase("零 件 号：", boldFont);
                document.add(ph1);
                ph1 = createPhrase("A0101101", boldFont);
                document.add(ph1);
                //插入空白行
                pt = new Paragraph("             ");
                document.add(pt);

                ph1 = createPhrase("       ", boldFont);
                document.add(ph1);
                ph1 = createPhrase("零件名称：", boldFont);
                document.add(ph1);
                ph1 = createPhrase("电力助力转向管柱带中间轴总成", boldFont);
                document.add(ph1);
                //插入空白行
                pt = new Paragraph("             ");
                document.add(pt);

                ph1 = createPhrase("       ", boldFont);
                document.add(ph1);
                ph1 = createPhrase("索赔数量：", boldFont);
                document.add(ph1);
                ph1 = createPhrase("1", boldFont);
                document.add(ph1);
                // 描述
                ph1 = createParagraph("描述", boldFont, Element.ALIGN_LEFT, 50, 0, 20, 0, BaseColor.GRAY, 710);
                document.add(ph1);
                // 描述内容
                ph1 = createParagraph("1. 基于原有移位逻辑上新增逻辑： 检查库位是否允许混方，如果不允许，PDC仓库的移位单中校验两个不同的零件编码不允许移入同一个库位，如下图，用户添加移位行点击“确认”的时候执行校验逻辑，如果校验失败则报错提醒“库位XXXXXX" +
                        "已存在零件XXXXX  保存失败", boldFont, Element.ALIGN_LEFT, 50, 50, 10, 0, BaseColor.WHITE, 0);
                document.add(ph1);
        // 5.关闭文档
        document.close();
        writer.close();
        return outPath;
    }

    public static void createContent(Phrase phrase, Document document, Font boldFont) throws DocumentException {

        phrase = createPhrase("       ", boldFont);
        document.add(phrase);
        phrase = createPhrase("零件号：", boldFont);
        document.add(phrase);
        phrase = createPhrase("A0101101", boldFont);
        document.add(phrase);
        //插入空白行
        phrase = new Paragraph("             ");
        document.add(phrase);
    }

    /**
     * 设置右边距
     *
     * @param str 对象
     * @param i   长度
     * @return 结果
     */
    public static String rightPad(String str, int i) {
        StringBuilder space = new StringBuilder();
        for (int k = 0; k < 1; k++) {
            space.append("                                         " + space);
        }
        String result = str + space;
        return result;
    }

    /**
     * 定义一个短语
     *
     * @param text 文本信息
     * @param font 设置字体
     * @return
     */
    private static Phrase createPhrase(String text, Font font) {
        // 将标题写进去
        Phrase pt = new Phrase();
        Chunk chunk = new Chunk(text, font);
        pt.add(chunk);
        return pt;
    }

    /**
     * 定义一个段落
     *
     * @param text            文本信息
     * @param font            设置字体
     * @param alignment       对齐方式
     * @param indentationLeft 左缩紧
     * @param spacingBefore   段落前间距
     * @param spacingAfter    段落后后间距
     * @return
     */
    private static Phrase createParagraph(String text, Font font, int alignment, float indentationLeft, float indentationRight, float spacingBefore,
                                          float spacingAfter, BaseColor backGroundColor, float extraRight) {
        // 将标题写进去
        Chunk chunk = new Chunk(text, font);
        chunk.setBackground(backGroundColor, 0, 0, extraRight, 0);
        Paragraph pt = new Paragraph(chunk);
        // 设置文字居中 0靠左 1，居中 2，靠右
        pt.setAlignment(alignment);
        // 段落左缩进
        pt.setIndentationLeft(indentationLeft);
        // 段落右缩进
        pt.setIndentationRight(indentationRight);
        // 上一行间距
        pt.setSpacingBefore(spacingBefore);
        // 下一行间距
        pt.setSpacingAfter(spacingAfter);
        return pt;
    }

    private static List<List<String>> createData() {
        List<List<String>> data = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        data.add(list);
        return data;
    }

    /**
     * 创建单元格
     *
     * @param table 定义表格
     * @param title 定义标题
     * @param data  定义数据
     * @param row
     * @param cols
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private static PdfPTable createCell(PdfPTable table, String[] title, String[] data, int row, int cols) throws DocumentException, IOException {
        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bfChinese, 15, Font.BOLD);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {
                PdfPCell cell = new PdfPCell();
                //设置表头
                if (cols == 0 && title != null) {
                    //这样表头才能居中
                    cell = new PdfPCell(new Phrase(title[j], font));
                    if (table.getRows().size() == 0) {
                        cell.setBorderWidthTop(1);
                    }
                }
                if (row == 1 && cols == 1) {
                    //设置顶部的边框宽度
                    cell.setBorderWidthTop(1);
                }
                if (j == 0) {
                    //设置左边的边框宽度
                    cell.setBorderWidthLeft(1);
                }
                if (j == (cols - 1)) {
                    //设置右边的边框宽度
                    cell.setBorderWidthRight(1);
                }
                if (i == (row - 1)) {
                    //设置底部的边框宽度
                    cell.setBorderWidthBottom(1);
                }
                //设置单元格高度
                //                cell.setMinimumHeight(100);
                //设置可以居中
                cell.setUseAscender(true);
                //设置水平居中
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                //设置垂直居中
                cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
                // 隐藏表格
                cell.disableBorderSide(PdfPCell.LISTITEM);
                table.addCell(cell);
            }
        }
        return table;
    }

    /**
     * 创建单元格
     *
     * @param table
     * @param row
     * @param cols
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private static PdfPTable createCellTwo(PdfPTable table, String[] title, int row, int cols) throws DocumentException, IOException {
        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bfChinese, 20, Font.BOLD);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {
                PdfPCell cell = new PdfPCell();
                if (i == 0 && title != null) {
                    cell = new PdfPCell(new Phrase(title[j], font));
                    if (table.getRows().size() == 0) {
                        cell.setBorderWidthTop(0);
                    }
                }

                cell.setBorderWidthTop(0);
                cell.setBorderWidthLeft(0);
                cell.setBorderWidthRight(0);
                cell.setBorderWidthBottom(0);

                cell.setMinimumHeight(25);
                cell.setUseAscender(true);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
                table.addCell(cell);
            }
        }
        return table;
    }
}
