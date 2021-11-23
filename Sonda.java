public class Sonda {

    private int X; // coordenada X
    private int Y; // coordenada Y
    private String sentidoAtual;
    private int numeroSonda; // numero identificador da Sonda

    public Sonda(int X, int Y, String sentidoAtual, int numeroSonda) {
        this.X = X;
        this.Y = Y;
        this.sentidoAtual = sentidoAtual;
        this.numeroSonda = numeroSonda;
    }

    public void alterarSentido(String instrucao) {

        // L faz a sonda virar 90 graus para a esquerda, sem mover a sonda.
        if (instrucao.equals("L")) {
            if (sentidoAtual.equals("N")) {
                this.setSentido("W");// norte muda para oeste
            } else if (sentidoAtual.equals("W")) {
                this.setSentido("S"); // oeste muda para sul
            } else if (sentidoAtual.equals("S")) {
                this.setSentido("E"); // sul muda para leste
            } else {
                this.setSentido("N"); // leste muda para norte
            }
        }

        // R faz a sonda virar 90 graus para a direita, sem mover a sonda.
        else {
            if (sentidoAtual.equals("N")) {
                this.setSentido("E"); // norte muda para leste
            } else if (sentidoAtual.equals("E")) {
                this.setSentido("S"); // leste muda para sul
            } else if (sentidoAtual.equals("S")) {
                this.setSentido("W"); // sul muda para oeste
            } else {
                this.setSentido("N"); // oeste muda para norte
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

    public String getSentidoAtual() {
        return this.sentidoAtual;
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

    public void setSentido(String sentido) {
        this.sentidoAtual = sentido;
    }
}