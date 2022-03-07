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
import edu.JavaPIDEV.entities.Admin;
import edu.JavaPIDEV.entities.Client;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author MSI
 */
public class pdfBackup {
    public void PdfBackup() throws FileNotFoundException, DocumentException {

        AdminCRUD ec = new AdminCRUD();
        String message = "Voici la liste Backup des Admins \n\n";

        String file_name = "src/PDF/liste_Admin.pdf";
        Document document = new Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Admin> Admin = ec.affichageAdmin();
        PdfPTable table = new PdfPTable(6);

        
        
        PdfPCell cl0 = new PdfPCell(new Phrase("Id de l'Admin"));
        table.addCell(cl0);
        PdfPCell cl1 = new PdfPCell(new Phrase("Nom de l'Admin"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Prenom"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Email"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("Mot de passe"));
        table.addCell(cl4);
        PdfPCell cl5 = new PdfPCell(new Phrase("Numero tel"));
        table.addCell(cl5);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Admin.size(); i++) {
            
            table.addCell("" + Admin.get(i).getId());
            table.addCell("" + Admin.get(i).getNom());
            table.addCell("" + Admin.get(i).getPrenom());
            table.addCell("" + Admin.get(i).getEmail());
            table.addCell("" + Admin.get(i).getMdp());
            table.addCell("" + Admin.get(i).getNumtel());

        }
        
        document.add(table);
        document.close();

    }
    public void PdfBackupClient() throws FileNotFoundException, DocumentException {

        ClientCRUD ec = new ClientCRUD();
        String message = "Voici la liste Backup des Clients \n\n";

        String file_name = "src/PDF/liste_Clients.pdf";
        Document document = new Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Client> Client = ec.affichageClient();
        PdfPTable table = new PdfPTable(9);

        
        
        PdfPCell cl0 = new PdfPCell(new Phrase("Cin du Client"));
        table.addCell(cl0);
        PdfPCell cl1 = new PdfPCell(new Phrase("Nom et Prenom"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Surnom"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Sexe"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("Email"));
        table.addCell(cl4);
        PdfPCell cl5 = new PdfPCell(new Phrase("Mot de passe"));
        table.addCell(cl5);
        PdfPCell cl6 = new PdfPCell(new Phrase("Date de naissance"));
        table.addCell(cl6);
        PdfPCell cl7 = new PdfPCell(new Phrase("Adresse"));
        table.addCell(cl7);
        PdfPCell cl8 = new PdfPCell(new Phrase("Image"));
        table.addCell(cl8);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Client.size(); i++) {
            
            table.addCell("" + Client.get(i).getCin());
            table.addCell("" + Client.get(i).getNomPrenom());
            table.addCell("" + Client.get(i).getSurnom());
            table.addCell("" + Client.get(i).getSexe());
            table.addCell("" + Client.get(i).getEmail());
            table.addCell("" + Client.get(i).getMdp());
            table.addCell("" + Client.get(i).getDateNaissance());
            table.addCell("" + Client.get(i).getAdresse());
            table.addCell("" + Client.get(i).getImage());

        }
        
        document.add(table);
        document.close();

    }
    
}
