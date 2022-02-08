package com.zhouheng.pdf;

import com.google.zxing.WriterException;
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
 *
 * @author zhouheng
 * @create 2022-01-27 11:19 上午
 */
public class PaperPdfRackLabelUtil {

    public static void main(String[] args) throws IOException, DocumentException, WriterException {
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
            DocumentException, WriterException {

        //输出路径
        String outPath = "/Users/zhouheng/chj/data/pdf/货架单标签.pdf";
        Document document = new Document(PageSize.A4, 0, 0, 5, 0);
        //设置横向
        com.itextpdf.text.Rectangle pageSize = new Rectangle(230, 115);
        document.setPageSize(pageSize);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(outPath)));

        //创建输出流
        document.open();


        PdfPTable pdfPTable = new PdfPTable(2);
        pdfPTable.setWidthPercentage(98f);
        pdfPTable.setWidths(new float[]{115, 115});
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
    private static PdfPTable createTable(PdfPTable table) throws DocumentException, IOException, WriterException {
        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bfChinese, 23, Font.BOLD);
        Font font1 = new Font(bfChinese, 10, Font.BOLD);
        Font font2 = new Font(bfChinese, 9, Font.BOLD);
        Font font3 = new Font(bfChinese, 6, Font.BOLD);
        Font font4 = new Font(bfChinese, 30, Font.BOLD);
        Font font5 = new Font(bfChinese, 40, Font.BOLD);
        Font font6 = new Font(bfChinese, 20, Font.BOLD);
        Font font7 = new Font(bfChinese, 50, Font.BOLD);

        // 第五行
        String path4 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test4.png");
        BarcodeUtil.generateQRCodeImage("M01-34010007", 200, 200, path4);
        Image png3 = Image.getInstance(path4);
        png3.scaleToFit(100, 100);
        PdfPCell cell10= createPdfCellImage(png3);
        cell10.setMinimumHeight(100);
        cell10.setRowspan(2);
        cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell10);

        PdfPCell cell14 = createPdfCellTitleContent(new Paragraph("业务单号:", font2));
        cell14.setMinimumHeight(80);
        cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell14);

        PdfPCell cell15 = createPdfCellTitleContent(new Paragraph("业务单号:", font2));
        cell15.setMinimumHeight(30);
        cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell15);

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
        cell.setMinimumHeight(30);
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
