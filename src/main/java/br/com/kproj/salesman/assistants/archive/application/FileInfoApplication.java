package br.com.kproj.salesman.assistants.archive.application;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfo;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.Optional;

public interface FileInfoApplication extends ModelService<FileInfo> {

    FileInfo register(FileInfo fileInfo, Optional<AppFile> appfileOpt);
}
