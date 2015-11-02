/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisnumerico;

import javax.swing.JOptionPane;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 *
 * @author toto
 */



public class SistemasResultado extends javax.swing.JPanel {

    /**
     * Creates new form SistemasResultado
     */
    public static double[] GaussElimination(int n, double [][]Aug){
        int i,j,k,l; double t; double Y[]=new double[10];
        for(j=0; j<n-1; j++){
            l=j;
            for(i=j+1; i<n; i++){
                if(Math.abs(Aug[i][j])> Math.abs(Aug[l][j]))
                    l=i;
                for(k=j; k<=n; k++){
                    t=Aug[j][k];
                    Aug[j][k]=Aug[l][k];
                    Aug[l][k]=t;
                }
                for(i=j+1; i<n; i++){
                    for(k=n; k>=j; k--){
                        Aug[i][k]-=Aug[j][k]*Aug[i][j]/Aug[j][j];
                    }
                }
            }   
        }
        for(j=n-1; j>=0; j--){
            for(t=0.0,k=j+1;k<n;k++) t+=Aug[j][k]*Y[k];
            Y[j]=(Aug[j][n]-t)/Aug[j][j];
        }
        return Y;
    }
    
    public SistemasResultado(String s, String s1) {
        initComponents();
        
        double Fx[] = new double[10];
        String J[][]=new String[10][10];
        double Jx[][]= new double[10][10];
        String F[]=s.split("\n");
        double X[]= new double[10];
        double Y[]=new double[10];
        int n=0;
        String g=""; 
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i)=='\n'){
                X[n]=Double.parseDouble(g);
                g="";
                n++;
            }else
                g+=s1.charAt(i);
        }
        X[n]=Double.parseDouble(g);
        n=F.length;
        DJep k = new DJep();
        String derivada = "";
        //DJep es la clase encargada de la derivacion en su escencia
        k.addStandardConstants();
        //agrega constantes estandares, pi, e, etc
        k.addStandardFunctions();
        //agrega funciones estandares cos(x), sin(x)
        k.addComplex();
        //por si existe algun numero complejo
        k.setAllowUndeclared(true);
        //permite variables no declarables
        k.setAllowAssignment(true);
        //permite asignaciones
        k.setImplicitMul(true);
        //regla de multiplicacion o para sustraccion y sumas
        k.addStandardDiffRules();
        
        for(int i=0; i<n; i++){
            String fi=F[i];
            for(int j=0; j<n; j++){
                try{
                    //coloca el nodo con una funcion preestablecida
                    Node node = k.parse(fi);
                    //deriva la funcion con respecto a x
                    String x="x"+(char)(j+(int)'1');
                    Node diff = k.differentiate(node,x);
                    //Simplificamos la funcion con respecto a x
                    Node simp = k.simplify(diff);
                    //Convertimos el valor simplificado en un String
                    derivada =k.toString(simp);
                    //imprime la función
                    // j.println(simp);
                } catch(ParseException e){ e.printStackTrace();}
                J[i][j]=derivada;
            }
        }
        jTextArea1.setText("\t\tMatriz Jacobiana:\n\n");
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.printf(J[i][j]+"    ");
                jTextArea1.setText(jTextArea1.getText()+"\t"+J[i][j]);
            }
            jTextArea1.setText(jTextArea1.getText()+"\n");
            System.out.println();
        }
        Evaluador v = new Evaluador();
        
        
        jTextArea1.setText(jTextArea1.getText()+"\n\n\t\tTabla de convergencia:\n\n");
        for(int z=0; z<20; z++){   
            for(int i=0; i<n; i++){
                Fx[i]=v.eval(F[i], X, n);
            }
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    Jx[i][j]=v.eval(J[i][j], X, n);
                }
            }
            double Aug[][]=new double[10][11];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    Aug[i][j]=Jx[i][j];
                }
                Aug[i][n]=Fx[i];
            }
            Y=GaussElimination(n, Aug);
            for(int i=0; i<n; i++){
                X[i]+=-Y[i];
            }
        }
        jTextArea1.setText(jTextArea1.getText()+"\n\n\t\tResultados:\n\n");
        for(int i=0; i<n; i++){
            System.out.println(X[i]);
            jTextArea1.setText(jTextArea1.getText()+"\nx"+(i+1)+": "+X[i]);
        }       
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(343, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
