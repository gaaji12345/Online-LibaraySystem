package bo.impl;

import bo.custom.BranchesBo;
import dao.custom.BranchesDao;
import dao.impl.BranchesDaoImpl;
import dto.Branchesdto;
import entity.Branches;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BranchesBOImpl implements BranchesBo {

    BranchesDao branchesDao=new BranchesDaoImpl();

    @Override
    public boolean saveBranches(Branchesdto dto) {
        return branchesDao.save(new Branches(
                dto.getBrId(),
                dto.getBname(),
                dto.getLocation(),
                dto.getBstatus()

        ));
    }

    @Override
    public boolean updateBranches(Branchesdto dto) {
        return branchesDao.update(new Branches(
                dto.getBrId(),
                dto.getBname(),
                dto.getLocation(),
                dto.getBstatus()

        ));
    }

    @Override
    public boolean deleteRoom(Branches dto) {
        return branchesDao.delete(new Branches(
                dto.getBrId(),
                dto.getBname(),
                dto.getLocation(),
                dto.getBstatus()


        ));
    }

    @Override
    public Branchesdto searchRoom(String id) {
         Branches dto  = branchesDao.search(id);

        return new Branchesdto(
                dto.getBrId(),
                dto.getBname(),
                dto.getLocation(),
                dto.getBstatus()

        );
    }

    @Override
    public ArrayList<Branchesdto> getAllBranches() {
        ArrayList<Branchesdto> brList = new ArrayList<>();

        brList.addAll(branchesDao.getAll().stream().map(br -> {
            return new Branchesdto(
                    br.getBrId(),
                    br.getBname(),
                    br.getLocation(),
                    br.getBstatus()
            );
        }).collect(Collectors.toList()));

        return brList;
    }




}
