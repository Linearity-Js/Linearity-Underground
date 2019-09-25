/*
 * Linearity
 */
package com.rubio.haro.digital.matrix;

/**
 *
 * @author rodrigo_rubio
 */
public class test {

    public static void main(String[] args) {

        //CASE 3:3X3 MATRIX
        Double[][] c = new Double[3][4];
        Matrix g = new Matrix(c);

        g.setValue(0, 0, 1.0);
        g.setValue(0, 1, 1.0);
        g.setValue(0, 2, 1.0);
        g.setValue(0, 3, 1.0);

        g.setValue(1, 0, 2.0);
        g.setValue(1, 1, 1.0);
        g.setValue(1, 2, 1.0);
        g.setValue(1, 3, 1.0);

        g.setValue(2, 0, 2.0);
        g.setValue(2, 1, 2.0);
        g.setValue(2, 2, 1.0);
        g.setValue(2, 3, 1.0);
        
        
        g.logMatrix();

        Transformer t = new Transformer();
        resultset r = t.getGaussJordanMatrix(g);
        Matrix mr;
        if (r.hasError()) {
            System.out.println(r.getMessage());
        } else {
            mr = r.getResult();

            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_YELLOW = "\u001B[33m";

            System.out.println(ANSI_YELLOW + "------------------------------" + ANSI_RESET);
            mr.logMatrix();
        }

        System.out.println("end.............");

    }
}
