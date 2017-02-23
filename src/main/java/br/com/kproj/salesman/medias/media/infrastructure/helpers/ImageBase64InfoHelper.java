package br.com.kproj.salesman.medias.media.infrastructure.helpers;



import br.com.kproj.salesman.infrastructure.exceptions.InternalArchitectureException;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImageBase64InfoHelper {


    public static String getType(String image) {
        Pattern pattern = Pattern.compile("(?<=data:).*(?=;.*)");
        Matcher matcher = pattern.matcher(image);
        if(matcher.find()) return matcher.group();
        return null;
    }

    public static ByteArrayInputStream decodeBase64(String image) {
        try {
            return new ByteArrayInputStream(Base64.getMimeDecoder().decode(image.split("\\,")[1].getBytes()));
        } catch(Exception e) {
            throw new InternalArchitectureException(e);
        }

    }
}
