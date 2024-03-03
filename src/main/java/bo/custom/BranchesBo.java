package bo.custom;

import dto.Branchesdto;
import entity.Branches;

import java.util.ArrayList;
import java.util.stream.Collectors;

public interface BranchesBo {

    public boolean saveBranches(Branchesdto dto) ;



    public boolean updateBranches(Branchesdto dto);

    public boolean deleteRoom(Branches dto) ;


    public Branchesdto searchRoom(String id);


    public ArrayList<Branchesdto> getAllBranches();

}
