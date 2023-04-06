package com.mvn.app.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteToFilePDF {
    public static final String SRC = "D:/Work/MultiThread/DemoMultiThread/thanhcong.pdf";
    public static final String DEST = "D:/Work/MultiThread/DemoMultiThread/thanhcongReal.pdf";

    public static void main(String[] args) throws Exception {
//        final Redactor redactor = new Redactor("D:/Work/MultiThread/DemoMultiThread/thanhcong.pdf");
//        redactor.apply(new ExactPhraseRedaction("@name@", new ReplacementOptions("HaiHE153344")));
//        redactor.apply(new ExactPhraseRedaction("@age@", new ReplacementOptions("23")));
//// Save the redacted file at different location with diferent name.
//        FileOutputStream stream = new FileOutputStream("D:/Work/MultiThread/DemoMultiThread/thanhcongReal.pdf");
//        RasterizationOptions rasterOptions = new RasterizationOptions();
//        rasterOptions.setEnabled(false);
//        redactor.save(stream, rasterOptions);

//        update();
        manipulatePdf(SRC, DEST);

    }

    private static void update() throws InvalidPasswordException, IOException {
        Map<String, String> map = new HashMap<>();
        map.put("@name@", "value to update");
        PDDocument document = PDDocument.load(new File("D:/Work/MultiThread/DemoMultiThread/thanhcong.pdf"));
        String text = new PDFTextStripper().getText(document);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (text.contains(entry.getKey())) {
                text=text.replace(entry.getKey(), "Hai");
            }
        }
        System.out.println(text);
        File out = new File("D:/Work/MultiThread/DemoMultiThread/thanhcongReal.pdf");
        document.save(out);
        document.close();
    }

    public static void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary dict = reader.getPageN(1);
        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
        PdfArray refs = null;
        if (dict.get(PdfName.CONTENTS).isArray()) {
            refs = dict.getAsArray(PdfName.CONTENTS);
        } else if (dict.get(PdfName.CONTENTS).isIndirect()) {
            refs = new PdfArray(dict.get(PdfName.CONTENTS));
        }
        for (int i = 0; i < refs.getArrayList().size(); i++) {
            PRStream stream = (PRStream) refs.getDirectObject(i);
            byte[] data = PdfReader.getStreamBytes(stream);
            stream.setData(new String(data).replace("name", "Nulo").getBytes());
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }
}
