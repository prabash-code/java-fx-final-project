package edu.icet.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.javafx.collections.ImmutableObservableList;
import edu.icet.db.DBConnection;
import edu.icet.model.dto.*;
import edu.icet.repository.MedicineRepository;
import edu.icet.repository.SalesDetailsRepository;
import edu.icet.repository.SalesRepository;
import edu.icet.repository.impl.MedicineRepositoryImpl;
import edu.icet.repository.impl.SalesDetailsRepositoryImpl;
import edu.icet.repository.impl.SalesRepositoryImpl;
import edu.icet.service.MedicineService;
import edu.icet.service.SalesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalesServiceImpl implements SalesService {
    SalesRepository salesRepository = new SalesRepositoryImpl();
    MedicineService medicineService = new MedicineServiceImpl();
    SalesDetailsRepository salesDetailsRepository = new SalesDetailsRepositoryImpl();
    MedicineRepository medicineRepository = new MedicineRepositoryImpl();


    @Override
    public void placeOrder(Sale sale, ObservableList<CartItem> list) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            salesRepository.addSales(connection, sale);

            for (CartItem cartItem : list) {
                salesDetailsRepository.add(new SaleDetails(
                        sale.getSaleId(),
                        cartItem.getItemId(),
                        cartItem.getQuantity()
                ));
                ResultSet resultSet = medicineRepository.searchItem(connection, cartItem.getItemId());
                if (resultSet.next()) {
                    Medicine medicine = new Medicine(
                            resultSet.getString("medicineId"),
                            resultSet.getString("brand"),
                            resultSet.getString("name"),
                            resultSet.getString("supplierId"),
                            resultSet.getDouble("unitPrice"),
                            resultSet.getInt("quantity"),
                            resultSet.getDate("manufactureDate").toLocalDate(),
                            resultSet.getDate("expireDate").toLocalDate());

                    medicineRepository.updateQuantity(connection, cartItem.getItemId(), (medicine.getQuantity() - cartItem.getQuantity()));
                }

            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public String getLastOrderId() {
        ResultSet lastId = salesRepository.getLastId();
        try {
            if (lastId.next()) {
                return lastId.getString("saleId");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Medicine SerchMedicine(String medicineName) throws SQLException {
        return medicineService.searchMedicine(medicineName);

    }

    @Override
    public ObservableList<Sale> getAll() {
        ResultSet allSaleDetails = salesRepository.getAllSaleDetails();
      ObservableList<Sale>list= FXCollections.observableArrayList();
        try {
            while (allSaleDetails.next()) {
                String totalStr = allSaleDetails.getString("total");
                double total=0.00;
                if(totalStr!=null && !totalStr.trim().isEmpty()){
                    total=Double.parseDouble(totalStr);
                }
                list.add(new Sale(allSaleDetails.getString("saleId"),
                        allSaleDetails.getDate("saleDate").toLocalDate(),
                        allSaleDetails.getString("customerName"),
                        allSaleDetails.getString("customerEmail"),
                        total
                ));

            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Invoice> generatePdf(String text) {
        ResultSet resultSet = salesRepository.generatePdf(text);
        ObservableList<Invoice>list=FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {
                list.add(new Invoice(
                        resultSet.getString("saleId"),
                        resultSet.getString("itemCode"),
                        resultSet.getInt("orderQuantity"),
                        resultSet.getString("customerName"),
                        resultSet.getString("total")
          ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateInvoicePDF(ObservableList<Invoice> invoices) {
        try {
            String path = "C:/reports";
            File folder = new File(path);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String output = path + "/customer_report.pdf";

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(output));
            doc.open();


            doc.add(new Paragraph("INVOICE"));
            doc.add(new Paragraph("Generated  on :" + LocalDate.now()));
            doc.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.addCell(new PdfPCell(new Phrase("Item Code")));
            table.addCell(new PdfPCell(new Phrase("Quantity")));

            String total="";
            String name="";

            for (Invoice invoice: invoices) {
                table.addCell(new PdfPCell(new Phrase(invoice.getItemCode())));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(invoice.getOrderQuantity()))));

                name=invoice.getCustomerName();
                total=invoice.getTotal();

            }

            doc.add(table);
            doc.add(new Paragraph("Total Price is :"+total));
            doc.add(new Paragraph("Customer Name is :"+name));
            doc.add(new Paragraph("-------*** Welcome to HealthNet ***-------"));
            doc.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("PDF Successful");
            alert.setContentText("Invoice generated successfully !!");
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
