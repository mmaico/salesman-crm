package br.com.kproj.salesman.infrastructure.repository;

import com.google.common.collect.Lists;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Collections;


public class Pager implements Pageable {

    private static final int BEGIN_PAGE = 4;

    private static final int END_PAGE = 4;

    public static final int PAGE_NUMBER_DEFAULT = 1;

    public static final int OFF_SET_DEFAULT = 0;

    public static final int PAGE_SIZE_DEFAULT = 10;

    private int pageNumber = PAGE_NUMBER_DEFAULT;

    public static final int FULL_SIZE = 300;

    @SuppressWarnings("unused")
    private int offset = OFF_SET_DEFAULT;

    private int pageSize = PAGE_SIZE_DEFAULT;

    private long totalItens;

    private Sort sort;

    @Override
    public int getOffset() {
        return this.getPageSize() * (this.getPageNumber() - 1);
    }

    @Override
    public int getPageNumber() {
        return this.pageNumber;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public Sort getSort() {

        return this.sort;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static Pager build() {
        return new Pager();
    }

    public Pager withPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Pager withOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public Pager withPageNumer(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public Pager one() {
        this.setPageSize(1);
        return this;
    }

    public long getTotalItens() {
        return this.totalItens;
    }

    public void setTotalItens(long totalItens) {
        this.totalItens = totalItens;
    }

    public static Pager ifNullReturnDefault(Pager pager) {

        if (pager == null) {
            return Pager.build();
        }

        return pager;
    }

    public int getTotalPages() {
        if (this.getTotalItens() == 0 || this.getPageSize() == 0) {
            return 0;
        }

        if (this.getTotalItens() < this.getPageSize()) {
            return 1;
        }

        BigDecimal qtdPages = new BigDecimal(this.getTotalItens()).divide(new BigDecimal(this.getPageSize()), 1,
                BigDecimal.ROUND_HALF_UP).setScale(BigDecimal.ROUND_UP, 0);

        return qtdPages.intValue();
    }

    public int getBeginPage() {
        int currentPage = this.getOffset();
        int begin = currentPage - Pager.BEGIN_PAGE;

        if (begin <= 0) {
            return 1;
        } else {
            return begin;
        }
    }

    public int getEndPage() {
        int totalPages = this.getTotalPages();
        int currentPage = this.getOffset();
        int end = currentPage + Pager.END_PAGE;

        if (end > totalPages) {
            return totalPages;
        } else {
            return end;
        }
    }

    public static Pager binding(Pageable pageable) {
        Pager pager = Pager.build();

        if (pageable.getPageNumber() > 0) {
            pager.setPageNumber(pageable.getPageNumber() + 1);
        }

        if (pageable.getPageSize() > 0) {
            pager.setPageSize(pageable.getPageSize());
            pager.setOffset(pageable.getPageSize() * (pager.getPageNumber() - 1));
        } else {
            pager.setOffset(0);
        }

        return pager;
    }

    public static <T> PageImpl<T> emptyPager() {
        return new PageImpl<T>(Collections.<T>emptyList());
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Pager uniqueResult() {
        this.setPageSize(1);
        return this;
    }

    @Override
    public Pageable first() {
        throw new NotImplementedException("Pager next not implemented");
    }

    @Override
    public boolean hasPrevious() {
        throw new NotImplementedException("Pager hasPrevious not implemented");
    }

    @Override
    public Pageable next() {
        throw new NotImplementedException("Pager next not implemented");
    }

    @Override
    public Pageable previousOrFirst() {
        throw new NotImplementedException("Pager previousOrFirst not implemented");
    }

    public static  <T> Page<T> empty() {
        return new PageImpl<T>(Lists.<T>newArrayList());
    }
}
