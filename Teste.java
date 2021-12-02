public class Teste {
    private Planalto planalto;

    public Teste() throws InterruptedException {
        System.out.println("\n-----------TESTE------------");
        testaMoverSonda();
    }

    public void testaMoverSonda() throws InterruptedException {
        planalto = new Planalto(5, 6);
        Sonda sonda1 = new Sonda(0, 0, Direcao.E, 1);
        Sonda sonda2 = new Sonda(5, 2, Direcao.W, 2);

        String[] instrucoes1 = new String[8];
        instrucoes1[0] = "M";
        instrucoes1[1] = "L";
        instrucoes1[2] = "M";
        instrucoes1[3] = "M";
        instrucoes1[4] = "R";
        instrucoes1[5] = "M";
        instrucoes1[6] = "M";
        instrucoes1[7] = "L";

        String[] instrucoes2 = new String[8];
        instrucoes2[0] = "M";
        instrucoes2[1] = "M";
        instrucoes2[2] = "R";
        instrucoes2[3] = "M";
        instrucoes2[4] = "L";
        instrucoes2[5] = "M";
        instrucoes2[6] = "M";
        instrucoes2[7] = "L";

        String resultadoEsperado1 = "3 2 N 1";
        String resultadoEsperado2 = "2 3 S 2";

        planalto.adicionarSonda(sonda1);
        planalto.adicionarSonda(sonda2);

        planalto.moverSonda(sonda1, instrucoes1);
        planalto.moverSonda(sonda2, instrucoes2);

        String resultado1 = formatarResultado(sonda1);
        String resultado2 = formatarResultado(sonda2);

        if (resultado1.equals(resultadoEsperado1) && resultado2.equals(resultadoEsperado2)) {
            System.out.println("Teste moverSonda() OK");
        } else {
            System.out.println("Teste moverSonda() FALHOU");
        }
    }

    public String formatarResultado(Sonda sonda) {
        return sonda.getX() + " " + sonda.getY() + " " + sonda.getDirecao() + " " + sonda.getNumeroSonda();
    }
}