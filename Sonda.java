public class Sonda {

    private int X; // coordenada X
    private int Y; // coordenada Y
    private Direcao direcao;
    private int numeroSonda; // numero identificador da Sonda

    public Sonda(int X, int Y, Direcao direcao, int numeroSonda) {
        this.X = X;
        this.Y = Y;
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
    public int getY() {
        return this.Y;
    }

    public int getX() {
        return this.X;
    }

    public Direcao getDirecao() {
        return this.direcao;
    }

    public int getNumeroSonda() {
        return this.numeroSonda;
    }

    // METODOS SETTER:
    public void setX(int novoX) {
        this.X = novoX;
    }

    public void setY(int novoY) {
        this.Y = novoY;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}