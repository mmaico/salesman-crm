package br.com.kproj.salesman.assistants.archive.application;

import br.com.kproj.salesman.assistants.archive.infrastructure.repository.FileInfoRepository;
import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfo;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.AppFileApplication;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileInfoApplicationImpl extends BaseModelServiceImpl<FileInfo> implements FileInfoApplication {

	@Autowired
	private FileInfoRepository repository;

    @Autowired
    private AppFileApplication appFileApplication;

    public BaseRepository<FileInfo, Long> getRepository() {
        return repository;
    }

    @Override
    public FileInfo register(FileInfo fileInfo, Optional<AppFile> appfileOpt) {

        if (fileInfo.isNew() && !appfileOpt.isPresent()) {
            throw new ValidationException(Sets.newHashSet("file.info.appfile.not.present"));
        }

        FileInfo fileInfoSaved = super.save(fileInfo);

        if (!fileInfo.isNew()) {
            if (appfileOpt.isPresent()) {
                AppFile appFileSaved = appFileApplication.save(fileInfo, appfileOpt.get());
                fileInfoSaved.setFile(appFileSaved);
            }
        } else {
            if (!appfileOpt.isPresent()) {
                throw new ValidationException(Sets.newHashSet("file.info.appfile.not.present"));
            }
            AppFile appFileSaved = appFileApplication.save(fileInfoSaved, appfileOpt.get());
            fileInfoSaved.setFile(appFileSaved);
        }

        return fileInfoSaved;
    }

    @Override
    public List<FileInfo> findPublicsAndSheredFiles(User user) {
        return repository.findPublicsAndSheredFiles(user);
    }

    @Override
    public List<FileInfo> findOwnFiles(User user) {
        return repository.findOwnFiles(user);
    }
}
