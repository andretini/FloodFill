import Types.*;

import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.io.*;
import java.util.zip.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        try {
            String estrutura = "pilha";
            String fileInputName = "flodfill.png";
            String fileOutputFolder = "vid";
            String fileOutputName = "flodfill_result";

            File dir = new File(fileOutputFolder);

            if (!dir.exists()) {
                dir.mkdirs();
            }
            Fila<int[]> fila = new Fila<int[]>();
            Pilha<int[]> pilha = new Pilha<int[]>();

            BufferedImage picture = ImageIO.read(new File(fileInputName));
            int width = picture.getWidth();
            int height = picture.getHeight();

            int[] start = { 25, 25 };
            int targetColor = picture.getRGB(start[0], start[1]);

            int fillColor = new Color(255, 0, 0).getRGB();
            int frameCount = 0;

            if (estrutura.equals("fila")) {
                fila.queue(start);
            }
            else if(estrutura.equals("pilha")){
                pilha.push(start);
            }
            while (!fila.isEmpty() || !pilha.isEmpty()) {
                frameCount += 1;

                int[] current = new int[] {0, 0};

                if (estrutura.equals("fila")) {
                    current = fila.dequeue();
                }
                else if(estrutura.equals("pilha")){
                    current = pilha.pop();
                }

                int x = current[0];
                int y = current[1];

                System.out.println("X: " + x + " -> y: " + y);
                if (picture.getRGB(x, y) != targetColor) {
                    continue;
                }

                picture.setRGB(x, y, fillColor);

                if (frameCount % 100 == 0) {
                    File output = new File(fileOutputFolder + "/frame_" + frameCount + ".png");
                    ImageIO.write(picture, "png", output);
                }

                if (estrutura.equals("fila")) {
                    if (x + 1 < width)
                        fila.queue(new int[] { x + 1, y });
                    if (x - 1 >= 0)
                        fila.queue(new int[] { x - 1, y });
                    if (y + 1 < height)
                        fila.queue(new int[] { x, y + 1 });
                    if (y - 1 >= 0)
                        fila.queue(new int[] { x, y - 1 });

                }
                else if(estrutura.equals("pilha")){
                    if (x + 1 < width)
                        pilha.push(new int[] { x + 1, y });
                    if (x - 1 >= 0)
                        pilha.push(new int[] { x - 1, y });
                    if (y + 1 < height)
                        pilha.push(new int[] { x, y + 1 });
                    if (y - 1 >= 0)
                        pilha.push(new int[] { x, y - 1 });
                }

            }
            File output = new File(fileOutputName + ".png");
            ImageIO.write(picture, "png", output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}