package com.gabriellorandi.booklibrary.book.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateBookRequest {

    @NotBlank(message = "{book.name.notBlank}")
    private String bookName;

    @NotEmpty(message = "{author.name.notEmpty}")
    private List<String> authorNames;

    @NotBlank(message = "{book.publisher.notBlank}")
    private String publisher;

    @Positive(message = "{book.publisher.positive}")
    private Integer publicationYear;

    private String summary;

    private String fileFormat;

}
