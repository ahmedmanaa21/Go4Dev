/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.JavaPIDEV.entities.Evenements;
import edu.JavaPIDEV.entities.ZoneCamping;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author melki
 */
public class PDFzoneCamping {
            public void liste_EvenementPDF() throws FileNotFoundException, DocumentException {

        ZoneCampingCRUD zc = new ZoneCampingCRUD();
        String message = "Voici la liste des zones de camping \n\n";

        String file_name = "src/PDF/liste_ZoneCamping.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<ZoneCamping> ZoneCamping = zc.affichageZoneCamping();
        PdfPTable table = new PdfPTable(5);

        PdfPCell cl1 = new PdfPCell(new Phrase("ID"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Région"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Délégation"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("Nom de la zone de camping"));
        table.addCell(cl4);
        PdfPCell cl5 = new PdfPCell(new Phrase("Description"));
        table.addCell(cl5);
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < ZoneCamping.size(); i++) {
            
            table.addCell("" + ZoneCamping.get(i).getId());
            table.addCell("" + ZoneCamping.get(i).getRegion());
            table.addCell("" + ZoneCamping.get(i).getDelegation());
            table.addCell("" + ZoneCamping.get(i).getNom_centre());
            table.addCell("" + ZoneCamping.get(i).getDescription());

        }
        document.add(table);

        document.close();

    }
}
