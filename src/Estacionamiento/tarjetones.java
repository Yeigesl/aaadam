package Estacionamiento;
  
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


@SuppressWarnings("serial")
public class tarjetones{
	static String controlador= "com.informix.jdbc.IfxDriver";
    static String conection=  "jdbc:informix-sqli://196.11.30.28:48620/estacionamiento:"
    		+ "INFORMIXSERVER=ol_ideaaa;user=tcontrol;password=gestion2009";
 
    
   
    public static  String [][] getValores(int u, int v)
    {
   
    	String[][] fila = null;
    			
    	     String a=Integer.toString(u);
    	     String b=Integer.toString(v);
			 fila=conexion("SELECT no_tarjeta, nombre,descripcion from tcontrol.maestro m \r\n" + 
			 		"JOIN tcontrol.tipo_vehiculo v \r\n" + 
			 		"ON m.id_tipo=v.id_tipo\r\n" + 
			 		"WHERE m.no_tarjeta BETWEEN "+a+"AND "+b);
			 

		return fila;
		
    }
    
    
    public static  String [][] getTodos()
    {
   
    	String[][] fila = null;
    			
    	     
			 fila=conexion("SELECT no_tarjeta, nombre,descripcion from tcontrol.maestro m \r\n" + 
			 		"JOIN tcontrol.tipo_vehiculo v \r\n" + 
			 		"ON m.id_tipo=v.id_tipo\r\n"); 

		return fila;
    }
    
    
    public static  String [][] getUno(int tarjeton)
    {
   
    	String[][] fila = null;
    			
    	     
			 fila=conexion("SELECT no_tarjeta, nombre,descripcion from tcontrol.maestro m \r\n" + 
			 		"JOIN tcontrol.tipo_vehiculo v \r\n" + 
			 		"ON m.id_tipo=v.id_tipo\r\n" +
			 		"WHERE m.no_tarjeta="+tarjeton);

		return fila;
    }
    
    
   
  
 

	public static 
    String[][] ResultSetToArray(ResultSet rs)
    {
        String resultado[][]=null;
 
        try
        {
 
        rs.last();
 
        ResultSetMetaData rsmd = rs.getMetaData();
 
        int numCols = rsmd.getColumnCount();
 
        int numFils =rs.getRow();
 
        resultado=new String[numFils][numCols];
        int j = 0;
 
        rs.beforeFirst();
 
        while (rs.next())
        {
            for (int i=0;i<numCols;i++)
            {
 
                resultado[j][i]=rs.getString(i+1);
              
				
			
            }
            j++;
 
        }
 
        }
        catch(Exception e)
        {
 
        }
      
        return resultado;
    }
 
    private static String[][] conexion(String sql)
    {
 
            try
            {
                    Class.forName(controlador);
               
 
                    Connection con = DriverManager.getConnection(conection);
                    Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                      ResultSet.CONCUR_READ_ONLY);
 
                    ResultSet rs = s.executeQuery(sql);
                    String[][] arr = ResultSetToArray(rs);
                    
 
                    s.close();
                    rs.close();
                    con.close();
 
                    return arr;
 
            }
            catch(Exception e)
            {
                   System.out.println(e.getMessage());
            }
 
            return null;
    }
 
    public static void main(String args[]) {
       
      
    }
}
