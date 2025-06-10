/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedidosfarmaciaunir.controller;

import com.pedidosfarmaciaunir.models.Medicamento;
import com.pedidosfarmaciaunir.views.FormPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringJoiner;
import javax.swing.JOptionPane;
import com.pedidosfarmaciaunir.views.ResumenPedido;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author marco
 */
public class PedidosController implements ActionListener {
    Medicamento model;
    FormPedido view;

    public PedidosController(Medicamento model, FormPedido view) {
        this.model = model;
        this.view = view;
        view.jButton2.addActionListener(this);
        view.jButton1.addActionListener(this);
    }
    
    public void iniciar(){
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.jButton1) {
            limpiarFormulario();
        } else if (e.getSource() == view.jButton2) {
            confirmarPedido();
        }
    }

    private void confirmarPedido() {
        String nombreMedicamento = view.jTextField1.getText().trim();
        if (nombreMedicamento.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Escriba el nombre del medicamento.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipoMedicamento = (String) view.jComboBox1.getSelectedItem();
        if (tipoMedicamento == null || tipoMedicamento.isEmpty() || tipoMedicamento.equals("Seleccione")) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar un tipo de medicamento.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cantidadStr = view.jTextField2.getText().trim();
        if (cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Debe llenar este campo.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(view, "La cantidad debe ser mayor a cero.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "La cantidad debe ser un número válido.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String distribuidor = null;
        if (view.jRadioButton1.isSelected()) {
            distribuidor = view.jRadioButton1.getText();
        } else if (view.jRadioButton2.isSelected()) {
            distribuidor = view.jRadioButton2.getText();
        } else if (view.jRadioButton3.isSelected()) {
            distribuidor = view.jRadioButton3.getText();
        }

        if (distribuidor == null) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar un distribuidor.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        StringJoiner sucursalJoiner = new StringJoiner(", ");
        if (view.jCheckBox1.isSelected()) {
            sucursalJoiner.add(view.jCheckBox1.getText());
        }
        if (view.jCheckBox2.isSelected()) {
            sucursalJoiner.add(view.jCheckBox2.getText());
        }
        String sucursal = sucursalJoiner.toString();

        if (sucursal.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar al menos una sucursal.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.setNombreMedicamento(nombreMedicamento);
        model.setTipoMedicamento(tipoMedicamento);
        model.setCantidad(cantidad);
        model.setDistribuidor(distribuidor);
        model.setSucursal(sucursal);

        String message = String.format("Revisa los datos del pedido:\n\n" +
                "Nombre: %s\n" +
                "Tipo: %s\n" +
                "Cantidad: %d\n" +
                "Distribuidor: %s\n" +
                "Sucursal(es): %s\n",
                nombreMedicamento, tipoMedicamento, cantidad, distribuidor, sucursal);

        // Mostrar el resumen del pedido en un nuevo JFrame
        String medicamentoInfo = String.format("%d unidades del %s %s", cantidad, tipoMedicamento.toLowerCase(), nombreMedicamento.toLowerCase());
        String direccion = "";
        if (sucursal.contains("Principal")) {
            direccion += "Calle de la Rosa n. 28";
        }
        if (sucursal.contains("Secundaria")) {
            if (!direccion.isEmpty()) {
                direccion += " y para la situada en ";
            }
            direccion += "Calle Alcazabilla n. 3";
        }
        direccion = "Para la farmacia situada en " + direccion;

        ResumenPedido resumenPanel = new ResumenPedido();
        resumenPanel.jTextField1.setText("Pedido enviado a: " + distribuidor);
        resumenPanel.jTextField4.setText("Concepto: " + medicamentoInfo);
        resumenPanel.jTextField2.setText("Dirección: " + direccion);

        resumenPanel.jButton1.addActionListener(e -> SwingUtilities.getWindowAncestor(resumenPanel).dispose());
        resumenPanel.jButton2.addActionListener(e -> {
            System.out.println("Pedido enviado");
            JOptionPane.showMessageDialog(resumenPanel, "Pedido enviado", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.getWindowAncestor(resumenPanel).dispose();
            limpiarFormulario();
        });

        JFrame frame = new JFrame("Confirmación de Pedido");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(resumenPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void limpiarFormulario() {
        view.jTextField1.setText("");
        view.jTextField2.setText("");
        view.jComboBox1.setSelectedIndex(0);
        view.limpiarSeleccionDistribuidor();
        view.jCheckBox1.setSelected(false);
        view.jCheckBox2.setSelected(false);
    }
}
