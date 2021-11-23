import java.io.*;
import java.util.*;

public class Leitura {

    private static ArrayList<String> input; // lista referente ao arquivo input.txt original, na integra
    public Planalto planalto;

    public Leitura() throws FileNotFoundException {
        input = new ArrayList<String>();
        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNext()) {
            input.add(sc.next());
        }
    }

    public void explorarMarte()
    {
        // Primeira entrada sao as coordenadas maximas do planalto
        String[] partes = input.get(0).split(",");
        int xMaxPlanalto = Integer.parseInt(partes[0]);
        int yMaxPlanalto = Integer.parseInt(partes[1]);

        // instanciando novo planalto
        planalto = new Planalto(xMaxPlanalto, yMaxPlanalto);
        System.out.println("\n Planalto medindo " + xMaxPlanalto + "x" + yMaxPlanalto + " pronto para ser explorado");

        int xSonda;
        int ySonda;
        String sentido;
        int numeroSonda = -1;

        // Segunda entrada eh a posicao inicial da sonda (x,y), seu sentido inicial e seu numero identificador
        for(int i=1;i<input.size();i++){
            // se comeca com um numero, refere-se a uma coordenada/sentido de uma nova sonda
            if(Character.isDigit(input.get(i).charAt(0))){
                // Primeira entrada sao as coordenadas maximas do planalto
                String[] coordenadas = input.get(i).split(",");
                xSonda  = Integer.parseInt(coordenadas[0]);
                ySonda = Integer.parseInt(coordenadas[1]);
                sentido = coordenadas[2];
                numeroSonda = Integer.parseInt(coordenadas[3]);

                Sonda novaSonda = new Sonda(xSonda, ySonda, sentido, numeroSonda);
                //plantar a sonda no planalto
                boolean pousou = planalto.pousarSonda(novaSonda);
                if(pousou) System.out.println("\n Sucesso ao pousar a sonda de número " + numeroSonda);

                if(!pousou){
                    //se deu erro ao plantar, mudara as coordenadas as coordenadas int para -1 para sinalizar nas saidas a falha
                    novaSonda.setX(-1);
                    novaSonda.setY(-1);

                    planalto.adicionarSonda(novaSonda);
                    System.out.println("\n Falha ao pousar a sonda de número " + numeroSonda);
                }
            }
            else{
                String[] instrucoes = input.get(i).split("");
                // se comeca com letra (L,R ou M) refere-se a comandos para a ultima sonda inserida
                System.out.println("\n Movimentando a sonda de numero " + numeroSonda + " em Marte... ");
                planalto.moverSonda(planalto.getSonda(numeroSonda), instrucoes);
            }
        }
    }

    public void exibirResultado(){
        for (Sonda sonda : planalto.getSondasEmSolo()) {
            System.out.println("\n---Sonda numero: " + sonda.getNumeroSonda() + " ---");
            System.out.println("X final: " + sonda.getX());
            System.out.println("Y final: " + sonda.getY());
            System.out.println("Sentido: " + sonda.getSentidoAtual());
        }

        System.out.println("\n---Resultado Resumido---");
        for (Sonda sonda : planalto.getSondasEmSolo()) {
            System.out.println("\n" + sonda.getX() + " " + sonda.getY() + " " + sonda.getSentidoAtual()+ " " + sonda.getNumeroSonda());
        }

    }
}
