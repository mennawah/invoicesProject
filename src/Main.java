import Controller.InvoiceTableController;
import Controller.InvoiceItemsTableController;
import Controller.UpdateMainComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

 class Main extends JFrame implements ActionListener {
     InvoiceTableController invoiceTableController = new InvoiceTableController();
     InvoiceItemsTableController invoiceItemsTableController = new InvoiceItemsTableController();

     JLabel invoiceNumberValue = new javax.swing.JLabel();
     JLabel invoiceDateValue = new javax.swing.JLabel();
     JLabel invoiceCustomerValue = new javax.swing.JLabel();
     JLabel invoiceTotalValue = new javax.swing.JLabel();

     UpdateMainComponents obj = () ->
     {
         invoiceNumberValue.setText(invoiceTableController.jTable_invoices.getValueAt(invoiceTableController.jTable_invoices.getSelectedRow(), 0).toString());
         invoiceDateValue.setText(invoiceTableController.jTable_invoices.getValueAt(invoiceTableController.jTable_invoices.getSelectedRow(), 1).toString());
         invoiceCustomerValue.setText(invoiceTableController.jTable_invoices.getValueAt(invoiceTableController.jTable_invoices.getSelectedRow(), 2).toString());
         invoiceTotalValue.setText(invoiceTableController.jTable_invoices.getValueAt(invoiceTableController.jTable_invoices.getSelectedRow(), 3).toString());
     };

    public Main() throws IOException {
        super("invoices");

        invoiceTableController.init();
        invoiceItemsTableController.init();
        invoiceTableController.setItemsTableView(invoiceItemsTableController, obj);
        invoiceItemsTableController.setInvoicesTableView(invoiceTableController);

        //All objects & Data
        JLabel invoiceNumberLabel = new javax.swing.JLabel("Invoice Number: ");
        JLabel invoiceDateLabel = new javax.swing.JLabel("Invoice Date: ");
        JLabel customerNameLabel = new javax.swing.JLabel("Customer Name: ");
        JLabel invoiceTotalLabel = new javax.swing.JLabel("Invoice Total: ");

        JButton jBtn_create = new JButton("Create");
        JButton jBtn_delete = new JButton("Delete");
        JButton jBtn_saveData = new JButton("Save All Data");
        JButton jBtn_save = new JButton("Add Item");
        JButton jBtn_cancel = new JButton("Delete Item");

        //Save All Data button. Saves all the data to CSV files.
        jBtn_saveData.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    invoiceTableController.saveData();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Create new invoice
        jBtn_create.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                invoiceTableController.addRow();
            }
        });

        // Delete selected invoice
        jBtn_delete.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                invoiceTableController.deleteRow();
            }
        });

        //Create new item inside an invoice
        jBtn_save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoiceItemsTableController.saveItem();
            }
        });

        // Delete an item from an invoice
        jBtn_cancel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                invoiceItemsTableController.deleteItem();
            }
        });

        //All panels and layouts
        JPanel jPanel_main = new javax.swing.JPanel();
        JPanel jPanel_left = new javax.swing.JPanel();
        JPanel jPanel_right = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel_mainLayout = new javax.swing.GroupLayout(jPanel_main);
        javax.swing.GroupLayout jPanel_leftLayout = new javax.swing.GroupLayout(jPanel_left);
        javax.swing.GroupLayout jPanel_rightLayout = new javax.swing.GroupLayout(jPanel_right);

        jPanel_main.setLayout(new GridLayout(1,2));
        jPanel_main.add(jPanel_left);
        jPanel_main.add(jPanel_right);

        jPanel_mainLayout.setHorizontalGroup(
                jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_mainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel_left, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                                        .addComponent(jPanel_right, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()
                        )
        );

        jPanel_leftLayout.setHorizontalGroup(
                jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_leftLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(invoiceTableController.jTable_invoices, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                                        .addComponent(jBtn_create, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                        .addComponent(jBtn_delete, javax.swing.GroupLayout.DEFAULT_SIZE,37, Short.MAX_VALUE))
                                        .addComponent(jBtn_saveData, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                .addContainerGap()
                        )
        );

        jPanel_rightLayout.setHorizontalGroup(
                jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_rightLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(invoiceNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(invoiceNumberValue))
                                        .addGroup(jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(invoiceDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(invoiceDateValue, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(customerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(invoiceCustomerValue, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(invoiceTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(invoiceTotalValue))

                                        .addComponent(invoiceItemsTableController.jTable_items, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                                        .addComponent(jBtn_save, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                        .addComponent(jBtn_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                                .addContainerGap()
                        )
        );

        add(jPanel_main);
        setPreferredSize( new Dimension( 750, 480 ) );
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        new Main().setVisible(true);
    }

     @Override
     public void actionPerformed(ActionEvent e) {
     }
 }