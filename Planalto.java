import java.util.*;

public class Planalto {
    
    private int Xmax;
    private int Ymax;
    private ArrayList<Sonda> sondasEmSolo;

    public Planalto(int X, int Y){
        this.Xmax = X;
        this.Ymax = Y;

        sondasEmSolo = new ArrayList<Sonda>();
    }

    public boolean pousarSonda(Sonda novaSonda){
        //se ja existir essa sonda na lista, nao é possivel adicionar outra com o mesmo numero
        if(this.getSonda(novaSonda.getNumeroSonda()) != null) return false;

        //verifica se ja existe alguma sonda onde se quer plantar outra
        if(!checarDestino(novaSonda.getX(), novaSonda.getY(), novaSonda.getNumeroSonda())) return false;
        
        //adiciona a nova sonda no Planalto
        adicionarSonda(novaSonda);
        return true;
    }
    
    public void moverSonda(Sonda sonda, String[] instrucoes){
        int xSonda = sonda.getX();
        int ySonda = sonda.getY();
        int numeroSonda = sonda.getNumeroSonda();

        for (String instrucao : instrucoes) {
            if (instrucao.contains("L") || instrucao.contains("R")){
                //faz sonda virar 90 graus para a esquerda(L) ou direita(R), sem mover a sonda.
                sonda.alterarSentido(instrucao);
            }

            // M faz com que a sonda mova-se para a frente um ponto da malha, mantendo a mesma direção.
            else if(instrucao.contains("M")){
              if(sonda.getSentidoAtual() == "N"){
                if (checarDestino(xSonda, ySonda+1, numeroSonda)){
                    //posicao destino livre
                    sonda.setY(ySonda++); //mover para o NORTE: y++, x nao muda
                }
              }    
              else if(sonda.getSentidoAtual() == "S"){
                if (checarDestino(xSonda, ySonda-1, numeroSonda)){
                    //posicao destino livre
                    sonda.setY(ySonda-1); //mover para o SUL: y--, x nao muda
                }
              }
              else if(sonda.getSentidoAtual() == "E"){
                if (checarDestino(xSonda+1, ySonda, numeroSonda)){
                    //posicao destino livre
                    sonda.setX(xSonda+1); //mover para o LESTE: y nao muda, x++
                }
              }
              else{
                if (checarDestino(xSonda-1, ySonda, numeroSonda)){
                    //posicao destino livre
                    sonda.setX(xSonda-1); //mover para o OESTE: y nao muda, x--
                }
              }
            }
        }

        //atualizando a sonda na lista de sondas em solo no planalto
        // for(int i=0; i <= sondasEmSolo.size(); i++){
        //     Sonda sondaAtualizar = sondasEmSolo.get(i);
        //     if(sondaAtualizar.getNumeroSonda() == sonda.getNumeroSonda()){
        //         sondaAtualizar.setX(sonda.getX());
        //         sondaAtualizar.setY(sonda.getY());
        //         sondaAtualizar.setSentido(sonda.getSentidoAtual());
        //     }
        // }
    }

    public boolean verificarLimites(int xDestino, int yDestino){
        if(xDestino >= this.Xmax || yDestino >= this.Ymax){
            //ultrapassou os limites do planalto
            return false;
        }
        return true;
    }

    
    public boolean checarDestino(int xDestino, int yDestino, int numeroSonda){
        for (Sonda sonda : sondasEmSolo) {
            if (((sonda.getX() == xDestino && sonda.getY() == yDestino) || !verificarLimites(xDestino, yDestino)) && sonda.getNumeroSonda() != numeroSonda){
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
    public Sonda getSonda(int numeroSonda){
        for (Sonda sonda : sondasEmSolo) {
            if (sonda.getNumeroSonda() == numeroSonda) return sonda;
        }
        return null;
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
