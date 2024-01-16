/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf_creator;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author javib
 */
public class PdfMaker {

    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    private static final String iTextExampleImage = "/home/xules/codigoxules/iText-Example-image.png";

    /**
     * We create a PDF document with iText using different elements to learn to
     * use this library. Creamos un documento PDF con iText usando diferentes
     * elementos para aprender a usar esta librería.
     *
     * @param pdfNewFile  <code>String</code> pdf File we are going to write.
     * Fichero pdf en el que vamos a escribir.
     */
    public void createPDF(File pdfNewFile ,String nino, String rey) {
        // Aquí introduciremos el código para crear el PDF.
        try {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No such file was found to generate the PDF "
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }
            document.open();

            // AQUÍ COMPLETAREMOS NUESTRO CÓDIGO PARA GENERAR EL PDF
            document.addTitle(
                    "Table export to PDF (Exportamos la tabla a PDF)");
            document.addSubject(
                    "Using iText (usando iText)");
            document.addKeywords(
                    "Java, PDF, iText");
            document.addAuthor(
                    "Código Xules");
            document.addCreator(
                    "Código Xules");
            // First page
            // Primera página 
            Chunk chunk = new Chunk("Querido "+nino+":", chapterFont);
            chunk.setBackground(BaseColor.LIGHT_GRAY);
            // Let's create de first Chapter (Creemos el primer capítulo)
            Chapter chapter = new Chapter(new Paragraph(chunk), 1);
            chapter.setNumberDepth(0);
            chapter.add(new Paragraph("", paragraphFont));
            chapter.add(new Paragraph("🌴 Aqui desde el lejano Oriente 🌠", paragraphFont));
            chapter.add(new Paragraph("Hemos recibido tu pedido de regalos", paragraphFont));
            chapter.add(new Paragraph("Y yo el rey "+rey+",en mi nombre y en nombre de mis compañeros. Hemos visto que has sido bueno y no recibirás carbón.", paragraphFont));
            chapter.add(new Paragraph("Ya hemos cargado tus regalos 🎁 "+nino+" en nuestros camellos 🐪🐪🐪, y pronto recibirás una gran sorpresa.", paragraphFont));
            chapter.add(new Paragraph("Te manda un gran abrazo tu rey favorito "+rey, paragraphFont));
            document.add(chapter);

            document.close();
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
    }
}

