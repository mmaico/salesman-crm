package br.com.kproj.salesman.infrastructure.helpers.files;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MimetypeUtilsTest {

    @Test
    public void shouldReturnExtensionByMimeType() {
        String mimeType = "application/vnd.ms-excel";
        String extensionExpected = ".xls";

        String found = MimetypeUtils.findExtension(mimeType);

        assertThat(found, is(extensionExpected));
    }

    @Test
    public void shouldReturnEmptyWhenNotFoundExtension() {
        String mimeType = "notExist-mimetype";
        String extensionExpected = "";

        String found = MimetypeUtils.findExtension(mimeType);

        assertThat(found, is(extensionExpected));
    }

    @Test
    public void shouldReturnTrueWhenExist() {
        String mimeType = "application/vnd.ms-excel";

        Boolean found = MimetypeUtils.existExtension(mimeType);

        assertThat(found, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenNotExist() {
        String mimeType = "invalid/mimetype";

        Boolean found = MimetypeUtils.existExtension(mimeType);

        assertThat(found, is(Boolean.FALSE));
    }

}