package br.com.akinicchi.authentication_authorization.utils;

public class ConstantUtil {

    public static final String ACCESS_APPLICATION = "access_application";
    public static final String PUBLIC_KEY_URL = "/public-key";
    public static final String CLIENT_ID = "clientId";
    public static final String SECRET_ID = "secretId";
    public static final String MOCK_RETURN_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAi/stos+0Jz1HKtyL9lS/A8S1YWJUiBu5mImwX54Nhij1an7G9Bordr" +
            "lgxQ2fqblBXXm8XltDDoJG7+iVzZo5wTgCmAV+wGT0GntRNMN7WbxheNpKas1r/bEw9DOfipUSrQVUAxYsgiUs0l9twvYt" +
            "aInGNWATIneZm9fKDpuf3wIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    private ConstantUtil() {
        throw new IllegalStateException("Utility class.");
    }
}
