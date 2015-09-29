package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class TimelineImageHelper {

    @Autowired
    private TimelineActivitiesService service;


    public boolean isImage(AppFile appfile) {

        if (!isBlank(appfile.getMimeType()) && appfile.getMimeType().startsWith("image/")) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public List<List<AppFile>> getHorizontalImagens(List<AppFile> appfiles, TimelineActivity activity) throws IOException {

        if (appfiles == null || activity == null) {
            return Lists.newArrayList();
        }

        List<AppFile> horizontalImages = Lists.newArrayList();

        for(AppFile appfile: appfiles) {
            if(isImage(appfile)) {
                if (appfile.getWidth() != null && appfile.getHeight() != null && (appfile.getWidth() > appfile.getHeight())) {
                    horizontalImages.add(appfile);
                }
            }
        }

        List<List<AppFile>> horizontalImagesGroup = Lists.newArrayList();

        for (int index = 0; index < horizontalImages.size(); index+=2) {
            List<AppFile> appFiles = Lists.newArrayList(horizontalImages.get(index));
            horizontalImagesGroup.add(appFiles);
            if ((index + 1) < horizontalImages.size()) {
                appFiles.add(horizontalImages.get(index + 1));
            }
        }

        return horizontalImagesGroup;
    }

    /**
     *
     * Recupera somente imagens que nao tem dimensoes ou imagens que sejam quadradas ou verticais(altura maior que largura).
     */
    public List<AppFile> getVerticalImagens(List<AppFile> appfiles, TimelineActivity activity) throws IOException {

        if (appfiles == null || activity == null) {
            return Lists.newArrayList();
        }

        List<AppFile> horizontalImages = Lists.newArrayList();

        for(AppFile appfile: appfiles) {
            if(isImage(appfile)) {
                if (appfile.getWidth() == null && appfile.getHeight() == null) {
                    horizontalImages.add(appfile);
                    continue;
                }

                if (appfile.getHeight() >= appfile.getWidth()) {
                    horizontalImages.add(appfile);
                }
            }
        }

        return horizontalImages;
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
