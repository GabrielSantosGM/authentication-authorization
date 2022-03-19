package br.com.akinicchi.authentication_authorization.utils;

public class ConstantUtil {

    public static final String ACCESS_APPLICATION = "access_application";
    public static final String CLIENT_NOT_REGISTERED = "ClientId não encontrado.";
    public static final String SECRET_ID_INVALID = "SecretId inválido.";
    public static final String INVALID_ARGUMENTS = "Parâmetros da request não devem ser nulos";

    private ConstantUtil() {
        throw new IllegalStateException("Utility class.");
    }
}
