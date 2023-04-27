package MySql.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Your name
 */
public class Main {
    public static void main(String... param) throws ClassNotFoundException {
    	
        System.out.println("Starting application...");
        
        Connection conectar = null;
        String sURL = "jdbc:mysql://localhost:3306/proyectofinal";
        
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	
        	try {
        		conectar = DriverManager.getConnection(sURL,"root","1234");
        	JOptionPane.showMessageDialog(null, "Conexión Exitosa");
        	} catch (SQLException ex) {
        		JOptionPane.showMessageDialog(null, "Conexión fallida");
        	}
        	
        	Statement s=null;
        	try {
        			s = conectar.createStatement();
        	}
        	catch (SQLException ex) {
        		//Logger.getLogger(PruebaConexion.class.getName()).log(Level.SEVERE, null, ex);
        	}
        	
        		int val=2;
        		
        		ResultSet rs=null;
        	try {
        		
        		rs = s.executeQuery("(SELECT * FROM proyectofinal.resultado as resultado inner join proyectofinal.equipo as equipo1 on resultado.cod_equipo1=equipo1.cod_equipo inner join proyectofinal.equipo as equipo2 on ( resultado.cod_equipo2=equipo2.cod_equipo) ) ;");
        		JOptionPane.showMessageDialog(null, "Consulta ha sido realizada");
        	}
        	catch (SQLException ex) {
        		JOptionPane.showMessageDialog(null, "Consulta fallida");
        	}
        	
        	Partido rr=null;
        	ArrayList<Partido> arrayPartido =new ArrayList<Partido>();
        	
        	Equipo equipo1 = null;
        	Equipo equipo2 = null;
        	int golesEquipo1 = 0;
       	 	int golesEquipo2 = 0;
       
        	
        	try {
        		while(rs.next()) {
        			
					rr=new Partido(equipo1, golesEquipo1, golesEquipo2, equipo2);
        				equipo1=new Equipo();
        				equipo2=new Equipo();
        				
        			equipo1.nombre=rs.getString(7);
        			equipo1.nombre=rs.getString(10);
        			
        			rr.equipo1=equipo1;
        			rr.equipo2=equipo2;
        			
        			rr.golesEquipo1=rs.getInt(3);
        			rr.golesEquipo2=rs.getInt(4);
        			arrayPartido.add(rr);
        			//System.out.println(rs.getString(7)+" "+rs.getInt(3)+" "+rs.getInt(4)+" "+rs.getString(10));
        		}
     
        		
        	} 
        	catch (SQLException ex) {
        		JOptionPane.showMessageDialog(null, "No se pueden mostrar los datos");
        	}
        	
        	Pronostico pr=null;
        	ArrayList<Pronostico> arrayPronostico =new ArrayList<Pronostico>();
        	
        	int gana1 = 0;
       	 	int empate = 0;
       	 	int gana2 = 0;
        	
        	try {
        		while(rs.next()) {
        			
        			pr=new Pronostico(equipo1, gana1, empate, gana2, equipo2);
        				equipo1=new Equipo();
        				equipo2=new Equipo();
        				
        			equipo1.nombre=rs.getString(8);
        			equipo1.nombre=rs.getString(11);
        			
        			pr.equipo1=equipo1;
        			pr.equipo2=equipo2;
        			
        			pr.gana1=rs.getInt(3);
        			pr.empate=rs.getInt(4);
        			pr.gana2=rs.getInt(5);
        			arrayPronostico.add(pr);
        			//System.out.println(rs.getString(7)+" "+rs.getInt(3)+" "+rs.getInt(4)+" "+rs.getString(10));
        		}
        	}
        	catch (SQLException ex){
        		
        	}
        	
        	
        	System.out.println("Puntaje del Usuario");
			System.out.println("--------------------------------------------");
			int puntos = calcularPuntaje(arrayPartido, arrayPronostico);
			System.out.println("--------------------------------------------");
			System.out.println("Puntaje: " + puntos);
        
    }
    
 // Método para calcular el puntaje
	private static int calcularPuntaje(ArrayList<Partido> arrayPartido, ArrayList<Pronostico> arrayPronostico) {

		int puntaje=0;
		System.out.println(String.format("-%20s -%20s","Resultado", "Pronostico"));
		System.out.println("--------------------------------------------");
		for (int i=0; i< arrayPartido.size(); i++) {
			 Partido unPartido = arrayPartido.get(i);
			 Equipo equipoGanador = null;
			 if (unPartido.getGolesEquipo1() > unPartido.getGolesEquipo2()) {
				 equipoGanador= unPartido.getEquipo1();
			 }
		
			 if(unPartido.getGolesEquipo1()< unPartido.getGolesEquipo2()) {
					 equipoGanador= unPartido.getEquipo2();
				 }
			 
			 
			 Equipo equipoMarcado = null;
			 Pronostico unResultado= arrayPronostico.get(i);
			 if (unResultado.getGana1() == 1) {
				 equipoMarcado= unResultado.getEquipo1();
			 }
			 
			 if (unResultado.getGana2() == 1) {
				 equipoMarcado= unResultado.getEquipo2();
			 }
			 
			 
			if (equipoGanador != null && equipoMarcado != null) {
				if(equipoGanador.getNombre().equals(equipoMarcado.getNombre())) {
					puntaje = puntaje + 1;
				}
				System.out.println(String.format("-%20s -%20s",equipoGanador.getNombre() , equipoMarcado.getNombre()));
			}
			else {
					if(equipoGanador == null && equipoMarcado == null) {
					puntaje = puntaje + 1;
					System.out.println(String.format("-%20s -%20s","Empate","Empate"));
					}
					else {
						if(equipoGanador == null) {
							System.out.println(String.format("-%20s -%20s","Empate" , equipoMarcado.getNombre()));
						}
						if(equipoMarcado == null) {
							System.out.println(String.format("-%20s -%20s",equipoGanador.getNombre() ,"Empate"));
						}
						
					}
					
					
					
				}
			
			
			
			
				
			 }
			
		
		
			return puntaje;
	
	}
    
}