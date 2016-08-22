package br.com.kproj.salesman.assistants.archive.view;

import br.com.kproj.salesman.assistants.archive.application.FileInfoApplication;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfo;
import br.com.kproj.salesman.infrastructure.entity.builders.AppFileBuilder;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

@RestController
public class FileInfoController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private FileInfoApplication application;


    @RequestMapping(value="/file-info/save")
    public @ResponseBody void saveFileInfo(@ModelAttribute("fileInfo") FileInfo fileInfo, @RequestParam(value="file", required=false) MultipartFile file) throws IOException {

        AppFileBuilder builder = AppFileBuilder.create(file);

        application.register(fileInfo, Optional.ofNullable(builder.hasFile() ? builder.build() : null));
    }

    @RequestMapping(value="/file-info/list", method = RequestMethod.GET)
    public ModelAndView listAll(Model model) {

        Iterable<FileInfo> result = application.findAll(Pager.build().withPageSize(1000));

        model.addAttribute("fileInfos", result);
        return new ModelAndView("/file-info/list");
    }

    @RequestMapping(value="/file-info/shared/list", method = RequestMethod.GET)
    public ModelAndView listSharedFiles(Model model) {
        UserEntity user = security.getPrincipal().getUser();

        Iterable<FileInfo> result = application.findPublicsAndSheredFiles(user);

        model.addAttribute("fileInfos", result);
        return new ModelAndView("/file-info/list");
    }

    @RequestMapping(value="/file-info/own/list", method = RequestMethod.GET)
    public ModelAndView listMyFiles(Model model) {
        UserEntity user = security.getPrincipal().getUser();

        Iterable<FileInfo> result = application.findOwnFiles(user);

        model.addAttribute("fileInfos", result);
        return new ModelAndView("/file-info/list");
    }


}
