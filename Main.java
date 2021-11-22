import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Leitura script = new Leitura();
            script.explorarMarte();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
