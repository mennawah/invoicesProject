package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceTableController {

    public JTable jTable_invoices  = new JTable();
    public ArrayList<InvoiceHeader> invoice_header_data = new ArrayList<InvoiceHeader>();
    InvoiceItemsTableController invoiceItemsTableController;
    DefaultTableModel model = new DefaultTableModel();
    ArrayList<InvoiceLine> invoice_items_data = new ArrayList<InvoiceLine>();
    UpdateMainComponents obj;

    public void init() throws IOException {
        this.setModel();
        this.loadDataFromFile();
        this.addListeners();
        this.addSelectionRowAction();
    }

    private void loadDataFromFile() throws IOException {
        DefaultTableModel model_invoices = model;

        Scanner scan_invoices = new Scanner(new File("C:\\Users\\mennatallahw\\Desktop\\invoices.csv"));
        String[] invoice_record = new String[4];
        while(scan_invoices.hasNext())
        {
            invoice_record = scan_invoices.nextLine().split(",");
            InvoiceHeader temp = new InvoiceHeader(invoice_record[0], invoice_record[1], invoice_record[2], invoice_record[3]);
            invoice_header_data.add(temp);
            model_invoices.addRow(invoice_record);
        }

        Scanner scan_items = new Scanner(new File("C:\\Users\\mennatallahw\\Desktop\\invoice_items.csv"));
        String[] item_record = new String[5];
        while(scan_items.hasNext())
        {
            item_record = scan_items.nextLine().split(",");
            InvoiceLine temp = new InvoiceLine(item_record[0], item_record[1], item_record[2], item_record[3], item_record[4]);
            invoice_items_data.add(temp);
        }
    }

    private void addListeners() {
        jTable_invoices.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = jTable_invoices.getSelectedRow();
                    invoice_header_data.get(row-1).setId(jTable_invoices.getValueAt(row,0).toString());
                    invoice_header_data.get(row-1).setDate(jTable_invoices.getValueAt(row,1).toString());
                    invoice_header_data.get(row-1).setCustomer(jTable_invoices.getValueAt(row,2).toString());
                    invoice_header_data.get(row-1).setTotal(jTable_invoices.getValueAt(row,3).toString());
                }
            }
        });
    }

    // When selecting a row in invoices table
    private void addSelectionRowAction() {
        jTable_invoices.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                try {
                    obj.updateInvoiceDetails();
                    DefaultTableModel model = invoiceItemsTableController.tablemodel_items;
                    model.setRowCount(1);
                    ArrayList<InvoiceLine> selectedInvoiceItems = new ArrayList<InvoiceLine>();
                    String selectedID = jTable_invoices.getValueAt(jTable_invoices.getSelectedRow(), 0).toString();
                    for (int i = 0; i < invoice_items_data.size(); i++) {
                        if (invoice_items_data.get(i).getId().equals(selectedID)) {
                            model.addRow(invoice_items_data.get(i).getModelRow());
                            selectedInvoiceItems.add(invoice_items_data.get(i));
                        }
                    }
                    invoiceItemsTableController.updateInvoiceItemsData(selectedInvoiceItems);
                } catch (ArrayIndexOutOfBoundsException ee) {}
            }
        });
    }

    public void setModel() {
        model.setColumnCount(4);
        model.addRow(new String[]{"ID", "date", "Customer", "Total"});
        jTable_invoices.setModel(model);
    }

    public void addRow() {
        String lastID = jTable_invoices.getValueAt(model.getRowCount()-1,0).toString();
        Integer newID = Integer.valueOf(lastID)+1;
        model.addRow(new String[]{newID.toString(), "dd-mm-yyyy", "TBF", "TBF"});
        invoice_header_data.add(new InvoiceHeader(newID.toString(), "dd-mm-yyyy", "TBF", "TBF"));
    }

    public void deleteRow() {
        int currentSelectedIndex = jTable_invoices.getSelectedRow();
        model.removeRow(currentSelectedIndex);
        invoice_header_data.remove(currentSelectedIndex - 1);
    }

    public void saveData() throws IOException {

        String invoice_header_line = new String();
        String invoice_item_line = new String();

        try
        {
            String filename= "C:\\Users\\mennatallahw\\Desktop\\invoices.csv";
            FileWriter fw1 = new FileWriter(filename,false);
            for (int i = 0; i < invoice_header_data.size(); i++) {
                invoice_header_line = String.join(",", invoice_header_data.get(i).getModelRow());
                fw1.write(invoice_header_line + "\n");
            }
            fw1.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

        try
        {
            String filename= "C:\\Users\\mennatallahw\\Desktop\\invoice_items.csv";
            FileWriter fw2 = new FileWriter(filename,false);
            for (int i = 0; i < invoice_items_data.size(); i++) {
                invoice_item_line = String.join(",", invoice_items_data.get(i).getModelRow());
                fw2.write(invoice_item_line + "\n");
            }
            fw2.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void setItemsTableView(InvoiceItemsTableController invoiceItemsTableController, UpdateMainComponents obj) {
        this.invoiceItemsTableController = invoiceItemsTableController;
        this.obj = obj;
    }
}

