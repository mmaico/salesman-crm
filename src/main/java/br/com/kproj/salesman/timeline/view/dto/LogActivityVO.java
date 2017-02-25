package br.com.kproj.salesman.timeline.view.dto;


import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.AppFileBuilder;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.safeIterable;
import static br.com.kproj.salesman.infrastructure.helpers.MultipartFileUtils.safe;

public class LogActivityVO {

    private LogActivity logActivity;

    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<AppFileEntity> getAppFiles() throws IOException {

        List<AppFileEntity> items = Lists.newArrayList();

        for (MultipartFile multipart : safeIterable(files)) {

//            AppFileEntity appFileEntity = new AppFileBuilder().withFile(safe(multipart).getBytes())
//                    .withMimeType(safe(multipart).getContentType())
//                    .withOriginalName(safe(multipart).getOriginalFilename())
//                    .withSize(safe(multipart).getSize())
//                    .addDimensionsIfImage()
//                    .build();


//            if (!appFileEntity.isValid()) {
//                items.add(appFileEntity);
//            }
        }

        return items;
    }

    public LogActivity getLogActivity() {
        return logActivity;
    }

    public void setLogActivity(LogActivity logActivity) {
        this.logActivity = logActivity;
    }
}
