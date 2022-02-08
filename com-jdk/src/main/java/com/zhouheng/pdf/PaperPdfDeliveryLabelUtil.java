package com.zhouheng.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 描述:
 * 入库单标签打印PDF文件
 *
 * @author zhouheng
 * @create 2022-01-17 2:36 下午
 */
public class PaperPdfDeliveryLabelUtil {

    public static void main(String[] args) throws IOException, DocumentException {
        writeExampaperPdf();
    }

    /**
     * 将需要打印的单子写到指定的位置为pdf
     *
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static String writeExampaperPdf() throws IOException,
            DocumentException {
        BaseFont bfChinese = BaseFont.createFont("simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        //输出路径
        String outPath = "/Users/zhouheng/chj/data/pdf/出库单打签.pdf";
        Document document = new Document(PageSize.A4, 0, 0, 5, 0);
        //设置横向
        Rectangle pageSize = new Rectangle(320, 250);
        document.setPageSize(pageSize);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(outPath)));

        //创建输出流
        document.open();
        document.add(new Chunk(""));

        PdfPTable pdfPTable = new PdfPTable(6);
        pdfPTable.setWidthPercentage(98f);
        pdfPTable.setWidths(new float[]{60, 50, 25, 55, 45, 65});
        createTable(pdfPTable);
        document.add(pdfPTable);


        document.close();
        writer.close();
        return outPath;
    }

    /**
     * 创建单元格
     *
     * @param table
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private static PdfPTable createTable(PdfPTable table) throws DocumentException, IOException {
        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font TextNameFont = new Font(bfChinese, 8, Font.BOLD);
        Font TextValueFont = new Font(bfChinese, 10, Font.BOLD);

        // 第一行
        Image png = Image.getInstance(CreatePDF.class.getResource("/img/lixiang.png"));
        png.scaleToFit(45, 9);
        PdfPCell pdfCellImage = createPdfCellImage(png);
        table.addCell(pdfCellImage);

        PdfPCell cell1 = createPdfCellTitleContent(new Paragraph("出库单号" + "\n" + "Order No.", TextNameFont));
        table.addCell(cell1);

        String path2 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test2.png");
        BarcodeUtil.generateFile("fkasdjfkasdjf", path2);
        Image png2 = Image.getInstance(path2);
        png2.scaleToFit(130, 35);
        PdfPCell cell21 = createPdfCellImage(png2);
        cell21.setColspan(4);
        table.addCell(cell21);

        // 第二行
        PdfPCell cell2 = createPdfCellTitleContent(new Paragraph("波次单号" + "\n" + "Wave No.", TextNameFont));
        table.addCell(cell2);

        String path3 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test3.png");
        BarcodeUtil.generateFile("fkasdjfkasdjf", path3);
        Image png3 = Image.getInstance(path3);
        png3.scaleToFit(130, 35);
        PdfPCell cell3 = createPdfCellImage(png3);
        cell3.setColspan(3);
        table.addCell(cell3);

        PdfPCell cell4 = createPdfCellTitleContent(new Paragraph("零件数量", TextNameFont));
        table.addCell(cell4);

        PdfPCell cell5 = createPdfCellValueContent(new Paragraph("10", TextValueFont));
        table.addCell(cell5);

        // 第三行
        PdfPCell cell6 = createPdfCellTitleContent(new Paragraph("零件编号" + "\n" + "Part No.", TextNameFont));
        table.addCell(cell6);

        PdfPCell cell7 = createPdfCellValueContent(new Paragraph("M01-54353443IJ01", TextValueFont));
        cell7.setColspan(3);
        table.addCell(cell7);

        PdfPCell cell8 = createPdfCellTitleContent(new Paragraph("存储区" + "\n" + "Area", TextNameFont));
        table.addCell(cell8);

        PdfPCell cell9 = createPdfCellValueContent(new Paragraph("PDC001C", TextValueFont));
        table.addCell(cell9);

        // 第四行
        PdfPCell cell10 = createPdfCellTitleContent(new Paragraph("出库标签号" + "\n" + "Inbound Lable No.", TextNameFont));
        table.addCell(cell10);

        String path4 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test4.png");
        BarcodeUtil.generateFile("fkasdjfkasdjf", path4);
        Image png4 = Image.getInstance(path4);
        png4.scaleToFit(130, 35);
        PdfPCell cell13 = createPdfCellImage(png4);
        cell13.setColspan(3);
        table.addCell(cell13);

        PdfPCell cell11 = createPdfCellTitleContent(new Paragraph("危品", TextNameFont));
        table.addCell(cell11);

        PdfPCell cell12 = createPdfCellValueContent(new Paragraph("N", TextValueFont));
        table.addCell(cell12);

        // 第五行
        PdfPCell cell14 = createPdfCellTitleContent(new Paragraph("零件名称" + "\n" + "Name", TextNameFont));
        table.addCell(cell14);

        PdfPCell cell15 = createPdfCellValueContent(new Paragraph("仪表板下本体盖板总成咖", TextValueFont));
        cell15.setColspan(3);
        table.addCell(cell15);

        PdfPCell cell16 = createPdfCellTitleContent(new Paragraph("库位号" + "\n" + "Location", TextNameFont));
        table.addCell(cell16);

        PdfPCell cell17 = createPdfCellValueContent(new Paragraph("C022210301", TextValueFont));
        table.addCell(cell17);

        // 第五行
        PdfPCell cell18 = createPdfCellTitleContent(new Paragraph("订单类型" + "\n" + "Type", TextNameFont));
        table.addCell(cell18);

        PdfPCell cell19 = createPdfCellValueContent(new Paragraph("库存采购入库", TextValueFont));
        table.addCell(cell19);

        PdfPCell cell20 = createPdfCellTitleContent(new Paragraph("打印时间", TextNameFont));
        table.addCell(cell20);

        PdfPCell cell22 = createPdfCellValueContent(new Paragraph("2020-04-17 18:49:00", TextValueFont));
        table.addCell(cell22);

        PdfPCell cell23 = createPdfCellTitleContent(new Paragraph("客户代码" + "\n" + "ASN", TextNameFont));
        table.addCell(cell23);

        PdfPCell cell24 = createPdfCellValueContent(new Paragraph("CEELSH01", TextValueFont));
        table.addCell(cell24);

        return table;
    }

    /**
     * 创建一个图片单元格
     *
     * @param image
     * @return
     */
    public static PdfPCell createPdfCellImage(Image image) {
        PdfPCell cell = new PdfPCell(image);
        cell.setBorderWidth(1);
        cell.setMinimumHeight(40);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    /**
     * 创建一个标题单元格
     *
     * @param paragraph
     * @return
     */
    public static PdfPCell createPdfCellTitleContent(Paragraph paragraph) {
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setBorderWidth(1);
        cell.setMinimumHeight(40);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    /**
     * 创建一个文本值单元格
     *
     * @param paragraph
     * @return
     */
    public static PdfPCell createPdfCellValueContent(Paragraph paragraph) {
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setBorderWidth(1);
        cell.setMinimumHeight(40);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}
