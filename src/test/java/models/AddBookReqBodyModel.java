package models;
import lombok.Data;

import java.util.List;

@Data
public class AddBookReqBodyModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}