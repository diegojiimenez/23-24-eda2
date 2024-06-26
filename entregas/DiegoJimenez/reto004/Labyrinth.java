import java.util.Scanner;

public class Labyrinth {

    private static final int FREE = 0;
    private static final int WALL = 1;
    private static final int PATH = 2;
    private static final int VISITED = 3;

    private static String[] celda = {" · ", "[ ]", " * ", " x "};

    public static boolean solveMaze(int[][] maze, int x, int y) {
        
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
            System.out.println("Fuera de límites: No se puede avanzar a la posición (" + x + ", " + y + ")");
            return false;
        }
      
        if (maze[x][y] != FREE) {
            System.out.println("Pared o bloqueo: No se puede avanzar a la posición (" + x + ", " + y + ")");
            return false;
        }
       
        if (x == maze.length - 1 && y == maze[0].length - 1) {
            maze[x][y] = PATH;
            System.out.println("¡Meta alcanzada! Se ha encontrado un camino exitoso.");
            printMaze(maze);
            return true;
        }
        
        maze[x][y] = PATH;
        printMaze(maze);

       
        System.out.println("Tratando de moverse a la posición (" + (x + 1) + ", " + y + ")");
        if (solveMaze(maze, x + 1, y)) return true;
        System.out.println("Tratando de moverse a la posición (" + x + ", " + (y + 1) + ")");
        if (solveMaze(maze, x, y + 1)) return true;
        System.out.println("Tratando de moverse a la posición (" + (x - 1) + ", " + y + ")");
        if (solveMaze(maze, x - 1, y)) return true;
        System.out.println("Tratando de moverse a la posición (" + x + ", " + (y - 1) + ")");
        if (solveMaze(maze, x, y - 1)) return true;

        
        maze[x][y] = VISITED; 
        System.out.println("Backtracking: Volviendo a la posición anterior (" + x + ", " + y + ")");
        printMaze(maze); 
        return false;
    }

    public static void printMaze(int[][] maze) {
        System.out.println("===".repeat(maze[0].length));
        for (int[] row : maze) {
            for (int col : row) {
                System.out.print(celda[col]);
            }
            System.out.println();
        }
        System.out.println("===".repeat(maze[0].length));
    }

    public static void main(String[] args) {
        int[][] maze = {
            {0,1,0,0,1,0,0,1,0,0,1,0,0,0},
            {0,1,0,1,1,0,1,1,0,1,1,0,1,0},
            {0,1,0,1,0,0,1,0,0,1,0,0,1,0},
            {0,1,0,1,0,0,0,0,0,1,0,0,1,0},
            {0,1,0,1,1,0,1,1,0,1,1,0,1,0},
            {0,0,0,1,0,0,1,0,0,0,0,0,1,0},
            {1,1,0,1,0,0,1,1,0,1,0,0,1,0},
            {0,0,0,0,0,1,0,0,0,0,0,1,0,0}
        };

        System.out.println("Laberinto:");
        printMaze(maze);
        
        System.out.println("Pulse una tecla para intentar resolver el laberinto...");
        new Scanner(System.in).nextLine();

        if (solveMaze(maze, 0, 0)) {
            System.out.println("Ruta de escape:");
            printMaze(maze);
        } else {
            System.out.println("No se puede escapar!!!.");
        }
    }
}
