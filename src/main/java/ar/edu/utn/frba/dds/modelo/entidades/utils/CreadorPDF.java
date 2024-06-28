package ar.edu.utn.frba.dds.modelo.entidades.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreadorPDF {
    public void crearPDF(String texto, String nombreArchivo) {
        try (Document document = new Document(new PdfDocument(new PdfWriter("./" + nombreArchivo + ".pdf")))) {
            document.add(new Paragraph(texto));
        } catch (Exception e) {

        }
    }
}
