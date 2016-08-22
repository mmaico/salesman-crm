package br.com.kproj.salesman.assistants.archive.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfo;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileInfoRepository extends BaseRepositoryLegacy<FileInfo, Long> {


    @Query("SELECT fi FROM FileInfo AS fi JOIN fi.sharedWith AS sw " +
            " WHERE fi.isPublic = true OR sw.user = :user")
    List<FileInfo> findPublicsAndSheredFiles(@Param("user")UserEntity user);

    @Query("SELECT fi FROM FileInfo AS fi WHERE fi.owner = :user")
    List<FileInfo> findOwnFiles(@Param("user")UserEntity user);
}
