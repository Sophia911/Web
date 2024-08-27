package com.se_backend.Interceptor;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class RequestReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public RequestReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = inputStream2String(request.getInputStream()).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
    public String inputStream2String(InputStream inputStream) {
        StringBuilder tempBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tempBody.append(line);
            }
        } catch (IOException e) {
            tempBody.append("get body params fail");
        }
        return tempBody.toString();
    }
}