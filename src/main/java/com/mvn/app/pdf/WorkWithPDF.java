package com.mvn.app.pdf;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
//import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.pdfcleanup.PdfCleaner;
import com.itextpdf.pdfcleanup.autosweep.CompositeCleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.Canvas;
import com.spire.doc.Document;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WorkWithPDF {

    public static void main(String[] args) throws IOException {
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
//        try{
//            PdfReader reader = new PdfReader("D:/Work/MultiThread/DemoMultiThread/VN.Thu-thong-bao-moi-hop.pdf");
//            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
//
//            System.out.println("Content file: "+textFromPage);
//
//            reader.close();
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }

        //read file Doc
//        try {
//            FileInputStream fis = new FileInputStream("D:/Work/MultiThread/DemoMultiThread/demo-apache-apoi-word.docx");
//            XWPFDocument document = new XWPFDocument(OPCPackage.open("D:/Work/MultiThread/DemoMultiThread/forSend.docx"));
////            List<XWPFParagraph> paragraphList = document.getParagraphs();
////            for (XWPFParagraph paragraph : paragraphList) {
////                for (XWPFRun xwpfRun : paragraph.getRuns()) {
////                    String docText = xwpfRun.getText(0);
////                    docText = docText.replace("${name}", "Hai");
////                    xwpfRun.setText(docText, 0);
////                }
////            }
//            System.out.println("==============================");
//            System.out.println("Read file using XWPFWordExtractor ");
//            XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
//            FileOutputStream fos = new FileOutputStream("D:/Work/MultiThread/DemoMultiThread/ketQuaDoc2.docx");
//            fos.write(wordExtractor.getText().getBytes("UTF-8"));
//            // find number of words in the document
////            long count = Arrays.stream(wordExtractor.getText().split("\\s+")).count();
//            document.close();
//            wordExtractor.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        //parse File
//        Document doc = new Document("D:/Work/MultiThread/DemoMultiThread/VN.Thu-thong-bao-moi-hop.docx");
//        doc.saveToFile("D:/Work/MultiThread/DemoMultiThread/thanhcong.pdf");
//        System.out.println("Successfull");


        //update document
        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get("D:\\Work\\MultiThread\\DemoMultiThread\\forSend.docx")))
        ) {

            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
            //Iterate over paragraph list and check for the replaceable text in each paragraph
            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {

                xwpfParagraph.getText().replace("\n", "").replace("\r", "");
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);
                    System.out.println("1 - "+docText);

                    //replacement and setting position
                    if (docText != null) {
                        docText = docText.replace("{name}", "Hai");
                        docText = docText.replace("{email}", "emailtestthoima@gmail.com");
                        docText = docText.replace("{time}", "TimeNe");
                        docText = docText.replace("{codong}", "Co Dong");
//                        docText = docText.replace("{name}", "Co Dong");
                        xwpfRun.setText(docText, 0);
                    }
                }
            }

//            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
//                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
//                    String docText = xwpfRun.getText(0);
//                    System.out.println(docText);
//                }
//            }

            // save the docs
            try (FileOutputStream out = new FileOutputStream("D:\\Work\\MultiThread\\DemoMultiThread\\ketQuaDoc.docx")) {
                doc.write(out);
            }
//
            Document doc2 = new Document("D:/Work/MultiThread/DemoMultiThread/ketQuaDoc.docx");
            doc2.saveToFile("D:/Work/MultiThread/DemoMultiThread/thanhcong.pdf");
            System.out.println("Successfull");
//
//        }
        }


    }

        private static void replaceTextContentFromDocument (PdfDocument pdfDocument) throws IOException {
            CompositeCleanupStrategy strategy = new CompositeCleanupStrategy();
            strategy.add(new RegexBasedCleanupStrategy("@name@").setRedactionColor(ColorConstants.WHITE));
            strategy.add(new RegexBasedCleanupStrategy("@age@").setRedactionColor(ColorConstants.WHITE));
            PdfCleaner.autoSweepCleanUp(pdfDocument, strategy);

            for (IPdfTextLocation location : strategy.getResultantLocations()) {
                PdfPage page = pdfDocument.getPage(location.getPageNumber() + 1);
                PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), page.getDocument());
                System.out.println(strategy.getResultantLocations().toString() + " ne");
                Canvas canvas = new Canvas(pdfCanvas, location.getRectangle());
                canvas.add(new Paragraph("oke").setFontSize(11.5f)
                        .setMarginTop(0f));
            }
        }

    private static String getTemplateMailShareholder() throws IOException {
        try (InputStream is = new FileInputStream("D:/Work/MultiThread/DemoMultiThread/demo-apache-apoi-word.docx");

             ByteArrayOutputStream result = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8").replace("\n", "").replace("\r", "");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }


    }
