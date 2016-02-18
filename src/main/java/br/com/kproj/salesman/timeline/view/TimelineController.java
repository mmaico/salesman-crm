package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.builders.AppFileBuilder;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.service.AppFileApplication;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
public class TimelineController {

    @Autowired
    private TimelineActivitiesApplication service;


    @Autowired
    private AppFileApplication appFileApplication;


    @RequestMapping("/timeline/{activityId}/file/{fileId}")
    public void getImage(HttpServletResponse response,
                         @PathVariable Long activityId, @PathVariable Long fileId) throws IOException {

        LogActivity activity = new LogActivity(activityId);
        AppFile file = AppFileBuilder.create(fileId).build();

        byte[] activityFile = this.service.getActivityFile(activity, file);

        IOUtils.write(activityFile, response.getOutputStream());
    }

    @RequestMapping("/file/{activityId}/app/{idAppFile}")
    public void getFile(HttpServletResponse response, @PathVariable Long activityId, @PathVariable Long idAppFile) throws IOException {

        byte[] file = service.getActivityFile(new LogActivity(activityId), new AppFile(idAppFile));

        Optional<AppFile> appfile = appFileApplication.getOne(idAppFile);

        if (!appfile.isPresent()) {
            return;
        }

        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + appfile.get().getOriginalName() + "\"");

        IOUtils.write(file, response.getOutputStream());

    }

}
