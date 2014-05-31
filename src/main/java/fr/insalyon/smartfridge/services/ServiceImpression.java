package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Arrays;
import java.util.List;

/** Effectue des impressions */
public class ServiceImpression {
    /** Imprime une liste d'aliments
     *
     * @param aliments La liste de courses
     */
    public static void imprimer(final List<Aliment> aliments) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }
                Graphics2D g2d = (Graphics2D)graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                int x = 0;
                int y = 20;
                for(Aliment a : aliments) {

                    g2d.drawString(a.toString(), x, y);
                    y += 20;
                }
                return PAGE_EXISTS;
            }
        });
        boolean doPrint = job.printDialog();
        if(doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}