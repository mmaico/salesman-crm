package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.builders.AppFileBuilder;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class TimelineImageHelperTest {

    @InjectMocks
    private TimelineImageHelper helper;


    @Test
    public void shouldGroupFilesHorizontal() throws IOException {
        this.helper = Mockito.spy(helper);
        List<AppFile> files = getAppFileStub();
        LogActivity logActivity = Mockito.mock(LogActivity.class);

        List<List<AppFile>> horizontalImagens = helper.getHorizontalImagens(files, logActivity);

        assertThat(horizontalImagens.get(0).get(0).getId(), is(1l));
        assertThat(horizontalImagens.get(0).get(1).getId(), is(2l));
        assertThat(horizontalImagens.get(0).size(), is(2));

        assertThat(horizontalImagens.get(1).get(0).getId(), is(3l));
        assertThat(horizontalImagens.get(1).get(1).getId(), is(4l));
        assertThat(horizontalImagens.get(1).size(), is(2));

        assertThat(horizontalImagens.get(2).get(0).getId(), is(5l));
        assertThat(horizontalImagens.get(2).size(), is(1));

        assertThat(horizontalImagens.size(), is(3));
    }





    private List<AppFile> getAppFileStub() {
        AppFile image1 = AppFileBuilder.create(1l)
                .withMimeType("image/jpg")
                .withWidth(10)
                .withHeight(5).build();

        AppFile image2 = AppFileBuilder.create(2l)
                .withWidth(10)
                .withHeight(5).withMimeType("image/png").build();

        AppFile image3 = AppFileBuilder.create(3l)
                .withWidth(10)
                .withHeight(5).withMimeType("image/png").build();

        AppFile image4 = AppFileBuilder.create(4l)
                .withWidth(10)
                .withHeight(5).withMimeType("image/png").build();

        AppFile image5 = AppFileBuilder.create(5l)
                .withWidth(10)
                .withHeight(5).withMimeType("image/png").build();

        AppFile image6 = AppFileBuilder.create(6l).withMimeType("text/text").build();

        AppFile image7 = AppFileBuilder.create(7l).withMimeType("text/text").build();

        AppFile image8 = AppFileBuilder.create(8l)
                .withWidth(5)
                .withHeight(10).withMimeType("image/png").build();

        return Lists.newArrayList(image1, image8, image2, image3, image4, image5, image6, image7);
    }

}