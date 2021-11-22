import java.io.*;
import java.util.*;

public class Leitura {

    private static ArrayList<String> input; // lista referente ao arquivo bd.txt original, na integra

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
        String[] partes = input.get(0).split(" ");
        int xMaxPlanalto = Integer.parseInt(partes[0]);
        int yMaxPlanalto = Integer.parseInt(partes[1]);

        // instanciando novo planalto
        Planalto planalto = new Planalto(xMaxPlanalto, yMaxPlanalto);
        System.out.println("Planalto medindo " + xMaxPlanalto + "x" + yMaxPlanalto + " pronto para ser explorado");

        int xSonda;
        int ySonda;
        String sentido;
        int numeroSonda = -1;

        // Segunda entrada eh a posicao inicial da sonda (x,y), seu sentido inicial e seu numero identificador
        for(int i=1;i<=input.size();i++){
            // se comeca com um numero, refere-se a uma coordenada/sentido de uma nova sonda
            if(Character.isDigit(input.get(i).charAt(0))){
                // Primeira entrada sao as coordenadas maximas do planalto
                String[] coordenadas = input.get(i).split(" ");
                xSonda  = Integer.parseInt(coordenadas[0]);
                ySonda = Integer.parseInt(coordenadas[1]);
                sentido = coordenadas[2];
                numeroSonda = Integer.parseInt(coordenadas[3]);

                Sonda novaSonda = new Sonda(xSonda, ySonda, sentido, numeroSonda);
                //plantar a sonda no planalto
                boolean pousou = planalto.pousarSonda(novaSonda);
                if(pousou) System.out.println("Sucesso ao pousar a sonda de número " + numeroSonda);

                if(!pousou){
                    //se deu erro ao plantar, mudara as coordenadas as coordenadas int para -1 para sinalizar nas saidas a falha
                    novaSonda.setX(-1);
                    novaSonda.setY(-1);

                    planalto.adicionarSonda(novaSonda);
                    System.out.println("Falha ao pousar a sonda de número " + numeroSonda);
                }

                

            }
            else{
                String[] instrucoes = input.get(i).split(" ");
                // se comeca com letra (L,R ou M) refere-se a comandos para a ultima sonda inserida
                planalto.moverSonda(planalto.getSonda(numeroSonda), instrucoes);
            }
        }
    }

    public void exibirResultado(){

    }
}
