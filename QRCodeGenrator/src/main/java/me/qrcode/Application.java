package me.qrcode;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import me.qrcode.model.Order;

public class Application {
	
	 private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

	    private static void generateQRCodeImage(String text, int width, int height, String filePath)
	            throws WriterException, IOException {
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

	        Path path = FileSystems.getDefault().getPath(filePath);
	        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	    }

	    public static void main(String[] args) {
	    	Order orderProducer = new Order(1,"saaz500",3);
	    	Gson gson = new Gson();
	    	String produceString = gson.toJson(orderProducer);
	    	Order orderConsumer = gson.fromJson(produceString, Order.class);
	    	
	    	System.out.println(orderConsumer);
	    	
	    	
	        try {
	            generateQRCodeImage(gson.toJson(orderProducer), 350, 350, QR_CODE_IMAGE_PATH);
	        } catch (WriterException e) {
	            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
	        } catch (IOException e) {
	            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
	        }
	    }
	}