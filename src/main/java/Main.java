import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    static ReentrantLock Banio1lock =new ReentrantLock();
    static ReentrantLock Banio2lock =new ReentrantLock();
    static ArrayList <Jugador> listaJugadores =new ArrayList<>();

            public static void main(String[]args){
                for (int i = 0; i < 10; i++){
                  listaJugadores.add(new Jugador("nombre = "+i ));
                }
                for (int i = 0; i < 10; i++){
                    listaJugadores.get(i).start();
                }
            }
}


class Jugador extends Thread {
  private String nombre;
  private boolean pisEncima=false;
    Jugador(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public void run () {
        if (Main.Banio1lock.tryLock()) {
            System.out.println("EL jugador" + nombre + "ha entrado al baño");
            pisEnelBanio();
            System.out.println("EL jugador " + nombre + " ha terminado en el baño");
            Main.Banio1lock.unlock();
        } else {
            if (Main.Banio2lock.tryLock()) {

            System.out.println("EL jugador" + nombre + "ha entrado al baño 2");
            pisEnelBanio();
            System.out.println("EL jugador" + nombre + "ha terminado en el baño 2");
            Main.Banio2lock.unlock();
        }else{
            System.out.println("El jugador " + nombre + " no ha entrado al baño");
            hacerPisEncima();
            System.out.println("El jugador " + nombre + " se ha hecho pis encima ");
        }
    }
    }




    public void pisEnelBanio(){
        try {
            Thread.sleep(3000);
             pisEncima = false;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void hacerPisEncima(){
        try {
            Thread.sleep(3000);
           pisEncima = false;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
