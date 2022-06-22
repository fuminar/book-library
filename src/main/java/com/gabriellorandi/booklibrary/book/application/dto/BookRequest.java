package com.gabriellorandi.booklibrary.book.application.dto;

import com.gabriellorandi.booklibrary.book.enums.BookType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "{book.name.notBlank}")
    private String bookName;

    @NotEmpty(message = "{author.name.notEmpty}")
    private List<String> authorNames;

    @NotBlank(message = "{book.publisher.notBlank}")
    private String publisher;

    @Positive(message = "{book.publicationYear.positive}")
    private Integer publicationYear;

    private String summary;

    private String fileFormat;

    public boolean isEBook() {
        return Strings.isNotBlank(fileFormat);
    }

    public BookType getBookType() {
        return isEBook() ? BookType.E_BOOK : BookType.BOOK;
    }
}
