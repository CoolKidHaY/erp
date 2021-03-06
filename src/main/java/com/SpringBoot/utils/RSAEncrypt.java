package com.SpringBoot.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



public class RSAEncrypt {
    private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
    /** 初始化密钥对生成器，密钥大小为96-1024位   */
    private static final int KEYLEN = 1024;
    /** 
     * 签名算法 
     */  
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";  
    
    /** 
     * 随机生成密钥对 
     */  
    public static void genKeyPair(String filePath) {  
    	KeyPairGenerator keyPairGen = null;
    	try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
    	// 初始化密钥对生成器，密钥大小为96-1024位
    	keyPairGen.initialize(KEYLEN,new SecureRandom()); 
        // 生成一个密钥对，保存在keyPair中  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        // 得到私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        // 得到公钥  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); 
        try {  
            // 得到公钥字符串  
            String publicKeyString = Base64.encode(publicKey.getEncoded());
            // 得到私钥字符串  
            String privateKeyString = Base64.encode(privateKey.getEncoded());
            // 将密钥对写入到文件  
            FileWriter pubfw = new FileWriter(filePath + "/publicKey.keystore");  
            FileWriter prifw = new FileWriter(filePath + "/privateKey.keystore");  
            BufferedWriter pubbw = new BufferedWriter(pubfw);  
            BufferedWriter pribw = new BufferedWriter(prifw);  
            pubbw.write(publicKeyString);  
            pribw.write(privateKeyString);  
            pubbw.flush();  
            pubbw.close();  
            pubfw.close();  
            pribw.flush();  
            pribw.close();  
            prifw.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }
    
    /** 
     * 从文件中输入流中加载公钥 
     *  
     * @param in 
     *            公钥输入流 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */  
    public static String loadPublicKeyByFile(String path) throws Exception {  
        try {  
            BufferedReader br = new BufferedReader(new FileReader(path + "/publicKey.keystore"));  
            String readLine = null;  
            StringBuilder sb = new StringBuilder();  
            while ((readLine = br.readLine()) != null) {  
                sb.append(readLine);  
            }  
            br.close();  
            return sb.toString();  
        } catch (IOException e) {  
            throw new Exception("公钥数据流读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥输入流为空");  
        }  
    }  
    

    //-------------------------------------------------使用公钥私钥   加解密    开始------------------------------------------------   
    /** 
     * 公钥加密过程 
     *  
     * @param publicKey 
     *            公钥 
     * @param plainTextData 
     *            明文数据 
     * @return 
     * @throws Exception 
     *             加密过程中的异常信息 
     */  
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)  
            throws Exception {  
        if (publicKey == null) {  
            throw new Exception("加密公钥为空, 请设置");  
        }  
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            byte[] output = cipher.doFinal(plainTextData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此加密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("加密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("明文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("明文数据已损坏");  
        }  
    }  
  
    /** 
     * 私钥加密过程 
     *  
     * @param privateKey 
     *            私钥 
     * @param plainTextData 
     *            明文数据 
     * @return 
     * @throws Exception 
     *             加密过程中的异常信息 
     */  
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData)  
            throws Exception {  
        if (privateKey == null) {  
            throw new Exception("加密私钥为空, 请设置");  
        }  
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
            byte[] output = cipher.doFinal(plainTextData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此加密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("加密私钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("明文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("明文数据已损坏");  
        }  
    }  
  
    /** 
     * 私钥解密过程 
     *  
     * @param privateKey 
     *            私钥 
     * @param cipherData 
     *            密文数据 
     * @return 明文 
     * @throws Exception 
     *             解密过程中的异常信息 
     */  
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)  
            throws Exception {  
        if (privateKey == null || cipherData == null) {  
            //throw new Exception("解密私钥为空, 请设置");
        	return new byte[0];
        }  
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            byte[] output = cipher.doFinal(cipherData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此解密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("解密私钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("密文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("密文数据已损坏");  
        }  
    }  
  
    /** 
     * 公钥解密过程 
     *  
     * @param publicKey 
     *            公钥 
     * @param cipherData 
     *            密文数据 
     * @return 明文 
     * @throws Exception 
     *             解密过程中的异常信息 
     */  
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)  
            throws Exception {  
        if (publicKey == null) {  
            throw new Exception("解密公钥为空, 请设置");  
        }  
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());  
            cipher.init(Cipher.DECRYPT_MODE, publicKey);  
            byte[] output = cipher.doFinal(cipherData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此解密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("解密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("密文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("密文数据已损坏");  
        }  
    }  
    //-------------------------------------------------使用公钥私钥   加解密    结束------------------------------------------------
  
    
    
    
    //-------------------------------------------------读取公钥私钥   开始------------------------------------------------
    /** 
     * 从字符串中加载公钥 
     *  
     * @param publicKeyStr 
     *            公钥数据字符串 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */  
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)  
            throws Exception {  
        try {  
            byte[] buffer = Base64.decode(publicKeyStr);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("公钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥数据为空");  
        }  
    }  
    
    /** 
     * 从文件中加载私钥 
     *  
     * @param keyFileName 
     *            私钥文件名 
     * @return 是否成功 
     * @throws Exception 
     */  
    public static String loadPrivateKeyByFile(String path) throws Exception {  
        try {  
            BufferedReader br = new BufferedReader(new FileReader(path  
                    + "/privateKey.keystore"));  
            String readLine = null;  
            StringBuilder sb = new StringBuilder();  
            while ((readLine = br.readLine()) != null) {  
                sb.append(readLine);  
            }  
            br.close();  
            return sb.toString();  
        } catch (IOException e) {  
            throw new Exception("私钥数据读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥输入流为空");  
        }  
    }  
  
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)  
            throws Exception {  
        try {  
            byte[] buffer = Base64.decode(privateKeyStr);  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("私钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥数据为空");  
        }  
    } 
    //-------------------------------------------------读取公钥私钥   结束------------------------------------------------
    
    
    //-------------------------------------------------RSA 签名验证   开始----------------------------------------------
    /** 
     * RSA签名 
     * @param content 待签名数据 
     * @param privateKey 商户私钥 
     * @param encode 字符集编码 
     * @return 签名值 
     */  
     public static String sign(String content, String privateKey, String encode)  
     {  
         try   
         {  
             PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec( Base64.decode(privateKey) );   
               
             KeyFactory keyf                 = KeyFactory.getInstance("RSA");  
             PrivateKey priKey               = keyf.generatePrivate(priPKCS8);  
   
             java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);  
   
             signature.initSign(priKey);  
             signature.update( content.getBytes(encode));  
   
             byte[] signed = signature.sign();  
               
             return Base64.encode(signed);  
         }  
         catch (Exception e)   
         {  
             e.printStackTrace();  
         }  
           
         return null;  
     }  
       
     public static String sign(String content, String privateKey)  
     {  
         try   
         {  
             PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec( Base64.decode(privateKey) );   
             KeyFactory keyf = KeyFactory.getInstance("RSA");  
             PrivateKey priKey = keyf.generatePrivate(priPKCS8);  
             java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);  
             signature.initSign(priKey);  
             signature.update( content.getBytes());  
             byte[] signed = signature.sign();  
             return Base64.encode(signed);  
         }  
         catch (Exception e)   
         {  
             e.printStackTrace();  
         }  
         return null;  
     }  
       
     /** 
     * RSA验签名检查 
     * @param content 待签名数据 
     * @param sign 签名值 
     * @param publicKey 分配给开发商公钥 
     * @param encode 字符集编码 
     * @return 布尔值 
     */  
     public static boolean doCheck(String content, String sign, String publicKey,String encode)  
     {  
         try   
         {  
             KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
             byte[] encodedKey = Base64.decode(publicKey);  
             PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));  
   
           
             java.security.Signature signature = java.security.Signature  
             .getInstance(SIGN_ALGORITHMS);  
           
             signature.initVerify(pubKey);  
             signature.update( content.getBytes(encode) );  
           
             boolean bverify = signature.verify( Base64.decode(sign) );  
             return bverify;  
               
         }   
         catch (Exception e)   
         {  
             e.printStackTrace();  
         }  
           
         return false;  
     }  
       
     public static boolean doCheck(String content, String sign, String publicKey)  
     {  
         try   
         {  
             KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
             byte[] encodedKey = Base64.decode(publicKey);  
             PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));  
   
           
             java.security.Signature signature = java.security.Signature  
             .getInstance(SIGN_ALGORITHMS);  
           
             signature.initVerify(pubKey);  
             signature.update( content.getBytes() );  
           
             boolean bverify = signature.verify( Base64.decode(sign) );  
             return bverify;  
               
         }   
         catch (Exception e)   
         {  
             e.printStackTrace();  
         }  
           
         return false;  
     } 
    
    //-------------------------------------------------RSA 签名验证  结束------------------------------------------------
    
    
    
    /** 
     * 字节数据转十六进制字符串 
     *  
     * @param data 
     *            输入数据 
     * @return 十六进制内容 
     */  
    public static String byteArrayToString(byte[] data) {  
        StringBuilder stringBuilder = new StringBuilder();  
        for (int i = 0; i < data.length; i++) {  
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移  
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);  
            // 取出字节的低四位 作为索引得到相应的十六进制标识符  
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);  
            if (i < data.length - 1) {  
                stringBuilder.append(' ');  
            }  
        }  
        return stringBuilder.toString();  
    }  
    
    
    /**
     * 验签
     * 
     * @throws Exception
     */
    public static boolean isSign(String filepath){
    	boolean flag = false;
        try {
			System.out.println("---------------私钥签名过程------------------");  
			String content="ihep_这是用于签名的原始数据";  
			String signstr=sign(content,RSAEncrypt.loadPrivateKeyByFile(filepath));  
			System.out.println("签名原串："+content);  
			System.out.println("签名串："+signstr);  
			
			System.out.println("---------------公钥校验签名------------------");  
			System.out.println("验签结果："+(flag = doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath))));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
    	return flag;
    }

    //全局佳美解密，使用方法----------------------------------------开始
    /*前台公钥加密，后台私钥解密*/
    public static String decrypt(String r) throws Exception {
    	String prikey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKe42Z++b9d5zg1Tf1OMiTBmL0Hg3DI5NUnTdfTpXv6BHisGgVFepaQ0csLrlnavqekJzHrKBxznLYizANgGHiXmZLHt8vGFY5JbEw6Sx1GQyCK8mYhkAt5pu93sbkW8cPR70tfZ1DCPLlYcVyRMriUIKaQrL/qVNmMNl7yAXsvvAgMBAAECgYB/f8oP4Zjr2LCG+N5mCQORQ1zRW7qIg2y6/oFA+Nc56bWhR4Su8qBAv44uFyF+f/NOMCC9hy75DdRTgqA1mBD5yFyl2ctOFM6UVws343brGGdVsLlF5plG55egRV9+YTiSDb6k4ne3agvy6KfVjxMVAP+iMCV4Gx9XWtGKmP6qoQJBAO+BwiqtR2S0ScaFAAH+7BoJE+6afxu94IHYTuoBANZXJLMrVJsfyPcGkQiEkScNWFdSM32ev6zwmO6cun/fR70CQQCzRZrcu4rYCyIoEr0I3YiDC7Fi75Dwd5FGA2B9m65rW5aR9OKMUEGE8M20/i3SR0QWqaurSZ5itzGZaoGGxVcbAkBoHwYVXY1bB1HHXwyY0YU88FNc+IdnvQ08BZiQ/Gyrweivh5fZdGD+mzkz9cXQr2mHVLIT247ClTo3CaW+b4gVAkARbLiKr+fk6+AcCQY6sF2ubxqExUhPNy/1oCpmyxT+9l4CPc0vWjWkGEvGRdZqU+b4VPGMqpEsgyXdBY8lbFT1AkEAlnJ8ULHBvc2eHXOg4QrJgJg5g8Wms/OJFJ50AJdHYdwaXRzUlYLzsOoHAtLHz5gNg+n37kdq7aAoqJTKjm7zkA==";
		
    	byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(prikey), Base64.decode(r));
        String restr=new String(res);  
        
        return restr;
    }
    
    /**js 加密方法*/
    //<script type="text/javascript" src="${ctx}/js/jsencrypt.min.js"></script> 
	//var encrypt = new JSEncrypt();
    //encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnuNmfvm/Xec4NU39TjIkwZi9B4NwyOTVJ03X06V7+gR4rBoFRXqWkNHLC65Z2r6npCcx6ygcc5y2IswDYBh4l5mSx7fLxhWOSWxMOksdRkMgivJmIZALeabvd7G5FvHD0e9LX2dQwjy5WHFckTK4lCCmkKy/6lTZjDZe8gF7L7wIDAQAB");
    //var data = encrypt.encrypt($("#username").val()+","+$("#password").val());
  //全局佳美解密，使用方法----------------------------------------结束
    
    public static void main2(String[] args) throws Exception {  
        String filepath="F:/1/";  
  
//        RSAEncrypt.genKeyPair(filepath);  
          
          
        System.out.println("--------------公钥加密私钥解密过程-------------------");  
        String plainText="ihep_公钥加密私钥解密";  
        //公钥加密过程  
        byte[] cipherData=RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)),plainText.getBytes());  
        String cipher=Base64.encode(cipherData);  
        //私钥解密过程  
        byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), Base64.decode(cipher));  
        String restr=new String(res);  
        System.out.println("原文："+plainText);  
        System.out.println("加密："+cipher);  
        System.out.println("解密："+restr);  
        System.out.println();  
          
        System.out.println("--------------私钥加密公钥解密过程-------------------");  
        plainText="ihep_私钥加密公钥解密";  
        //私钥加密过程  
        cipherData=RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)),plainText.getBytes());  
        cipher=Base64.encode(cipherData);  
        //公钥解密过程  
        res=RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), Base64.decode(cipher));  
        restr=new String(res);  
        System.out.println("原文："+plainText);  
        System.out.println("加密："+cipher);  
        System.out.println("解密："+restr);  
        System.out.println();  
          
        System.out.println("---------------私钥签名过程------------------");  
        String content="ihep_这是用于签名的原始数据";  
        String signstr=sign(content,RSAEncrypt.loadPrivateKeyByFile(filepath));  
        System.out.println("签名原串："+content);  
        System.out.println("签名串："+signstr);  
        System.out.println();  
          
        System.out.println("---------------公钥校验签名------------------");  
//        System.out.println("签名原串："+content);  
//        System.out.println("签名串："+signstr);  
          
        System.out.println("验签结果："+doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath)));  
        System.out.println();  
          
    }  
    
}
