package com.school.school_management.Base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
public class PagedResoure<T> {
    private List<T> content;
    private PageMetadata page;

    public PagedResoure(List<T> content, PageMetadata pageMetaData) {
        this.content = content;
        this.page = pageMetaData;

    }

    @Data
    @AllArgsConstructor
    public static class PageMetadata {

        @JsonProperty
        private long size;
        @JsonProperty
        private long totalElements;
        @JsonProperty
        private long totalPages;
        @JsonProperty
        private long number;
    }
}
