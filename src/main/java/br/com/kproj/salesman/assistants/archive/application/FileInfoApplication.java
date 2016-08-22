package br.com.kproj.salesman.assistants.archive.application;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfo;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;
import java.util.Optional;

public interface FileInfoApplication extends ModelLegacyService<FileInfo> {

    FileInfo register(FileInfo fileInfo, Optional<AppFile> appfileOpt);

    List<FileInfo> findPublicsAndSheredFiles(UserEntity user);

    List<FileInfo> findOwnFiles(UserEntity user);
}
