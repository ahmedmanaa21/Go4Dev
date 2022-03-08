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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author melki
 */
public class PDFevenement {
         public void liste_EvenementPDF() throws FileNotFoundException, DocumentException {

        EvenementsCRUD ec = new EvenementsCRUD();
        String message = "Voici la liste des événements \n\n";

        String file_name = "src/PDF/liste_evenement.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Evenements> Evenement = ec.affichageEvenements();
        PdfPTable table = new PdfPTable(5);

        
        
        PdfPCell cl1 = new PdfPCell(new Phrase("Nom de l'événement"));
        table.addCell(cl1);
        PdfPCell cl = new PdfPCell(new Phrase("Description"));
        table.addCell(cl);
        PdfPCell cl2 = new PdfPCell(new Phrase("Date début"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Date fin"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("Affiche de l'événement"));
        table.addCell(cl4);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Evenement.size(); i++) {
            
            table.addCell("" + Evenement.get(i).getNom());
            table.addCell("" + Evenement.get(i).getDescription());
            table.addCell("" + Evenement.get(i).getDate_deb());
            table.addCell("" + Evenement.get(i).getDate_fin());
            table.addCell("" + Evenement.get(i).getImage());

        }
        document.add(table);

        document.close();

    }
}
