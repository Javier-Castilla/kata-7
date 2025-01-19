package software.ulpgc.kata4.apps.windows.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

public class DatasetDownloader {

    public static String checkFile() {
        String directoryName = "data";
        String fileName = "title.basics.tsv";
        String filePath = directoryName + File.separator + fileName;

        String urlGzip = "https://datasets.imdbws.com/title.basics.tsv.gz";
        String temporalGzipPath = "archivo_temporal.gz";

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("El archivo '" + fileName + "' no existe. Descargando archivo GZIP...");

            try {
                downloadDataset(urlGzip, temporalGzipPath);
                System.out.println("Archivo GZIP descargado con éxito.");

                File directory = new File(directoryName);
                if (!directory.exists()) {
                    directory.mkdirs();
                    System.out.println("Carpeta destino creada: " + directoryName);
                }

                decompressDataset(temporalGzipPath, filePath);
                System.out.println("Archivo descomprimido en '" + filePath + "'.");

                Files.delete(Paths.get(temporalGzipPath));
                System.out.println("Archivo GZIP temporal eliminado.");
            } catch (Exception e) {
                System.err.println("Ocurrió un error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo '" + fileName + "' ya existe en la carpeta '" + directoryName + "'.");
        }
        return directoryName + "/" + fileName;
    }

    private static void downloadDataset(String urlStr, String destination) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int readBytes;
            while ((readBytes = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }
        }
    }

    private static void decompressDataset(String gzipPath, String destination) throws IOException {
        try (GZIPInputStream gzipIn = new GZIPInputStream(new FileInputStream(gzipPath));
             FileOutputStream fileOut = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int readBytes;
            while ((readBytes = gzipIn.read(buffer)) != -1) {
                fileOut.write(buffer, 0, readBytes);
            }
        }
    }
}
