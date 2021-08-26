import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class buscaminas {

    //IDEA,     Que el jugador pueda escoger que le pueda poner como letra a las casillas no encontradas

    //variables globales
    private static Integer[][] tabla;
    private static String[][] tablaShow;

    private static int contMinas;           //Cuantas minas lleva descubiertas
    private static int fila;                //El número de fila, se usa cuando se generan los num Random
    private static int colm;                //El número de columna, se usa cuando se generan los num Random
    private static String numUsados = "";   //Número usados
    private static int numOportunidades;    //Cuantas oportunidades lleva de descubrir una mina
    private static int numCasDescu;         //numero de casillas descubiertas
    private static int puntos;              // Acumulador de puntos

    private static int seguirJuego = 0;    //Se usa para saber cual menu se va a mostrar al principio

    //inputs
    private static int seguirjuegoSoN;      //Variable para seguir jeugo, sí o no
    private static int inputNivelOPerso;     //Input de si elige un nivel o hacer tabla personalizada
    private static int inputNivel;          //Input de cual nivel quiere el jugador
    private static int numLargo;            //que de cuanto por cuanto va a ser la tabla, numLargo X numLargo
    private static int numMinas;           //Cuantas minas va a haber en el tablero
    private static int jugar;              //Si quiere descubrir una casilla o descubrir una mina
    private static int jugarRenglon;       //Cual renglon selecciona el jugador
    private static int jugarColumna;       //Cual columna selecciona el jugador
    private static int terminar;            //Input de si se quire termianr partida o no


    //Aquí solamente se llama a un método llamado principal
    public static void main(String args[])
    {
        principal();
    }


    //Este es el método principal, donde se llama a la mayoría de los otros métodos
    public static void principal()
    {

       while (true)
       {


           numLargo = 0;
           numMinas = 0;
           contMinas = 0;
           fila = 0;
           colm = 0;
           seguirjuegoSoN = 0;
           numUsados = "";
           jugar = 0;
           jugarRenglon = 0;
           jugarColumna = 0;
           numOportunidades = 0;
           numCasDescu = 0;
           inputNivelOPerso = 0;
           inputNivel = 0;
           terminar = 0;
           puntos = 0;

           valiSeguirJugando();



           if ( buscaminas.seguirjuegoSoN == 1)
           {
               valiElegirNivelOPersonalizacion();

               if(buscaminas.inputNivelOPerso == 1)
               {

                   pregunta();

                   tabla = new Integer[buscaminas.numLargo ][buscaminas.numLargo ];
                   tablaShow = new String [buscaminas.numLargo ][buscaminas.numLargo ];

                   random();
                   ponerNumeros();
                   jugar();

                   seguirJuego++;
               }
               else if (buscaminas.inputNivelOPerso == 2)
               {
                   valiElegirNivel();
                   if(inputNivel == 1)
                   {
                       numLargo = 8;
                       numMinas = 10;

                       tabla = new Integer[buscaminas.numLargo ][buscaminas.numLargo ];
                       tablaShow = new String [buscaminas.numLargo ][buscaminas.numLargo ];

                       random();

                       ponerNumeros();
                       jugar();

                       seguirJuego++;
                   }

                   else if(inputNivel == 2)
                   {
                       numLargo = 16;
                       numMinas = 40;

                       tabla = new Integer[buscaminas.numLargo ][buscaminas.numLargo ];
                       tablaShow = new String [buscaminas.numLargo ][buscaminas.numLargo ];

                       random();

                       ponerNumeros();

                       jugar();

                       seguirJuego++;
                   }

                   else if(inputNivel == 3)
                   {
                       numLargo = 22;
                       numMinas = 100;

                       tabla = new Integer[buscaminas.numLargo ][buscaminas.numLargo ];
                       tablaShow = new String [buscaminas.numLargo ][buscaminas.numLargo ];

                       random();
                       ponerNumeros();
                       jugar();

                       seguirJuego++;
                   }
               }

           }
           else if (buscaminas.seguirjuegoSoN == 2)
           {
               System.out.println("           ------------------------------------");
               System.out.println("           | Gracias por jugar!!              |");
               System.out.println("           | Adiós  (>‿◠)✌                   |");
               System.out.println("___________\\ /--------------------------------");
               System.out.println("|  _    _  |");
               System.out.println("| |_|  |_| |");
               System.out.println("|   ____/  |");
               System.out.println("|__________|");
               break;
           }
       }
    }


    /*En este método están las instrucciones que se le dan al usuario
    Si es la primera vez que se le muestra al usuario las instrucciones, dice "Que bueno que decidiste jugar",
    después de la primera vez le va a decir "que bueno que decidiste jugar de nuevo"
     */
    public static void instrucciones ()
    {
        if (buscaminas.seguirJuego > 1)
        {
            System.out.println("           ------------------------------------");
            System.out.println("           | Decidiste jugar otra vez!!        |");
            System.out.println("           | Aquí debajo están las             |");
            System.out.println("           | intrucciones                      |");
            System.out.println("           | Buena suerte :)                   |");
            System.out.println("___________\\ /--------------------------------");
            System.out.println("|  _    _  |");
            System.out.println("| |_|  |_| |");
            System.out.println("|   ____/  |");
            System.out.println("|__________|");
        }
        else
        {
            System.out.println("           ------------------------------------");
            System.out.println("           | Que bueno que decidiste jugar,   |");
            System.out.println("           | aquí debajo estan las            |");
            System.out.println("           | intrucciones.                    |");
            System.out.println("           | Buena suerte :)                  |");
            System.out.println("___________\\ /--------------------------------");
            System.out.println("|  _    _  |");
            System.out.println("| |_|  |_| |");
            System.out.println("|   ____/  |");
            System.out.println("|__________|");

            try
            {
                Thread.sleep(3*1000);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }

        System.out.println("");
        System.out.println("Instrucciones:");
        System.out.println("1. Trata de descubrir todas las casillas sin mina");
        System.out.println("   o encuentra todas las minas");
        System.out.println("2. Las S en las casillas significa que estas no han sido descubiertas");
        System.out.println("3. Los 0 son las minas");
        System.out.println("4. Solamente tienes 3 oportunidades para descubrir una ");
        System.out.println("   mina");
        System.out.println("5. Si descubres una casilla vacía, se van a descubrir ");
        System.out.println("5. las de alrededor ");
        System.out.println("");
    }


    /* Validador de si el jugador quiere seguir jugando, si no se termina el programa
    Le pide al jugador un número y se verifica que sea un número y 1 o 2, si no se le vuelve a pedir otro número.
    también si es la primera vez que juega, se le muestra el personaje, cuando vuelva a jugar ya no se va a mostrar al personaje
     */
    public static void valiSeguirJugando()
    {
        boolean validInput = false;
        Scanner scan = new Scanner(System.in);
        if( buscaminas.seguirJuego == 0)
        {
            System.out.println("           ------------------------------------");
            System.out.println("           | Hola, mi nombre es BM,           |");
            System.out.println("           | ¿Desea jugar buscaminas?         |");
            System.out.println("           | Escribe debajo                   |");
            System.out.println("           | 1 = Sí                           |");
            System.out.println("           | 2 = No                           |");
            System.out.println("___________\\ /--------------------------------");
            System.out.println("|  _    _  |");
            System.out.println("| |_|  |_| |");
            System.out.println("|   ____/  |");
            System.out.println("|__________|");

        }
        else
        {
            System.out.println("Desea seguir jugando?     1 = Sí     2 = No");
        }

        String input = scan.nextLine();
        while (validInput == false)
        {

            try
            {
                buscaminas.seguirjuegoSoN = Integer.valueOf(input);
                if (buscaminas.seguirjuegoSoN == 1 || buscaminas.seguirjuegoSoN == 2)
                {
                    validInput = true;

                }
                else
                {
                    System.out.println("Es 1 o 2, escribe de nuevo");
                    input = scan.nextLine();
                }
            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es una opción válida");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }


    /*Donde se pregunta al usuario si quiere un 1 = juego personalizado o quiere 2 = escoger un nivel
    se valida que el número que haya entrado sea 1 o 2 y que sea número
     */
    public static void valiElegirNivelOPersonalizacion()
    {
        boolean validInput = false;
        Scanner scan = new Scanner(System.in);

        System.out.println("Elige una opción: ");
        System.out.println("1 = Juego Personalizado");
        System.out.println("2 = Elegir Nivel");

        String input = scan.nextLine();
        while (validInput == false)
        {

            try
            {
                buscaminas.inputNivelOPerso = Integer.valueOf(input);
                if (buscaminas.inputNivelOPerso == 1 || buscaminas.inputNivelOPerso == 2)
                {
                    validInput = true;

                }
                else
                {
                    System.out.println("Es 1 o 2, escribe de nuevo");
                    input = scan.nextLine();
                }
            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es una opción válida");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }

    /*Se pregunta al jugador que nivel quiere jugar, 1 = principiante, 2 = intermedio y 3 = Experto
    Se valida que el número que entre sea número y sea 1, 2 o 3 */
    public static void valiElegirNivel()
    {
        boolean validInput = false;
        Scanner scan = new Scanner(System.in);

        System.out.println("Elige un nivel: ");
        System.out.println("1 = Principiante");
        System.out.println("2 = Intermedio");
        System.out.println("3 = Experto");

        String input = scan.nextLine();
        while (validInput == false)
        {

            try
            {
                buscaminas.inputNivel = Integer.valueOf(input);
                if (buscaminas.inputNivel == 1 || buscaminas.inputNivel == 2 || buscaminas.inputNivel == 3)
                {
                    validInput = true;

                }
                else
                {
                    System.out.println("Es 1, 2 o 3, escribe de nuevo");
                    input = scan.nextLine();
                }
            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es una opción válida");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }

    /*Se pregunta de cuanto x cuanto quiere el tablero y
    cuántas minas quiere si el jugador escoge tablero personalizado */
    public static void pregunta()
    {
        validadorTamanoTablero();

        validadorNumMinas();



    }

    /*Se asigna las minas a casillas random,
    Se crea un arrayList donde se van guardando los que ya se usaron,
    después se crean dos números random que se juntan en una string, se verifica que no se haya repetido
    en el arrayList y si se repite se generan otro par de números, si no se repiten con ninguno de los del ArrayList
    Se añade ese string al ArrayList */
    public static void random()
    {
        Random ran = new Random();
        String juntos = "";
        ArrayList<String> numUsados = new ArrayList<String>();

        for( int i = 1; i <= numMinas; i++)
        {
            fila = ran.nextInt(numLargo);
            colm = ran.nextInt(numLargo);

            String f1 = String.valueOf(fila);
            String c1 = String.valueOf(colm);

            juntos = f1 + c1;

            if( numUsados.isEmpty() )
            {
                numUsados.add(juntos);
                tabla [fila][colm] = 0;
            }
            else
            {

                for(int j = 0; j < numUsados.size(); j++)
                {

                    while(numUsados.get(j).equals(juntos))
                    {

                        fila = ran.nextInt(numLargo);
                        colm = ran.nextInt(numLargo);

                        f1 = String.valueOf(fila);
                        c1 = String.valueOf(colm);

                        juntos = f1 + c1;
                        j = 0;
                    }
                }

                numUsados.add(juntos);
                tabla [fila][colm] = 0;
            }
        }
    }

    //En este método se ponen los numero alrededor de las minas
    public static void ponerNumeros()
    {
        for (int i = 0; i < numLargo; i++)
        {
            for (int j = 0; j < numLargo; j++)
            {
                if (tabla[i][j] != null)
                {
                    if (tabla[i][j] == 0)
                    {
                        if (j == 0 && i == 0)
                        {

                            if (tabla[0 + 1][0] == null)
                            {
                                tabla[0 + 1][0] = 1;
                            }
                            else
                            {
                                if(tabla [0 + 1][0] != 0)
                                {
                                    tabla [0 + 1][0] = tabla[0 + 1][0] + 1;
                                }
                            }

                            if (tabla [0 + 1][0 + 1] == null)
                            {
                                tabla[0 + 1][0 + 1] = 1;
                            }
                            else
                            {
                                if(tabla [0 + 1][0 + 1]!= 0)
                                {
                                    tabla [0 + 1][0 + 1] = tabla [0 + 1][0 + 1] + 1;
                                }
                            }

                            if (tabla [0][0 + 1] == null)
                            {
                                tabla[0][0 + 1] = 1;
                            }
                            else
                            {
                                if(tabla [0][0 + 1] != 0)
                                {
                                    tabla [0][0 + 1] = tabla [0][0 + 1] + 1;
                                }
                            }
                        }

                        else if (i == numLargo - 1 && j == 0)
                        {

                            if (tabla [numLargo -2][0] == null)
                            {
                                tabla [numLargo -2][0] = 1;
                            }
                            else
                            {
                                if(tabla [numLargo -2][0]  != 0)
                                {
                                    tabla [numLargo -2][0] = tabla [numLargo -2][0] + 1;
                                }
                            }

                            if (tabla [numLargo -2][0+1] == null)
                            {
                                tabla [numLargo -2][0+1] = 1;
                            }
                            else
                            {
                                if(tabla [numLargo -2][0+1] != 0)
                                {
                                    tabla [numLargo -2][0+1] = tabla [numLargo -2][0+1] + 1;
                                }
                            }

                            if (tabla [numLargo -1][0+1] == null)
                            {
                                tabla [numLargo -1][0+1] = 1;
                            }
                            else
                            {
                                if(tabla [numLargo -1][0+1] != 0)
                                {
                                    tabla [numLargo -1][0+1] = tabla [numLargo -1][0+1] + 1;
                                }
                            }

                        }
                        else if (i == numLargo - 1 && j == numLargo - 1)
                        {
                            if (tabla  [numLargo -2][numLargo -1] == null)
                            {
                                tabla  [numLargo -2][numLargo -1] = 1;
                            }
                            else
                            {
                                if(tabla [numLargo -2][numLargo -1]!= 0)
                                {
                                    tabla [numLargo -2][numLargo -1] = tabla [numLargo -2][numLargo -1] + 1;
                                }
                            }

                            if (tabla  [numLargo -2][numLargo -2] == null)
                            {
                                tabla  [numLargo -2][numLargo -2] = 1;
                            }
                            else
                            {
                                if(tabla [numLargo -2][numLargo -2] != 0)
                                {
                                    tabla  [numLargo -2][numLargo -2] = tabla  [numLargo -2][numLargo -2] + 1;
                                }
                            }

                            if (tabla  [numLargo -1][numLargo -2] == null)
                            {
                                tabla  [numLargo -1][numLargo -2] = 1;
                            }
                            else
                            {
                                if(tabla [numLargo -1][numLargo -2] != 0)
                                {
                                    tabla [numLargo -1][numLargo -2]  = tabla [numLargo -1][numLargo -2]  + 1;
                                }
                            }
                        }

                        else if (i == 0 && j == numLargo - 1)
                        {
                            if (tabla [0][numLargo-2] == null)
                            {
                                tabla [0][numLargo-2] = 1;
                            }
                            else
                            {
                                if(tabla [0][numLargo-2] != 0)
                                {
                                    tabla [0][numLargo-2]  = tabla [0][numLargo-2] + 1;
                                }
                            }

                            if (tabla [0+1][numLargo-2]  == null)
                            {
                                tabla [0+1][numLargo-2]  = 1;
                            }
                            else
                            {
                                if(tabla [0+1][numLargo-2]  != 0)
                                {
                                    tabla [0+1][numLargo-2]  = tabla [0+1][numLargo-2] + 1;
                                }
                            }

                            if (tabla  [0+1][numLargo-1]  == null)
                            {
                                tabla  [0+1][numLargo-1]  = 1;
                            }
                            else
                            {
                                if(tabla [0+1][numLargo-1] != 0)
                                {
                                    tabla [0+1][numLargo-1]  = tabla [0+1][numLargo-1] + 1;
                                }
                            }
                        }

                        else if (j == 0 && i != 0 && i != numLargo-1)
                        {
                            if (tabla  [i-1][0]   == null)
                            {
                                tabla  [i-1][0]   = 1;
                            }
                            else
                            {
                                if(tabla [i-1][0]  != 0)
                                {
                                    tabla [i-1][0]   = tabla [i-1][0]  + 1;
                                }
                            }

                            if (tabla  [i-1][0+1]  == null)
                            {
                                tabla  [i-1][0+1]  = 1;
                            }
                            else
                            {
                                if(tabla [i-1][0+1] != 0)
                                {
                                    tabla [i-1][0+1]   = tabla [i-1][0+1] + 1;
                                }
                            }

                            if (tabla  [i][0+1]  == null)
                            {
                                tabla  [i][0+1] = 1;
                            }
                            else
                            {
                                if(tabla [i][0+1]  != 0)
                                {
                                    tabla [i][0+1]  = tabla [i][0+1] + 1;
                                }
                            }

                            if (tabla   [i+1][0+1] == null)
                            {
                                tabla   [i+1][0+1] = 1;
                            }
                            else
                            {
                                if(tabla [i+1][0+1]  != 0)
                                {
                                    tabla [i+1][0+1] = tabla [i+1][0+1]+ 1;
                                }
                            }

                            if (tabla  [i+1][0] == null)
                            {
                                tabla  [i+1][0]= 1;
                            }
                            else
                            {
                                if(tabla [i+1][0] != 0)
                                {
                                    tabla [i+1][0] = tabla [i+1][0] + 1;
                                }
                            }

                        }
                        else if (i == 0 && j != 0 && j != numLargo - 1)
                        {
                            if (tabla  [0][j-1] == null)
                            {
                                tabla  [0][j-1] = 1;
                            }
                            else
                            {
                                if(tabla [0][j-1] != 0)
                                {
                                    tabla [0][j-1] = tabla [0][j-1] + 1;
                                }
                            }

                            if (tabla  [0+1][j-1] == null)
                            {
                                tabla  [0+1][j-1] = 1;
                            }
                            else
                            {
                                if(tabla [0+1][j-1] != 0)
                                {
                                    tabla [0+1][j-1] = tabla [0+1][j-1] + 1;
                                }
                            }

                            if (tabla [0+1][j] == null)
                            {
                                tabla [0+1][j] = 1;
                            }
                            else
                            {
                                if(tabla [0+1][j] != 0)
                                {
                                    tabla [0+1][j] = tabla [0+1][j] + 1;
                                }
                            }

                            if (tabla [0+1][j+1]  == null)
                            {
                                tabla [0+1][j+1]  = 1;
                            }
                            else
                            {
                                if(tabla [0+1][j+1] != 0)
                                {
                                    tabla [0+1][j+1] = tabla [0+1][j+1] + 1;
                                }
                            }

                            if (tabla  [0][j+1]  == null)
                            {
                                tabla  [0][j+1]   = 1;
                            }
                            else
                            {
                                if(tabla [0][j+1] != 0)
                                {
                                    tabla [0][j+1] = tabla [0][j+1] + 1;
                                }
                            }
                        }
                        else if (j == numLargo - 1 && i != 0 && i != numLargo - 1)
                        {
                            if (tabla [i-1][numLargo-1] == null)
                            {
                                tabla  [i-1][numLargo-1]   = 1;
                            }
                            else
                            {
                                if(tabla [i-1][numLargo-1] != 0)
                                {
                                    tabla [i-1][numLargo-1] = tabla [i-1][numLargo-1] + 1;
                                }
                            }

                            if (tabla  [i-1][numLargo-2] == null)
                            {
                                tabla  [i-1][numLargo-2] = 1;
                            }
                            else
                            {
                                if(tabla [i-1][numLargo-2] != 0)
                                {
                                    tabla [i-1][numLargo-2] = tabla [i-1][numLargo-2] + 1;
                                }
                            }

                            if (tabla [i][numLargo-2] == null)
                            {
                                tabla [i][numLargo-2]  = 1;
                            }
                            else
                            {
                                if(tabla [i][numLargo-2] != 0)
                                {
                                    tabla [i][numLargo-2] = tabla [i][numLargo-2] + 1;
                                }
                            }

                            if (tabla  [i+1][numLargo-2] == null)
                            {
                                tabla  [i+1][numLargo-2]  = 1;
                            }
                            else
                            {
                                if(tabla [i+1][numLargo-2] != 0)
                                {
                                    tabla [i+1][numLargo-2] = tabla [i+1][numLargo-2] + 1;
                                }
                            }

                            if (tabla [i+1][numLargo-1] == null)
                            {
                                tabla [i+1][numLargo-1] = 1;
                            }
                            else
                            {
                                if(tabla [i+1][numLargo-1] != 0)
                                {
                                    tabla [i+1][numLargo-1] = tabla [i+1][numLargo-1] + 1;
                                }
                            }
                        }

                        else if (i == numLargo - 1 && j != 0 && j != numLargo - 1)
                        {
                            if (tabla [numLargo-1][j-1] == null)
                            {
                                tabla [numLargo-1][j-1] = 1;
                            }
                            else
                            {
                                if(tabla [numLargo-1][j-1] != 0)
                                {
                                    tabla [numLargo-1][j-1] = tabla [numLargo-1][j-1] + 1;
                                }
                            }

                            if (tabla  [numLargo-2][j-1] == null)
                            {
                                tabla  [numLargo-2][j-1] = 1;
                            }
                            else
                            {
                                if(tabla  [numLargo-2][j-1] != 0)
                                {
                                    tabla  [numLargo-2][j-1] = tabla  [numLargo-2][j-1] + 1;
                                }
                            }

                            if (tabla  [numLargo-2][j] == null)
                            {
                                tabla  [numLargo-2][j] = 1;
                            }
                            else
                            {
                                if(tabla  [numLargo-2][j] != 0)
                                {
                                    tabla  [numLargo-2][j] = tabla  [numLargo-2][j] + 1;
                                }
                            }

                            if (tabla  [numLargo-2][j+1] == null)
                            {
                                tabla  [numLargo-2][j+1] = 1;
                            }
                            else
                            {
                                if(tabla  [numLargo-2][j+1] != 0)
                                {
                                    tabla  [numLargo-2][j+1] = tabla [numLargo-2][j+1] + 1;
                                }
                            }

                            if (tabla  [numLargo-1][j+1] == null)
                            {
                                tabla  [numLargo-1][j+1] = 1;
                            }
                            else
                            {
                                if(tabla  [numLargo-1][j+1] != 0)
                                {
                                    tabla  [numLargo-1][j+1] = tabla [numLargo-1][j+1] + 1;
                                }
                            }
                        }
                        else
                        {
                            if (tabla [i-1][j-1] == null)
                            {
                                tabla  [i-1][j-1] = 1;
                            }
                            else
                            {
                                if(tabla [i-1][j-1] != 0)
                                {
                                    tabla [i-1][j-1]= tabla [i-1][j-1] + 1;
                                }
                            }

                            if (tabla [i][j-1] == null)
                            {
                                tabla [i][j-1] = 1;
                            }
                            else
                            {
                                if(tabla [i][j-1] != 0)
                                {
                                    tabla [i][j-1] = tabla [i][j-1] + 1;
                                }
                            }

                            if (tabla [i+1][j-1] == null)
                            {
                                 tabla [i+1][j-1] = 1;
                            }
                            else
                            {
                                if(tabla [i+1][j-1] != 0)
                                {
                                    tabla [i+1][j-1] = tabla [i+1][j-1] + 1;
                                }
                            }

                            if (tabla [i+1][j] == null)
                            {
                                tabla [i+1][j] = 1;
                            }
                            else
                            {
                                if(tabla [i+1][j] != 0)
                                {
                                    tabla [i+1][j] = tabla [i+1][j] + 1;
                                }
                            }

                            if (tabla [i+1][j+1] == null)
                            {
                                tabla [i+1][j+1] = 1;
                            }
                            else
                            {
                                if(tabla [i+1][j+1] != 0)
                                {
                                    tabla [i+1][j+1] = tabla [i+1][j+1] + 1;
                                }
                            }

                            if (tabla [i][j+1] == null)
                            {
                                tabla [i][j+1] = 1;
                            }
                            else
                            {
                                if(tabla [i][j+1] != 0)
                                {
                                    tabla [i][j+1]= tabla [i][j+1] + 1;
                                }
                            }

                            if (tabla [i-1][j+1] == null)
                            {
                                tabla [i-1][j+1]= 1;
                            }
                            else
                            {
                                if(tabla [i-1][j+1] != 0)
                                {
                                    tabla [i-1][j+1] = tabla [i-1][j+1] + 1;
                                }
                            }

                            if (tabla [i-1][j] == null)
                            {
                                tabla [i-1][j] = 1;
                            }
                            else
                            {
                                if(tabla [i-1][j] != 0)
                                {
                                    tabla [i-1][j] = tabla [i-1][j] + 1;
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    //Este método valida que el tamaño del tablero que el jugador escribió sea un número y sea mayor a 1
    public static void validadorTamanoTablero()
    {
        boolean validInput = false;
        Scanner scan = new Scanner(System.in);
        if( buscaminas.seguirJuego == 0)
        {
            System.out.println("");
            System.out.println("           ------------------------------------");
            System.out.println("           | ¿De cuánto por cuánto desea el    |");
            System.out.println("           |tablero?  Escriba el número        |");
            System.out.println("           | Abajo hay un ejemplo de un        |");
            System.out.println("           | tablero de 5 x 5.                 |");
            System.out.println("           |    0   1   2   3   4              |");
            System.out.println("           |   ____________________            |");
            System.out.println("           | 0.| S | S | S | S | S |           |");
            System.out.println("           | 1.| S | S | S | S | S |           |");
            System.out.println("           | 2.| S | S | S | S | S |           |");
            System.out.println("           | 3.| S | S | S | S | S |           |");
            System.out.println("           | 4.| S | S | S | S | S |           |");
            System.out.println("           |   ____________________            |");
            System.out.println("___________\\ /--------------------------------");
            System.out.println("|  _    _  |");
            System.out.println("| |_|  |_| |");
            System.out.println("|  \\____/  |");
            System.out.println("|__________|");
        }
        else
        {
            System.out.println("¿De cuánto por cuánto desea el tablero?");
        }
        String input = scan.nextLine();
        while (validInput == false)
        {

            try
            {
                buscaminas.numLargo = Integer.valueOf(input);
                if(buscaminas.numLargo > 1)
                {
                    validInput = true;
                }
                else
                {
                    System.out.println("No se puede hacer una tabla con un 1 o menor que un 1 ");
                    System.out.println("Introduce otro número");
                    input = scan.nextLine();
                }

            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es un número");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }

    //En este método se valida que el número de minas sea >= 1 y que sea menor a (numLargo)*(numLargo)
    public static void validadorNumMinas()
    {



        boolean validInput = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("¿Cuántas minas desea en el tablero?  Escriba el número");
        String input = scan.nextLine();

        int limite = (buscaminas.numLargo*buscaminas.numLargo);

        while (validInput == false)
        {

            try
            {
                buscaminas.numMinas = Integer.valueOf(input);  //Trata de convertir el input a entero, si on puede, le a decir que introdusca otra cosa.

                if (buscaminas.numMinas >= 1  && buscaminas.numMinas < limite)
                {
                    validInput = true;

                }
                else
                {
                    int limUsuario = limite - 1;  //Para que el juegador sepa de que numero a que numero puede poner

                    System.out.println("escriba un numero entre el 1 y el "+ limUsuario);
                    input = scan.nextLine();
                }
            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es una opción válida");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }

    /*En este método se muestra el arreglo tabla, la que tiene todos las minas y los ceros,
    usualmente se muestra cuando el jugador pierde o gana */
    public static void tablaMostrar()
    {

        System.out.print("     ");
        for(int a = 0; a < numLargo; a++ )
        {
            if (a == 10)
            {
                System.out.print(a+"  ");
            }
            else if (a < 10)
            {
                System.out.print(a+"   ");
            }
            else
            {
                System.out.print(a+"  ");
            }
        }
        System.out.println("");
        System.out.print("   ");
        for(int a = 0; a < numLargo; a++ )
        {
            System.out.print("____");
        }

        System.out.println("");
        for (int i = 0; i < numLargo; i++)
        {
            if(i<10)
            {
                System.out.print(i + "  ");
                System.out.print("|");
            }
            else
            {
                System.out.print(i + " ");
                System.out.print("|");
            }


            for (int j = 0; j < numLargo; j++)
            {
                if (tabla[i][j] == null)
                {
                    System.out.print("   "+ "|");
                }
                else
                {
                    System.out.print(" "+tabla[i][j] + " "+"|");
                }
            }
            System.out.print("");
            System.out.println("");

        }

        System.out.print("   ");
        for(int a = 0; a < numLargo; a++ )
        {
            System.out.print("____");
        }
        System.out.println("");
        System.out.println("");
    }


    //En este método muestra el arreglo tablaShow, el que se le va a enseñar a el jugador
    public static void tablaShowMostrar()
    {

        System.out.print("     ");
        for(int a = 0; a < numLargo; a++ )
        {
            if (a == 10)
            {
            System.out.print(a+"  ");
            }
            else if (a < 10)
            {
                System.out.print(a+"   ");
            }
            else
            {
                System.out.print(a+"  ");
            }

        }

        System.out.println("");
        System.out.print("   ");

        for(int a = 0; a < numLargo; a++ )
        {
            System.out.print("____");
        }

        System.out.println("");
        for (int i = 0; i < numLargo; i++)
        {
            if (i < 10)
            {
            System.out.print(i + "  ");
            }
            else
            {
                System.out.print(i + " ");
            }

            System.out.print("|");

            for (int j = 0; j < numLargo; j++)
            {
                if (tablaShow[i][j] == null)
                {
                    System.out.print(" S "+ "|");
                }
                else
                {
                    if (tabla[i][j] == null)
                    {
                        System.out.print(" "+" "+ " "+"|");
                    }
                    else
                    {
                        System.out.print(" "+tabla[i][j] + " "+"|");
                    }

                }
            }
            System.out.print("");
            System.out.println("");

        }

        System.out.print("   ");
        for(int a = 0; a < numLargo; a++ )
        {
            System.out.print("____");
        }
        System.out.println("");
        System.out.println("");
    }

    //Es donde el jugador hace todas las movidas, y donde estan las validaciones para ver si ganó
    public static void jugar()
    {

        boolean busca = true;

        instrucciones();
        tablaShowMostrar();


        while (busca == true)
        {
            validadorjugar();

            if (buscaminas.jugar == 1)
            {
                validadorJugarRenglon();
                validadorJugarColumna();

                if(tablaShow[buscaminas.jugarRenglon][buscaminas.jugarColumna] != null)

                {
                    System.out.println("Esa casilla ya está descubierta, vuélvelo a intentar o elige otra opción");
                }
                else
                {
                    if(tabla [buscaminas.jugarRenglon][buscaminas.jugarColumna] == null)
                    {
                        descubridor();
                        tablaShow[buscaminas.jugarRenglon][buscaminas.jugarColumna] = "v";
                        tablaShowMostrar();
                        System.out.println("Esta vacía, puedes seguir jugando");
                        buscaminas.puntos ++;

                        numCasDescu++;

                        if(numCasDescu == (numLargo*numLargo)-(numMinas))
                        {
                            tablaMostrar();

                            System.out.println("           ------------------------------------");
                            System.out.println("           | Ganaste!!! Felicidades            |");
                            System.out.println("           | Descubriste todas las casillas    |");
                            System.out.println("           | sin mina                          |");
                            System.out.println("___________\\ /--------------------------------");
                            System.out.println("|  _    _  |");
                            System.out.println("| |_|  |_| |");
                            System.out.println("|  \\____/  |");
                            System.out.println("|__________|");

                            System.out.println("Tu puntuación es: 100%");
                            busca = false;
                        }

                    }


                    else
                    {
                        if(tabla [buscaminas.jugarRenglon][buscaminas.jugarColumna] == 0)
                        {
                            tablaMostrar();

                            System.out.println("           ------------------------------------");
                            System.out.println("           | Perdiste, encontraste una mina    |");
                            System.out.println("           | Suerte la próxima                 |");
                            System.out.println("___________\\ /--------------------------------");
                            System.out.println("|  _    _  |");
                            System.out.println("| |_|  |_| |");
                            System.out.println("|   ____   |");
                            System.out.println("|__________|");

                            int totalpuntos = numLargo*numLargo;
                            System.out.println("Tu puntuación es: "+(puntos*100)/totalpuntos+"%");
                            busca = false;
                        }
                        else
                        {

                            tablaShow [buscaminas.jugarRenglon][buscaminas.jugarColumna] = "v";
                            tablaShowMostrar();
                            buscaminas.puntos++;

                            numCasDescu++;
                            if(numCasDescu == (numLargo*numLargo)-(numMinas))
                            {
                                tablaMostrar();

                                System.out.println("           ------------------------------------");
                                System.out.println("           | Ganaste!!! Felicidades            |");
                                System.out.println("           | Descubriste todas las casillas    |");
                                System.out.println("           | sin mina                          |");
                                System.out.println("___________\\ /--------------------------------");
                                System.out.println("|  _    _  |");
                                System.out.println("| |_|  |_| |");
                                System.out.println("|  \\____/  |");
                                System.out.println("|__________|");

                                System.out.println("Tu puntuación es: 100%");
                                busca = false;
                            }

                        }
                    }


                }


            }
            else if(buscaminas.jugar == 2)
            {
                validadorJugarRenglon();
                validadorJugarColumna();

                if(tablaShow[buscaminas.jugarRenglon][buscaminas.jugarColumna] != null)
                {
                    System.out.println("Esa casilla ya está descubierta, vuélvelo a intentar o elige otra opción");
                }
                else
                {

                    if( tabla [buscaminas.jugarRenglon][buscaminas.jugarColumna] == null)
                    {
                        System.out.println("No hay mina");
                        numOportunidades++;
                        if (numOportunidades == 3)
                        {
                            tablaMostrar();

                            System.out.println("           ------------------------------------");
                            System.out.println("           | Perdiste, te acabaste todas tus   |");
                            System.out.println("           | oportunidades, suerte la próxima  |");
                            System.out.println("___________\\ /--------------------------------");
                            System.out.println("|  _    _  |");
                            System.out.println("| |_|  |_| |");
                            System.out.println("|   ____   |");
                            System.out.println("|__________|");

                            int totalpuntos = numLargo*numLargo;
                            System.out.println("Tu puntuación es: "+(puntos*100)/totalpuntos+"%");

                            busca = false;
                        }
                        else
                        {
                            int restan = 3 -  numOportunidades;
                            System.out.println("Te quedan "+restan+" oportunidades");
                        }
                    }
                    else if(tabla [buscaminas.jugarRenglon][buscaminas.jugarColumna] == 0)
                    {

                        tablaShow [buscaminas.jugarRenglon][buscaminas.jugarColumna] = "v";

                        tablaShowMostrar();
                        System.out.println("Sí hay una mina , muy bien");
                        contMinas++;
                        buscaminas.puntos ++;

                        if (contMinas == numMinas)
                        {
                            tablaMostrar();

                            System.out.println("           ------------------------------------");
                            System.out.println("           | Ganaste!!! Felicidades            |");
                            System.out.println("           | Descubriste todas las casillas    |");
                            System.out.println("           | con mina                          |");
                            System.out.println("___________\\ /--------------------------------");
                            System.out.println("|  _    _  |");
                            System.out.println("| |_|  |_| |");
                            System.out.println("|  \\____/  |");
                            System.out.println("|__________|");

                            System.out.println("Tu puntuación es: 100%");
                            busca = false;
                        }

                    }
                    else
                    {
                        System.out.println("No hay mina");
                        numOportunidades++;
                        if (numOportunidades == 3)
                        {
                            tablaMostrar();

                            System.out.println("           ------------------------------------");
                            System.out.println("           | Perdiste, te acabaste todas tus   |");
                            System.out.println("           | oportunidades, suerte la próxima  |");
                            System.out.println("___________\\ /--------------------------------");
                            System.out.println("|  _    _  |");
                            System.out.println("| |_|  |_| |");
                            System.out.println("|   ____   |");
                            System.out.println("|__________|");

                            int totalpuntos = numLargo*numLargo;
                            System.out.println("Tu puntuación es: "+(puntos*100)/totalpuntos+"%");

                            busca = false;
                        }
                        else
                        {
                            int restan = 3 -  numOportunidades;
                            System.out.println("Te quedan "+restan+" oportunidades");
                        }

                    }
                }
                }
            else if(buscaminas.jugar == 3 )
            {
                validadorTermianrPartida();

                if ( buscaminas.terminar == 1)
                {
                    busca = false;
                    tablaMostrar();
                    System.out.println("           ------------------------------------");
                    System.out.println("           | Lástima                           |");
                    System.out.println("           | Arriba se muestra la tabla        |");
                    System.out.println("           | descubierta                       |");
                    System.out.println("           | Suerte la próxima                 |");
                    System.out.println("___________\\ /--------------------------------");
                    System.out.println("|  _    _  |");
                    System.out.println("| |_|  |_| |");
                    System.out.println("|   ____   |");
                    System.out.println("|__________|");
                }

                else if ( buscaminas.terminar == 2)
                {
                    System.out.println("Bien, Sigue jugando");
                }

            }


        }
    }

    //Se valida que el jugador hata escogido una opcion del menú cuando ya esta jugando
    public static void validadorjugar()
    {
        boolean validInput = false;
        Scanner scan = new Scanner(System.in);
        if(seguirJuego >= 1)
        {

            System.out.println(" 1 = Descubrir una casilla     2 = descubrir una mina      3 = Terminar Partida");
        }
        else
        {

            System.out.println("           ------------------------------------");
            System.out.println("           | Tienes 3 opciones:               |");
            System.out.println("           | 1: Descubrir una casilla         |");
            System.out.println("           | 2: Descubrir una mina            |");
            System.out.println("           | 3: Terminar partida              |");
            System.out.println("           | Escribe tu respuesta:            |");
            System.out.println("___________\\ /--------------------------------");
            System.out.println("|  _    _  |");
            System.out.println("| |_|  |_| |");
            System.out.println("|   ____/  |");
            System.out.println("|__________|");

            seguirJuego++;

        }

        String input = scan.nextLine();
        while (validInput == false)
        {

            try
            {
                buscaminas.jugar = Integer.valueOf(input);
                if (buscaminas.jugar == 1 || buscaminas.jugar == 2 || buscaminas.jugar == 3)
                {
                    validInput = true;

                }
                else
                {
                    System.out.println("Es 1, 2 o 3, escribe de nuevo");
                    input = scan.nextLine();
                }
            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es una opción válida");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }

    //Se valida que el renglón que haya escogido sea numero y esté entre 0 y (numLargo-1)
    public static void validadorJugarRenglon()
    {
        boolean validInput = false;
        Scanner scan = new Scanner(System.in);
        System.out.println(" Escribe el número de renglón");

        String input = scan.nextLine();
        while (validInput == false)
        {

            try
            {
                buscaminas.jugarRenglon = Integer.valueOf(input);
                if (buscaminas.jugarRenglon >= 0 && buscaminas.jugarRenglon < buscaminas.numLargo )
                {
                    validInput = true;

                }
                else
                {
                    int quedan = numLargo-1;
                    System.out.println("Es del 0 al "+quedan+", escribe de nuevo");
                    input = scan.nextLine();
                }
            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es una opción válida");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }

    //Se valida que la columna que haya escogido sea numero y esté entre 0 y (numLargo-1)
    public static void validadorJugarColumna()
    {
        boolean validInput = false;
        Scanner scan = new Scanner(System.in);
        System.out.println(" Escribe el número de columna ");

        String input = scan.nextLine();
        while (validInput == false)
        {

            try
            {
                buscaminas.jugarColumna = Integer.valueOf(input);
                if (buscaminas.jugarColumna >= 0 && buscaminas.jugarColumna< buscaminas.numLargo )
                {
                    validInput = true;

                }
                else
                {
                    int quedan = numLargo-1;
                    System.out.println("Es del 0 al "+quedan+", escribe de nuevo");
                    input = scan.nextLine();
                }
            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es una opción válida");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }

    //Se valida que este seguro que quiere terminar la partida
    public static void validadorTermianrPartida()
    {
        boolean validInput = false;
        Scanner scan = new Scanner(System.in);
        System.out.println(" ¿Seguro que quieres terminar partida?");
        System.out.println(" 1 = Sí    2 = No");

        String input = scan.nextLine();
        while (validInput == false)
        {

            try
            {
                buscaminas.terminar = Integer.valueOf(input);
                if ( buscaminas.terminar == 1  || buscaminas.terminar == 2)
                {
                    validInput = true;

                }
                else
                {
                    int quedan = numLargo-1;
                    System.out.println("Escribe 1 o 2");
                    input = scan.nextLine();
                }
            }

            catch (Exception e)
            {
                System.out.println("Lo que introdujo no es una opción válida");
                System.out.println("Introduce una respuesta válida");
                input = scan.nextLine();
            }
        }
    }

    /*
    Cuando se descubre una casilla vacía, sus casillas alrededor se descubren, solamente
    si no se han descubierto y son diferentes a 0 o igual a null en el arreglo tabla
     */
    public static void descubridor()
    {
        if (jugarRenglon == 0 && jugarColumna == 0)
        {


            if (tablaShow[0 + 1][0] == null)
            {
                if(tabla[0 + 1][0] == null ||tabla[0 + 1][0] != 0)
                {
                    tablaShow[0 + 1][0] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow[0 + 1][0 + 1] == null)
            {
                if (tabla[0 + 1][0 + 1] == null || tabla[0 + 1][0 + 1] != 0)
                {

                    tablaShow[0 + 1][0 + 1] = "v";
                    numCasDescu++;
                }
            }

            if (tablaShow[0][0 + 1] == null)
            {
                if (tabla[0][0 + 1] == null || tabla[0][0 + 1] != 0)
                {
                    tablaShow[0][0 + 1] = "v";
                    numCasDescu++;
                }
            }

        }

        else if (jugarRenglon == numLargo - 1 && jugarColumna== 0)
        {

            if(tablaShow[numLargo -2][0] == null)
            {
                if (tabla [numLargo -2][0] == null || tabla[numLargo -2][0] != 0)
                {
                    tablaShow [numLargo -2][0] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [numLargo -2][0+1] == null)
            {
                if (tabla [numLargo -2][0+1] == null || tabla[numLargo -2][0+1] != 0)
                {
                    tablaShow [numLargo -2][0+1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [numLargo -1][0+1] == null)
            {
                if (tabla [numLargo -1][0+1] == null || tabla [numLargo -1][0+1] != 0)
                {
                    tablaShow [numLargo -1][0+1] = "v";
                    numCasDescu++;
                }
            }
        }
        else if (jugarRenglon == numLargo - 1 && jugarColumna == numLargo - 1)
        {

            if (tablaShow[numLargo - 2][numLargo - 1] == null)
            {
                if (tabla[numLargo - 2][numLargo - 1] == null || tabla[numLargo - 2][numLargo - 1] != 0)
                {
                    tablaShow[numLargo - 2][numLargo - 1] = "v";
                    numCasDescu++;
                }
            }


            if (tablaShow[numLargo - 2][numLargo - 2] == null)
            {
                if (tabla[numLargo - 2][numLargo - 2] == null || tabla[numLargo - 2][numLargo - 2] != 0)
                {
                    tablaShow[numLargo - 2][numLargo - 2] = "v";
                    numCasDescu++;
                }
            }

            if (tablaShow[numLargo - 1][numLargo - 2] == null)
            {
                if (tabla[numLargo - 1][numLargo - 2] == null || tabla[numLargo - 1][numLargo - 2] != 0)
                {
                    tablaShow[numLargo - 1][numLargo - 2] = "v";
                    numCasDescu++;
                }
            }
        }

        else if (jugarRenglon == 0 && jugarColumna == numLargo - 1)
        {

            if(tablaShow [0][numLargo-2] == null)
            {
                if (tabla[0][numLargo - 2] == null ||tabla[0][numLargo - 2] != 0)
                {
                    tablaShow[0][numLargo - 2] = "v";
                    numCasDescu++;
                }
            }


            if(tablaShow [0+1][numLargo-2] == null)
            {
                if (tabla[0 + 1][numLargo - 2] == null || tabla[0 + 1][numLargo - 2] != 0)
                {
                    tablaShow[0 + 1][numLargo - 2] = "v";
                    numCasDescu++;
                }
            }


            if(tablaShow [0+1][numLargo-1]  == null) {
                if (tabla[0 + 1][numLargo - 1] == null || tabla[0 + 1][numLargo - 1] != 0) {
                    tablaShow[0 + 1][numLargo - 1] = "v";
                    numCasDescu++;
                }
            }
        }

        else if (jugarColumna == 0 && jugarRenglon != 0 && jugarRenglon != numLargo-1)
        {

            if(tablaShow [jugarRenglon-1][0]  == null)
            {
                if (tabla[jugarRenglon - 1][0] == null || tabla[jugarRenglon - 1][0] != 0)
                {
                    tablaShow[jugarRenglon - 1][0] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon-1][0+1]  == null)
            {
                if (tabla[jugarRenglon - 1][0 + 1] == null || tabla[jugarRenglon - 1][0 + 1] != 0)
                {
                    tablaShow[jugarRenglon - 1][0 + 1] = "v";
                    numCasDescu++;
                }
            }


            if(tablaShow [jugarRenglon][0+1]   == null)
            {
                if (tabla[jugarRenglon][0 + 1] == null ||tabla[jugarRenglon][0 + 1] != 0)
                {
                    tablaShow[jugarRenglon][0 + 1] = "v";
                    numCasDescu++;
                }
            }


            if(tablaShow [jugarRenglon+1][0+1]   == null)
            {
                if (tabla[jugarRenglon + 1][0 + 1] == null || tabla[jugarRenglon + 1][0 + 1] != 0)
                {
                    tablaShow[jugarRenglon + 1][0 + 1] = "v";
                    numCasDescu++;
                }
            }


            if(tablaShow [jugarRenglon+1][0]   == null)
            {
                if (tabla[jugarRenglon + 1][0] == null || tabla[jugarRenglon + 1][0] != 0)
                {
                    tablaShow[jugarRenglon + 1][0] = "v";
                    numCasDescu++;
                }
            }

        }
        else if (jugarRenglon == 0 && jugarColumna != 0 && jugarColumna != numLargo - 1)
        {

            if(tablaShow [jugarRenglon+1][0]   == null)
            {
                if (tabla[0][jugarColumna - 1] == null || tabla[0][jugarColumna - 1] != 0)
                {
                    tablaShow[0][jugarColumna - 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [0+1][jugarColumna-1]   == null) {

                if (tabla[0 + 1][jugarColumna - 1] == null || tabla[0 + 1][jugarColumna - 1] != 0) {
                    tablaShow[0 + 1][jugarColumna - 1] = "v";
                    numCasDescu++;
                }
            }


            if(tablaShow [0+1][jugarColumna]   == null)
            {
                if (tabla[0 + 1][jugarColumna] == null || tabla[0 + 1][jugarColumna] != 0)
                {
                    tablaShow[0 + 1][jugarColumna] = "v";
                    numCasDescu++;
                }
            }


            if(tablaShow [0+1][jugarColumna+1]   == null)
            {
                if (tabla[0 + 1][jugarColumna + 1] == null || tabla[0 + 1][jugarColumna + 1] != 0)
                {
                    tablaShow[0 + 1][jugarColumna + 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [0][jugarColumna+1] == null)
            {
                if (tabla[0][jugarColumna + 1] == null || tabla[0][jugarColumna + 1] != 0)
                {
                    tablaShow[0][jugarColumna + 1] = "v";
                    numCasDescu++;
                }
            }
        }
        else if (jugarColumna == numLargo - 1 && jugarRenglon != 0 && jugarRenglon != numLargo - 1)
        {
            if(tablaShow [jugarRenglon-1][numLargo-1] == null)
            {
                if (tabla[jugarRenglon - 1][numLargo - 1] == null || tabla[jugarRenglon - 1][numLargo - 1] != 0)
                {
                    tablaShow[jugarRenglon - 1][numLargo - 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon-1][numLargo-2] == null)
            {
                if (tabla[jugarRenglon - 1][numLargo - 2] == null || tabla[jugarRenglon - 1][numLargo - 2] != 0)
                {
                    tablaShow[jugarRenglon - 1][numLargo - 2] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon][numLargo-2] == null)
            {
                if (tabla[jugarRenglon][numLargo - 2] == null || tabla[jugarRenglon][numLargo - 2] != 0)
                {
                    tablaShow[jugarRenglon][numLargo - 2] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon+1][numLargo-2] == null)
            {
                if (tabla[jugarRenglon + 1][numLargo - 2] == null || tabla[jugarRenglon + 1][numLargo - 2] != 0)
                {
                    tablaShow[jugarRenglon + 1][numLargo - 2] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon+1][numLargo-1] == null)
            {
                if (tabla[jugarRenglon + 1][numLargo - 1] == null || tabla[jugarRenglon + 1][numLargo - 1] != 0)
                {
                    tablaShow[jugarRenglon + 1][numLargo - 1] = "v";
                    numCasDescu++;
                }
            }
        }

        else if (jugarRenglon == numLargo - 1 && jugarColumna != 0 && jugarColumna != numLargo - 1)
        {

            if(tablaShow [numLargo-1][jugarColumna-1] == null)
            {
                if (tabla[numLargo - 1][jugarColumna - 1] == null || tabla[numLargo - 1][jugarColumna - 1] != 0)
                {
                    tablaShow[numLargo - 1][jugarColumna - 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [numLargo-2][jugarColumna-1] == null)
            {
                if (tabla[numLargo - 2][jugarColumna - 1] == null || tabla[numLargo - 2][jugarColumna - 1] != 0)
                {
                    tablaShow[numLargo - 2][jugarColumna - 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [numLargo-2][jugarColumna] == null)
            {
                if (tabla[numLargo - 2][jugarColumna] == null || tabla[numLargo - 2][jugarColumna] != 0)
                {
                    tablaShow[numLargo - 2][jugarColumna] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [numLargo-2][jugarColumna+1] == null)
            {
                if (tabla[numLargo - 2][jugarColumna + 1] == null || tabla[numLargo - 2][jugarColumna + 1] != 0)
                {
                    tablaShow[numLargo - 2][jugarColumna + 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [numLargo-1][jugarColumna+1] == null)
            {
                if (tabla[numLargo - 1][jugarColumna + 1] == null || tabla[numLargo - 1][jugarColumna + 1] != 0)
                {
                    tablaShow[numLargo - 1][jugarColumna + 1] = "v";
                    numCasDescu++;
                }
            }
        }
        else
        {
            if(tablaShow [jugarRenglon-1][jugarColumna-1] == null)
            {
                if (tabla[jugarRenglon - 1][jugarColumna - 1] == null || tabla[jugarRenglon - 1][jugarColumna - 1] != 0)
                {
                    tablaShow[jugarRenglon - 1][jugarColumna - 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon][jugarColumna-1] == null)
            {
                if (tabla[jugarRenglon][jugarColumna - 1] == null || tabla[jugarRenglon][jugarColumna - 1] != 0)
                {
                    tablaShow[jugarRenglon][jugarColumna - 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon+1][jugarColumna-1] == null)
            {
                if (tabla[jugarRenglon + 1][jugarColumna - 1] == null || tabla[jugarRenglon + 1][jugarColumna - 1] != 0)
                {
                    tablaShow[jugarRenglon + 1][jugarColumna - 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon+1][jugarColumna] == null)
            {
                if (tabla[jugarRenglon + 1][jugarColumna] == null || tabla[jugarRenglon + 1][jugarColumna] != 0)
                {
                    tablaShow[jugarRenglon + 1][jugarColumna] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon+1][jugarColumna+1] == null)
            {
                if (tabla[jugarRenglon + 1][jugarColumna + 1] == null || tabla[jugarRenglon + 1][jugarColumna + 1] != 0)
                {
                    tablaShow[jugarRenglon + 1][jugarColumna + 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon][jugarColumna+1] == null)
            {
                if (tabla[jugarRenglon][jugarColumna + 1] == null || tabla[jugarRenglon][jugarColumna + 1] != 0)
                {
                    tablaShow[jugarRenglon][jugarColumna + 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon-1][jugarColumna+1] == null)
            {
                if (tabla[jugarRenglon - 1][jugarColumna + 1] == null || tabla[jugarRenglon - 1][jugarColumna + 1] != 0)
                {
                    tablaShow[jugarRenglon - 1][jugarColumna + 1] = "v";
                    numCasDescu++;
                }
            }

            if(tablaShow [jugarRenglon-1][jugarColumna] == null)
            {
                if (tabla[jugarRenglon - 1][jugarColumna] == null || tabla[jugarRenglon - 1][jugarColumna] != 0)
                {
                    tablaShow[jugarRenglon - 1][jugarColumna] = "v";
                    numCasDescu++;
                }
            }
        }


    }

}