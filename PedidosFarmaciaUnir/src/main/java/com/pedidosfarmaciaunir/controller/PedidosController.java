/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedidosfarmaciaunir.controller;

import com.pedidosfarmaciaunir.models.Medicamento;
import com.pedidosfarmaciaunir.views.FormPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        }
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
