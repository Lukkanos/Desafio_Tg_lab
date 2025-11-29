public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("Start Here");

        // Escolhe uma matriz solucionada para usar no VALIDADOR (VALIDATOR)
        Integer[][][] solvedMatrix = SudokuSource.getSolvedMatrix(1);

        // ....

        // Escolhe uma matriz NÃO resolvida para usar no SOLUCIONADOR (SOLVER)
        Integer[][][] unsolvedMatrix = SudokuSource.getUnsolvedMatrix(1);
        
        // ....
        for(int m = 1 ; m < 4 ; m++){
            System.out.println("Matriz " + m);
            Integer[][] sudoku = sudokuCompleto(m);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    
                    int num = sudoku[i][j];
                    
                    if (num == 0)
                    continue;
                    
                    boolean resultado = validaLinha(sudoku, i, j, num) || validaLinha(sudoku, i, j, num) || validaQuadrado(sudoku, i, j, num);

                    if (!resultado){
                        System.out.println("Linha " + i + " coluna " + j + " " + num + " inválido\n");
                    }
                }
                
            }
            imprime(sudoku);
        }

    }

    public static Integer[][] sudokuCompleto(int id) {
        Integer[][] sudoku = new Integer[9][9];
        Integer[][][] solved = SudokuSource.getSolvedMatrix(id);

        int index = 0;
        for (int blocoLinha = 0; blocoLinha < 3; blocoLinha++) {
            for (int blocoColuna = 0; blocoColuna < 3; blocoColuna++) {

                Integer[][] bloco = solved[index++];

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        int linha = blocoLinha * 3 + i;
                        int coluna = blocoColuna * 3 + j;

                        sudoku[linha][coluna] = bloco[i][j];
                    }
                }
            }
        }

        return sudoku;
    }

    public static boolean validaLinha(Integer[][] sudoku, int linha, int coluna, int num) {
        for (int i = 0; i < 9; i++) {
            if (i == coluna)
                continue;
            if (sudoku[linha][i] != null && sudoku[linha][i] == num) {
                return false;
            }
        }
        return true;
    }

    public static boolean validaColuna(Integer[][] sudoku, int linha, int coluna, int num) {
        for (int i = 0; i < 9; i++) {
            if (i == linha)
                continue;
            if (sudoku[i][coluna] != null && sudoku[i][coluna] == num) {
                
                return false;
            }
        }
        return true;
    }

    public static boolean validaQuadrado(Integer[][] sudoku, int linha, int coluna, int num) {
        int blocoColuna = coluna - coluna % 3;
        int blocoLinha = linha - linha % 3;
        for (int i = blocoLinha; i < blocoLinha + 3; i++) {
            for (int j = blocoColuna; j < blocoColuna + 3; j++) {
                if (i == linha && j == coluna)
                    continue;
                if (sudoku[i][j] != null && sudoku[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void imprime(Integer[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" ");
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");

    }
}

