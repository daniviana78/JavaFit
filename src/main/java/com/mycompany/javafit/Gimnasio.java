/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafit;

/**
 *
 * @author Dani
 */

import java.io.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * Clase principal de lógica de negocio del sistema JavaFit que implementa el patrón Singleton.
 * Centraliza las estructuras de datos globales para la persistencia, la gestión 
 * de usuarios, el control del catálogo de actividades y la administración de reservas.
 * * @author Daniel Viana y Adrián Fernández
 * @version 1.0
 */
public class Gimnasio implements Serializable {
    
    /** Instancia única de la clase Gimnasio (Patrón Singleton). */
    private static Gimnasio instancia= null;
    /** Lista que almacena el listado de todos los socios registrados. */
    private ArrayList<Socio> socios= new ArrayList();
    /** Lista que almacena el listado de todos los administradores. */
    private ArrayList<Administrador> administradores= new ArrayList();
    /** Lista que contiene el catálogo completo de actividades del gimnasio. */
    private ArrayList<Actividad> actividades= new ArrayList();
    /** Estructura de datos dinámica que contiene el censo de las salas disponibles. */
    private ArrayList<Sala> salas= new ArrayList();
    /** Lista que centraliza el histórico y control de reservas activas. */
    private ArrayList<Reserva> reservas= new ArrayList();
    /** Almacena la referencia del usuario autenticado actualmente en la sesión de la plataforma. */
    private Usuario usuarioLogeado;
    
    /**
     * Constructor predeterminado de la clase.
     * Registra automáticamente las credenciales del administrador por defecto.
     */
    public Gimnasio() {
        Administrador adminPorDefecto = new Administrador("admin@javafit.com", "admin");
        this.administradores.add(adminPorDefecto);
    }
    
    /**
     * Obtiene o inicializa la instancia única del sistema de gestión del gimnasio.
     * @return La instancia única global de la clase Gimnasio.
     */
    public static Gimnasio getInstancia() {
        if (instancia == null) {
            instancia = cargarDatos();
        }
            return instancia;
    }

    /**
     * Obtiene la estructura de datos que almacena a los socios.
     * @return Un ArrayList con los socios del sistema.
     */
    public ArrayList<Socio> getSocios() {
        return socios;
    }

    /**
     * Establece la estructura de datos que almacena a los socios.
     * @param socios El nuevo ArrayList de socios.
     */
    public void setSocios(ArrayList<Socio> socios) {
        this.socios = socios;
    }
    
    /**
     * Obtiene la estructura de datos que almacena a los administradores.
     * @return Un ArrayList con los administradores cargados.
     */
    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    /**
     * Establece la estructura de datos que almacena a los administradores.
     * @param administradores El nuevo ArrayList de administradores.
     */
    public void setAdministradores(ArrayList<Administrador> administradores) {
        this.administradores = administradores;
    }
    
