package br.com.kproj.salesman.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.Media;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.MediaStorage;
import net.vidageek.mirror.list.dsl.MirrorList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class ReflectionsHelperTest {

    @Test
    public void shouldCreateANewInstanceOfClass() {
        UserEntity newInstance = ReflectionsHelper.newInstance(UserEntity.class);

        assertThat(newInstance, notNullValue());
    }

    @Test
    public void shouldVerifyIfClassHasAnnotation() {
        ClassWithMediaAnnotation object = new ClassWithMediaAnnotation();

        Boolean hasAnnotation = ReflectionsHelper.hasAnnotation(object, Media.class);

        assertThat(hasAnnotation, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseIfObjectIdNull() {
        ClassWithMediaAnnotation object = null;

        Boolean hasAnnotation = ReflectionsHelper.hasAnnotation(object, Media.class);

        assertThat(hasAnnotation, is(Boolean.FALSE));
    }

    @Test
    public void shouldFindFieldsWithParamAnnotation() {
        ClassWithMediaAnnotation object = new ClassWithMediaAnnotation();

        MirrorList<Field> fields = ReflectionsHelper.findFields(object, MediaStorage.class);

        assertThat(fields.get(0).getName(), is("app"));
    }

    @Test
    public void shouldReturnAListFieldEmptyWhenOBjectIsNull() {
        ClassWithMediaAnnotation object = null;

        MirrorList<Field> fields = ReflectionsHelper.findFields(object, MediaStorage.class);

        assertThat(fields.isEmpty(), is(Boolean.TRUE));
    }


    @Media(name="clazz-ann")
    public class ClassWithMediaAnnotation {

        @MediaStorage(name="files")
        private AppFileEntity app;
    }

}