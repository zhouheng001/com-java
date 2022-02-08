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
 * @create 2022-01-25 4:54 下午
 */
public class PaperPdfMaterialLabelUtil {

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
        String outPath = "/Users/zhouheng/chj/data/pdf/零件打签.pdf";
        Document document = new Document(PageSize.A4, 0, 0, 5, 0);
        //设置横向
        com.itextpdf.text.Rectangle pageSize = new Rectangle(230, 115);
        document.setPageSize(pageSize);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(outPath)));

        //创建输出流
        document.open();


        PdfPTable pdfPTable = new PdfPTable(4);
        pdfPTable.setWidthPercentage(98f);
        pdfPTable.setWidths(new float[]{50, 10, 100, 70});
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
        Font font1 = new Font(bfChinese, 10, Font.BOLD);
        Font font2 = new Font(bfChinese, 6, Font.BOLD);

        // 第一行
        Image png = Image.getInstance(CreatePDF.class.getResource("/img/lixiang.png"));
        png.scaleToFit(45, 9);
        PdfPCell pdfCellImage = createPdfCellImage(png);
        pdfCellImage.setColspan(2);
        table.addCell(pdfCellImage);

        PdfPCell cell1 = createPdfCellTitleContent(new Paragraph("M01-34010007", font1));
        table.addCell(cell1);

        PdfPCell cell2 = createPdfCellTitleContent(new Paragraph("2", font1));
        table.addCell(cell2);

        // 第二行
        PdfPCell cell3 = createPdfCellTitleContent(new Paragraph("零件名称", font1));
        cell3.setColspan(2);
        cell3.setMinimumHeight(35);
        table.addCell(cell3);

        PdfPCell cell4 = createPdfCellValueContent(new Paragraph("转向拉杆防尘罩卡环大幅发酒疯打瞌睡地方", font1));
        table.addCell(cell4);

        PdfPCell cell5 = createPdfCellTitleContent(new Paragraph("合格", font1));
        table.addCell(cell5);

        // 第三行
        PdfPCell cell6 = createPdfCellTitleContent(new Paragraph("追溯码:"+"\n"+
                "有效日期:"+"\n"
                +"生产厂家:"+"\n"
                +"生产地址:"+"\n"
                +"销售商:"+"\n"
                +"销售地址:"+"\n", font2));
        cell6.setColspan(3);
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell6.setVerticalAlignment(Element.ALIGN_TOP);
        cell6.setMinimumHeight(50);
        table.addCell(cell6);


        String path4 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test4.png");
        BarcodeUtil.generateQRCodeImage("M01-34010007", 500, 500, path4);
        Image png4 = Image.getInstance(path4);
        png4.scaleToFit(50, 50);

        PdfPCell cell7 = createPdfCellImage(png4);
        cell7.setMinimumHeight(50);
        table.addCell(cell7);

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
        cell.setBorderWidth(0);
        cell.setMinimumHeight(20);
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
        cell.setBorderWidth(0);
        cell.setMinimumHeight(20);
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
        cell.setBorderWidth(0);
        cell.setMinimumHeight(20);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}
