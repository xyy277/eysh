package com.aebiz.app.web.commons.utils;

import com.aebiz.app.web.commons.base.Globals;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author: jxx
 * @date: 2017/8/22 9:32
 * 二维码生成工具类
 */
public class QrcodeUtil {
    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 生成二维码
     * @param content 二维码内容
     */
    public static byte[] encode(String content){
        try {
            BitMatrix byteMatrix;
            byteMatrix= new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, Globals.QRCODE_WIDTH, Globals.QRCODE_HEIGHT);
            BufferedImage image = toBufferedImage(byteMatrix);
            ByteArrayOutputStream out =new ByteArrayOutputStream();
            ImageIO.write(image, "png", out);
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK:WHITE);
            }
        }
        return image;
    }

}
