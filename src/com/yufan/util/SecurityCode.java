package com.yufan.util;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SecurityCode extends HttpServlet {

    private Font mFont;
    private int lineWidth;
    private int width;
    private int height;
    private int count;

    public SecurityCode() {
        mFont = new Font("Arial", 1, 15);
        lineWidth = 2;
        width = 60;
        height = 20;
        count = 200;
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/gif");
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics2D g = (Graphics2D) image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(mFont);
        g.setColor(getRandColor(0, 20));
        g.drawRect(0, 0, width - 1, height - 1);
        for (int i = 0; i < count; i++) {
            g.setColor(getRandColor(150, 200));
            int x = random.nextInt(width - lineWidth - 1) + 1;
            int y = random.nextInt(height - lineWidth - 1) + 1;
            int xl = random.nextInt(lineWidth);
            int yl = random.nextInt(lineWidth);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand = (new StringBuilder(String.valueOf(sRand))).append(rand).toString();
            g.setColor(new Color(20 + random.nextInt(130), 20 + random.nextInt(130), 20 + random.nextInt(130)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        request.getSession().setAttribute("valideCode", sRand);
        g.dispose();
        ImageIO.write(image, "PNG", response.getOutputStream());
    }


    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String line = buf.readLine();
        if (!line.equals("0")) {
            System.out.println("输入值为:" + line);
        } else {
            System.out.println("退出" + line);
            System.exit(0);
        }
    }
}
