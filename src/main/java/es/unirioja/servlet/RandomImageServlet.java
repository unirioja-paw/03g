package es.unirioja.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RandomImageServlet", urlPatterns = {"/RandomImageServlet"})
public class RandomImageServlet extends HttpServlet {

    private final int buffSize = 1024;
    private final int availableImageCount = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpg");
        OutputStream out = response.getOutputStream();
        String imageFilename = getRandomImageFilename();
        String imagePath = "/assets/images/" + imageFilename;
        System.out.println("Image path: " + imagePath);
        InputStream in = null;
        try {
            in = getServletContext().getResourceAsStream(imagePath);
            copy(in, out);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        System.out.println("Request done");
    }

    private String getRandomImageFilename() {
        String filename = String.format(
                "random-image_%02d.jpg",
                randomIntBetween(0, availableImageCount - 1)
        );
        return filename;
    }

    private int randomIntBetween(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void copy(InputStream is, OutputStream os) throws IOException {
        if (is == null) {
            System.out.println("Cannot copy: InputStream is null");
            return;
        }
        byte buff[] = new byte[buffSize];
        int leidos = is.read(buff);
        while (leidos != -1) {
            os.write(buff, 0, leidos);
            leidos = is.read(buff);
        }
        os.flush();
    }

}
