// --------------- VER ABAJO INFO SOBRE JWT ------------------------

package com.cursojava.curso.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/* La anotación @Component es una anotación de Spring que indica que una clase
es un componente de la aplicación y debe ser escaneada y registrada
automáticamente en el contexto de la aplicación. Cuando se utiliza esta
anotación, Spring detecta automáticamente la clase y la crea como un bean de
Spring, lo que significa que la instancia de la clase es administrada por el
contenedor de Spring.
VER ABAJO PARA MÁS INFO DE @COMPONENT*/
@Component
public class JWTUtil {
    /* La anotación @Value es una anotación de Spring que se utiliza para
    inyectar valores en las propiedades de una clase.
    --- El valor primero debe ser especificado en application.properties
    --- Para que Spring pueda inyectar valores utilizando @Value, la clase
    debe ser un componente de Spring (@Component, @Repository, @Controller,
    @Service, etc)*/
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log = LoggerFactory
            .getLogger(JWTUtil.class);

    /**
     * Create a new token.
     *
     * @param id
     * @param subject
     * @return
     */
    public String create(String id, String subject) {
        // crea el JWT (el texto en formato JSON que se pasa al cliente/navegador)

        // The JWT signature algorithm used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //  sign JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //  set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getValue(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();

        return claims.getSubject();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getKey(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();

        return claims.getId();
    }
}


/*
-------------------------------- JWT ----------------------------------------

JWT (JSON Web Token) es un estándar de token de acceso que se utiliza para la
autenticación y la autorización en aplicaciones web y móviles. El token JWT
es un objeto JSON que se utiliza para transmitir información de forma segura
entre dos partes.

El token JWT consta de tres partes separadas por un punto ".". Estas partes
son:
    1. Encabezado (header): contiene información sobre el tipo de token y el
    algoritmo utilizado para firmar el token.
    2. Carga útil (payload): contiene la información del usuario que se va a
    transmitir. Esta información puede incluir el nombre de usuario, el rol,
    la fecha de vencimiento del token, etc.
    3. Firma (signature): se utiliza para verificar la integridad del token.
    La firma se calcula utilizando la clave secreta del servidor y la
    información del encabezado y la carga útil.

El proceso de autenticación y autorización usando JWT es el siguiente:

    1. El usuario envía sus credenciales (nombre de usuario y contraseña) al
    servidor.
    2. El servidor verifica las credenciales y, si son válidas, genera un token
    JWT.
    3. El servidor envía el token JWT al cliente.
    4. El cliente almacena el token JWT y lo envía con cada solicitud al
    servidor.
    5. El servidor verifica la firma del token JWT y, si es válida, permite
    el acceso a los recursos protegidos.

JWT se utiliza ampliamente en aplicaciones web y móviles porque es fácil de
implementar y es seguro. Además, el token JWT es autocontenido, lo que
significa que toda la información necesaria para la autenticación y
autorización se encuentra en el token, lo que hace que sea fácil de transmitir
y almacenar en el lado del cliente.

-------------------------------- @COMPONENT ------------------------------------

Los componentes pueden ser inyectados en otras clases utilizando las
anotaciones @Autowired, @Inject o @Resource. También se pueden utilizar otras
anotaciones de componentes, como @Service, @Repository y @Controller, que
extienden la funcionalidad de @Component y proporcionan un significado
semántico más específico.

En resumen, @Component es una anotación que se utiliza para marcar una clase
como un componente de la aplicación, lo que permite que Spring la detecte y
administre automáticamente como un bean de Spring.

*/