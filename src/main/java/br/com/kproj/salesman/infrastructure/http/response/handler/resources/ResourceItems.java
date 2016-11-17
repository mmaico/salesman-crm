package br.com.kproj.salesman.infrastructure.http.response.handler.resources;

import java.util.Collection;
import java.util.Objects;

public class ResourceItems {
  private Long totalItems;
  private Integer pageSize;

  private final Collection<?> items;
  private final String uri;

  public ResourceItems(Collection<?> resources, String uri) {
    this.items = resources;
    this.uri = uri;
  }

  public ResourceItems(Collection<?> resources, String uri, Long totalItems, Integer pageSize) {
    this.items = resources;
    this.uri = uri;
    this.totalItems = totalItems;
    this.pageSize = pageSize;
  }

  public Collection<?> getItems() {
    return items;
  }

  public String getUri() {
    return uri;
  }

  public Long getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(Long totalItems) {
    this.totalItems = totalItems;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    final ResourceItems that = (ResourceItems) o;
    return Objects.equals(getItems(), that.getItems()) &&
        Objects.equals(getUri(), that.getUri());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getItems(), getUri());
  }
}