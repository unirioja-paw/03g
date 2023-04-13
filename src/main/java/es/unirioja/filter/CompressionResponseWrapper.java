package es.unirioja.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CompressionResponseWrapper extends HttpServletResponseWrapper {

    private GZIPServletOutputStream outputStream;
    private PrintWriter writer;

    public CompressionResponseWrapper(HttpServletResponse request) {
        super(request);
    }

    @Override
    public synchronized ServletOutputStream getOutputStream()
            throws IOException {
        if (this.writer != null) {
            throw new IllegalStateException("getWriter() already called.");
        }
        if (this.outputStream == null) {
            this.outputStream
                    = new GZIPServletOutputStream(super.getOutputStream());
        }
        return this.outputStream;
    }

    @Override
    public synchronized PrintWriter getWriter() throws IOException {
        if (this.writer == null && this.outputStream != null) {
            throw new IllegalStateException(
                    "getOutputStream() already called.");
        }
        if (this.writer == null) {
            this.outputStream
                    = new GZIPServletOutputStream(super.getOutputStream());
            this.writer = new PrintWriter(new OutputStreamWriter(
                    this.outputStream, this.getCharacterEncoding()
            ));
        }
        return this.writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (this.writer != null) {
            this.writer.flush();
        } else if (this.outputStream != null) {
            this.outputStream.flush();
        }
        super.flushBuffer();
    }

    @Override
    public void setContentLength(int length) {
    }

    @Override
    public void setContentLengthLong(long length) {
    }

    @Override
    public void setHeader(String name, String value) {
        if (!"content-length".equalsIgnoreCase(name)) {
            super.setHeader(name, value);
        }
    }

    @Override
    public void addHeader(String name, String value) {
        if (!"content-length".equalsIgnoreCase(name)) {
            super.setHeader(name, value);
        }
    }

    @Override
    public void setIntHeader(String name, int value) {
        if (!"content-length".equalsIgnoreCase(name)) {
            super.setIntHeader(name, value);
        }
    }

    @Override
    public void addIntHeader(String name, int value) {
        if (!"content-length".equalsIgnoreCase(name)) {
            super.setIntHeader(name, value);
        }
    }

    public void finish() throws IOException {
        if (this.writer != null) {
            this.writer.close();
        } else if (this.outputStream != null) {
            this.outputStream.finish();
        }
    }

}
