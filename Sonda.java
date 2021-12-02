public class Sonda {

    private Posicao posicao;
    private Direcao direcao;
    private int numeroSonda; // numero identificador da Sonda

    public Sonda(int X, int Y, Direcao direcao, int numeroSonda) {
        posicao = new Posicao(X, Y);
        this.direcao = direcao;
        this.numeroSonda = numeroSonda;
    }

    public void alterarSentido(String instrucao) {

        // L faz a sonda virar 90 graus para a esquerda, sem mover a sonda.
        if (instrucao.equals("L")) {
            if (direcao.equals(Direcao.N)) {
                this.setDirecao(Direcao.W);// norte muda para oeste
            } else if (direcao.equals(Direcao.W)) {
                this.setDirecao(Direcao.S); // oeste muda para sul
            } else if (direcao.equals(Direcao.S)) {
                this.setDirecao(Direcao.E); // sul muda para leste
            } else {
                this.setDirecao(Direcao.N); // leste muda para norte
            }
        }

        // R faz a sonda virar 90 graus para a direita, sem mover a sonda.
        else {
            if (direcao.equals(Direcao.N)) {
                this.setDirecao(Direcao.E); // norte muda para leste
            } else if (direcao.equals(Direcao.E)) {
                this.setDirecao(Direcao.S); // leste muda para sul
            } else if (direcao.equals(Direcao.S)) {
                this.setDirecao(Direcao.W); // sul muda para oeste
            } else {
                this.setDirecao(Direcao.N); // oeste muda para norte
            }
        }
    }

    // MÉTODOS GETTERS:
    public Posicao getPosicao(){
        return this.posicao;
    }

    public Direcao getDirecao() {
        return this.direcao;
    }

    public int getNumeroSonda() {
        return this.numeroSonda;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}