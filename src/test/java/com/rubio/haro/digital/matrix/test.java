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
        Double[][] c = new Double[3][3];
        Matrix g = new Matrix(c);

        /*
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
        
        g.setFieldValue(0, 0, 2);
        g.setFieldValue(0, 1, -3);
        g.setFieldValue(0, 2, 0);
        g.setFieldValue(0, 3, 8);
        
        g.setFieldValue(1, 0, 4);
        g.setFieldValue(1, 1, -5);
        g.setFieldValue(1, 2, 1);
        g.setFieldValue(1, 3, 15);
        
        g.setFieldValue(2, 0,2);
        g.setFieldValue(2, 1, 0);
        g.setFieldValue(2, 2, 4);
        g.setFieldValue(2, 3, 1);
        
         */
        g.setValue(0, 0, 2.0);
        g.setValue(0, 1, -3.0);
        g.setValue(0, 2, 0.0);
        //g.setValue(0, 3, 8.0);

        g.setValue(1, 0, 4.0);
        g.setValue(1, 1, -5.0);
        g.setValue(1, 2, 1.0);
        //g.setValue(1, 3, 15.0);

        g.setValue(2, 0, 2.0);
        g.setValue(2, 1, 0.0);
        g.setValue(2, 2, 4.0);
        //g.setValue(2, 3, 1.0);

        g.logMatrix();

        Transformer t =  new Transformer();
        ///t.getGaussJordanMatrix(g).getResult().logMatrix();
        t.getTransposeMatrix(g);
        /* try {
                Solver s = new Solver();
                double[] sol;
                
                sol = s.solveEquationByGaussJordan(g);
                System.out.println("solutions");
                for (double d : sol) {
                    System.out.println(d);
                }
                
                final String ANSI_RESET = "\u001B[0m";
                final String ANSI_YELLOW = "\u001B[33m";
                
                System.out.println(ANSI_YELLOW + "------------------------------" + ANSI_RESET);
            } catch (Exception ex) {
                System.err.println("error cachado");
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        System.out.println("end.............");

    }
}
