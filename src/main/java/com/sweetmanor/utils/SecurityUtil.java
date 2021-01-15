package com.sweetmanor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 安全工具类：目前只支持AES和RSA两种加密方式
 * 
 * @version 1.0 2016-11-22
 * @author ijlhjj
 */
public class SecurityUtil {
	public static final String AES = "AES";// 对称加密
	public static final String RSA = "RSA";// 非对称加密

	/**
	 * md5信息摘要
	 */
	public static String md5(String data) {
		return DigestUtils.md5Hex(data);
	}

	/**
	 * md5信息摘要
	 */
	public static String md5(File file) {
		try {
			return DigestUtils.md5Hex(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * sha-1信息摘要
	 */
	public static String sha(String data) {
		return DigestUtils.sha1Hex(data);
	}

	/**
	 * sha-1信息摘要
	 */
	public static String sha(File file) {
		try {
			return DigestUtils.sha1Hex(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * aes加密
	 * 
	 * @param content 明文
	 * @param key     密钥
	 * @return 密文字节数组
	 */
	public static byte[] aesEncode(String content, SecretKey key) {
		return encode(content.getBytes(), AES, key, Cipher.ENCRYPT_MODE);
	}

	/**
	 * aes解密
	 * 
	 * @param content 密文
	 * @param key     密钥
	 * @return 明文字节数组
	 */
	public static byte[] aesDecode(byte[] content, SecretKey key) {
		return encode(content, AES, key, Cipher.DECRYPT_MODE);
	}

	/**
	 * rsa公钥加密
	 * 
	 * @param content   明文
	 * @param publicKey 公钥
	 * @return 密文字节数组
	 */
	public static byte[] rsaEncode(String content, RSAPublicKey publicKey) {
		return encode(content.getBytes(), RSA, publicKey, Cipher.ENCRYPT_MODE);
	}

	/**
	 * rsa公钥解密
	 * 
	 * @param content   密文字节数组
	 * @param publicKey 公钥
	 * @return 明文字节数组
	 */
	public static byte[] rsaDecode(byte[] content, RSAPublicKey publicKey) {
		return encode(content, RSA, publicKey, Cipher.DECRYPT_MODE);
	}

	/**
	 * rsa私钥加密
	 * 
	 * @param content    明文
	 * @param privateKey 私钥
	 * @return 密文字节数组
	 */
	public static byte[] rsaEncode(String content, RSAPrivateKey privateKey) {
		return encode(content.getBytes(), RSA, privateKey, Cipher.ENCRYPT_MODE);
	}

	/**
	 * rsa私钥解密
	 * 
	 * @param content    密文字节数组
	 * @param privateKey 私钥
	 * @return 明文字节数组
	 */
	public static byte[] rsaDecode(byte[] content, RSAPrivateKey privateKey) {
		return encode(content, RSA, privateKey, Cipher.DECRYPT_MODE);
	}

	/**
	 * 加密解密公共方法
	 * 
	 * @param content   原始字节数组
	 * @param algorithm 算法，只支持: AES RSA
	 * @param key       密钥，只支持： SecretKey RSAPublicKey RSAPrivateKey
	 * @param isEncode  加密解密模式，只支持： Cipher.ENCRYPT_MODE Cipher.DECRYPT_MODE
	 * @return 加密/解密后的字节数组，非法参数将返回 null
	 */
	private static byte[] encode(byte[] content, String algorithm, Key key, int isEncode) {
		// 参数检查
		if (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE)
			return null;
		if (algorithm == null || (!algorithm.equalsIgnoreCase(AES) && !algorithm.equalsIgnoreCase(RSA)))
			return null;
		if (key == null
				|| (!(key instanceof RSAPublicKey) && !(key instanceof RSAPrivateKey) && !(key instanceof SecretKey)))
			return null;

		try {
			Cipher cipher = Cipher.getInstance(algorithm);// 创建加密、解密工具对象
			cipher.init(isEncode, key);
			return cipher.doFinal(content);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * aes加密文件
	 * 
	 * @param srcFile    待加密文件路径
	 * @param targetFile 加密文件存储路径
	 * @param key        密钥
	 */
	public static void aesEncode(String srcFile, String targetFile, SecretKey key) {
		encode(srcFile, targetFile, AES, key, Cipher.ENCRYPT_MODE);
	}

	/**
	 * aes解密文件
	 * 
	 * @param srcFile    待解密文件路径
	 * @param targetFile 解密后文件存储路径
	 * @param key        密钥
	 */
	public static void aesDecode(String srcFile, String targetFile, SecretKey key) {
		encode(srcFile, targetFile, AES, key, Cipher.DECRYPT_MODE);
	}

	/**
	 * rsa公钥加密文件
	 * 
	 * @param srcFile    待加密文件路径
	 * @param targetFile 加密后目标文件存储路径
	 * @param publicKey  公钥
	 */
	public static void rsaEncode(String srcFile, String targetFile, RSAPublicKey publicKey) {
		encode(srcFile, targetFile, RSA, publicKey, Cipher.ENCRYPT_MODE);
	}

	/**
	 * rsa公钥解密文件
	 * 
	 * @param srcFile    待解密文件路径
	 * @param targetFile 解密后目标文件存储路径
	 * @param publicKey  公钥
	 */
	public static void rsaDecode(String srcFile, String targetFile, RSAPublicKey publicKey) {
		encode(srcFile, targetFile, RSA, publicKey, Cipher.DECRYPT_MODE);
	}

	/**
	 * rsa私钥加密文件
	 * 
	 * @param srcFile    待加密文件路径
	 * @param targetFile 加密后目标文件存储路径
	 * @param privateKey 私钥
	 */
	public static void rsaEncode(String srcFile, String targetFile, RSAPrivateKey privateKey) {
		encode(srcFile, targetFile, RSA, privateKey, Cipher.ENCRYPT_MODE);
	}

	/**
	 * rsa私钥解密文件
	 * 
	 * @param srcFile    待解密文件路径
	 * @param targetFile 解密后目标文件存储路径
	 * @param privateKey 私钥
	 */
	public static void rsaDecode(String srcFile, String targetFile, RSAPrivateKey privateKey) {
		encode(srcFile, targetFile, RSA, privateKey, Cipher.DECRYPT_MODE);
	}

	/**
	 * 加密解密文件公共方法
	 * 
	 * @param srcFile    待 加密/解密 文件路径
	 * @param targetFile 加密/解密 后文件存储路径
	 * @param key        密钥
	 * @param isEncode   加密/解密 标识
	 */
	private static void encode(String srcFile, String targetFile, String algorithm, Key key, int isEncode) {
		// 参数检查
		if (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE)
			return;
		if (algorithm == null || (!algorithm.equalsIgnoreCase(AES) && !algorithm.equalsIgnoreCase(RSA)))
			return;
		if (key == null
				|| (!(key instanceof RSAPublicKey) && !(key instanceof RSAPrivateKey) && !(key instanceof SecretKey)))
			return;

		try (FileChannel inChannel = new FileInputStream(srcFile).getChannel();
				FileChannel outChannel = new FileOutputStream(targetFile).getChannel();) {
			Cipher cipher = Cipher.getInstance(algorithm);// 创建加密、解密工具对象
			cipher.init(isEncode, key);

			ByteBuffer inBuffer = ByteBuffer.allocate(1024);// 创建一个1024大小的输入缓冲区
			ByteBuffer outBuffer = ByteBuffer.allocate(1024);// 创建一个同样大小的输出缓冲区
			// 循环读取文件内容
			while (inChannel.read(inBuffer) != -1) {
				inBuffer.flip();
				outBuffer.clear();
				cipher.doFinal(inBuffer, outBuffer);// 执行加密/解密操作
				outBuffer.flip();
				outChannel.write(outBuffer);
				inBuffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成对称密钥的方法
	 * 
	 * @param algorithm 生成密钥的算法
	 * @param filename  密钥保存的文件名
	 * @return 成功返回true，失败返回false
	 */
	public static boolean createSecretKey(String algorithm, String filename) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance(algorithm);// 创建密钥生成器
			SecretKey key = keygen.generateKey();// 生成密钥
			ObjectAccessUtil.writeToFile(key, filename);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 生成非对称密钥的方法
	 * 
	 * @param algorithm      生成密钥的算法
	 * @param privateKeyFile 私钥保存路径
	 * @param publicKeyFile  公钥保存路径
	 * @return 成功返回true，失败返回false
	 */
	public static boolean createKeyPair(String algorithm, String privateKeyFile, String publicKeyFile) {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);// 创建非对称密钥生成器
			keyPairGen.initialize(1024);// 密钥大小初始化为1024位

			KeyPair keyPair = keyPairGen.generateKeyPair();// 创建非对称密钥
			PrivateKey privateKey = keyPair.getPrivate();// 获取私钥
			PublicKey publicKey = keyPair.getPublic();// 获取公钥

			ObjectAccessUtil.writeToFile(privateKey, privateKeyFile);
			ObjectAccessUtil.writeToFile(publicKey, publicKeyFile);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
