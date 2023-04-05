package com.mvn.app.pdf;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.spire.doc.Document;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

public class WorkWithPDF {

    public static void main(String[] args) {
        //create file PDF
//        Document document = new Document();
//        try {
//            // khởi tạo một PdfWriter truyền vào document và FileOutputStream
//            PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
//
//            // mở file để thực hiện viết
//            document.open();
//            // thêm nội dung sử dụng add function
//            document.add(new Paragraph("A Hello World PDF document."));
//            // đóng file
//            document.close();
//
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        //read file PDF
        try{
            PdfReader reader = new PdfReader("D:/Code/Java/WorkWithFile/DemoMultiThread/VN.Thu-thong-bao-moi-hop.pdf");
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

            System.out.println("Content file: "+textFromPage);

            reader.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        //read file Doc
        try {
            FileInputStream fis = new FileInputStream("D:/Code/Java/WorkWithFile/DemoMultiThread/demo-apache-apoi-word.docx");
            XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
            List<XWPFParagraph> paragraphList = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphList) {
                System.out.println(paragraph.getText());
            }

            System.out.println("==============================");
            System.out.println("Read file using XWPFWordExtractor ");
            XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
            // find number of words in the document
            long count = Arrays.stream(wordExtractor.getText().split("\\s+")).count();
            System.out.println("Total words: " + count);
            System.out.println(wordExtractor.getText());
            wordExtractor.close();
            document.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Document doc = new Document("D:/Code/Java/WorkWithFile/DemoMultiThread/VN.Thu-thong-bao-moi-hop.docx");
        doc.saveToFile("D:/Code/Java/WorkWithFile/DemoMultiThread/thanhcong.pdf");
        System.out.println("Successfull");
    }
}
