import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
          try {
              Leitura script = new Leitura();
              script.explorarMarte();
              script.exibirResultado();
              Teste teste = new Teste();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }

    }
}
