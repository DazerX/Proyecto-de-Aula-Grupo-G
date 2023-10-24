package Vista;

import java.time.LocalDate;
import Entidades.*;
import Logica.*;
import java.util.ArrayList;

public class Vista {

    private LogicaFactura logicaFactura;
    private LogicaServicio logicaServicio;
    private LogicaEmpleado logicaTecnico;

    public Vista() {
        this.logicaFactura = new LogicaFactura();
        this.logicaServicio = new LogicaServicio();
        this.logicaTecnico = new LogicaEmpleado();
    }
    public void IniciarSesion(){
        char op='S';
        Login iniciarSesion= new Login("1234");
        while(op=='S'){
        String contraseña = Entrada.leerString("Ingrese la contraseña:  ");
        if(iniciarSesion.validarContraseña(contraseña)){
            this.ejecutarMenu();
            break;
        }else{
            do{
                op=Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N': ");
            }while(op!='S' && op!='N');
            if(op=='N'){
                System.out.println("Aplicacion finalizada");
            }
        }
        }
    }
    public void Menu() {
        System.out.println(" \n ");
        System.out.println("----------Software Taller JJAD------------");
        System.out.println("1. Factura");
        System.out.println("");
        System.out.println("2. Tecnico");
        System.out.println("");
        System.out.println("3. Servicio");
        System.out.println("");
        System.out.println("4. Pagar Nomina al empleado");
        System.out.println("");
        System.out.println("5. Salir");
    }

    public void ejecutarMenu() {
        char Op = 'S';
        while (Op == 'S') {
            this.Menu();
            int op = Entrada.leerInt("Seleccione una opcion: ");
            switch (op) {
                case 1:
                    this.ejecutarMenuFactura();
                    break;
                case 2:
                    this.ejecutarMenuTecnico();
                    break;
                case 3:
                    this.ejecutarMenuServicio();
                    break;
                case 4:
                    this.Nomina();
                    break;
                case 5:
                    System.out.println("Aplicacion finalizada");
                    Op = 'N';
            }
        }
    }

    //Todo lo relacionado con factura
    public void MenuFactura() {
        System.out.println(" \n ");
        System.out.println("----------Factura-------------");
        System.out.println("1. Crear");
        System.out.println("");
        System.out.println("2. Consultar");
        System.out.println("");
        System.out.println("3. Modificar");
        System.out.println("");
        System.out.println("4. Eliminar");
        System.out.println("");
        System.out.println("5. Lista de Facturas");
        System.out.println("");
        System.out.println("6. Salir");
    }

    public void ejecutarMenuFactura() {
        if (this.logicaServicio.consultarServicios().size()==0 && this.logicaFactura.consultarFacturas().size()==0) {
            System.out.println("****Error...NO hay tecnicos ni servicios registrados****");
        } else {
            char Op = 'S';
            
                while (Op == 'S') {
                    this.MenuFactura();
                    int op = Entrada.leerInt("Seleccione una opcion: ");
                    switch (op) {
                        case 1:
                            this.CrearFactura();
                            break;
                        case 2:
                            this.ConsultarFactura();
                            break;

                        case 3:
                            this.ModificarFactura();
                            break;

                        case 4:
                            this.EliminarFactura();
                            break;
                        case 5:
                            this.MostrarListaFacturas();
                            break;
                        case 6:
                            Op = 'N';
                    }
                }
            }
        }
    

