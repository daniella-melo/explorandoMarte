import java.util.ArrayList;

public class Planalto {

    private int Xmax;
    private int Ymax;
    private ArrayList<Sonda> sondasEmSolo;

    public Planalto(int X, int Y) {
        this.Xmax = X;
        this.Ymax = Y;

        sondasEmSolo = new ArrayList<Sonda>();
    }

    public boolean pousarSonda(Sonda novaSonda) throws Exception {
        if (this.getSonda(novaSonda.getNumeroSonda()) != null)
            throw new Exception("\n Ja existe uma sonda em solo com o numero indentificador informado");

        if (!checarDestino(novaSonda.getX(), novaSonda.getY(), novaSonda.getNumeroSonda()))
            throw new Exception(
                    "\n Ja existe uma sonda na posicao de pouso desejada ou as coordenadas ultrapassaram os limites do planalto");

        adicionarSonda(novaSonda);
        return true;
    }

    public void moverSonda(Sonda sonda, String[] instrucoes) throws InterruptedException {
        int numeroSonda = sonda.getNumeroSonda();

        for (String instrucao : instrucoes) {
            int xSonda = sonda.getX();
            int ySonda = sonda.getY();

            if (instrucao.equals("L") || instrucao.equals("R")) {
                // faz sonda virar 90 graus para a esquerda(L) ou direita(R), sem mover a sonda.
                sonda.alterarSentido(instrucao);
            }

            // M faz com que a sonda mova-se para a frente um ponto da malha, mantendo a
            // mesma direção.
            else {
                if (sonda.getDirecao().equals(Direcao.N)) {
                    if (checarDestino(xSonda, ySonda + 1, numeroSonda)) {
                        sonda.setY(ySonda + 1); // mover para o NORTE
                    }
                } else if (sonda.getDirecao().equals(Direcao.S)) {
                    if (checarDestino(xSonda, ySonda - 1, numeroSonda)) {
                        sonda.setY(ySonda - 1); // mover para o SUL
                    }
                } else if (sonda.getDirecao().equals(Direcao.E)) {
                    if (checarDestino(xSonda + 1, ySonda, numeroSonda)) {
                        sonda.setX(xSonda + 1); // mover para o LESTE
                    }
                } else {
                    if (checarDestino(xSonda - 1, ySonda, numeroSonda)) {
                        sonda.setX(xSonda - 1); // mover para o OESTE
                    }
                }
            }
            Thread.currentThread().sleep(0, 1);
        }

    }

    public void decolarSonda(int numeroSonda) throws Exception {
        Sonda sonda = this.getSonda(numeroSonda);
        if (sonda == null)
            throw new Exception("\n Nao há uma sonda em solo com o identificador informado");

        removerSonda(sonda);
    }

    private boolean limiteValido(int xDestino, int yDestino) {
        return !(xDestino >= this.Xmax || yDestino >= this.Ymax || xDestino < 0 || yDestino < 0);
    }

    public boolean checarDestino(int xDestino, int yDestino, int numeroSonda) {
        for (Sonda sonda : sondasEmSolo) {
            if ((sonda.getX() == xDestino && sonda.getY() == yDestino) || !limiteValido(xDestino, yDestino)) {
                // ja existe uma sonda na coordenada desejada ou ultrapassou o limite do
                // planalto
                return false;
            }
        }
        return true;
    }

    public void adicionarSonda(Sonda novaSonda) {
        this.sondasEmSolo.add(novaSonda);
    }

    public void removerSonda(Sonda sonda) {
        this.sondasEmSolo.remove(sonda);
    }

    // checa se ja tem uma sonda em solo com o mesmo numero identificador
    public Sonda getSonda(int numeroSonda) {
        for (Sonda sonda : sondasEmSolo) {
            if (sonda.getNumeroSonda() == numeroSonda)
                return sonda;
        }
        return null;
    }

    // METODOS GETTERS:

    public int getX() {
        return this.Xmax;
    }

    public int getY() {
        return this.Ymax;
    }

    public ArrayList<Sonda> getSondasEmSolo() {
        return this.sondasEmSolo;
    }

}
