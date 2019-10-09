/*
 * Linearity
 */
package com.rubio.haro.digital.matrix;

/**
 *
 * @author rodrigo_rubio
 */
public class Solver {

    public Solver() {
    }

    public double[] solveEquationByGaussJordan(Matrix g) throws Exception {
        double[] results = new double[g.getRows()];

        Transformer t = new Transformer();
        resultset r = t.getGaussJordanMatrix(g);
        if (!r.hasError()) {
            if (hasSolution(r.getResult())) {

                g = r.getResult();
                for (int i = 0; i < g.getRows(); i++) {
                    double coefficient = 0;
                    double independent = 0;
                    int cont = 0;
                    for (int j = 0; j < g.getCols(); j++) {
                        if (g.getValue(i, j) != 0) {
                            if (cont == 0) {
                                coefficient = g.getValue(i, j);
                                cont++;
                            } else {
                                independent = g.getValue(i, j);
                                cont++;
                            }
                        }
                    }
                    results[i] = independent / coefficient;
                }
                return results;
            } else {
                throw new Exception("Linear System without solution");
            }
        } else {
            throw new Exception(r.getMessage());
        }

    }

    private Boolean hasSolution(Matrix diagonalMatrix) {
        try {
            for (int i = 0; i < diagonalMatrix.getRows(); i++) {
                int cont = 0;
                for (int j = 0; j < (diagonalMatrix.getCols() - 1); j++) {
                    if (diagonalMatrix.getValue(i, j) == 0) {
                        cont++;
                    }
                }
                if (cont == (diagonalMatrix.getCols()- 1)) {
                    System.out.println("no tiene solucion");
                    return false;
                    
                }
            }
            System.out.println("tiene solucion");
            return true;
        } catch (Exception e) {
            System.out.println("El sistema ha morido");
            throw e;
        }
    }
    
}
