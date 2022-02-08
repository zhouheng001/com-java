package com.zhouheng.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 装箱单 打签
 *
 * @author huxingbin
 */

public class PackageOrderLabelPrintPDF {

    public static void main(String[] args) throws IOException, DocumentException {
        writeExampaperPdf();
    }


    public static String writeExampaperPdf() throws IOException, DocumentException {
        BaseFont bfChinese = BaseFont.createFont("simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        //设置字体样式
        com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(bfChinese, 25, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font textFont = new com.itextpdf.text.Font(bfChinese, 8, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(bfChinese, 8, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font tenFont = new com.itextpdf.text.Font(bfChinese, 10, com.itextpdf.text.Font.BOLD);


        //输出路径
        String outPath = "/Users/zhouheng/chj/data/pdf/装箱单打签.pdf";
        Document document = new Document(PageSize.A4, 0, 0, 10, 0);
        //设置横向
        com.itextpdf.text.Rectangle pageSize = new Rectangle(420, 280);
        document.setPageSize(pageSize);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(outPath)));

        //创建输出流
        document.open();
        document.add(new Chunk(""));

        PdfPTable pdfPTable = new PdfPTable(6);
        pdfPTable.setWidthPercentage(98f);
        pdfPTable.setWidths(new float[]{50, 83, 50, 83, 50, 84});
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
        png.scaleToFit(105, 40);
        png.setAbsolutePosition(30, 520);
        PdfPCell pdfCellImage = createPdfCellImage(png);
        pdfCellImage.setColspan(2);
        table.addCell(pdfCellImage);

        PdfPCell cell2 = createPdfCellTitleContent(new Paragraph("发货方代码" + "\n" + "Shipper Code", TextNameFont));
        table.addCell(cell2);

        PdfPCell cell3 = createPdfCellValueContent(new Paragraph("PDC001", TextValueFont));
        table.addCell(cell3);

        PdfPCell cell4 = createPdfCellTitleContent(new Paragraph("收货方代码" + "\n" + "Consignee Code", TextNameFont));
        table.addCell(cell4);

        PdfPCell cell5 = createPdfCellValueContent(new Paragraph("CASZV02", TextValueFont));
        table.addCell(cell5);

        // 第二行
        PdfPCell cell6 = createPdfCellTitleContent(new Paragraph("订单号" + "\n" + "Order No.", TextNameFont));
        table.addCell(cell6);

        PdfPCell cell7 = createPdfCellValueContent(new Paragraph("SS200839123B9", TextValueFont));
        table.addCell(cell7);

        PdfPCell cell8 = createPdfCellTitleContent(new Paragraph("箱号" + "\n" + "Carton No.", TextNameFont));
        table.addCell(cell8);

        PdfPCell cell9 = createPdfCellValueContent(new Paragraph("C00136739", TextValueFont));
        table.addCell(cell9);

        PdfPCell cell10 = createPdfCellTitleContent(new Paragraph("打印时间" + "\n" + "Issue Date", TextNameFont));
        table.addCell(cell10);

        PdfPCell cell11 = createPdfCellValueContent(new Paragraph("2022-01-08 07:49:53", TextValueFont));
        table.addCell(cell11);

        // 第三行
        PdfPCell cell12 = createPdfCellTitleContent(new Paragraph("订单号条形码" + "\n" + "Order No.", TextNameFont));
        table.addCell(cell12);

        String path1 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test1.png");
        BarcodeUtil.generateFile("dfjasdkfjasf", path1);
        Image png1 = Image.getInstance(path1);
        png1.scaleToFit(180, 43);
        PdfPCell cell13 = createPdfCellImage(png1);
        cell13.setColspan(5);
        table.addCell(cell13);

        // 第四行
        PdfPCell cell14 = createPdfCellTitleContent(new Paragraph("纸箱尺寸（mm）" + "\n" + "Package Size", TextNameFont));
        table.addCell(cell14);

        PdfPCell cell15 = createPdfCellValueContent(new Paragraph("1300x850x1350", TextValueFont));
        cell15.setColspan(3);
        table.addCell(cell15);

        PdfPCell cell16 = createPdfCellTitleContent(new Paragraph("重量（kg）" + "\n" + "Gross Weight", TextNameFont));
        table.addCell(cell16);

        PdfPCell cell17 = createPdfCellValueContent(new Paragraph("99", TextValueFont));
        table.addCell(cell17);

        // 第五行
        PdfPCell cell18 = createPdfCellTitleContent(new Paragraph("目的地" + "\n" + "Destination", TextNameFont));
        table.addCell(cell18);

        PdfPCell cell19 = createPdfCellValueContent(new Paragraph("苏州市工业园区通园路100号" + "\n" + "联系人：温浩" + "\n" + "电话：15101516445", TextValueFont));
        cell19.setColspan(5);
        table.addCell(cell19);

        // 第六行
        PdfPCell cell20 = createPdfCellTitleContent(new Paragraph("箱号条形码" + "\n" + "Carton No.", TextNameFont));
        table.addCell(cell20);

        String path2 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test2.png");
        BarcodeUtil.generateFile("fkasdjfkasdjf", path2);
        Image png2 = Image.getInstance(path2);
        png2.scaleToFit(180, 43);
        PdfPCell cell21 = createPdfCellImage(png2);
        cell21.setColspan(5);
        table.addCell(cell21);

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
        cell.setMinimumHeight(45);
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
