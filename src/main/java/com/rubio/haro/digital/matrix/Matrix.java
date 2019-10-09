/*
 * Linearity
 */
package com.rubio.haro.digital.matrix;

/**
 *
 * @author rodrigo_rubio
 */
public class Matrix {
    private String name;
    private Double[][] matrix;

    public Matrix() {
        matrix = new Double[0][0];
    }

    public Matrix(Double[][] matrix) {
        this.matrix = matrix;
    }

    public void setMatrix(Double[][] matrix) {
        this.matrix = matrix;
    }

    public void setValue(int row, int col, Double value) {
        this.matrix[row][col] = value;
    }
    
    public Double getValue(int row, int col) {
        return this.matrix[row][col];
    }

    public Double[][] getMatrix() {
        return matrix;
    }

    /**
     * The method need constructed matrix
     *
     * @return
     */
    public int getRows() {
        return this.matrix.length;
    }

    /**
     * The method need constructed matrix
     *
     * @return
     */
    public int getCols() {
        return this.matrix[0].length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private boolean isValid() {
        try {
            return (getCols() > 0 && getRows() > 0);
        } catch (Exception e) {
            return false;
        }
        
    }

    public void logMatrix() {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";

        try {
            if (isValid()) {
                for (int i = 0; i < getRows(); i++) {
                    for (int j = 0; j < getCols(); j++) {
                        System.out.print(ANSI_GREEN + matrix[i][j] + " , ");
                    }
                    System.out.println("\n");
                }
            } else {
                System.out.print(ANSI_GREEN + " La matrix esta vacia!!! ");
            }
        } catch (Exception e) {
            System.out.print(ANSI_GREEN + " Error en la matrix!!! ");
        }
    }

}
