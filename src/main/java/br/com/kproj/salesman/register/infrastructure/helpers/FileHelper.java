package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileHelper {

    public boolean isImage(AppFile appfile) {

        if (appfile.getMimeType().startsWith("image/")) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public boolean hasOnlyImage(List<AppFile> appfiles) {

        if (appfiles == null) {
            return Boolean.FALSE;
        }
        int totalFiles = appfiles.size();

        return appfiles.stream()
                .filter(e -> e.getMimeType().startsWith("image/"))
                .count() == totalFiles;
    }


}
