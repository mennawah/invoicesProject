package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InvoiceItemsTableController {
    public JTable jTable_items  = new JTable();
    public ArrayList<InvoiceLine> invoice_items_data = new ArrayList<InvoiceLine>();
    private InvoiceTableController invoiceTableController;
    DefaultTableModel tablemodel_items = new DefaultTableModel();

    public void init() {
        this.setModel();
        this.addListeners();
    }

    public void setModel() {
        tablemodel_items.setColumnCount(5);
        tablemodel_items.addRow(new String[]{"ID","Item Name", "Price", "Quantity", "Sub-Total"});
        jTable_items.setModel(tablemodel_items);
    }

    public void saveItem() {
        if (invoiceTableController.jTable_invoices.getSelectedRow() >= 0) {
            DefaultTableModel model = (DefaultTableModel) jTable_items.getModel();
            String[] newItem = new String[]{invoiceTableController.jTable_invoices.getValueAt(invoiceTableController.jTable_invoices.getSelectedRow(), 0).toString(), "TBF", "0", "0","0"};
            model.addRow(newItem);
            InvoiceLine temp = new InvoiceLine(newItem[0], newItem[1], newItem[2], newItem[3], newItem[4]);
            invoice_items_data.add(temp);
        }
        else JOptionPane.showMessageDialog(null, "Please select an invoice before adding a new item");
    }

    public void deleteItem() {
        if (jTable_items.getSelectedRow() >= 0) {
            ArrayList<InvoiceLine> items_temp = new ArrayList<InvoiceLine>();
            DefaultTableModel model = tablemodel_items;
            String selectedID = invoiceTableController.jTable_invoices.getValueAt(invoiceTableController.jTable_invoices.getSelectedRow(), 0).toString();
            for (int i = 0; i < invoice_items_data.size(); i++)
                if (invoice_items_data.get(i).getId().equals(selectedID)) {
                    items_temp.add(invoice_items_data.get(i));
                }
            invoice_items_data.removeAll(items_temp);
            items_temp.remove(jTable_items.getSelectedRow() - 1);
            model.removeRow(jTable_items.getSelectedRow());
            invoice_items_data.addAll(items_temp);
            items_temp.clear();
        }
        else JOptionPane.showMessageDialog(null, "Please select an invoice item to be deleted.");
    }

    public void addListeners() {
        jTable_items.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = jTable_items.getSelectedRow();
                    ArrayList<InvoiceLine> items_temp = new ArrayList<InvoiceLine>();
                    DefaultTableModel model = (DefaultTableModel) jTable_items.getModel();
                    String selectedID = invoiceTableController.jTable_invoices.getValueAt(invoiceTableController.jTable_invoices.getSelectedRow(), 0).toString();
                    for (int i = 0; i < invoice_items_data.size(); i++)
                        if (invoice_items_data.get(i).getId().equals(selectedID)) {
                            items_temp.add(invoice_items_data.get(i));
                        }
                    invoice_items_data.removeAll(items_temp);
                    items_temp.get(row-1).setId(jTable_items.getValueAt(row,0).toString());
                    items_temp.get(row-1).setItemName(jTable_items.getValueAt(row,1).toString());
                    items_temp.get(row-1).setPrice(jTable_items.getValueAt(row,2).toString());
                    items_temp.get(row-1).setQuantity(jTable_items.getValueAt(row,3).toString());
                    int price = Integer.valueOf(jTable_items.getValueAt(row,2).toString());
                    int qty = Integer.valueOf(jTable_items.getValueAt(row,3).toString());
                    int subtotal = price * qty;
                    items_temp.get(row-1).setSubTotal(Integer.toString(subtotal));
                    jTable_items.setValueAt(Integer.toString(subtotal),row,4);

                    int grandtotal = 0;
                    for (int i=0; i<items_temp.size(); i++) grandtotal = grandtotal + Integer.valueOf(items_temp.get(i).getSubTotal());
                    invoiceTableController.jTable_invoices.setValueAt(Integer.toString(grandtotal),invoiceTableController.jTable_invoices.getSelectedRow(),3);
                    invoiceTableController.invoice_header_data.get(invoiceTableController.jTable_invoices.getSelectedRow()-1).setTotal(Integer.toString(grandtotal));

                    invoice_items_data.addAll(items_temp);
                    items_temp.clear();
                }
            }
        });
    }

    public void updateInvoiceItemsData(ArrayList<InvoiceLine> invoice_items_data) {
        this.invoice_items_data = invoice_items_data;
    }

    public void setInvoicesTableView(InvoiceTableController invoiceTableController) {
        this.invoiceTableController = invoiceTableController;
    }
}
