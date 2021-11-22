import java.util.*;

public class Sonda{

    private int X; //coordenada X
    private int Y; //coordenada Y
    private String sentidoAtual;
    private int numeroSonda; //numero identificador da Sonda


        //Cada sonda é representada por duas linhas. A primeira indica sua posição inicial e a segunda uma série de instruções indicando para a sonda como ela deverá explorar o planalto.

        //A posição é representada por dois inteiros e uma letra separados por espaços, correpondendo à coordenada X-Y e à direção da sonda. Cada sonda será controlada sequencialmente, o que quer dizer que a segunda sonda só irá se movimentar após que a primeira tenha terminado suas instruções.

    public Sonda(int X, int Y, String sentidoAtual, int numeroSonda){
        this.X = X;
        this.Y = Y;
        this.sentidoAtual = sentidoAtual;
        this.numeroSonda = numeroSonda;
    }

    public void alterarSentido(String instrucao){

        // L faz a sonda virar 90 graus para a esquerda, sem mover a sonda.
        if(instrucao.contains("L")){
            if (sentidoAtual == "N") setSentido("W"); //norte muda para oeste
            else if(sentidoAtual == "W") setSentido("S"); //oeste muda para sul
            else if(sentidoAtual == "S") setSentido("E"); //sul muda para leste
            else setSentido("N"); //leste muda para norte
        }
        
        // R faz a sonda virar 90 graus para a direita, sem mover a sonda.
        else if(instrucao.contains("R")){
            if (sentidoAtual == "N") setSentido("E"); //norte muda para leste
            else if(sentidoAtual == "E") setSentido("S"); //leste muda para sul
            else if(sentidoAtual == "S") setSentido("W"); //sul muda para oeste
            else setSentido("N"); //oeste muda para norte
        }
    }

    //MÉTODOS GETTERS:
    public int getY(){
        return this.Y;
    }

    public int getX(){
        return this.X;
    }

    public String getSentidoAtual(){
        return this.sentidoAtual;
    }

    public int getNumeroSonda(){
        return this.numeroSonda;
    }

    //METODOS SETTER publicos
    public void setX(int novoX){
        this.X = novoX;
    }

    public void setY(int novoY){
        this.Y = novoY;
    }

    public void setSentido(String sentido){
        this.sentidoAtual = sentido;
    }
}