    /**
     * Obtiene el catálogo de actividades deportivas.
     * @return Un ArrayList con las actividades registradas.
     */
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece el catálogo de actividades deportivas.
     * @param actividades El nuevo ArrayList de actividades.
     */
    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }
    
    /**
     * Obtiene el listado de salas físicas del gimnasio.
     * @return Un ArrayList con las salas existentes.
     */
    public ArrayList<Sala> getSalas() {
        return salas;
    }

    /**
     * Establece el listado de salas del gimnasio.
     * @param salas El nuevo ArrayList de salas.
     */
    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }
    
    /**
     * Obtiene la estructura de datos que gestiona todas las reservas.
     * @return Un ArrayList con las reservas generales.
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Establece la estructura de datos para la gestión de reservas.
     * @param reservas El nuevo ArrayList de reservas.
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    /**
     * Obtiene el usuario que tiene la sesión activa.
     * @return El objeto Usuario actualmente autenticado.
     */
    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    /**
     * Establece el usuario que tiene la sesión activa.
     * @param usuarioLogeado El objeto Usuario que inicia sesión.
     */
    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
    
    /**
     * Registra una nueva actividad estándar en el catálogo si no existe previamente.
     * @param titulo El título identificativo de la actividad.
     * @param tipo El tipo o categoría de la clase dirigida.
     * @param sala La sala física donde se va a realizar.
     * @param horarios El listado de turnos programados en formato ArrayList.
     * @param monitor El instructor encargado.
     * @param imagen El elemento visual representativo.
     * @return true si la actividad se añadió correctamente; false si ya existía.
     */
    public boolean crearActividad(String titulo, String tipo, Sala sala, ArrayList horarios, String monitor, ImageIcon imagen){
        
        Actividad nuevaActividad = new Actividad(titulo, tipo, sala, horarios, monitor, imagen);
        
        if(!actividades.contains(nuevaActividad)){
            
            actividades.add(nuevaActividad);
            this.guardarDatos();
            return true;
        }
        else{
            return false;
        }

    }
    
    
    /**
     * Registra una nueva actividad de carácter especial con coste adicional en el catálogo.
     * @param titulo El título identificativo de la actividad.
     * @param tipo El tipo o categoría de la sesión.
     * @param sala La sala física donde se va a desarrollar.
     * @param horarios El listado de turnos programados en formato ArrayList.
     * @param monitor El instructor encargado.
     * @param imagen El elemento visual descriptivo.
     * @param precio El importe económico base fijado para la sesión.
     * @param descripcion Detalle o explicaciones sobre el contenido especial de la clase.
     * @return true si la actividad especial se registró con éxito; false si estaba duplicada.
     */
    public boolean crearActividad(String titulo, String tipo, Sala sala, ArrayList horarios, String monitor, ImageIcon imagen, double precio, String descripcion){
        
        Actividad nuevaActividadEspecial = new ActividadEspecial(titulo, tipo, sala, horarios, monitor, imagen, precio, descripcion);
        
        if(!actividades.contains(nuevaActividadEspecial)){
            
            actividades.add(nuevaActividadEspecial);
            this.guardarDatos();
            return true;
        }
        else{
            return false;
        }

    }
    
    /**
     * Elimina una actividad del catálogo del sistema localizándola por su título.
     * @param titulo El título de la actividad a dar de baja.
     * @return true si se localizó y eliminó la actividad; false en caso contrario.
     */
    public boolean borrarActividad(String titulo){
                
        for(int i=0;i<actividades.size();i++){
            Actividad act= actividades.get(i);
            String pruebaTitulo= act.getTitulo();
            
            if (titulo.equals(pruebaTitulo)){
                actividades.remove(act);
                guardarDatos();
                return true;
            }

        }
        return false;
    }
    
    /**
     * Modifica los atributos de una actividad existente en el catálogo, controlando si es especial.
     * @param act La instancia original de la actividad a modificar.
     * @param nuevoTitulo El nuevo título a establecer.
     * @param nuevoTipo La nueva categoría de la actividad.
     * @param nuevaSala La nueva sala física del gimnasio.
     * @param nuevoMonitor El nombre del nuevo monitor responsable.
     * @param nuevaImagen El nuevo archivo gráfico asociado.
     * @param nuevoPrecio El coste actualizado (utilizado si es actividad especial).
     * @param nuevaDesc La descripción detallada nueva (utilizada si es actividad especial).
     * @return true si la modificación se completó con éxito; false si el objeto es nulo o inválido.
     */
    public boolean modificarActividad(Actividad act, String nuevoTitulo, String nuevoTipo, Sala nuevaSala, String nuevoMonitor, ImageIcon nuevaImagen, double nuevoPrecio, String nuevaDesc) {
    
        if (act == null || !actividades.contains(act)) {
            return false;
        }
                act.setTitulo(nuevoTitulo);
                act.setTipo(nuevoTipo);
                act.setSala(nuevaSala);
                act.setMonitor(nuevoMonitor);
                act.setImagen(nuevaImagen);

                if (act instanceof ActividadEspecial) {
                    
                    ActividadEspecial esp = (ActividadEspecial) act;
                    esp.setPrecio(nuevoPrecio);
                    esp.setDescripcion(nuevaDesc);
                }

                guardarDatos(); 
                return true;
        }
    
    /**
     * Filtra la lista global de actividades combinando criterios mediante Streams de Java.
     * @param tipo Filtro por categoría de actividad (ignora si es null).
     * @param monitor Filtro por nombre del instructor (ignora si es null).
     * @param dia Filtro por el día programado del horario (ignora si es null).
     * @return Un nuevo ArrayList de actividades filtradas que cumplen todos los criterios.
     */
    public ArrayList<Actividad> buscarActividades(String tipo, String monitor, String dia) {
        
        return actividades.stream()
                .filter(a -> (tipo == null || a.getTipo().equalsIgnoreCase(tipo)))
                .filter(a -> (monitor == null || a.getMonitor().equalsIgnoreCase(monitor)))
                .filter(a -> dia == null || a.getHorarios().stream()
                        .filter(h -> h.getDia().equals(dia))
                        .count()>0)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    /**
     * Filtra la lista global de socios registrados basándose en el correo y/o la suscripción VIP.
     * @param correo Dirección de correo electrónico completa o parcial a buscar (ignora si es null).
     * @param vip Indicador booleano para filtrar por condición VIP (ignora si es null).
     * @return Un nuevo ArrayList de socios que se ajustan a las especificaciones dadas.
     */
    public ArrayList<Socio> buscarSocios(String correo, Boolean vip) {
        return socios.stream()
                .filter(s -> correo == null || s.getCorreo().toLowerCase().equals(correo.toLowerCase()))
                .filter(s -> vip == null || s.isSocioVIP() == vip)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    
    /**
     * Filtra e interconecta las reservas por socio y fecha, ordenándolas de forma cronológica ascendente.
     * @param socio Instancia del Socio a consultar (ignora si es null).
     * @param fecha Límite temporal inicial LocalDate para mostrar reservas (ignora si es null).
     * @return Un ArrayList de reservas ordenado por fecha de manera ascendente.
     */
    public ArrayList<Reserva> buscarReservas(Socio socio, LocalDate fecha) {
        Comparator<Reserva> fechaComp = new Comparator<Reserva>() {
            @Override
            public int compare(Reserva r1, Reserva r2) {
                return r1.getFechaReserva().compareTo(r2.getFechaReserva());
            }
        };

        return reservas.stream()
                .filter(r -> socio == null || r.getCliente().equals(socio))
                .filter(r -> fecha == null || !r.getFechaReserva().isBefore(fecha))
                .sorted(fechaComp)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    
    /**
     * Añade un nuevo socio a la estructura de datos siempre que el correo electrónico no esté en uso.
     * @param s El objeto Socio a registrar.
     * @return true si se completó el registro con éxito; false si el correo electrónico ya existía.
     */
    public boolean registrarSocio(Socio s){
        if(buscarSocios(s.getCorreo(),null).isEmpty()){
            socios.add(s);
            guardarDatos();
            return true;  
        }
        else{
            return false;
        }
    }
    
    /**
     * Autentica el acceso al sistema validando las credenciales de socios y administradores.
     * @param correo Correo electrónico introducido por el usuario.
     * @param clave Contraseña introducida por el usuario.
     * @return El objeto de la clase Usuario (Socio o Administrador) si coincide; null en caso de error.
     */
    public Usuario login(String correo, String clave){
        Usuario user= socios.stream()
                .filter(s -> s.getCorreo().equalsIgnoreCase(correo) && s.getClave().equals(clave))
                .findFirst()
                .orElse(null);
       if (user==null){
            user = administradores.stream()
                .filter(a -> a.getCorreo().equalsIgnoreCase(correo) && a.getClave().equals(clave))
                .findFirst()
                .orElse(null);      
       }
       
       this.setUsuarioLogeado(user);
       
       return user;
                
    }
    
    /**
     * Procesa la solicitud de reserva para una sesión deportiva controlando los límites de aforo.
     * @param act La actividad objeto de la reserva.
     * @param cliente El socio que solicita la plaza.
     * @param horario El horario específico en el que se asistirá.
     * @return true si la reserva se consolidó con éxito; false si se superó el aforo permitido.
     */
    public boolean reservar(Actividad act, Socio cliente, Horario horario){
        
        LocalDate fechaReserva= LocalDate.now();
        
        long reservasActuales= reservas.stream()
                .filter(r -> r.getActividad().equals(act) && r.getHorario().equals(horario))
                .count();
        
        if (reservasActuales < act.getSala().getAforo()) {
            Reserva nuevaReserva = new Reserva(act, cliente, horario, fechaReserva);
            nuevaReserva.setImporte(calcularImporte(cliente, act));
            reservas.add(nuevaReserva);
            cliente.getReservas().add(nuevaReserva);
            guardarDatos();
                        
            return true;
        }
        
        return false;
        
    }
    
    /**
     * Calcula el coste final de una reserva aplicando un descuento de fidelización a los socios VIP.
     * @param s El socio que realiza la reserva.
     * @param act La actividad objeto del cálculo.
     * @return El importe final numérico tras evaluar todas las condiciones aplicables.
     */
    public double calcularImporte(Socio s, Actividad act){
        
        double importe=0;
        if (act instanceof ActividadEspecial){
            importe= ((ActividadEspecial) act).getPrecio();
        }
        
        if (s.isSocioVIP()==true){
            importe= importe * 0.9;
        }
        
        return importe;
    }
    
    /**
     * Elimina de forma definitiva una reserva activa del listado del gimnasio.
     * @param r El objeto Reserva concreto a cancelar.
     * @return true si la reserva fue localizada y dada de baja; false si no existía.
     */
    public boolean cancelarReserva(Reserva r){
        for(int i=0; i<reservas.size();i++){
            if (reservas.get(i).equals(r)){
                reservas.remove(i);
                guardarDatos();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Actualiza y sincroniza los campos de información personal de un socio existente en la lista.
     * @param s El socio original sobre el cual aplicar los cambios.
     * @param nuevoCorreo El nuevo correo electrónico.
     * @param nuevaClave La nueva contraseña.
     * @param nuevoNombre El nombre completo actualizado.
     * @param nuevoTelefono El número telefónico modificado.
     * @param nuevaDireccion El domicilio postal actualizado.
     * @param nuevaTarjetaCredito El número de tarjeta financiera modificado.
     * @param nuevoSocioVIP El nuevo estado de suscripción VIP (true o false).
     * @return true si los cambios se grabaron con éxito; false si el objeto es nulo o no está registrado.
     */
    public boolean modificarSocio(Socio s, String nuevoCorreo, String nuevaClave, String nuevoNombre, String nuevoTelefono, String nuevaDireccion, String nuevaTarjetaCredito, boolean nuevoSocioVIP){
        if (s == null || !socios.contains(s)) {
            return false;
        }
        
        s.setCorreo(nuevoCorreo);
        s.setClave(nuevaClave);
        s.setNombre(nuevoNombre);
        s.setTelefono(nuevoTelefono);
        s.setDireccion(nuevaDireccion);
        s.setTarjetaCredito(nuevaTarjetaCredito);
        s.setSocioVIP(nuevoSocioVIP);
        
        guardarDatos(); 
        return true;
        
    }
    
    /**
     * Genera un archivo de texto físico estructurado en disco con los datos desglosados del cobro.
     * @param r La reserva seleccionada para emitir el comprobante.
     * @throws IOException Si ocurre un fallo crítico durante la apertura o escritura del fichero de texto.
     */
    public void generaFactura(Reserva r) throws IOException {
        DateTimeFormatter formatoCorto = DateTimeFormatter.ofPattern("dd/MM/yyyy");        
        String fn = r.getFechaReserva().format(formatoCorto);
        String nombreSocio = r.getCliente().getNombre().trim().replace(" ", "_");;
        String tituloActividad = r.getActividad().getTitulo().trim().replace(" ", "_");;
        String diaActividad  = r.getHorario().getDia().trim().replace(" ", "_");;
        String turnoActividad  = r.getHorario().getTurno().trim().replace(" ", "_").replace(":", "-");
        String rutaFicheroFactura = "./Facturas/Factura(" + nombreSocio + '_' + tituloActividad + '_' + diaActividad + '_' + turnoActividad + '_' + fn.replace('/', '_') + ").txt";
        double importe = r.getImporte();
        try {
            File dirFacturas = new File("./Facturas");

            if (!dirFacturas.exists()) {
                dirFacturas.mkdir();
            }

            FileWriter fw = new FileWriter(rutaFicheroFactura);
            try (PrintWriter salida = new PrintWriter(new BufferedWriter(fw))) {
                salida.println("-------------------------------- Factura de la Reserva --------------------------------");
                salida.println("\n");
                salida.println("Actividad: " + r.getActividad());
                salida.println("\n");
                salida.println("Cliente: " + r.getCliente());
                salida.println("\n");
                salida.println("Horario: " + r.getHorario());
                salida.println("\n");
                salida.println("Fecha de realización de la reserva: " + r.getFechaReserva());
                salida.println("---------------------------------------------------------------------------------");
                salida.println("IMPORTE: " + importe);
                salida.println("\n");
                salida.println("-------------------------------------------------------------------------------");
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        }
    }
    
    
    /**
     * Serializa en disco el estado actual completo de la instancia del objeto Gimnasio en un archivo binario.
     */
    public void guardarDatos() {
        
        try (FileOutputStream fos = new FileOutputStream("datos.dat"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            System.out.println("Datos guardados con éxito.");

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    /**
     * Recupera el estado guardado del gimnasio mediante la deserialización del archivo binario de datos.
     * @return El objeto Gimnasio reconstruido con su información persistida, o una nueva instancia vacía ante errores.
     */
    public static Gimnasio cargarDatos() {
        
        File archivo = new File("datos.dat");

        if (!archivo.exists()) {
            System.out.println("No se encontró archivo de datos. Creando nuevo sistema...");
            return new Gimnasio(); 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Gimnasio) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar datos, creando sistema nuevo: " + e.getMessage());
            return new Gimnasio();
        }
    }
    

    /**
     * Devuelve una cadena de texto que detalla las colecciones y el estado general interno del gimnasio.
     * @return Cadena formateada con los listados de datos de la aplicación.
     */
    @Override
    public String toString() {
        return "Gimnasio{" + "socios=" + socios + ", administradores=" + administradores + ", actividades=" + actividades + ", salas=" + salas + ", reservas=" + reservas + ", usuarioLogeado=" + usuarioLogeado + '}';
    }
    
    
    
}