import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
          try {
              Leitura script = new Leitura();
              script.explorarMarte();
              script.exibirResultado();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }

    }
}
