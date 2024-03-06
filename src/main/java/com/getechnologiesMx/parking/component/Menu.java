package com.getechnologiesMx.parking.component;


import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.getechnologiesMx.parking.dto.StayDTO;
import com.getechnologiesMx.parking.dto.TypeVehicleDTO;
import com.getechnologiesMx.parking.dto.VehicleDTO;
import com.getechnologiesMx.parking.service.StayService;
import com.getechnologiesMx.parking.service.TypeVehicleService;
import com.getechnologiesMx.parking.service.VehicleService;
import com.getechnologiesMx.parking.util.Constants;


@Component
public class Menu {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private TypeVehicleService typeVehicleService;

    @Autowired
    private StayService stayService;

    @Async
    public void showMenu() {

        TypeVehicleDTO typeVehicleDto = new TypeVehicleDTO();
        typeVehicleDto.setName("Official");
        typeVehicleService.saveTypeVehicleDTO(typeVehicleDto);
        typeVehicleDto.setName("Resident");
        typeVehicleService.saveTypeVehicleDTO(typeVehicleDto);
        typeVehicleDto.setName("NoResident");
        typeVehicleService.saveTypeVehicleDTO(typeVehicleDto);
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        do {
            System.out.println("Menú:");
            System.out.println("1. Registrar entrada de vehículo");
            System.out.println("2. Registrar salida de vehículo");
            System.out.println("3. Dar de alta vehículo oficial");
            System.out.println("4. Dar de alta vehículo de residente");
            System.out.println("5. Dar de alta vehículo de no residente");
            System.out.println("6. Iniciar nuevo mes");
            System.out.println("7. Generar informe de pagos de residentes");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: no se permiten letras");
            }

            switch (opcion) {
                case 1:
                    registerEntry(scanner);
                    break;
                case 2:
                    registerDeparture(scanner);
                    break;
                case 3:
                    registerVehicleOfficial(scanner);
                    break;
                case 4:
                    registerVehicleResident(scanner);
                    break;
                case 5:
                    registerVehicleNoResident(scanner);
                    break;
                case 6:
                    startNewMonth();
                    break;
                case 7:
                    generateResidentPaymentsReport(scanner);
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 8);

        scanner.close();
    }

    private void registerEntry(Scanner scanner) {
        System.out.print("Ingrese el número de placa del vehículo: ");
        String numberPlate = scanner.nextLine();

        // Obtener el vehículo por número de placa
        VehicleDTO vehicleDto = vehicleService.findByNumberPlate(numberPlate);
        if (vehicleDto != null) {
            // Registrar la entrada del vehículo
            StayDTO stayDto = new StayDTO();
            stayDto.setVehicleDTO(vehicleDto);
            stayService.registerEntry(stayDto);

            System.out.println("Entrada registrada exitosamente.");
        }

        else {
            System.out.println("No se encontro Vehiculo registrado");
        }
    }

    private void registerDeparture(Scanner scanner) {
        System.out.print("Ingrese el número de placa del vehículo: ");
        String numberPlate = scanner.nextLine();

        // Obtener el vehículo por número de placa
        VehicleDTO vehicleDto = vehicleService.findByNumberPlate(numberPlate);
        if (vehicleDto != null) {
            StayDTO stayDto = stayService.findByVehicle(vehicleDto);
            if (stayDto != null) {
                stayDto.setVehicleDTO(vehicleDto);

                stayService.registerDeparture(stayDto);
                System.out.println("\n\nSalida registrada exitosamente.\n\n");
                if (stayDto.getVehicleDTO().getTypeVehicleDTO().getName().equals("NoResident")) {
                    System.out
                            .println("Num. Placa:\t" + stayDto.getVehicleDTO().getNumberPlate()
                                    + "\tTiempo Estacionado (min):\t"
                                    + ChronoUnit.MINUTES.between(
                                            stayDto.getTimeEntry(), stayDto.getTimeDeparture())
                                    + "\tCantidad a Pagar:\t"
                                    + ChronoUnit.MINUTES.between(stayDto.getTimeEntry(),
                                            stayDto.getTimeDeparture())
                                            * Constants.priceNoResident);
                    System.out.println("\n");
                }


            } else {
                System.out.println(
                        "No se encontro registro de entrada al estacionamiento del auto, favor de ingresar una entrada2");
            }

        } else {
            System.out.println("No se encontro Vehiculo registrado");
        }

    }

    private void registerVehicleOfficial(Scanner scanner) {
        System.out.print("Ingrese el número de placa del vehículo oficial: ");
        String numberPlate = scanner.nextLine();

        // Crear el vehículo oficial y guardarlo en la base de datos
        VehicleDTO vehicleDto = new VehicleDTO();
        vehicleDto.setNumberPlate(numberPlate);
        vehicleService.oficcialRegisterVehicle(vehicleDto);

        System.out.println("Vehículo oficial dado de alta exitosamente.");
    }

    private void registerVehicleResident(Scanner scanner) {
        System.out.print("Ingrese el número de placa del vehículo de residente: ");
        String numberPlate = scanner.nextLine();

        // Crear el vehículo de residente y guardarlo en la base de datos
        VehicleDTO vehicleDto = new VehicleDTO();
        vehicleDto.setNumberPlate(numberPlate);
        vehicleService.residentRegisterVehicle(vehicleDto);

        System.out.println("Vehículo de residente dado de alta exitosamente.");
    }

    private void registerVehicleNoResident(Scanner scanner) {
        System.out.print("Ingrese el número de placa del vehículo de no residente: ");
        String numberPlate = scanner.nextLine();

        // Crear el vehículo de no residente y guardarlo en la base de datos
        VehicleDTO vehicleDto = new VehicleDTO();
        vehicleDto.setNumberPlate(numberPlate);
        vehicleService.noResidentRegisterVehicle(vehicleDto);

        System.out.println("Vehículo de no residente dado de alta exitosamente.");
    }

    private void startNewMonth() {
        // Lógica para iniciar un nuevo mes (limpiar registros de estancias de vehículos oficiale y
        // tiempo estacionado de residentes)
        stayService.iniciarNuevoMes();
        System.out.println("Nuevo mes iniciado exitosamente.");
    }

    private void generateResidentPaymentsReport(Scanner scanner) {
        System.out.print("Ingrese el nombre del informe a generar: ");
        String nameFile = scanner.nextLine();

        stayService.generarInformePagosResidentes(nameFile);
        System.out.println("Informe de pagos de residentes generado exitosamente.");
    }
}
