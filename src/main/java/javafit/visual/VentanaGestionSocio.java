/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javafit.visual;

import com.mycompany.javafit.Actividad;
import com.mycompany.javafit.Gimnasio;
import java.util.ArrayList;

/**
 * Clase que representa la ventana principal de gestión para los socios.
 * Permite visualizar el perfil, consultar/reservar actividades y gestionar reservas.
 *
 * @author Usuario
 */

public class VentanaGestionSocio extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaGestionSocio.class.getName());

    /**
     * Creates new form VentanaSocio.
     * Inicializa los componentes, carga los datos del perfil del socio
     * y rellena las tablas de actividades y reservas.
     */
    public VentanaGestionSocio() {
        initComponents();
        cargarDatosPerfil();
        cargarTablaActividades("Todos", "", "Todos");
        cargarTablaMisReservas();
    }
    /**
     * Carga en la tabla correspondiente las reservas asociadas al socio actual.
     * Filtra la lista global de reservas del gimnasio para mostrar únicamente
     * las del usuario logeado.
     */
    private void cargarTablaMisReservas() {
    // 1. Obtenemos el modelo de tu tabla de reservas (cambia jTable2 por el nombre de tu tabla si fuese necesario)
    javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable2.getModel();
    
    // 2. Vaciamos la tabla por completo antes de rellenar para evitar duplicados
    modelo.setRowCount(0);
    
    // 3. Identificamos al socio que está logeado en este momento
    com.mycompany.javafit.Socio socioActual = (com.mycompany.javafit.Socio) com.mycompany.javafit.Gimnasio.getInstancia().getUsuarioLogeado();
    
    if (socioActual != null) {
        
        // 4. Le pedimos TODAS las reservas del gimnasio a la clase principal de Dani
        // (Usa Ctrl + Espacio en .getReservas() si te saliera rojo por haberlo llamado diferente en Gimnasio.java)
        java.util.ArrayList<com.mycompany.javafit.Reserva> todasLasReservas = com.mycompany.javafit.Gimnasio.getInstancia().getReservas();
        
        if (todasLasReservas != null) {
            // 5. Recorremos la lista completa del gimnasio
            for (com.mycompany.javafit.Reserva res : todasLasReservas) {
                
                // ¡AQUÍ ESTÁ EL TRUCO! Comprobamos si el cliente de esa reserva es nuestro socio actual
                if (res.getCliente() != null && res.getCliente().getCorreo().equals(socioActual.getCorreo())) {
                    
                    Object[] fila = new Object[4];
                    
                    // Usamos los métodos exactos que hemos visto en su clase Reserva.java:
                    fila[0] = res.getHorario().getDia();          // Columna Fecha / Día
                    fila[1] = res.getActividad().getTitulo();   // Columna Actividad
                    fila[2] = res.getHorario().getTurno();        // Columna Horario / Turno
                    fila[3] = "Confirmada (" + res.getImporte() + "€)"; // Columna Estado con el precio de Dani
                    
                    modelo.addRow(fila);
                }
            }
        }
    }
}
    /**
     * Carga y filtra la tabla de actividades disponibles según los criterios especificados.
     * 
     * @param tipoFiltro Tipo de actividad a filtrar (ej. "Yoga", "Todos").
     * @param monitorFiltro Nombre del monitor a buscar (coincidencia parcial).
     * @param diaFiltro Día de la semana en que se imparte la actividad (ej. "Lunes", "Todos").
     */
    private void cargarTablaActividades(String tipoFiltro, String monitorFiltro, String diaFiltro) {
    
    javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaActividades.getModel();
    modelo.setRowCount(0);
    
    java.util.ArrayList<com.mycompany.javafit.Actividad> listaActividades = com.mycompany.javafit.Gimnasio.getInstancia().getActividades();
    
    if (listaActividades != null) {
        for (com.mycompany.javafit.Actividad act : listaActividades) {
            
            // 1. Comprobamos el Tipo
            boolean coincideTipo = tipoFiltro.equals("Todos") || act.getTipo().equalsIgnoreCase(tipoFiltro);
            
            // 2. Comprobamos el Monitor
            boolean coincideMonitor = monitorFiltro.isEmpty() || act.getMonitor().toLowerCase().contains(monitorFiltro.toLowerCase());
            
            // 3. Comprobamos el Día (Buscamos dentro de sus horarios)
            boolean coincideDia = false;
            if (diaFiltro.equals("Todos") || diaFiltro.isEmpty()) {
                coincideDia = true; // Si no filtra por día o pone "Todos", lo damos por bueno
            } else if (act.getHorarios() != null) {
                // Recorremos los horarios de la actividad para ver si alguno cuadra con el día buscado
                for (com.mycompany.javafit.Horario h : act.getHorarios()) {
                    if (h.getDia().equalsIgnoreCase(diaFiltro)) {
                        coincideDia = true;
                        break; // Si encontramos una coincidencia, dejamos de buscar en esta actividad
                    }
                }
            }
            
            // Si cumple con los TRES filtros a la vez, se añade a la tabla
            if (coincideTipo && coincideMonitor && coincideDia) {
                Object[] fila = new Object[3]; 
                fila[0] = act.getTitulo();
                fila[1] = act.getTipo();
                fila[2] = act.getMonitor();
                
                modelo.addRow(fila);
            }
        }
    }
}
    /**
     * Carga los datos del socio logeado en los campos de texto correspondientes
     * a la pestaña de "Mi Perfil".
     */
    private void cargarDatosPerfil() {
        com.mycompany.javafit.Socio socioActual = (com.mycompany.javafit.Socio) com.mycompany.javafit.Gimnasio.getInstancia().getUsuarioLogeado();
        
        if (socioActual != null) {
            campoNombre.setText(socioActual.getNombre());
            campoCorreo.setText(socioActual.getCorreo());
            campoTelefono.setText(socioActual.getTelefono());
            campoDireccion.setText(socioActual.getDireccion());
            campoTarjeta.setText(socioActual.getTarjetaCredito());
            campoClave.setText(socioActual.getClave());
            
            if (socioActual.isSocioVIP()) {
                campoEstado.setText("Socio VIP (10% Descuento)");
            } else {
                campoEstado.setText("Socio Básico");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jScrollBar1 = new javax.swing.JScrollBar();
        campoMonitor1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        BusquedaYReserva = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoTipo = new javax.swing.JComboBox<>();
        jLabelDia = new javax.swing.JLabel();
        campoMonitor = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaActividades = new javax.swing.JTable();
        botonReservar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        campoDia = new javax.swing.JComboBox<>();
        MisReservas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        cancelarReserva = new javax.swing.JButton();
        MiPerfil = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        campoCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        campoTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        campoDireccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        campoTarjeta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campoClave = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        botonGuardarCambios = new javax.swing.JButton();
        campoEstado = new javax.swing.JTextField();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaFit");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("JavaFit");

        jLabel2.setText("Tipo:");

        campoTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yoga", "Musculación", "Cardio", "Natación", "Todos" }));
        campoTipo.addActionListener(this::campoTipoActionPerformed);

        jLabelDia.setText("Dia:");

        jButton1.setText("Buscar ");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Limpiar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        tablaActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Título", "Tipo", "Monitor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaActividades.getTableHeader().setReorderingAllowed(false);
        tablaActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaActividadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaActividades);
        if (tablaActividades.getColumnModel().getColumnCount() > 0) {
            tablaActividades.getColumnModel().getColumn(2).setResizable(false);
        }

        botonReservar.setText("Reservar Clase Seleccionada");
        botonReservar.addActionListener(this::botonReservarActionPerformed);

        jLabel4.setText("Monitor:");

        campoDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo", "Todos" }));

        javax.swing.GroupLayout BusquedaYReservaLayout = new javax.swing.GroupLayout(BusquedaYReserva);
        BusquedaYReserva.setLayout(BusquedaYReservaLayout);
        BusquedaYReservaLayout.setHorizontalGroup(
            BusquedaYReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusquedaYReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BusquedaYReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addGroup(BusquedaYReservaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(BusquedaYReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BusquedaYReservaLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(38, 38, 38)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(BusquedaYReservaLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoMonitor)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelDia)
                                .addGap(12, 12, 12)
                                .addComponent(campoDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BusquedaYReservaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonReservar)
                .addGap(161, 161, 161))
        );
        BusquedaYReservaLayout.setVerticalGroup(
            BusquedaYReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusquedaYReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BusquedaYReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDia)
                    .addComponent(campoMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(campoDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BusquedaYReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(botonReservar)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Búsqueda y Reserva", BusquedaYReserva);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "Actividad", "Horario", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        cancelarReserva.setText("Cancelar Reserva");
        cancelarReserva.addActionListener(this::cancelarReservaActionPerformed);

        javax.swing.GroupLayout MisReservasLayout = new javax.swing.GroupLayout(MisReservas);
        MisReservas.setLayout(MisReservasLayout);
        MisReservasLayout.setHorizontalGroup(
            MisReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MisReservasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MisReservasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelarReserva)
                .addGap(189, 189, 189))
        );
        MisReservasLayout.setVerticalGroup(
            MisReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MisReservasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelarReserva)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mis Reservas", MisReservas);

        jLabel5.setText("Nombre:");

        jLabel6.setText("Correo:");

        campoCorreo.addActionListener(this::campoCorreoActionPerformed);

        jLabel7.setText("Teléfono:");

        jLabel8.setText("Dirección:");

        jLabel9.setText("Tarjeta de crédito:");

        jLabel10.setText("Contraseña:");

        jLabel11.setText("Estado:");

        botonGuardarCambios.setText("Guardar Cambios");
        botonGuardarCambios.addActionListener(this::botonGuardarCambiosActionPerformed);

        campoEstado.setEditable(false);

        javax.swing.GroupLayout MiPerfilLayout = new javax.swing.GroupLayout(MiPerfil);
        MiPerfil.setLayout(MiPerfilLayout);
        MiPerfilLayout.setHorizontalGroup(
            MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MiPerfilLayout.createSequentialGroup()
                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MiPerfilLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(MiPerfilLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCorreo))
                            .addGroup(MiPerfilLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MiPerfilLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTelefono))
                            .addGroup(MiPerfilLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoDireccion))
                            .addGroup(MiPerfilLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MiPerfilLayout.createSequentialGroup()
                                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MiPerfilLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoClave))
                                    .addGroup(MiPerfilLayout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoEstado)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(MiPerfilLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(botonGuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        MiPerfilLayout.setVerticalGroup(
            MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MiPerfilLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(campoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(campoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(campoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(campoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(campoClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(campoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonGuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mi Perfil", MiPerfil);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Maneja el evento de clic en el botón de búsqueda de actividades.
     * Aplica los filtros de tipo, monitor y día seleccionados en la interfaz.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Asegúrate de que los nombres de los componentes (comboTipo, campoMonitor, comboDia) coinciden con los de tu diseño
    String tipoElegido = campoTipo.getSelectedItem().toString(); 
    String monitorEscrito = campoMonitor.getText().trim();
    
    // Leemos el día que ha elegido en el desplegable (cambia comboDia por jComboBox2 o como se llame el tuyo)
    String diaElegido = campoDia.getSelectedItem().toString(); 

    // Llamamos a la tabla pasándole los TRES filtros
    cargarTablaActividades(tipoElegido, monitorEscrito, diaElegido);
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Maneja el evento de clic en el botón para limpiar los filtros de actividades.
     * Restablece los campos de búsqueda y recarga todas las actividades.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        campoTipo.setSelectedIndex(0); // Vuelve al primer elemento ("Todos" o "Yoga")
        campoMonitor.setText("");
        campoDia.setSelectedIndex(8);
        // Volvemos a cargar todo
        cargarTablaActividades("Todos", "","Todos");
    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     * Maneja el evento de clic en el botón para cancelar una reserva.
     * Solicita confirmación al usuario y elimina la reserva seleccionada en la tabla.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void cancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarReservaActionPerformed
        // TODO add your handling code here:                                                   
    // 1. Comprobamos si el usuario ha seleccionado alguna fila de la tabla de reservas
    // (Asegúrate de que tu tabla de reservas se llama jTable2, si no, cambia el nombre)
    int filaSeleccionada = jTable2.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona primero la reserva que deseas cancelar.");
        return;
    }
    
    // 2. Extraemos los datos clave de la fila seleccionada para identificar la reserva única
    String diaClase = jTable2.getValueAt(filaSeleccionada, 0).toString();
    String tituloActividad = jTable2.getValueAt(filaSeleccionada, 1).toString();
    String turnoClase = jTable2.getValueAt(filaSeleccionada, 2).toString();
    
    // 3. Obtenemos el socio que está usando la aplicación actualmente
    com.mycompany.javafit.Socio socioActual = (com.mycompany.javafit.Socio) com.mycompany.javafit.Gimnasio.getInstancia().getUsuarioLogeado();
    
    if (socioActual != null) {
        // 4. Preguntamos al usuario si está seguro de cancelar para evitar errores accidentales
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que quieres cancelar tu reserva de " + tituloActividad + "?", 
                "Confirmar cancelación", javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            
            // 5. Traemos todas las reservas del sistema para buscar la que coincide exactamente
            java.util.ArrayList<com.mycompany.javafit.Reserva> listaReservasSistema = com.mycompany.javafit.Gimnasio.getInstancia().getReservas();
            com.mycompany.javafit.Reserva reservaAEliminar = null;
            
            if (listaReservasSistema != null) {
                for (com.mycompany.javafit.Reserva res : listaReservasSistema) {
                    // Comprobamos si coincide el cliente, el título de la clase, el día y el turno exacto
                    if (res.getCliente() != null && res.getCliente().getCorreo().equals(socioActual.getCorreo()) &&
                        res.getActividad() != null && res.getActividad().getTitulo().equalsIgnoreCase(tituloActividad) &&
                        res.getHorario() != null && res.getHorario().getDia().equalsIgnoreCase(diaClase) &&
                        res.getHorario().getTurno().equalsIgnoreCase(turnoClase)) {
                        
                        reservaAEliminar = res;
                        break; // La hemos encontrado, salimos del bucle
                    }
                }
            }
            
            // 6. Si encontramos la reserva en el sistema, la borramos y actualizamos el archivo
            if (reservaAEliminar != null) {
                listaReservasSistema.remove(reservaAEliminar);
                
                // Forzamos el guardado en el archivo datos.dat para que el cambio persista al cerrar
                com.mycompany.javafit.Gimnasio.getInstancia().guardarDatos();
                
                javax.swing.JOptionPane.showMessageDialog(this, "La reserva se ha cancelado correctamente.");
                
                // 7. Refrescamos la tabla inmediatamente para que desaparezca visualmente
                cargarTablaMisReservas();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: No se pudo localizar la reserva seleccionada en el sistema.");
            }
        }
    }
    }//GEN-LAST:event_cancelarReservaActionPerformed
    /**
     * Maneja el evento de acción en el campo de texto de correo.
     *
     * @param evt Evento de acción disparado por el campo de texto.
     */
    private void campoCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCorreoActionPerformed
    /**
     * Maneja el evento de clic en el botón para guardar cambios en el perfil.
     * Actualiza los datos del socio activo con la información introducida en los campos.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void botonGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarCambiosActionPerformed
        // TODO add your handling code here:
        // 1. Obtenemos el socio que está usando la aplicación ahora mismo
com.mycompany.javafit.Socio socioActual = (com.mycompany.javafit.Socio) com.mycompany.javafit.Gimnasio.getInstancia().getUsuarioLogeado();

if (socioActual != null) {
    // 2. Actualizamos sus datos usando los "setters" de la clase de tu compañero
    socioActual.setNombre(campoNombre.getText().trim());
    socioActual.setTelefono(campoTelefono.getText().trim());
    socioActual.setDireccion(campoDireccion.getText().trim());
    socioActual.setTarjetaCredito(campoTarjeta.getText().trim());
    
    // Si queréis permitir que cambie su correo, quita las // de la línea de abajo:
    // socioActual.setCorreo(campoCorreo.getText().trim());
    
    // La contraseña hay que sacarla así porque es un campo de seguridad especial:
    String nuevaClave = new String(campoClave.getPassword());
    socioActual.setClave(nuevaClave);
    // Llamamos al método que sobreescribe el archivo datos.dat
    com.mycompany.javafit.Gimnasio.getInstancia().guardarDatos();
    // 3. Avisamos al usuario con un mensaje en pantalla
    javax.swing.JOptionPane.showMessageDialog(this, "¡Tus datos se han actualizado correctamente!");
} else {
    javax.swing.JOptionPane.showMessageDialog(this, "Error: No se ha podido identificar al usuario.");
}
    }//GEN-LAST:event_botonGuardarCambiosActionPerformed
    /**
     * Maneja el evento de clic en el botón para reservar una actividad.
     * Verifica la selección, comprueba si ya existe la reserva, y la efectúa
     * generando un recibo si es exitosa.
     *
     * @param evt Evento de acción disparado por el botón.
     */
    private void botonReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReservarActionPerformed
                                             
    // 1. Averiguamos qué fila de la tabla ha seleccionado el usuario
    int filaSeleccionada = tablaActividades.getSelectedRow();

    if (filaSeleccionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, haz clic en una actividad de la tabla primero.");
        return; 
    }

    // 2. Sacamos el Título de la actividad (columna 0)
    String tituloClase = tablaActividades.getValueAt(filaSeleccionada, 0).toString();

    // 3. Buscamos la actividad real en el sistema
    com.mycompany.javafit.Actividad actividadElegida = null;
    java.util.ArrayList<com.mycompany.javafit.Actividad> lista = com.mycompany.javafit.Gimnasio.getInstancia().getActividades();

    for (com.mycompany.javafit.Actividad act : lista) {
        if (act.getTitulo().equals(tituloClase)) {
            actividadElegida = act;
            break; 
        }
    }

    // 4. Obtenemos al socio logeado
    com.mycompany.javafit.Socio socioActual = (com.mycompany.javafit.Socio) com.mycompany.javafit.Gimnasio.getInstancia().getUsuarioLogeado();

    if (actividadElegida != null && socioActual != null) {
        
        // 5. SOLUCIÓN AL DÍA: Si borramos el desplegable, cogemos el primer día de la actividad
        String diaElegido = "Lunes"; // Por defecto
        if (actividadElegida.getHorarios() != null && !actividadElegida.getHorarios().isEmpty()) {
             diaElegido = actividadElegida.getHorarios().get(0).getDia();
        }
        
        // 6. Creamos el objeto Horario envolviendo el día
        com.mycompany.javafit.Horario turnoObjeto = new com.mycompany.javafit.Horario(diaElegido, "Mañana"); 
        
        boolean yaExisteReserva = false;
        java.util.ArrayList<com.mycompany.javafit.Reserva> listaReservasSistema = com.mycompany.javafit.Gimnasio.getInstancia().getReservas();
        
        if (listaReservasSistema != null) {
            for (com.mycompany.javafit.Reserva res : listaReservasSistema) {
                // ¡AHORA SÍ! Usamos getHorario() para sacar el objeto, y a ese objeto le pedimos el Día y el Turno
                if (res.getCliente() != null && res.getCliente().getCorreo().equals(socioActual.getCorreo()) &&
                    res.getActividad() != null && res.getActividad().getTitulo().equalsIgnoreCase(actividadElegida.getTitulo()) &&
                    res.getHorario() != null && res.getHorario().getDia().equalsIgnoreCase(turnoObjeto.getDia()) &&
                    res.getHorario().getTurno().equalsIgnoreCase(turnoObjeto.getTurno())) {
                    
                    yaExisteReserva = true;
                    break; // Ya lo encontramos, paramos de buscar
                }
            }
        }

        // Si el bucle detectó que ya está apuntado, frenamos la reserva inmediatamente
        if (yaExisteReserva) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "¡Ya estás inscrito en esta actividad para el " + diaElegido + "! No puedes duplicar la reserva.", 
                "Reserva Duplicada", javax.swing.JOptionPane.WARNING_MESSAGE);
            return; // Detiene la ejecución del método aquí mismo
        }

        // 7. Si pasa el control, llamamos al método oficial para reservar
        boolean exito = com.mycompany.javafit.Gimnasio.getInstancia().reservar(actividadElegida, socioActual, turnoObjeto);
        
        if (exito) {
            // Guardamos automáticamente para que la reserva persista al cerrar
            com.mycompany.javafit.Gimnasio.getInstancia().guardarDatos();
            
            // =========================================================================
            // 📄 NUEVO: GENERAR EL RECIBO/FACTURA EN TEXTO
            // =========================================================================
            try {
                // 1. Conseguimos la lista de reservas
                java.util.ArrayList<com.mycompany.javafit.Reserva> listaRes = com.mycompany.javafit.Gimnasio.getInstancia().getReservas();
                if (listaRes != null && !listaRes.isEmpty()) {
                    // 2. Cogemos la última reserva (la que acabamos de hacer)
                    com.mycompany.javafit.Reserva ultimaReserva = listaRes.get(listaRes.size() - 1);
                    
                    // 3. Llamamos al método de Dani para generar el archivo .txt
                    com.mycompany.javafit.Gimnasio.getInstancia().generaFactura(ultimaReserva);
                }
            } catch (java.io.IOException e) {
                System.out.println("No se pudo generar el archivo de la factura: " + e.getMessage());
            }
            // =========================================================================
            
            javax.swing.JOptionPane.showMessageDialog(this, "¡Reserva confirmada para: " + tituloClase + "!\nSe ha generado tu recibo en la carpeta Facturas.");
            cargarTablaMisReservas(); 
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se ha podido reservar (quizás está llena).");
        }                                            
    }//GEN-LAST:event_botonReservarActionPerformed
    }
    /**
     * Maneja el evento de clic con el ratón en la tabla de actividades.
     * Abre la ventana de detalles de la actividad seleccionada.
     *
     * @param evt Evento de ratón disparado por la tabla.
     */
    private void tablaActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaActividadesMouseClicked
        // TODO add your handling code here:
        // 1. Obtenemos la fila en la que el usuario ha hecho clic
    int fila = tablaActividades.getSelectedRow();
    
    if (fila >= 0) {
        
        // 2. Sacamos el título de la actividad (suponiendo que sigue en la columna 0)
        String titulo = tablaActividades.getValueAt(fila, 0).toString();
        
        // 3. Buscamos la actividad con tu código usando Streams (¡muy elegante!)
        com.mycompany.javafit.Actividad actSeleccionada = com.mycompany.javafit.Gimnasio.getInstancia().getActividades().stream()
                .filter(a -> a.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
        
        // 4. Si la encontramos, abrimos su ficha
        if (actSeleccionada != null) {
            
            if (actSeleccionada instanceof com.mycompany.javafit.ActividadEspecial) {
                // Es especial, abrimos la ventana VIP
                javafit.visual.VentanaActividadEspecial ficha = new javafit.visual.VentanaActividadEspecial((com.mycompany.javafit.ActividadEspecial) actSeleccionada);
                ficha.setVisible(true);
                ficha.setLocationRelativeTo(null);
                // Esto asegura que al cerrar la ficha NO se cierre todo el programa, solo esa ventanita
                ficha.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                
            } else {
                // Es normal, abrimos la ficha estándar
                javafit.visual.VentanaActividad ficha = new javafit.visual.VentanaActividad(actSeleccionada);
                ficha.setVisible(true);
                ficha.setLocationRelativeTo(null);
                ficha.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            }
        }
    }
    }//GEN-LAST:event_tablaActividadesMouseClicked
    /**
     * Maneja el evento de selección en el menú desplegable de tipos de actividad.
     *
     * @param evt Evento de acción disparado por el ComboBox.
     */
    private void campoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTipoActionPerformed
    /**
     * Maneja el evento de clic con el ratón en la tabla de reservas.
     * Abre la ventana de detalles de la actividad vinculada a la reserva seleccionada.
     *
     * @param evt Evento de ratón disparado por la tabla.
     */
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        // 1. Obtenemos la fila en la que el usuario ha hecho clic
    // (Asegúrate de cambiar 'jTable2' por el nombre de tu tabla de reservas si es distinto)
    int filaSeleccionada = jTable2.getSelectedRow();

    if (filaSeleccionada >= 0) {
        
        // 2. Sacamos el título de la actividad (recordemos que en esta tabla está en la columna 1)
        String titulo = jTable2.getValueAt(filaSeleccionada, 1).toString();

        // 3. Buscamos la actividad completa en el sistema
        com.mycompany.javafit.Actividad actSeleccionada = null;
        java.util.ArrayList<com.mycompany.javafit.Actividad> lista = com.mycompany.javafit.Gimnasio.getInstancia().getActividades();
        
        for (com.mycompany.javafit.Actividad act : lista) {
            if (act.getTitulo().equals(titulo)) {
                actSeleccionada = act;
                break;
            }
        }

        // 4. Si la encontramos, abrimos la ventana de detalles
        if (actSeleccionada != null) {
            
            // Comprobamos si es una actividad especial o normal para abrir la ventana correcta
            if (actSeleccionada instanceof com.mycompany.javafit.ActividadEspecial) {
                // Como son vecinas en la misma carpeta, la llamamos por su nombre directamente:
                VentanaActividadEspecial ventana = new VentanaActividadEspecial((com.mycompany.javafit.ActividadEspecial) actSeleccionada);
                ventana.setVisible(true);
                ventana.setLocationRelativeTo(null); // Para que salga centrada en la pantalla
            } else {
                VentanaActividad ventana = new VentanaActividad(actSeleccionada);
                ventana.setVisible(true);
                ventana.setLocationRelativeTo(null); // Para que salga centrada
            }
        }
    }
    }//GEN-LAST:event_jTable2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
        logger.log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> new VentanaGestionSocio().setVisible(true));
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BusquedaYReserva;
    private javax.swing.JPanel MiPerfil;
    private javax.swing.JPanel MisReservas;
    private javax.swing.JButton botonGuardarCambios;
    private javax.swing.JButton botonReservar;
    private javax.swing.JPasswordField campoClave;
    private javax.swing.JTextField campoCorreo;
    private javax.swing.JComboBox<String> campoDia;
    private javax.swing.JTextField campoDireccion;
    private javax.swing.JTextField campoEstado;
    private javax.swing.JTextField campoMonitor;
    private javax.swing.JTextField campoMonitor1;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoTarjeta;
    private javax.swing.JTextField campoTelefono;
    private javax.swing.JComboBox<String> campoTipo;
    private javax.swing.JButton cancelarReserva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDia;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tablaActividades;
    // End of variables declaration//GEN-END:variables
}
