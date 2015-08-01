package br.com.kproj.salesman.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class AppFileValidatorTest {

    private @InjectMocks
    AppFileValidator validator;


    @Test
    public void shouldMarkErrorsOnRequiredAttributes() {
        AppFile appFile = new AppFile();

        Set<String> errors = validator.validateToSave(appFile);

        assertThat(errors.contains("app.file.error.name"), is(Boolean.TRUE));
        assertThat(errors.contains("app.file.error.mimetype"), is(Boolean.TRUE));
        assertThat(errors.contains("app.file.error.size"), is(Boolean.TRUE));
        assertThat(errors.contains("app.file.error.file"), is(Boolean.TRUE));
    }

    @Test
    public void shouldNotReturnErrors() {
        AppFile appFile = new AppFile();
        appFile.setMimeType("application/msexcel");
        appFile.setOriginalName("ag2");
        appFile.setSize(2344l);
        appFile.setFile(new byte[10]);


        Set<String> errors = validator.validateToSave(appFile);

        assertThat(errors.isEmpty(), is(Boolean.TRUE));
    }


}