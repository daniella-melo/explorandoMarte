import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Leitura {

    private static final int IDENTIFICADOR_INVALIDO = -1;
    private static ArrayList<String> input; // lista referente ao arquivo input.txt original, na integra
    public Planalto planalto;

    public Leitura() throws FileNotFoundException {
        input = new ArrayList<String>();
        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNext()) {
            input.add(sc.nextLine());
        }
    }

    public void explorarMarte() throws InterruptedException {
        // Primeira entrada sao as coordenadas maximas do planalto
        String[] partes = input.get(0).split(" ");

        int xMaxPlanalto = Integer.parseInt(partes[0]);
        int yMaxPlanalto = Integer.parseInt(partes[1]);

        // instanciando novo planalto
        planalto = new Planalto(xMaxPlanalto, yMaxPlanalto);
        System.out.println("\n Planalto medindo " + xMaxPlanalto + "x" + yMaxPlanalto + " pronto para ser explorado");

        int xSonda;
        int ySonda;
        Direcao direcao;
        int numeroSonda = IDENTIFICADOR_INVALIDO;

        // Segunda entrada eh a posicao inicial da sonda (x,y), seu direcao inicial e
        // seu numero identificador
        for (int i = 1; i < input.size(); i++) {
            if (linhaDeCoordenadas(i)) {
                String[] coordenadas = input.get(i).split(" ");
                xSonda = Integer.parseInt(coordenadas[0]);
                ySonda = Integer.parseInt(coordenadas[1]);
                direcao =  Direcao.valueOf(coordenadas[2]);

                numeroSonda = Integer.parseInt(coordenadas[3]);

                Sonda novaSonda = new Sonda(xSonda, ySonda, direcao, numeroSonda);

                try {
                    planalto.pousarSonda(novaSonda);
                    System.out.println("\n Sucesso ao pousar a sonda de número " + numeroSonda);
                } catch (Exception e) {
                    System.out.println("\n Falha ao pousar a sonda de número " + numeroSonda + " (" + e + ")");
                    i++; // pular a proxima linha, referente as instrucoes dessa sonda
                    continue;
                }

            } else if (input.get(i).startsWith("D")) {
                String[] decolar = input.get(i).split(" ");
                numeroSonda = Integer.parseInt(decolar[1]);
                try {
                    planalto.decolarSonda(numeroSonda);
                    System.out.println("\n Sucesso ao decolar a sonda de número " + numeroSonda);
                } catch (Exception e) {
                    System.out.println("\n Falha ao decolar a sonda de número " + numeroSonda + " (" + e + ")");
                }
            } else {
                String[] instrucoes = input.get(i).split("");
                // se comeca com letra (L,R ou M) refere-se a comandos para a ultima sonda
                // inserida
                System.out.println("\n Movimentando a sonda de numero " + numeroSonda + " em Marte... ");
                planalto.moverSonda(planalto.getSonda(numeroSonda), instrucoes);
            }
        }
    }

    public void exibirResultado() {
        System.out.println("\n--- SONDAS EM SOLO:  ---");
        for (Sonda sonda : planalto.getSondasEmSolo()) {
            System.out.println("\n---Sonda numero: " + sonda.getNumeroSonda() + " ---");
            System.out.println("X final: " + sonda.getX());
            System.out.println("Y final: " + sonda.getY());
            System.out.println("Sentido: " + sonda.getDirecao());
        }

        System.out.println("\n---Resultado Resumido---");
        for (Sonda sonda : planalto.getSondasEmSolo()) {
            System.out.println(
                    "\n" + sonda.getX() + " " + sonda.getY() + " " + sonda.getDirecao() + " " + sonda.getNumeroSonda());
        }

    }

    // se comeca com um numero, refere-se a uma coordenada/direcao de uma nova sonda
    private boolean linhaDeCoordenadas(int i) {
        return (Character.isDigit(input.get(i).charAt(0)));
    }
}
