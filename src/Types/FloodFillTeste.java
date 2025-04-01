package Types;

import Types.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FloodFillTeste {
    private String fileInputPath;
    private String fileOutputPath;
    private int[] startPosition;
    private String estrutura = "pilha";

    public void setFileInputPath(String path) {
        this.fileInputPath = path;
    }

    public void setFileOutputPath(String path) {
        this.fileOutputPath = path;
    }

    public void setFillInitialPosition(int x, int y) {
        this.startPosition = new int[]{x, y};
    }

    public void executeFill() {
        try {
            File dir = new File(fileOutputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Fila<int[]> fila = new Fila<>();
            Pilha<int[]> pilha = new Pilha<>();

            BufferedImage picture = ImageIO.read(new File(fileInputPath));
            int width = picture.getWidth();
            int height = picture.getHeight();

            int targetColor = picture.getRGB(startPosition[0], startPosition[1]);
            int fillColor = new Color(255, 0, 0).getRGB();
            int frameCount = 0;

            if (estrutura.equals("fila")) {
                fila.queue(startPosition);
            } else {
                pilha.push(startPosition);
            }

            while (!fila.isEmpty() || !pilha.isEmpty()) {
                frameCount++;
                int[] current = estrutura.equals("fila") ? fila.dequeue() : pilha.pop();

                if (current == null) continue;
                int x = current[0];
                int y = current[1];

                if (picture.getRGB(x, y) != targetColor) continue;

                picture.setRGB(x, y, fillColor);

                if (frameCount % 100 == 0) {
                    File output = new File(fileOutputPath + "/frame_" + frameCount + ".png");
                    ImageIO.write(picture, "png", output);
                }

                if (x + 1 < width) addToStructure(fila, pilha, new int[]{x + 1, y});
                if (x - 1 >= 0) addToStructure(fila, pilha, new int[]{x - 1, y});
                if (y + 1 < height) addToStructure(fila, pilha, new int[]{x, y + 1});
                if (y - 1 >= 0) addToStructure(fila, pilha, new int[]{x, y - 1});
            }

            File output = new File(fileOutputPath + "/result.png");
            ImageIO.write(picture, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToStructure(Fila<int[]> fila, Pilha<int[]> pilha, int[] position) {
        if (estrutura.equals("fila")) {
            fila.queue(position);
        } else {
            pilha.push(position);
        }
    }
}