    //Todo lo relacionado con Factura
    public void CrearFactura() {
        int Op = 'S';
        int tVeh;
        String tVehiculo = null;
        int tamRing;
        Factura facturaNueva = null;
        while (Op == 'S') {
            System.out.println("\n");
            System.out.println("------------Crear Factura------------------\n");
            String nombre = Entrada.leerLinea("Ingrese el nombre del Cliente: ");
            LocalDate fecha = LocalDate.now();//Fecha actual
            do {
                tVeh = Entrada.leerInt("Ingrese el tipo de Vehiculo\n"
                        + "1.Camioneta  \n"
                        + "2.Automovil \n"
                        + "Seleccione:  ");
            } while (tVeh != 1 && tVeh != 2);
            switch (tVeh) {
                case 1:
                    tVehiculo = "Camioneta";
                    break;
                case 2:
                    tVehiculo = "Automovil";
                    break;
            }
            String placa = Entrada.leerString("Ingrese la placa del vehiculo: ");
            System.out.println("");
            String marca = Entrada.leerString("Ingrese la marca del vehiculo: ");
            System.out.println("");
            String modelo = Entrada.leerString("Ingrese el modelo del vehiculo: ");
            System.out.println("");
            int noCilindro = Entrada.leerInt("Ingrese el numero de cilindros: ");
            System.out.println("");
            tamRing = Entrada.leerInt("Ingrese el tamaño del ring: ");
            System.out.println("");
            char opcion = Entrada.leerChar("Servicios realizados \n"
                    + "Un solo servicio->1 \n"
                    + "Varios servicios->otro*\n"
                    + "Seleccione: ");

            //SERVICIOS REALIZADOS
            if (opcion == 1) {
                char OP = 'S', Op2;
                Servicio servicioRealizado;
                while (OP == 'S') {
                    String codigo = Entrada.leerString("Ingrese el codigo del servicio realizado al vehiculo: ");
                    servicioRealizado = this.logicaServicio.buscarServicio(codigo);
                    if (servicioRealizado == null) {
                        System.out.println("No existen coincidencias");
                        do {
                            OP = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N' ");
                        } while (OP != 'S' && OP != 'N');
                    } else {
                        System.out.println(servicioRealizado.toString());
                        do {
                            Op2 = Entrada.leerChar("Desea añadirlo Si->'S' No->'N' ");
                        } while (Op2 != 'S' && Op2 != 'N');
                        if (Op2 == 'N') {
                            do {
                                OP = Entrada.leerChar("Desea volver a intentarlo con otro servicio Si->'S' No->'N': ");
                            } while (OP != 'S' && OP != 'N');
                        } else {

                            String cedula = Entrada.leerString("Ingrese la cedula del tecnico que realizo el servicio");
                            Tecnico tecnicoServicio = this.logicaTecnico.buscarTecnico(cedula);
                            
                            if (tecnicoServicio == null) {
                                System.out.println("No existe coincidencias ");
                                do {
                                    OP = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N' ");
                                } while (OP != 'S' && OP != 'N');
                            } else {
                                System.out.println(tecnicoServicio.toString());
                                String tipoTecnico = tecnicoServicio.getTipoTecnico();

                                //SI EL QUE REALIZO EL SERVICIO ES UN TECNICO MONOSERVICIO
                                if (tipoTecnico.equals("Tecnico monoServicio")) {
                                    TUnico tecnico = (TUnico) tecnicoServicio;
                                    if (tecnico.getOficio().getCodigo().equals(servicioRealizado.getCodigo())) {
                                        facturaNueva = new Factura(nombre, fecha, tVehiculo, placa, marca, modelo, noCilindro, tamRing, tecnico, servicioRealizado);
                                        String nombreServicio = servicioRealizado.getNombreServicio();

                                        //Sincronizacion
                                        if (nombreServicio.equals("Sincronizacion")) {
                                            Sincronizacion servRea = (Sincronizacion) servicioRealizado;
                                            servRea.calcularCosto(noCilindro);
                                            facturaNueva.calcularCostoUnicoServicio();
                                            this.logicaFactura.registrarFactura(facturaNueva);
                                            System.out.println("Factura Registrada con exito");
                                            facturaNueva.ImprimirFactura();
                                            OP = 'N';
                                        }

                                        //Alineacion
                                        if (nombreServicio.equals("Alineacion")) {
                                            Alineacion servRea = (Alineacion) servicioRealizado;
                                            servRea.calcularCosto(tVeh);
                                            facturaNueva.calcularCostoUnicoServicio();
                                            this.logicaFactura.registrarFactura(facturaNueva);
                                            System.out.println("Factura Registrada con exito");
                                            facturaNueva.ImprimirFactura();
                                            OP = 'N';
                                        }

                                        //Balanceo 
                                        if (nombreServicio.equals("Balanceo")) {
                                            Balanceo servRea = (Balanceo) servicioRealizado;
                                            servRea.calcularCosto(tVeh);
                                            facturaNueva.calcularCostoUnicoServicio();
                                            this.logicaFactura.registrarFactura(facturaNueva);
                                            System.out.println("Factura Registrada con exito");
                                            facturaNueva.ImprimirFactura();
                                            OP = 'N';
                                        }

                                        //Calibrar Valvulas
                                        if (nombreServicio.equals("Calibrar Valvulas")) {
                                            CalibrarValvulas servRea = (CalibrarValvulas) servicioRealizado;
                                            servRea.calcularCosto(noCilindro);
                                            facturaNueva.calcularCostoUnicoServicio();
                                            this.logicaFactura.registrarFactura(facturaNueva);
                                            System.out.println("Factura Registrada con exito");
                                            facturaNueva.ImprimirFactura();
                                            OP = 'N';
                                        }
                                    } else {
                                        System.out.println("Error el tecnico no realiza dicho servicio");
                                        do {
                                            OP = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N' ");
                                        } while (OP != 'S' && OP != 'N');
                                    }
                                } else {

                                    //SI EL QUE HIZO EL SERVICIO ES TECNICO MULTISERVICIOS
                                    boolean confirmar = false;

                                    TMultiservicio tecnico = (TMultiservicio) tecnicoServicio;
                                    for (Servicio s : tecnico.getOficios()) {
                                        s.equals(servicioRealizado);
                                        confirmar = true;
                                    }
                                    if (confirmar == true) {
                                        facturaNueva = new Factura(nombre, fecha, tVehiculo, placa, marca, modelo, noCilindro, tamRing, tecnico, servicioRealizado);
                                        String nombreServicio = servicioRealizado.getNombreServicio();

                                        if (nombreServicio.equals("Sincronizacion")) {
                                            Sincronizacion servRea = (Sincronizacion) servicioRealizado;
                                            servRea.calcularCosto(noCilindro);
                                            facturaNueva.calcularCostoUnicoServicio();
                                            this.logicaFactura.registrarFactura(facturaNueva);
                                            System.out.println("Factura Registrada con exito");
                                            facturaNueva.ImprimirFactura();
                                            OP = 'N';
                                        }

                                        //Alineacion
                                        if (nombreServicio.equals("Alineacion")) {
                                            Alineacion servRea = (Alineacion) servicioRealizado;
                                            servRea.calcularCosto(tVeh);
                                            facturaNueva.calcularCostoUnicoServicio();
                                            this.logicaFactura.registrarFactura(facturaNueva);
                                            System.out.println("Factura Registrada con exito");
                                            facturaNueva.ImprimirFactura();
                                            OP = 'N';
                                        }

                                        //Balanceo 
                                        if (nombreServicio.equals("Balanceo")) {
                                            Balanceo servRea = (Balanceo) servicioRealizado;
                                            servRea.calcularCosto(tVeh);
                                            facturaNueva.calcularCostoUnicoServicio();
                                            this.logicaFactura.registrarFactura(facturaNueva);
                                            System.out.println("Factura Registrada con exito");
                                            facturaNueva.ImprimirFactura();
                                            OP = 'N';
                                        }

                                        //Calibrar Valvulas
                                        if (nombreServicio.equals("Calibrar Valvulas")) {
                                            CalibrarValvulas servRea = (CalibrarValvulas) servicioRealizado;
                                            servRea.calcularCosto(noCilindro);
                                            facturaNueva.calcularCostoUnicoServicio();
                                            this.logicaFactura.registrarFactura(facturaNueva);
                                            System.out.println("Factura Registrada con exito");
                                            facturaNueva.ImprimirFactura();
                                            OP = 'N';
                                        }

                                    } else {
                                        do {
                                            OP = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N' ");
                                        } while (OP != 'S' && OP != 'N');

                                    }
                                }

                            }
                        }
                    }
                }

            } else {

                //MUCHOS SERVICIOS REALIZADOS
                char eleccion = 'S', op2 = 0;
                boolean encontro = false;
                boolean encontro2 = false;
                boolean encontro3 = false;
                Servicio servAplicado;
                ArrayList<Servicio> serviciosRealizados=null;
                ArrayList<Tecnico> tecnicosRegistrados=null;
                while (eleccion == 'S') {
                    String codigo = Entrada.leerString("Ingrese el codigo del servicio realizado al vehiculo: ");
                    servAplicado = this.logicaServicio.buscarServicio(codigo);
                    if (servAplicado == null) {
                        System.out.println("No existen coincidencias");
                        do {
                            eleccion = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N' ");
                        } while (eleccion != 'S' && eleccion != 'N');
                    } else {
                        System.out.println(servAplicado.toString());
                        do {
                            op2 = Entrada.leerChar("Desea añadirlo Si->'S' No->'N' ");
                        } while (op2 != 'S' && op2 != 'N');
                        if (op2 == 'S') {
                            if(serviciosRealizados!=null){
                            for (Servicio s : serviciosRealizados) {
                                if (s.getCodigo().equals(servAplicado.getCodigo())) {
                                    encontro3 = true;
                                }
                            }
                            }
                            if (encontro3 == true) {
                                System.out.println("Error... servicio ya registrado");
                            } else {
                                String cedula = Entrada.leerString("Ingrese la cedula del empleado que realizo el servicio: ");
                                Tecnico tecnico = this.logicaTecnico.buscarTecnico(cedula);
                                if (tecnico == null) {
                                    System.out.println("No existen coincidencias");
                                    do {
                                        eleccion = Entrada.leerChar("Desa volver a intentar Si->'S' No->'N': ");
                                    } while (eleccion != 'S' && eleccion != 'N');
                                } else {
                                    System.out.println(tecnico.toString());
                                    String tipoTecnico = tecnico.getTipoTecnico();
                                    //Tecnico Unico
                                    if (tipoTecnico.equals("Tecnico monoServicios")) {
                                        TUnico tecn = (TUnico) this.logicaTecnico.buscarTecnico(cedula);
                                        if (tecn.getOficio().getCodigo().equals(servAplicado.getCodigo())) {
                                            for (Tecnico t : tecnicosRegistrados) {
                                                if (t.getCedula().equals(tecn.getCedula())) {
                                                    encontro2 = true;
                                                }
                                            }
                                            if (encontro2 == true) {
                                                System.out.println("Error.... Ya el tecnico anteriormente habia   registrado");
                                            } else {
                                                tecnicosRegistrados.add(tecn);
                                                serviciosRealizados.add(servAplicado);
                                                System.out.println("Servicio registrado correctamente");
                                                if (serviciosRealizados.size() != 1) {
                                                    do {
                                                        eleccion = Entrada.leerChar("Desea registrar otro servicio: ");
                                                    } while (eleccion != 'S' && eleccion != 'N');
                                                }
                                            }
                                        } else {
                                            System.out.println("Error el tecnico ingresado no ejerce dicho servicio");
                                            do {
                                                eleccion = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N': ");
                                            } while (eleccion != 'S' && eleccion != 'N');
                                        }
                                        //TecnicoMultiservicio
                                    } else {
                                        boolean confirmar = false;

                                        TMultiservicio tecnic = (TMultiservicio) this.logicaTecnico.buscarTecnico(cedula);
                                        for (Servicio s : tecnic.getOficios()) {
                                            if( s.getCodigo().equals(servAplicado.getCodigo())){
                                            confirmar = true;
                                            }
                                        }
                                        if (confirmar == true) {
                                            System.out.println(tecnic.toString());

                                            tecnicosRegistrados.add(tecnic);
                                            serviciosRealizados.add(servAplicado);
                                            System.out.println("Servicio registrado correctamente");
                                            if (serviciosRealizados.size() != 1) {
                                                do {

                                                    eleccion = Entrada.leerChar("Desea registrar otro servicio: ");
                                                } while (eleccion != 'S' && eleccion != 'N');
                                            }

                                        } else {
                                            System.out.println("Error el tecnico ingresado no ejerce dicho servicio");
                                            do {
                                                eleccion = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N': ");
                                            } while (eleccion != 'S' && eleccion != 'N');
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
                if (serviciosRealizados != null) {
                    Factura fMultipleServicio = new Factura(nombre, fecha, tVehiculo, placa, marca, modelo, noCilindro, tamRing, tecnicosRegistrados, serviciosRealizados);
                    this.logicaFactura.registrarFactura(fMultipleServicio);
                    for (Servicio s1 : serviciosRealizados) {
                        //Sincronizacion
                        if (s1.equals("Sincronizacion")) {
                            Sincronizacion servRea = (Sincronizacion) s1;
                            servRea.calcularCosto(noCilindro);
                            fMultipleServicio.calcularCostoMultiServicios();
                            this.logicaFactura.registrarFactura(facturaNueva);
                            System.out.println("Factura Registrada con exito");
                            fMultipleServicio.ImprimirFactura();

                        }

                        //Alineacion
                        if (s1.equals("Alineacion")) {
                            Alineacion servRea = (Alineacion) s1;
                            servRea.calcularCosto(tVeh);
                            fMultipleServicio.calcularCostoMultiServicios();
                            this.logicaFactura.registrarFactura(facturaNueva);
                            System.out.println("Factura Registrada con exito");
                            fMultipleServicio.ImprimirFactura();
                        }

                        //Balanceo 
                        if (s1.equals("Balanceo")) {
                            Balanceo servRea = (Balanceo) s1;
                            servRea.calcularCosto(tVeh);
                            fMultipleServicio.calcularCostoMultiServicios();
                            this.logicaFactura.registrarFactura(facturaNueva);
                            System.out.println("Factura Registrada con exito");
                            fMultipleServicio.ImprimirFactura();

                        }

                        //Calibrar Valvulas
                        if (s1.equals("Calibrar Valvulas")) {
                            CalibrarValvulas servRea = (CalibrarValvulas) s1;
                            servRea.calcularCosto(noCilindro);
                            fMultipleServicio.calcularCostoMultiServicios();
                            this.logicaFactura.registrarFactura(facturaNueva);
                            System.out.println("Factura Registrada con exito");
                            fMultipleServicio.ImprimirFactura();
                        }
                    }
                    System.out.println("Factura registrada");
                    do {
                        Op = Entrada.leerChar("Desea registrar otra Factura Si->'S'   No->'N' ");
                    } while (Op != 'S' && Op != 'N');
                }
            }
        }
    }

    /* 
    Falta Colocar en los servicios si es balanceo, Sincronizacion,Alineacion, Calibrar Valvulas enviarle los argumentos para que hagan el precio
     */
    @SuppressWarnings("empty-statement")
    public void ConsultarFactura() {
        char OP = 'S';
        Factura facturaBuscada = null;
        if (this.logicaFactura.consultarFacturas().size() == 0) {
            System.out.println("***Error...No existen facturas creadas***");
        } else {
            while (OP == 'S') {
                int consecutivo = Entrada.leerInt("Ingrese el consecutivo de la factura");
                facturaBuscada = this.logicaFactura.buscarFactura(consecutivo);
                if (facturaBuscada == null) {
                    System.out.println("No existen coincidencias");
                    do {
                        OP = Entrada.leerChar("Desea hacer otra consulta Si->'S' No->'N' ");
                    } while (OP != 'S' && OP != 'N');
                } else {
                    facturaBuscada.ImprimirFactura();
                    OP = Entrada.leerChar("Desea hacer otra consulta Si->'S' No->'N' ");
                }
                while (OP != 'S' && OP != 'N');
            }
        }
    }

    public void ModificarFactura() {
        char OP = 'S', tVehiculo, op;
        if (this.logicaFactura.consultarFacturas().size() == 0) {
            System.out.println("***Error...No existen facturas creadas***");
        } else {
            while (OP == 'S') {
                System.out.println("----------Modificar Factura-------------");
                int consecutivo = Entrada.leerInt("Ingrese el consecutivo de la factura");
                Factura facturaBuscada = this.logicaFactura.buscarFactura(consecutivo);
                if (facturaBuscada == null) {
                    System.out.println("***No existen coincidencias***");
                } else {
                    System.out.println(facturaBuscada.toString());
                    do {
                        op = Entrada.leerChar("Que desea modificar "
                                + "1. Nombre del cliente\n"
                                + "2. Marca y modelo \n"
                                + "Seleccione:  "
                        );
                    } while (op != '1' && op != '2');
                    switch (op) {
                        case 1:
                            String nombre = Entrada.leerString("Ingrese el nombre");
                            facturaBuscada.setNomCliente(nombre);
                            System.out.println("Datos modificados correctamente");
                            break;
                        case 2:
                            Vehiculo vehiculo = facturaBuscada.getAuto();
                            String marca = Entrada.leerLinea("Ingrese la marca del vehiculo: ");
                            vehiculo.setMarca(marca);
                            String modelo = Entrada.leerLinea("Ingrese el modelo del vehiculo: ");
                            vehiculo.setModelo(modelo);
                            System.out.println("Datos modificados correctamente");

                    }
                }
            }
        }
    }

    public void MostrarListaFacturas() {
        System.out.println("----------Listas de Facturas Registradas----------------");
        if (this.logicaFactura.consultarFacturas().size() == 0) {
            System.out.println("***Error...No existen facturas creadas***");
        } else {
            ArrayList<Factura> listaFactura = this.logicaFactura.consultarFacturas();
            if (listaFactura == null) {
                System.out.println("No existen servicios registrados");
            } else {
                for (Factura f : listaFactura) {
                    System.out.println(f.toString());
                    System.out.println("\n");
                }
            }

        }
    }

    public void EliminarFactura() {
        if (this.logicaFactura.consultarFacturas().size() == 0) {
            System.out.println("***Error...No existen facturas creadas***");
        } else {
            char OP = 'S';
            Factura facturaEliminar;

            while (OP == 'S') {
                int consecutivo = Entrada.leerInt("Ingrese el consecutivo de la placa a eliminar: ");
                facturaEliminar = this.logicaFactura.buscarFactura(consecutivo);
                if (facturaEliminar == null) {
                    System.out.println("No existe coincidencias");
                    do {
                        OP = Entrada.leerChar("Quiere volver a intentar Si->'S' No->'N': ");
                    } while (OP != 'S' && OP != 'N');
                } else {
                    System.out.println(facturaEliminar.toString());
                    do {
                        OP = Entrada.leerChar("Esta seguro de eliminar la factura Si->'S' No->'N': ");
                    } while (OP != 'S' && OP != 'N');
                    if (OP == 'S') {
                        this.logicaFactura.eliminarFactura(consecutivo);
                    }
                }
            }
        }
    }

//Todo lo Relacionado con Tecnico
    public void MenuTecnico() {
        System.out.println(" \n ");
        System.out.println("----------Empleados-------------");
        System.out.println("1. Añadir nuevo empleado");
        System.out.println("");
        System.out.println("2. Consultar ");
        System.out.println("");
        System.out.println("3. Modificar ");
        System.out.println("");
        System.out.println("4. Despedir");
        System.out.println("");
        System.out.println("5. Lista de empleados");
        System.out.println("");
        System.out.println("6. Salir");
    }

    public void ejecutarMenuTecnico() {
        if (this.logicaServicio.consultarServicios().size() == 0) {
            System.out.println("***Error debe por lo menos añadir un servicio***");
        } else {
            char Op = 'S';
            while (Op == 'S') {
                this.MenuTecnico();
                int op = Entrada.leerInt("Seleccione una opcion");
                switch (op) {
                    case 1:
                        this.CrearTecnico();
                        break;
                    case 2:
                        this.ConsultarEmpleado();
                        break;
                    case 3:
                        this.ModificarEmpleado();
                        break;
                    case 4:
                        this.DespedirEmpleado();
                        break;
                    case 5:
                        this.ListaEmpleados();
                        break;
                    case 6:
                        Op = 'N';
                }
            }
        }
    }

    public void CrearTecnico() {
        int OP = 'S';
        int tipoTec;
        Tecnico nuevo=null;
        while (OP == 'S') {
            System.out.println("----------Contratar Empleado-------------");
            String nombre = Entrada.leerLinea("Ingrese el nombre: ");
            String apellido = Entrada.leerLinea("Ingrese los apellidos: ");
            String cedula = Entrada.leerString("Ingrese la cedula: ");
            LocalDate fechaIngreso = Entrada.leerFecha("Ingrese la fecha de ingreso del empleado");
            String detalle = Entrada.leerLinea("Ingrese especialidad: ");
            //do{
            tipoTec = Entrada.leerInt("El empleado es multiservicios ->1  unico ->2: ");
            //}while (tipoTec != 1 || tipoTec != 2);
            if (tipoTec == 1) {
                String tipoTecnico = "Tecnico multiServicio";
                TMultiservicio empleadoNuevo = new TMultiservicio(tipoTecnico, nombre, apellido, cedula, fechaIngreso, detalle);
                char op = 'S';
                char elegir;
                while (op == 'S') {
                    String codigo = Entrada.leerString("Ingrese el codigo del servicio el cual ofrece el empleado: ");
                    Servicio servicio = this.logicaServicio.buscarServicio(codigo);
                    if (servicio == null) {
                        System.out.println("No existe coincidencias");
                        do {
                            op = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N': ");
                        } while (op != 'S' && op != 'N');
                    } else {
                        System.out.println(servicio.toString());

                        do {
                            elegir = Entrada.leerChar("Desea añadirlo Si->'S' No->'N': ");
                        } while (elegir != 'S' && elegir != 'N');
                        if (elegir == 'S') {
                            empleadoNuevo.addServicio(servicio);
                            do {
                                op = Entrada.leerChar("El empleado ofrece mas servicios Si->'S' No->'N': ");
                            } while (op != 'S' && op != 'N');
                            if(op=='N'){
                                System.out.println("Empleado registrado");
                                nuevo = empleadoNuevo;
                                nuevo.MostrarTecnico();
                                do {
                                    
                                OP = Entrada.leerChar("Desea contratar a otro empleado Si->'S' No->'N': ");
                            } while (OP != 'S' && OP != 'N');
                            }
                        } else {
                            do {
                                OP = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N': ");
                            } while (OP != 'S' && OP != 'N');

                        }

                    }

                }
                
                this.logicaTecnico.registrarEmpleado(nuevo);
                System.out.println("Tecnico Registrado con exito");
            } else {
                
                String tipoTecnico = "Tecnico monoServicio";
                Servicio servicio = null;
                char eleccion;
                char opp = 'S';
                while (opp == 'S') {
                    String codigo = Entrada.leerString("Ingrese el codigo del servicio el cual ofrece el empleado: ");
                    servicio = this.logicaServicio.buscarServicio(codigo);
                    if (servicio == null) {
                        System.out.println("No existe coincidencias");
                        do {
                            opp = Entrada.leerChar("Desea volver a intentarlo");
                        } while (opp != 'S' && opp != 'N');
                    } else {
                        System.out.println(servicio.toString());
                        do {
                            eleccion = Entrada.leerChar("Desea añadirlo Si-'S' No->'N': ");
                        } while (eleccion != 'S' && eleccion != 'N');
                        if (eleccion == 'S') {
                            Tecnico empleado = new TUnico(tipoTecnico, servicio, nombre, apellido, cedula, fechaIngreso, detalle);
                            this.logicaTecnico.registrarEmpleado(empleado);
                            System.out.println("**Tecnico registrado con exito**");
                            System.out.println("");
                            empleado.MostrarTecnico();
                            System.out.println("");
                            do {
                                OP = Entrada.leerChar("Desea contratar otro empleado Si->'S' No->'N': ");
                            } while (OP != 'S' && OP != 'N');
                             if(OP=='N' || OP=='S'){
                                 opp='N';
                             }
                        } else {
                            do {
                                OP = Entrada.leerChar("Desea volver a intentarlo Si->'S' No->'N': ");
                            } while (OP != 'S' && OP != 'N');
                        }
                    }

                }
            }

        }

    }

    public void ConsultarEmpleado() {
        if (this.logicaTecnico.consultarTecnico().size() == 0) {
            System.out.println("***Error...No existen tecnicos registrados***");
        } else {
            char OP = 'S';
            while (OP == 'S') {
                System.out.println("----------Consultar Empleado-------------");
                String cedula = Entrada.leerString("Ingrese la cedula del empleado: ");
                Tecnico tecEncontrado = this.logicaTecnico.buscarTecnico(cedula);
                if (tecEncontrado == null) {
                    System.out.println("No existen coincidencias");
                } else {
                    System.out.println("");
                  tecEncontrado.MostrarTecnico();
                }
                do {
                    OP = Entrada.leerChar("Desea consultar otro empleado Si->'S'  No-> 'N' ");
                } while (OP != 'S' && OP != 'N');
            }
        }
    }

    public void ModificarEmpleado() {
        char OP = 'S';
        if (this.logicaTecnico.consultarTecnico().size() == 0) {
            System.out.println("***Error...No existen tecnicos registrados***");
        } else {
            while (OP == 'S') {
                System.out.println("----------Modificar Empleado-------------");
                String cedula = Entrada.leerString("Ingrese la cedula del empleado: ");
                Tecnico tecEncontrado = this.logicaTecnico.buscarTecnico(cedula);
                if (tecEncontrado == null) {
                    System.out.println("No existen coincidencias");
                } else {
                    System.out.println(tecEncontrado.toString());
                    char op = Entrada.leerChar("Que desea modificar "
                            + "Nombre del empleado -> 1"
                            + "Apellido del empleado  ->2"
                            + "Cedula del empleado ->3"
                            + "Detalle-> 4"
                    );
                    switch (op) {
                        case 1:
                            String nombre = Entrada.leerLinea("Ingrese el nombre: ");
                            tecEncontrado.setNombre(nombre);
                            System.out.println("Cambios realizados correctamente\n");
                            break;
                        case 2:
                            String apellido = Entrada.leerLinea("Ingrese el apellido: ");
                            tecEncontrado.setApellido(apellido);
                            System.out.println("Cambios realizados correctamente\n");
                            break;
                        case 3:
                            String ced = Entrada.leerString("Ingrese la cedula: ");
                            tecEncontrado.setCedula(ced);
                            System.out.println("Cambios realizados correctamente\n");
                            break;
                        case 4:
                            String detalle = Entrada.leerLinea("Ingrese la especializacion: ");
                            tecEncontrado.setDetalle(detalle);
                            System.out.println("Cambios realizados correctamente\n");
                            break;
                    }
                }
            }
        }
    }

    public void DespedirEmpleado() {
        char op, OP = 'S';
        if (this.logicaTecnico.consultarTecnico().size() == 0) {
            System.out.println("***Error...No existen tecnicos registrados***");
        } else {
            while (OP == 'S') {
                System.out.println("----------Despedir Empleado-------------");
                String cedula = Entrada.leerString("Ingrese la cedula del empleado: ");
                Tecnico tecEncontrado = this.logicaTecnico.buscarTecnico(cedula);
                if (tecEncontrado == null) {
                    System.out.println("No existen coincidencias");
                    do {
                        OP = Entrada.leerChar("Desea volver a intentarlo Si->'S'  No->'N': ");
                    } while (OP != 'S' && OP != 'N');
                } else {
                    tecEncontrado.MostrarTecnico();
                    do {
                        op = Entrada.leerChar("Esta seguro de despedirlo Si->'S'  No->'N': ");
                    } while (op != 'S' && op != 'N');
                    if (op == 'S') {
                        this.logicaTecnico.eliminarTecnico(cedula);
                        System.out.println("Empleado despedido correctamente\n ");
                        do {
                            OP = Entrada.leerChar("Desea despedir otro empleado Si->'S'  No->'N': ");
                        } while (OP != 'S' && OP != 'N');

                    } else {
                        do {
                            OP = Entrada.leerChar("Desea volver a intentarlo Si->'S'  No->'N': ");
                        } while (OP != 'S' && OP != 'N');
                    }
                }

            }
        }
    }

    public void ListaEmpleados() {
        System.out.println("----------Lista de Empleados----------------");

        ArrayList<Tecnico> listaTecnico = this.logicaTecnico.consultarTecnico();
        if (listaTecnico.size() == 0) {
            System.out.println("No existen empleados registrados");
        } else {
            for (Tecnico t : listaTecnico) {
                t.MostrarTecnico();
                System.out.println("\n");
            }
        }
    }

    //Todo lo relacionado con servicio 
    /*
    Numero de servicios 4 que no se repiten de la misma clase 
     */
    public void MenuServicio() {
        System.out.println(" \n ");
        System.out.println("----------Servicios-------------");
        System.out.println("1. Crear");
        System.out.println("");
        System.out.println("2. Consultar");
        System.out.println("");
        System.out.println("3. Modificar");
        System.out.println("");
        System.out.println("4. Eliminar");
        System.out.println("");
        System.out.println("5. Lista de Servicios");
        System.out.println("");
        System.out.println("6. Salir");
    }

    public void ejecutarMenuServicio() {
        char Op = 'S';
        while (Op == 'S') {
            this.MenuServicio();
            int op = Entrada.leerInt("Seleccione una opcion: ");
            switch (op) {
                case 1:
                    this.CrearServicio();
                    break;
                case 2:
                    this.ConsultarServicio();
                    break;
                case 3:
                    this.ModificarServicio();
                    break;
                case 4:
                    this.EliminarServicio();
                    break;
                case 5:
                    this.MostrarListaServicio();
                    break;
                case 6:
                    Op = 'N';
            }
        }
    }

    public void CrearServicio() {

        int op;
        char OP = 'S';
        String descripcion;
        double porcentaje;
        if (this.logicaServicio.consultarServicios().size() == 4) {
            System.out.println("***Error... Lista de servicios llena***");

        } else {

            while (OP == 'S') {

                System.out.println("----------Crear Servicio----------------");

                do {

                    op = Entrada.leerInt(
                            "1. Sincronizacion   \n"
                            + "2. Alineacion      \n"
                            + "3. Balanceo         \n"
                            + "4. Calibrar Valvulas\n"
                            + "Seleccione:  ");
                } while (op < 1 || op > 4);
                descripcion = Entrada.leerLinea("Descripcion del servicio: ");
                double porc = Entrada.leerDouble("Porcentaje de pago a trabajador % : ");
                porcentaje = porc / 100;
                ArrayList<Servicio> serviciosRegistrados =this.logicaServicio.consultarServicios();
                switch (op) {
                    case 1:
                        boolean encontro1 = false;
                        for (Servicio s :serviciosRegistrados) {
                            if (s.getNombreServicio().equals("Sincronizacion")) {
                                encontro1 = true;
                            }
                        }
                        if (encontro1 == true) {
                            System.out.println("Error... Servicio ya registrado");
                        } else {

                            //Sincronizacion
                            double precio1 = Entrada.leerDouble("Ingrese el precio por [3 y 4] cilindros: ");
                            System.out.println("");
                            double precio2 = Entrada.leerDouble("Ingrese el precio por [6]  cilindros : ");
                            System.out.println("");
                            double precio3 = Entrada.leerDouble("Ingrese el precio por [8] de cilindros : ");
                            Servicio sincronizacion = new Sincronizacion(descripcion, porcentaje, precio1, precio2, precio3);
                            this.logicaServicio.registrarServicio(sincronizacion);
                            System.out.println(sincronizacion.toString());

                        }

                        break;

                    case 2:
                        boolean encontro2 = false;
                        for (Servicio s : serviciosRegistrados) {
                            if (s.getNombreServicio().equals("Alineacion")) {
                                encontro1 = true;
                            }
                        }
                        if (encontro2 == true) {
                            System.out.println("Error... Servicio ya registrado");
                        } else {
                            //Alineacion
                            double precio1 = Entrada.leerDouble("Ingrese el precio si el vehiculo es camioneta: ");
                            System.out.println("");
                            double precio2 = Entrada.leerDouble("Ingrese el precio si el vehiculo es automovil: ");
                            System.out.println("");
                            Servicio alineacion = new Alineacion(descripcion, porcentaje, precio1, precio2);
                            this.logicaServicio.registrarServicio(alineacion);
                            System.out.println(alineacion.toString());

                        }
                        break;
                    case 3:
                        boolean encontro3 = false;
                        for (Servicio s : serviciosRegistrados) {
                            if (s.getNombreServicio().equals("Balanceo")) {
                                encontro3 = true;
                            }
                        }
                        if (encontro3 == true) {
                            System.out.println("Error... Servicio ya registrado");
                        } else {
                            //Balanceo
                            double precio1 = Entrada.leerDouble("Ingrese el precio por llanta de tamaño de ring [13] y [14] : ");
                            System.out.println("");
                            double precio2 = Entrada.leerDouble("Ingrese el precio por llanta de tamaño de ring entre [15-18] : ");
                            System.out.println("");
                            Servicio balanceo = new Balanceo(descripcion, porcentaje, precio1, precio2);
                            this.logicaServicio.registrarServicio(balanceo);
                            System.out.println(balanceo.toString());

                        }
                        break;
                    case 4:
                        boolean encontro4 = false;
                        for (Servicio s : serviciosRegistrados) {
                            if (s.getNombreServicio().equals("Calibrar Valvulas")) {
                                encontro4 = true;
                            }
                        }
                        if (encontro4 == true) {
                            System.out.println("Error.. Servicio ya registrado");
                        } else {
                            //Calibrar Valvulas
                            double precio1 = Entrada.leerDouble("Ingrese el precio por [3 y 4] cilindros: ");
                            System.out.println("");
                            double precio2 = Entrada.leerDouble("Ingrese el precio por [6]  cilindros : ");
                            System.out.println("");
                            Servicio calibrarValvulas = new CalibrarValvulas(descripcion, porcentaje, precio1, precio2);
                            this.logicaServicio.registrarServicio(calibrarValvulas);
                            System.out.println(calibrarValvulas.toString());

                        }
                        break;
                }
                if (this.logicaServicio.consultarServicios().size() != 4) {
                    do {
                        OP = Entrada.leerChar("Desea crear otro Servicio  Si->'S'  No->'N' ");
                    } while (OP != 'S' && OP != 'N');
                }
                if (this.logicaServicio.consultarServicios().size() == 4) {
                    OP = 'N';
                }
            }

        }
    }

    public void ConsultarServicio() {
        char OP = 'S';
        if (this.logicaServicio.consultarServicios().size() == 0) {
            System.out.println("***Error...No existen Servicios registrado***");
        } else {
            while (OP == 'S') {
                System.out.println("----------Consultar Servicio----------------");
                String codigo = Entrada.leerString("Ingrese el codigo del servicio a buscar");
                Servicio servicioBuscado = this.logicaServicio.buscarServicio(codigo);
                if (servicioBuscado == null) {
                    System.out.println("No existen coincidencias");
                } else {
                    System.out.println(servicioBuscado.toString());
                }
                do {
                    OP = Entrada.leerChar("Desea consultar otro servicio Si->'S'  No-> 'N': ");
                } while (OP != 'S' && OP != 'N');
            }
        }
    }

    public void ModificarServicio() {
        char OP = 'S';
        int op;
        if (this.logicaServicio.consultarServicios().size() == 0) {
            System.out.println("***Error...No existen Servicios registrados***");
        } else {
            while (OP == 'S') {
                System.out.println("----------Modificar Servicio----------------");
                String codigo = Entrada.leerString("Ingrese el codigo del servicio a modificar");
                Servicio servicioBuscado = this.logicaServicio.buscarServicio(codigo);
                if (servicioBuscado == null) {
                    System.out.println("No existe coincidencias");
                } else {
                    System.out.println(servicioBuscado.toString());
                    do {
                        op = Entrada.leerInt("Que desea modificar \n"
                                + "1. Costo del servicio \n"
                                + "2. Porcentaje de pago al empleado \n"
                                + "3. Descripcion del servicio \n"
                                + "4. Codigo del servicio \n"
                                + "5. Cancelar\n"
                                + ": ");
                    } while (op < 1 || op > 5);
                    switch (op) {
                        case 1:
                            String nombreServicio = servicioBuscado.getNombreServicio();
                            //Sincronizacion
                            if (nombreServicio.equals("Sincronizacion")) {

                                Sincronizacion serv = (Sincronizacion) servicioBuscado;
                                double precio1 = Entrada.leerDouble("Ingrese el precio por [3 y 4] cilindros: ");
                                System.out.println("");
                                serv.setPrecio1(precio1);
                                double precio2 = Entrada.leerDouble("Ingrese el precio por [6]  cilindros : ");
                                System.out.println("");
                                serv.setPrecio2(precio2);
                                double precio3 = Entrada.leerDouble("Ingrese el precio por [8] de cilindros : ");
                                serv.setPrecio3(precio3);
                                System.out.println("Modificaciones registradas");

                            }

                            //Alineacion
                            if (nombreServicio.equals("Alineacion")) {
                                Alineacion serv = (Alineacion) servicioBuscado;
                                double precio1 = Entrada.leerDouble("Ingrese el precio si el vehiculo es camioneta: ");
                                System.out.println("");
                                serv.setPrecio1(precio1);
                                double precio2 = Entrada.leerDouble("Ingrese el precio si el vehiculo es automovil: ");
                                System.out.println("");
                                serv.setPrecio2(precio2);
                                System.out.println("Modificaciones registradas");
                            }

                            //Balanceo 
                            if (nombreServicio.equals("Balanceo")) {
                                Balanceo serv = (Balanceo) servicioBuscado;
                                double precio1 = Entrada.leerDouble("Ingrese el precio por llanta de tamaño de ring [13] y [14] : ");
                                System.out.println("");
                                serv.setPrecio1(precio1);
                                double precio2 = Entrada.leerDouble("Ingrese el precio por llanta de tamaño de ring entre [15-18] : ");
                                serv.setPrecio2(precio2);
                                System.out.println("Modificaciones registradas");

                            }

                            //Calibrar Valvulas
                            if (nombreServicio.equals("Calibrar Valvulas")) {
                                CalibrarValvulas serv = (CalibrarValvulas) servicioBuscado;
                                double precio1 = Entrada.leerDouble("Ingrese el precio por [3 y 4] cilindros: ");
                                System.out.println("");
                                serv.setPrecio1(precio1);
                                double precio2 = Entrada.leerDouble("Ingrese el precio por [6]  cilindros : ");
                                System.out.println("");
                                serv.setPrecio2(precio2);
                                System.out.println("Modificaciones registradas");
                            }
                            break;
                        case 2:

                            //Porcentaje
                            double porc = Entrada.leerDouble("Ingrese el nuevo porcentaje% : ");
                            double porcentaje = porc / 100;
                            servicioBuscado.setPorcentaje(porcentaje);
                            break;
                        case 3:

                            //descripcion
                            String descripcion = Entrada.leerLinea("Ingrese la nueva descripcion: ");
                            servicioBuscado.setDescripcion(descripcion);
                            break;
                        case 4:

                            //Codigo
                            String cod = Entrada.leerString("Ingrese el nuevo codigo: ");
                            servicioBuscado.setCodigo(cod);
                            break;
                        case 5:
                            OP = 'N';
                    }

                }
                do {
                    OP = Entrada.leerChar("Desea modificar otro servicio Si->'S' No->'N': ");
                } while (OP != 'S' && OP != 'N');
            }
        }
    }

    public void EliminarServicio() {
        char op, OP = 'S';
        if (this.logicaServicio.consultarServicios().size() == 0) {
            System.out.println("***Error...No existen Servicios registrados***");
        } else {
            while (OP == 'S') {
                System.out.println("----------Eliminar Servicio----------------");
                String codigo = Entrada.leerString("Ingrese el codigo a eliminar");
                Servicio servicioBuscado = this.logicaServicio.buscarServicio(codigo);
                if (servicioBuscado == null) {
                    System.out.println("No existe coincidencias");
                } else {
                    System.out.println(servicioBuscado.toString());
                    do {
                        op = Entrada.leerChar("Esta seguro de eliminarlo Si->'S'  No->'N' ");
                    } while (op != 'S' && op != 'N');
                    if (op == 'S') {
                        this.logicaServicio.borrarServicio(codigo);
                    }
                }
                do {
                    OP = Entrada.leerChar("Desea eliminar otro Servicio Si->'S'  No->'N' ");
                } while (OP != 'S' && OP != 'N');
            }
        }
    }

    public void MostrarListaServicio() {
        System.out.println("----------Listas de Servicios Registrados----------------");
        ArrayList<Servicio> listaServicios = this.logicaServicio.consultarServicios();
        if (listaServicios.size() == 0) {
            System.out.println("***Error...No existen servicios registrados***");
        } else {
            for (Servicio s : listaServicios) {
                System.out.println(s.toString());
                System.out.println("\n");
            }
        }
    }

    public void Nomina() {
        if (this.logicaTecnico.consultarTecnico().size() == 0) {
            System.out.println("No existen empleados registrados");
        } else {
            System.out.println("----------Nomina----------------");
            char OP = 'S';
            while (OP == 'S') {
                String cedula = Entrada.leerString("Ingrese la cedula del tecnico");
                Tecnico tecnicoPagar = this.logicaTecnico.buscarTecnico(cedula);
                if (tecnicoPagar == null) {
                    System.out.println("Error... No existen coincidencias");
                } else {
                    System.out.println("Periodo de Nomina");
                    LocalDate fechaInicio = Entrada.leerFecha("Ingrese la fecha de inicio");
                    LocalDate fechaFinal = Entrada.leerFecha("Ingrese la fecha final");
                    ArrayList<Factura> listaFacturas = this.logicaFactura.consultarFacturas();
                    Nomina nominaTecnico = new Nomina(tecnicoPagar, listaFacturas, fechaInicio, fechaFinal);
                    nominaTecnico.ImprimirNomina();
                }

            }

        }
    }
}
