import java.util.*;

public class Planalto {
    
    private int Xmax;
    private int Ymax;
    private ArrayList<Sonda> sondasEmSolo = new ArrayList<Sonda>();

    public Planalto(int X, int Y){
        this.Xmax = X;
        this.Ymax = Y;
    }

    public boolean plantarSonda(Sonda novaSonda){
        //verifica se ja existe alguma sonda onde se quer plantar outra
        if(!checarDestino(novaSonda.getX(), novaSonda.getY())) return false;
    
        //adiciona a nova sonda no Planalto
        adicionarSonda(novaSonda);
        return true;
    }
    
    public void moverSonda(Sonda sonda, ArrayList<String> instrucoes){
        int xSonda = sonda.getX();
        int ySonda = sonda.getY();

        for (String instrucao : instrucoes) {
            if (instrucao.contains("L") || instrucao.contains("R")){
                //faz sonda virar 90 graus para a esquerda(L) ou direita(R), sem mover a sonda.
                sonda.alterarSentido(instrucao);
            }

            // M faz com que a sonda mova-se para a frente um ponto da malha, mantendo a mesma direção.
            else if(instrucao.contains("M")){
              if(sonda.getSentidoAtual() == "N"){
                if (checarDestino(xSonda, ySonda++)){
                    //posicao destino livre
                    sonda.setY(ySonda++); //mover para o NORTE: y++, x nao muda
                }
              }    
              else if(sonda.getSentidoAtual() == "S"){
                if (checarDestino(xSonda, ySonda--)){
                    //posicao destino livre
                    sonda.setY(ySonda--); //mover para o SUL: y--, x nao muda
                }
              }
              else if(sonda.getSentidoAtual() == "E"){
                if (checarDestino(xSonda++, ySonda)){
                    //posicao destino livre
                    sonda.setY(xSonda++); //mover para o LESTE: y nao muda, x++
                }
              }
              else{
                if (checarDestino(xSonda--, ySonda)){
                    //posicao destino livre
                    sonda.setY(xSonda--); //mover para o OESTE: y nao muda, x--
                }
              }
            }
        }

        //atualizando a sonda na lista de sondas em solo no planalto
        for(int i=0; i <= sondasEmSolo.size(); i++){
            Sonda sondaAtualizar = sondasEmSolo.get(i);
            if(sondaAtualizar.getNumeroSonda() == sonda.getNumeroSonda()){
                sondaAtualizar.setX(sonda.getX());
                sondaAtualizar.setY(sonda.getY());
                sondaAtualizar.setSentido(sonda.getSentidoAtual());
            }
        }
    }

    public boolean verificarLimites(int xDestino, int yDestino){
        if(xDestino > this.Xmax || yDestino > this.Ymax){
            //ultrapassou os limites do planalto
            return false;
        }
        return true;
    }

    
    public boolean checarDestino(int xDestino, int yDestino){
        for (Sonda sonda : sondasEmSolo) {
            if ((sonda.getX() == xDestino || sonda.getY() == yDestino) || !verificarLimites(xDestino, yDestino)){
                //ja existe uma sonda na coordenada desejada ou ultrapassou o limite do planalto
                return false;
            }
        }
        return true;
    }

    public void adicionarSonda(Sonda novaSonda){
        this.sondasEmSolo.add(novaSonda);
    }

    //checa se ja tem uma sonda em solo com o mesmo numero identificador
    public boolean existeSonda(int numeroSonda){
        for (Sonda sonda : sondasEmSolo) {
            if (sonda.getNumeroSonda() == numeroSonda) return true;
        }
        return false;
    }


    //METODOS GETTERS:

    public int getX(){
        return this.Xmax;
    }

    public int getY(){
        return this.Ymax;
    }

    public  ArrayList<Sonda> getSondasEmSolo(){
        return this.sondasEmSolo;
    }

}
