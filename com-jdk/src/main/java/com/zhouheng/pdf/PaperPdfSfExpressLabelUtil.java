package com.zhouheng.pdf;

import com.google.zxing.WriterException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import sun.net.www.content.image.png;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 描述:
 *
 * @author zhouheng
 * @create 2022-01-26 10:54 上午
 */
public class PaperPdfSfExpressLabelUtil {


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
        String outPath = "/Users/zhouheng/chj/data/pdf/顺丰快递单标签.pdf";
        Document document = new Document(PageSize.A4, 0, 0, 5, 0);
        //设置横向
        com.itextpdf.text.Rectangle pageSize = new Rectangle(280, 400);
        document.setPageSize(pageSize);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(outPath)));

        //创建输出流
        document.open();


        PdfPTable pdfPTable = new PdfPTable(9);
        pdfPTable.setWidthPercentage(98f);
        pdfPTable.setWidths(new float[]{50, 20,10, 20, 40,20,10,30,80});
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

        // 第一行
        PdfPCell cell1 = createPdfCellTitleContent(new Paragraph("ZJ", font3));
        cell1.setColspan(4);
        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell1);

        PdfPCell cell2 = createPdfCellTitleContent(new Paragraph("打印时间", font3));
        table.addCell(cell2);

        PdfPCell cell3 = createPdfCellTitleContent(new Paragraph("2022-01-20 18:38:15", font3));
        cell3.setColspan(4);
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell3);

        // 第二行
        PdfPCell cell4 = createPdfCellTitleContent(new Paragraph("1/1"+"\n\n"+"母单号", font1));
        cell4.setMinimumHeight(70);
        table.addCell(cell4);

        String path1 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test1.png");
        BarcodeUtil.generateFile("SF1347662712311", path1);
        Image png1 = Image.getInstance(path1);
        png1.scaleToFit(110, 70);
        PdfPCell cell5 = createPdfCellImage(png1);
        cell5.setMinimumHeight(70);
        cell5.setColspan(6);
        table.addCell(cell5);

        PdfPCell cell6 = createPdfCellTitleContent(new Paragraph("T29", font7));
        cell6.setColspan(2);
        cell6.setMinimumHeight(70);
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell6);

        // 第三行
        PdfPCell cell7 = createPdfCellTitleContent(new Paragraph("311", font5));
        cell7.setMinimumHeight(50);
        cell7.setColspan(9);
        cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell7);

        // 第四行
        Image png2 = Image.getInstance(PaperPdfSfExpressLabelUtil.class.getResource("/img/shou.png"));
        png2.scaleToFit(40, 30);
        PdfPCell cell8 = createPdfCellImage(png2);
        cell8.setMinimumHeight(30);
        table.addCell(cell8);

        PdfPCell cell9 = createPdfCellTitleContent(new Paragraph("马志飞 139****8202"+"\n"+"河北石家庄市长安区瀚唐", font2));
        cell9.setMinimumHeight(30);
        cell9.setColspan(8);
        cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell9);

        // 第五行
        String path4 = "/Users/zhouheng/chj/data/pdf/" + String.format("EG%s", "test4.png");
        BarcodeUtil.generateQRCodeImage("M01-34010007", 500, 500, path4);
        Image png3 = Image.getInstance(path4);
        png3.scaleToFit(70, 70);
        PdfPCell cell10= createPdfCellImage(png3);
        cell10.setMinimumHeight(80);
        cell10.setColspan(6);
        cell10.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell10);

        PdfPCell cell11 = createPdfCellTitleContent(new Paragraph("  已"+"\n"+"  验"+"\n"+"  视", font6));
        cell11.setMinimumHeight(80);
        cell11.setColspan(3);
        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell11);

        // 第六行
        Image png4 = Image.getInstance(PaperPdfSfExpressLabelUtil.class.getResource("/img/ji.png"));
        png4.scaleToFit(40, 30);
        PdfPCell cell12 = createPdfCellImage(png4);
        cell12.setMinimumHeight(60);
        table.addCell(cell12);

        PdfPCell cell13 = createPdfCellTitleContent(new Paragraph("理想汽车    400-686-0900"+"\n"+"北京市顺义区保汇一街3号院2好楼（航港物流园2期）", font2));
        cell13.setMinimumHeight(30);
        cell13.setColspan(8);
        cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell13);

        // 第七行
        PdfPCell cell14 = createPdfCellTitleContent(new Paragraph("业务单号:", font2));
        cell14.setColspan(3);
        cell14.setMinimumHeight(30);
        table.addCell(cell14);

        PdfPCell cell15 = createPdfCellTitleContent(new Paragraph("BA220120B2499Q", font2));
        cell15.setColspan(6);
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
