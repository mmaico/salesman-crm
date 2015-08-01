package br.com.kproj.salesman.infrastructure.helpers.files;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.Media;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.MediaStorage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemPathHelperTest {

    private @InjectMocks
    FileSystemPathHelper fileSystemPathUtils;

    private String basePath = "/home/user/storage";

    @Before
    public void setUp() {
        setField(this.fileSystemPathUtils, "basePath", this.basePath);
    }

    @Test
    public void shouldMountBasePathByEntity() {
        TestBuildPath test = new TestBuildPath(10l);

        String basePathExpected = this.basePath + "/test/image/" + test.getId();

        String mountBasePathFile = fileSystemPathUtils.mountBasePathFile(test);

        assertThat(mountBasePathFile, is(basePathExpected));
    }

    @Test
    public void shouldReturnBasePathDefaultIfObjectIsNull() {
        TestBuildPath test = null;

        String basePathExpected = this.basePath;

        String mountBasePathFile = fileSystemPathUtils.mountBasePathFile(test);

        assertThat(mountBasePathFile, is(basePathExpected));
    }

    @Test
    public void shouldReturnTheFullPathFile() {
        TestBuildPath test = new TestBuildPath(10l);
        AppFile imageFile = new AppFile(3l);
        imageFile.setMimeType("application/vnd.ms-excel");

        String pathExpected = this.basePath + "/test/image/10/3.xls";

        String pathFile = fileSystemPathUtils.getPathFile(test, imageFile);

        assertThat(pathFile, is(pathExpected));
    }

    @Media(name="test")
    public class TestBuildPath extends Identifiable {

        private static final long serialVersionUID = 1L;

        @MediaStorage(name="image")
        private AppFile image;

        public TestBuildPath(Long id) {
            this.setId(id);
        }

    }

}