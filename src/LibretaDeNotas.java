import java.util.*;

public class LibretaDeNotas {

    public static final String RED = "\033[0;31m";

    public static void main(String[] args) {

        Map<String, ArrayList<Double>> listaAlumnosNotas = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        int cantidadAlumos = -1;

        while (cantidadAlumos <= 0) {
            System.out.println("Ingrese la cantidad de alumos: ");
            cantidadAlumos = sc.nextInt();
            sc.nextLine();

            if (cantidadAlumos <= 0) {
                System.out.println("La cantidad de alumos debe ser un número positivo");
            }
        }

        System.out.println("Ingrese la cantidad de notas");
        int cantidadNotas = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidadAlumos; i++) {
            System.out.println("Ingrese el nombre del alumno " + (i + 1));
            String nombreAlumno = sc.nextLine();

            ArrayList<Double> notasAlumno = listaAlumnosNotas.getOrDefault(nombreAlumno, new ArrayList<>());
            double notaAlumno;

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Ingrese la nota " + (j + 1) + " del alumno: " + nombreAlumno);
                notaAlumno = sc.nextDouble();

                sc.nextLine();

                if (notaAlumno >= 1.0 && notaAlumno <= 7.0) {
                    notasAlumno.add(notaAlumno);
                } else {
                    System.out.println("La nota debe ser mayor o igual a 1 y menor o igual a 7. Intente nuevamente.");
                    j--;
                }
            }
            listaAlumnosNotas.put(nombreAlumno, notasAlumno);
        }

        System.out.println(listaAlumnosNotas);
        System.out.println("**************************");
        CalcularpromedioNotaMximaMinima(listaAlumnosNotas);
    }

    public static void verificarPromedio(Map<String, ArrayList<Double>> listaAlumnosNotas) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante para ver su promedio: ");
        String nombreEstudiante = sc.nextLine();

        double sumaNotasCurso = 0.0;
        int totalNotas = 0;

        for (Map.Entry<String, ArrayList<Double>> entry : listaAlumnosNotas.entrySet()) {
            ArrayList<Double> notas = entry.getValue();
            for (double nota : notas) {
                sumaNotasCurso += nota;
                totalNotas++;
            }
        }

        double promedioCurso = sumaNotasCurso / totalNotas;
        System.out.println("El promedio del curso es: " + promedioCurso);

        if (listaAlumnosNotas.containsKey(nombreEstudiante)) {
            ArrayList<Double> notasEstudiante = listaAlumnosNotas.get(nombreEstudiante);
            double sumaNotasEstudiante = 0.0;

            for (double nota : notasEstudiante) {
                sumaNotasEstudiante += nota;
            }

            double promedioEstudiante = sumaNotasEstudiante / notasEstudiante.size();
            System.out.println("El promedio de " + nombreEstudiante + " es: " + promedioEstudiante);

            if (promedioEstudiante > promedioCurso) {
                System.out.println(nombreEstudiante + " tiene un promedio superior al promedio del curso.");
            } else if (promedioEstudiante < promedioCurso) {
                System.out.println(nombreEstudiante + " tiene un promedio inferior al promedio del curso.");
            } else {
                System.out.println(nombreEstudiante + " tiene un promedio igual al promedio del curso.");
            }
        } else {
            System.out.println("El estudiante " + nombreEstudiante + " no se encuentra en la lista.");
        }
    }

    public static void calcularPromedioPorEstudiante(Map<String, ArrayList<Double>> listaAlumnosNotas) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante para ver su promedio: ");
        String nombreEstudiante = sc.nextLine();

        double promedioNota = 0.0;

        if (listaAlumnosNotas.containsKey(nombreEstudiante)) {
            ArrayList<Double> notas = listaAlumnosNotas.get(nombreEstudiante);

            for (Double nota : notas) {
                promedioNota += nota;
            }
            promedioNota /= notas.size();
            System.out.println("El promedio de " + nombreEstudiante + " es " + promedioNota);
        }

    }

    public static void CalcularpromedioNotaMximaMinima(Map<String, ArrayList<Double>> listaAlumnosNotas) {
        for (Map.Entry<String, ArrayList<Double>> entry : listaAlumnosNotas.entrySet()) {
            String nombre = entry.getKey();
            ArrayList<Double> notas = entry.getValue();

            double suma = 0;
            double notaMaxima = Double.MIN_VALUE;
            double notaMinima = Double.MAX_VALUE;

            for (Double nota : notas) {
                suma += nota;
                if (nota > notaMaxima) {
                    notaMaxima = nota;
                }
                if (nota < notaMinima) {
                    notaMinima = nota;
                }
            }

            double promedio = suma / notas.size();
            System.out.println("El promedio de " + nombre + " es " + promedio);
            System.out.println("La nota maxima de " + nombre + " es: " + notaMaxima);
            System.out.println("La nota minima de " + nombre + " es: " + notaMinima);

        }
        menu(listaAlumnosNotas);
    }

    public static void notaAprobadoReprobado(Map<String, ArrayList<Double>> listaAlumnosNotas) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante para verificar su nota: ");
        String nombreEstudiante = sc.nextLine();

        double promedio = 0;

        if (listaAlumnosNotas.containsKey(nombreEstudiante)) {
            ArrayList<Double> notas = listaAlumnosNotas.get(nombreEstudiante);

            for (Double nota : notas) {
                promedio += nota;
            }
            promedio /= notas.size();
            System.out.println("El promedio de " + nombreEstudiante + " es " + promedio);

            if (promedio >= 4.0) {
                System.out.println(nombreEstudiante + " está aprobado.");
            } else {
                System.out.println(nombreEstudiante + " está reprobado.");
            }
        } else {
            System.out.println("El estudiante no esta registrado");
        }
    }

    public static void menu(Map<String, ArrayList<Double>> listaAlumnosNotas) {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("***** Seleccione una opción *****");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante.");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.");
            System.out.println("0. Salir del Menú.");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    calcularPromedioPorEstudiante(listaAlumnosNotas);
                    break;
                case 2:
                    notaAprobadoReprobado(listaAlumnosNotas);
                    break;
                case 3:
                    verificarPromedio(listaAlumnosNotas);
                    break;
                case 0:
                    System.out.println("Saliendo des sistema...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }
}
