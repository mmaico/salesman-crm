package br.com.kproj.salesman.assistants.archive.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfoEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fileInfoRepositoryArchiveModule")
public interface FileInfoRepository extends BaseRepositoryLegacy<FileInfoEntity, Long> {


    @Query("SELECT fi FROM FileInfo AS fi JOIN fi.sharedWith AS sw " +
            " WHERE fi.isPublic = true OR sw.user = :user")
    List<FileInfoEntity> findPublicsAndSheredFiles(@Param("user") UserEntity user);

    @Query("SELECT fi FROM FileInfo AS fi WHERE fi.owner = :user")
    List<FileInfoEntity> findOwnFiles(@Param("user") UserEntity user);
}
