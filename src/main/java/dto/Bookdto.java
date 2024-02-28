package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bookdto {
    private String bId;
    private String title;
    private String author;
    private String genre;
    private String status;
}
