package com.mvn.app.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WorkWithPDF {
    public static void main(String[] args) throws FileNotFoundException {
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


//        File file=new File("D:/Work/MultiThread/RemoteLinux/VN.Thu-thong-bao-moi-hop.pdf");
//        FileInputStream fs = new FileInputStream(file);
//        //read file PDF
//        try{
//            PdfReader reader = new PdfReader("D:/Work/MultiThread/RemoteLinux/VN.Thu-thong-bao-moi-hop.pdf");
//            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
//
//            System.out.println("Content file: "+textFromPage);
//
//            reader.close();
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }

        //read file Doc

//        String fileName = "D:/Work/MultiThread/RemoteLinux/demo-apache-apoi-word.docx";
//
//        try (XWPFDocument doc = new XWPFDocument(
//                Files.newInputStream(Paths.get(fileName)))) {
//
//            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
//            String docText = xwpfWordExtractor.getText();
//            System.out.println(docText);
//            // find number of words in the document
//            long count = Arrays.stream(docText.split("\\s+")).count();
//            System.out.println("Total words: " + count);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            FileInputStream fis = new FileInputStream("D:/Work/MultiThread/RemoteLinux/demo-apache-apoi-word.docx");
            XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
            List<XWPFParagraph> paragraphList = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphList) {
                System.out.println(paragraph.getText());
            }
            System.out.println("==============================");
            System.out.println("Read file using XWPFWordExtractor ");
            XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
            System.out.println(wordExtractor.getText());
            wordExtractor.close();
            document.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
