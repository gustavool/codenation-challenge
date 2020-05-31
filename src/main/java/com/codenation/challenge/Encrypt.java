package com.codenation.challenge;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

abstract public class Encrypt {

	public Encrypt() {
		
	}
	//reference: https://gist.github.com/giraam/7413306
	public static String sha1(String decifrado) {
	    String sha1 = null;
	    try {
	        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
	        msdDigest.update(decifrado.getBytes("UTF-8"), 0, decifrado.length());
	        sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
	    } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
	        Logger.getLogger(JsonCodenation.class.getName()).log(Level.SEVERE, null, e);
	    }
	    return sha1;
	}
	
}
