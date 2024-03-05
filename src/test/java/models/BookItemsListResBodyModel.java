package models;

import lombok.Data;

import java.util.List;

@Data
public class BookItemsListResBodyModel {
    private List<Books> books;
}