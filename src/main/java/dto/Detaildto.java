package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Detaildto {
    private String dId;
    private Date sdate;
    private Date edate;
    private Userdto user;
    private Bookdto book;

}
