package br.com.kproj.salesman.timeline.view.dto;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.builders.AppFileBuilder;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.BusinessProposalApprovalActivity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.safeIterable;
import static br.com.kproj.salesman.infrastructure.helpers.MultipartFileUtils.safe;

public class BusinessProposalApprovalActivityVO {

    private BusinessProposalApprovalActivity approval;


    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<AppFile> getAppFiles() throws IOException {

        List<AppFile> items = Lists.newArrayList();

        for (MultipartFile multipart : safeIterable(files)) {

            AppFile appFile = new AppFileBuilder().withFile(safe(multipart).getBytes())
                    .withMimeType(safe(multipart).getContentType())
                    .withOriginalName(safe(multipart).getOriginalFilename())
                    .withSize(safe(multipart).getSize())
                    .addDimensionsIfImage()
                    .build();

            if (!appFile.isValid()) {
                items.add(appFile);
            }
        }

        return items;
    }

    public BusinessProposalApprovalActivity getApproval() {
        return approval;
    }

    public void setApproval(BusinessProposalApprovalActivity approval) {
        this.approval = approval;
    }
}
