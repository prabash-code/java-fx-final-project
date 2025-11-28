package edu.icet.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.icet.model.dto.Medicine;
import edu.icet.model.dto.Notification;
import edu.icet.model.dto.SalesHistory;
import edu.icet.model.dto.Supplier;
import edu.icet.repository.ReportsRepository;
import edu.icet.repository.impl.ReportsRepositoryImpl;
import edu.icet.service.ReportsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReportsServiceImpl implements ReportsService {

    ReportsRepository reportsRepository = new ReportsRepositoryImpl();
    ObservableList<Medicine> list = FXCollections.observableArrayList();
    ObservableList<SalesHistory> listHistory = FXCollections.observableArrayList();
    ObservableList<Supplier> listSuppliers = FXCollections.observableArrayList();
    ObservableList<Notification> listNotification = FXCollections.observableArrayList();


    @Override
    public void getAll() {
        list.clear();
        ResultSet all = reportsRepository.getAll();

        try {
            while (all.next()) {
                list.add(new Medicine(
                        all.getString("medicineId"),
                        all.getString("brand"),
                        all.getString("name"),
                        all.getString("supplierId"),
                        all.getDouble("unitPrice"),
                        all.getInt("quantity"),
                        all.getDate("manufactureDate").toLocalDate(),
                        all.getDate("expireDate").toLocalDate()
                ));

            }
            generatePdf(list);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAllSales() {
        listHistory.clear();
        ResultSet allDetails = reportsRepository.getAllSales();

        try {
            while (allDetails.next()) {
                listHistory.add(new SalesHistory(
                        allDetails.getString("saleId"),
                        allDetails.getString("customerName"),
                        allDetails.getDate("saleDate").toLocalDate(),
                        allDetails.getString("customerEmail")
                ));

            }
            generateHistoryPdf(listHistory);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAllSuppiers() {
        listSuppliers.clear();
        ResultSet allDetails = reportsRepository.getAllSuppliers();

        try {
            while (allDetails.next()) {
                listSuppliers.add(new Supplier(
                        allDetails.getString("supplier_id"),
                        allDetails.getString("name"),
                        allDetails.getString("company"),
                        allDetails.getString("email"),
                        allDetails.getString("contact_number")
                ));

            }
            generateSupplier(listSuppliers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void getAllNotification() {


    }

    private void generateSupplier(ObservableList<Supplier> listSuppliers) {
        try {
            String path = "C:/reports";
            File folder = new File(path);

            if (!folder.exists()) {
                folder.mkdirs();
            }
            ;
            String output = path + "/suppliers_report.pdf";

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(output));
            doc.open();


            doc.add(new Paragraph("Suppliers  Report!!"));
            doc.add(new Paragraph("Generated  on :" + LocalDate.now()));
            doc.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.addCell(new PdfPCell(new Phrase("ID")));
            table.addCell(new PdfPCell(new Phrase("NAME")));
            table.addCell(new PdfPCell(new Phrase("COMPANY")));
            table.addCell(new PdfPCell(new Phrase("EMAIL")));
            table.addCell(new PdfPCell(new Phrase("CONTACT NUMBER")));


            for (Supplier supplier: listSuppliers) {
                table.addCell(new PdfPCell(new Phrase(supplier.getSupplierId())));
                table.addCell(new PdfPCell(new Phrase(supplier.getName())));
                table.addCell(new PdfPCell(new Phrase(supplier.getCompany())));
                table.addCell(new PdfPCell(new Phrase(supplier.getEmail())));
                table.addCell(new PdfPCell(new Phrase(supplier.getPhone())));
            }

            doc.add(table);
            doc.add(new Paragraph("Supplier Report Generated !!!"));
            doc.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Operation Successful");
            alert.setContentText("Supplier report has generated successfully !!");
            alert.showAndWait();


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }



    private void generateHistoryPdf(ObservableList<SalesHistory> listHistory) {
        try {
            String path = "C:/reports";
            File folder = new File(path);

            if (!folder.exists()) {
                folder.mkdirs();
            }
            ;
            String output = path + "/sales_report.pdf";

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(output));
            doc.open();


            doc.add(new Paragraph("Sales History Report!!"));
            doc.add(new Paragraph("Generated  on :" + LocalDate.now()));
            doc.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell(new PdfPCell(new Phrase("ID")));
            table.addCell(new PdfPCell(new Phrase("DATE")));
            table.addCell(new PdfPCell(new Phrase("NAME")));
            table.addCell(new PdfPCell(new Phrase("EMAIL")));


            for (SalesHistory sales : listHistory) {
                table.addCell(new PdfPCell(new Phrase(sales.getSaleId())));
                table.addCell(new PdfPCell(new Phrase(sales.getDate().toString())));
                table.addCell(new PdfPCell(new Phrase(sales.getName())));
                table.addCell(new PdfPCell(new Phrase(sales.getEmail())));
            }

            doc.add(table);
            doc.add(new Paragraph("Sales Report Generated !!!"));
            doc.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Operation Successful");
            alert.setContentText("Sales report has generated successfully !!");
            alert.showAndWait();


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }


    public void generatePdf(List<Medicine> list) {
        try {
            String path = "C:/reports";
            File folder = new File(path);

            if (!folder.exists()) {
                folder.mkdirs();
            }
            ;
            String output = path + "/medicine_report.pdf";

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(output));
            doc.open();


            doc.add(new Paragraph("Medicine Report!!"));
            doc.add(new Paragraph("Generated  on :" + LocalDate.now()));
            doc.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.addCell(new PdfPCell(new Phrase("ID")));
            table.addCell(new PdfPCell(new Phrase("BRAND")));
            table.addCell(new PdfPCell(new Phrase("NAME")));
            table.addCell(new PdfPCell(new Phrase("SUPPLIER ID")));
            table.addCell(new PdfPCell(new Phrase("UNIT PRICE")));
            table.addCell(new PdfPCell(new Phrase("QUANTITY")));
            table.addCell(new PdfPCell(new Phrase("MANUFACTURE DATE")));
            table.addCell(new PdfPCell(new Phrase("EXPIRE DATE")));


            for (Medicine medicine : list) {
                table.addCell(new PdfPCell(new Phrase(medicine.getMedicineId())));
                table.addCell(new PdfPCell(new Phrase(medicine.getBrand())));
                table.addCell(new PdfPCell(new Phrase(medicine.getName())));
                table.addCell(new PdfPCell(new Phrase(medicine.getSupplierId())));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(medicine.getUnitPrice()))));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(medicine.getQuantity()))));
                table.addCell(new PdfPCell(new Phrase(medicine.getManufactureDate().toString())));
                table.addCell(new PdfPCell(new Phrase(medicine.getExpireDate().toString())));

            }

            doc.add(table);
            doc.add(new Paragraph("Medicine Report Generated !!!"));
            doc.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Operation Successful");
            alert.setContentText("medicine report has generated successfully !!");
            alert.showAndWait();


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }





}
