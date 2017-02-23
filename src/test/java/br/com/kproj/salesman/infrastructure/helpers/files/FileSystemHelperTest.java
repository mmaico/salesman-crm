package br.com.kproj.salesman.infrastructure.helpers.files;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class FileSystemHelperTest {

    private @InjectMocks
    FileSystemHelper fileSystemUtils;

    private @Mock
    FileSystemPathHelper pathUtils;

    private @Captor
    ArgumentCaptor<String> stringPathCaptor;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void shouldCreateDirs() {
        String path = "/home/user/test/storage";

        this.fileSystemUtils = spy(this.fileSystemUtils);
        File fileMock = mock(File.class);

        doReturn(fileMock).when(this.fileSystemUtils).createFile(path);

        this.fileSystemUtils.mkdirs(path);

        verify(fileMock).mkdirs();
    }
    @Test
    public void shouldVerifyPathPameter() {
        String path = "/home/user/test/storage";

        this.fileSystemUtils = spy(this.fileSystemUtils);
        File fileMock = mock(File.class);

        doReturn(fileMock).when(this.fileSystemUtils).createFile(stringPathCaptor.capture());

        this.fileSystemUtils.mkdirs(path);

        String value = stringPathCaptor.getValue();

        assertThat(value, is(path));
    }

    @Test
    public void shouldFindFileOnFilesystem() throws IOException {

        UserEntity entity = new UserEntity();
        entity.setId(2l);

        AppFileEntity appFileEntity = new AppFileEntity();
        appFileEntity.setId(3l);

        File newFolder = this.tempFolder.newFolder("tempFolder");

        File newTempFileCreated = new File(newFolder, appFileEntity.getId() + ".xls");

        BufferedWriter out = new BufferedWriter(new FileWriter(newTempFileCreated));
        out.write("Write this message in file created.");
        out.close();

        newTempFileCreated.createNewFile();

        given(this.pathUtils.getPathFile(eq(entity), eq(appFileEntity))).willReturn(newTempFileCreated.getAbsolutePath());

        byte[] file = this.fileSystemUtils.getFile(entity, appFileEntity);

        assertThat(file.length, is(35));
    }

    @Test
    public void shouldReturnEmptyByteArrayWhenFileNotExist() throws IOException {

        UserEntity entity = new UserEntity();
        entity.setId(2l);

        AppFileEntity appFileEntity = new AppFileEntity();
        appFileEntity.setId(3l);

        given(this.pathUtils.getPathFile(eq(entity), eq(appFileEntity))).willReturn("file-not-found");

        byte[] file = this.fileSystemUtils.getFile(entity, appFileEntity);

        assertThat(file.length, is(0));
    }

    @Test
    public void shouldDelegateToFileSystemPathUtilsOnMethodGetBasePath() {
        UserEntity entity = new UserEntity();
        entity.setId(2l);
        String pathExpected = "path/file";

        given(this.pathUtils.mountBasePathFile(entity)).willReturn(pathExpected);

        String basePath = this.fileSystemUtils.getBasePath(entity);

        assertThat(basePath, is(pathExpected));
    }

    @Test
    public void shouldDelegateToFileSystemPathUtilsOnMethodGetPathFile() {
        UserEntity entity = new UserEntity();
        entity.setId(2l);

        AppFileEntity appFileEntity = new AppFileEntity();
        appFileEntity.setId(3l);

        String pathExpected = "path/file/2/3.jpg";

        given(this.pathUtils.getPathFile(eq(entity), eq(appFileEntity))).willReturn(pathExpected);

        String basePath = this.fileSystemUtils.getPathFile(entity, appFileEntity);

        assertThat(basePath, is(pathExpected));
    }

    @Test
    public void shouldWriteFileOnDisk() throws IOException {

        File newFolder = this.tempFolder.newFolder("tempFolder");

        File newTempFileCreated = new File(newFolder, "file-test.xls");
        InputStream resourceAsStream = FileSystemPathHelperTest.class.getResourceAsStream("/files/file-test.xls");

        byte[] fileToBytes = IOUtils.toByteArray(resourceAsStream);

        this.fileSystemUtils.writeFile(newTempFileCreated.getAbsolutePath(), fileToBytes);


        byte[] fileRead = FileUtils.readFileToByteArray(newTempFileCreated.getAbsoluteFile());

        assertThat(fileRead, is(fileToBytes));
    }

    @Test(expected= RuntimeException.class)
    public void shouldThrowExceptionWhenFileNotFound() throws IOException {
        String pathFileNotFound = "path/not-found";

        InputStream resourceAsStream = FileSystemPathHelperTest.class.getResourceAsStream("/files/file-test.xls");

        byte[] fileToBytes = IOUtils.toByteArray(resourceAsStream);

        this.fileSystemUtils.writeFile(pathFileNotFound, fileToBytes);
    }
}