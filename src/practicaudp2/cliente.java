package practicaudp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class cliente {

    public static void main(String[] args) {
        
        final int puerto_servidor = 5001;
        int many = 0;
        byte[] buffer = new byte[1024];
        
        
        try {
            do{
                InetAddress direccionServidor = InetAddress.getByName("Aqui va la ip del servidor");
                DatagramSocket socketUDP = new DatagramSocket();

                String mensaje;//variable donde se almacenara la cadena de caracteres
                Scanner teclado = new Scanner(System.in);//sentencia para poder leer desde el teclado
                System.out.println("Ingrese una cadena de caracteres: ");//imprimimos por pantalla un mensaje
                mensaje = teclado.nextLine();//leemos dentro de la variable mensaje la cadena 
                
                if(mensaje.isEmpty() || mensaje == null){
                    System.out.println("Se detuvo el prpceso del cliente");
                    socketUDP.close();
                    break;
                }
                buffer = mensaje.getBytes();//obtenemos el tamaño de la cadena

                DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto_servidor);

                System.out.println("Se envio el datagrama!");
                socketUDP.send(pregunta);

                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                socketUDP.receive(peticion);

                System.out.println("Se recibio la peticion!");
                mensaje = new String(peticion.getData());//leemos en la variable mensaje la respuesta que el servidor envio
           
                System.out.println(mensaje);//mostramos el mensaje(respuesta) recibida
            }while(many == 0);
            
            
        } catch (SocketException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
