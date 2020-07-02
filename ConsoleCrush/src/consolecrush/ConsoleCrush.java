package consolecrush;

import java.util.Scanner;

public class ConsoleCrush {

    //Variable jugador (Puntaje,Vida)
    private Jugador jugador = new Jugador();
    //Variables Matriz
    private Matriz matriz = new Matriz(jugador);
    //Variable extra
    //private boolean confirmacion = false;
    AutoScan autoScan = new AutoScan();

    private ConsoleCrush() {
        while (true) {
            if (autoScan.AutoScann(matriz.getMatriz())) {
                //Todo en orden, la matriz permite jugar    
                break;
            } else {
                System.out.println("==========================================================================================================================================");
                System.out.println("===============================La matriz generada no permitia ningun movimiento, reseteando matriz...=====================================");
                System.out.println("==========================================================================================================================================");
                matriz = new Matriz(jugador);
            }
        }
        //Control de jugador (5 vidas de 50 movimientos cada una)
        while (jugador.getJugar() == true) {
            for (int li = 0; li < 50; li++) {//50 movimientos de 50
                matriz.Dibujar(matriz.getMatriz(), jugador.getPuntaje(), jugador.getVidas());
                while (true) {//Realizacion del movimiento
                    while (true) {//Captura de datos
                        //Captura Casilla
                        matriz.setXcasilla(CapturaDatos(true, "X", matriz.getMatriz()));
                        matriz.setYcasilla(CapturaDatos(true, "Y", matriz.getMatriz()));
                        //Captura Movimiento       
                        matriz.setXmovimiento(CapturaDatos(false, "X", matriz.getMatriz()));
                        matriz.setYmovimiento(CapturaDatos(false, "Y", matriz.getMatriz()));
                        //Verificando validez del movimiento
                        if (matriz.getXcasilla() != matriz.getXmovimiento() || matriz.getYcasilla() != matriz.getYmovimiento()) {
                            if (matriz.getXmovimiento() == matriz.getXcasilla() + 1) {
                                if (matriz.getYmovimiento() == matriz.getYcasilla()) {
                                    break;
                                } else {
                                    System.out.println("==========================================================================================================================================");
                                    System.out.println("==================================================¡No se permiten movimientos en diagonal!================================================");
                                    System.out.println("=============¡La coordenada Y del movimiento no puede tener mas de 1 casilla de disistancia de la coordenada Y de la casilla seleccionada!");
                                    System.out.println("==========================================================================================================================================");
                                    matriz.Dibujar(matriz.getMatriz(), jugador.getPuntaje(), jugador.getVidas());
                                }
                            } else if (matriz.getXmovimiento() == matriz.getXcasilla() - 1) {
                                if (matriz.getYmovimiento() == matriz.getYcasilla()) {
                                    break;
                                } else {
                                    System.out.println("==========================================================================================================================================");
                                    System.out.println("==================================================¡No se permiten movimientos en diagonal!================================================");
                                    System.out.println("=============¡La coordenada Y del movimiento no puede tener mas de 1 casilla de disistancia de la coordenada Y de la casilla seleccionada!");
                                    System.out.println("==========================================================================================================================================");
                                    matriz.Dibujar(matriz.getMatriz(), jugador.getPuntaje(), jugador.getVidas());
                                }
                            } else if (matriz.getXmovimiento() == matriz.getXcasilla()) {
                                if (matriz.getYmovimiento() == matriz.getYcasilla() + 1) {
                                    break;
                                } else if (matriz.getYmovimiento() == matriz.getYcasilla() - 1) {
                                    break;
                                } else {
                                    System.out.println("==========================================================================================================================================");
                                    System.out.println("=============================================================¡Movimiento invalido!========================================================");
                                    System.out.println("=============¡La coordenada Y del movimiento no puede tener mas de 1 casilla de disistancia de la coordenada Y de la casilla seleccionada!");
                                    System.out.println("==========================================================================================================================================");
                                    matriz.Dibujar(matriz.getMatriz(), jugador.getPuntaje(), jugador.getVidas());
                                }
                            } else {
                                System.out.println("==========================================================================================================================================");
                                System.out.println("=================================================¡No se permiten movimientos en diagonal!=================================================");
                                System.out.println("=============¡La coordenada X del movimiento no puede tener mas de 1 casilla de disistancia de la coordenada X de la casilla seleccionada!");
                                System.out.println("==========================================================================================================================================");
                                matriz.Dibujar(matriz.getMatriz(), jugador.getPuntaje(), jugador.getVidas());
                            }
                        } else {
                            System.out.println("==========================================================================================================================================");
                            System.out.println("=============================================================¡Movimiento invalido!========================================================");
                            System.out.println("==============================¡Las coordenadas del movimiento no pueden ser las mismas a las de la casilla seleccionada!==================");
                            System.out.println("==========================================================================================================================================");
                            matriz.Dibujar(matriz.getMatriz(), jugador.getPuntaje(), jugador.getVidas());
                        }
                    }
                    if (matriz.Movimiento(matriz.getXcasilla(), matriz.getYcasilla(), matriz.getXmovimiento(), matriz.getYmovimiento(), true, jugador, matriz.getMatriz(),false)) {
                        if (autoScan.AutoScann(matriz.getMatriz())) {
                            //Hay movimientos disponibles, todo sigue normal
                        } else {
                            //No hay movimientos disponibles, reiniciamos la matriz
                            System.out.println("==========================================================================================================================================");
                            System.out.println("=========================No quedan movimientos posibles, generando nuevo tablero para  continuar el juego...==============================");
                            System.out.println("==========================================================================================================================================");
                            while (true) {
                                matriz = new Matriz(jugador);
                                if (autoScan.AutoScann(matriz.getMatriz())) {
                                    break;
                                } else {
                                    //Esta matriz tampoco permitia movimientos, creando otra...
                                }
                            }
                        }
                        //Verificando filas y columnas residuales
                        while (true) {
                            if (autoScan.AutoPoints(matriz, matriz.getMatriz(), jugador)) {
                                //Se encontraron filas o columnas por lo que volvemos a escanear si de esta ultima eliminacion hay nuevas filas o columnas
                                //+Puntos Extra
                            } else {
                                //Ya no hay mas filas o columnas residuales
                                break;
                            }
                        }
                        break;
                    } else {
                        System.out.println("==========================================================================================================================================");
                        System.out.println("==============================================================¡Movimiento imposible!======================================================");
                        System.out.println("==========================================================================================================================================");
                    }
                    matriz.Dibujar(matriz.getMatriz(), jugador.getPuntaje(), jugador.getVidas());//temporal mientras se agrega la accion automatica de eliminar tras caida
                }
                if (jugador.getPuntaje() >= 1000) {
                    System.out.println("==========================================================================================================================================");
                    System.out.println("==========================================================¡Has conseguido 1000 puntos!====================================================");
                    System.out.println("================================================================Juego terminado===========================================================");
                    System.out.println("==========================================================================================================================================");
                    jugador.setJugar(false);
                    break;
                }
            }
            if (jugador.getPuntaje() < 1000) {
                jugador.restarVidas();//Restando una vida
            }
            if (jugador.getVidas() <= 0) {
                matriz.Dibujar(matriz.getMatriz(), jugador.getPuntaje(), jugador.getVidas());
                System.out.println("==========================================================================================================================================");
                System.out.println("====================================================================¡Juego terminado!=====================================================");
                System.out.println("==================================================================Te quedaste sin vidas===================================================");
                System.out.println("==========================================================================================================================================");
                jugador.setJugar(false);
            }
        }
        //Retorno al menu principal
        System.out.println("==========================================================================================================================================");
        System.out.println("========================================================¡Retornando al menu principal!====================================================");
        System.out.println("==========================================================================================================================================");
    }

    private int CapturaDatos(boolean casilla, String coordenada, int[][] matriz) {
        int num = 0;
        while (true) {
            if (casilla) {
                System.out.print(">Ingrese la coordenada " + coordenada + " de la casilla a mover:");
            } else {
                System.out.print(">Ingrese la coordenada " + coordenada + " del movimiento:");
            }
            Scanner Dato = new Scanner(System.in);
            try {
                num = Dato.nextInt();
                System.out.println("");//Esto en caso de que ingresen muy rapidamente evitara que los mensajes se corran
                if (num >= 0 && num < matriz.length) {
                    break;
                } else {
                    System.out.println("==========================================================================================================================================");
                    System.out.println("==================================================¡Error, solo puede ingresar valores entre 0-8!==========================================");
                    System.out.println("==========================================================================================================================================");
                }
            } catch (Exception e) {
                System.out.println("==========================================================================================================================================");
                System.out.println("==================================================¡Error, solo puede ingresar valores entre 0-8!==========================================");
                System.out.println("==========================================================================================================================================");
            }
        }
        return num;
    }

    public static void main(String[] args) {
        ConsoleCrush Game = new ConsoleCrush();
    }
}
