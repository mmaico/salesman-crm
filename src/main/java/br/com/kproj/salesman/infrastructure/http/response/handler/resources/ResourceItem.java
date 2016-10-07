package br.com.kproj.salesman.infrastructure.http.response.handler.resources;

import java.util.Objects;

public class ResourceItem {

  private final Object item;
  private final String uri;

  public ResourceItem(Object body, String uri) {
    this.item = body;
    this.uri = uri;
  }

  public Object getItem() {
    return item;
  }

  public String getUri() {
    return uri;
  }
  
  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    final ResourceItem that = (ResourceItem) o;
    return Objects.equals(getItem(), that.getItem()) &&
        Objects.equals(getUri(), that.getUri());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getItem(), getUri());
  }
}
