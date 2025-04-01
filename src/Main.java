import Types.*;

public class Main {
    public static void main(String[] args) {
        FloodFillTeste floodFill = new FloodFillTeste();

        // Configura os caminhos dos arquivos
        floodFill.setFileInputPath("flodfill.png");
        floodFill.setFileOutputPath("vid");

        // Define a posição inicial do preenchimento
        floodFill.setFillInitialPosition(90, 90);

        // Executa o algoritmo de Flood Fill
        floodFill.executeFill();
    }
}