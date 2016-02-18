package br.com.kproj.salesman.assistants.archive.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfo;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileInfoRepository extends BaseRepository<FileInfo, Long> {


    @Query("SELECT fi FROM FileInfo AS fi JOIN fi.sharedWith AS sw " +
            " WHERE fi.isPublic = true OR sw.user = :user")
    List<FileInfo> findPublicsAndSheredFiles(@Param("user")User user);

    @Query("SELECT fi FROM FileInfo AS fi WHERE fi.owner = :user")
    List<FileInfo> findOwnFiles(@Param("user")User user);
}
