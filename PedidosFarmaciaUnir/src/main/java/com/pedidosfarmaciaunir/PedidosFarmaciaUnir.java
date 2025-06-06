/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pedidosfarmaciaunir;

import com.pedidosfarmaciaunir.controller.PedidosController;
import com.pedidosfarmaciaunir.models.Medicamento;
import com.pedidosfarmaciaunir.views.FormPedido;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author marco
 */
public class PedidosFarmaciaUnir {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Medicamento model = new Medicamento();
            FormPedido view = new FormPedido();
            
            JFrame frame = new JFrame("Pedidos Farmacia UNIR");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(view);
            frame.pack();
            frame.setLocationRelativeTo(null);
            
            PedidosController controller = new PedidosController(model, view);
            
            frame.setVisible(true);
        });
    }
}
