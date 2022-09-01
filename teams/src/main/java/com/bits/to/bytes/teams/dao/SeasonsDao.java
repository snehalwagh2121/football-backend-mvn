//package com.bits.to.bytes.teams.dao;
//
//import com.bits.to.bytes.teams.dbmapper.SeasonsDBMapper;
//import com.bits.to.bytes.teams.objectmapper.SeasonsObjectMapper;
//import com.bits.to.bytes.teams.repository.SeasonsRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class SeasonsDao {
//    Logger logger = LoggerFactory.getLogger(SeasonsDao.class);
//
//    @Autowired
//    SeasonsRepository seasonsRepository;
//    @Autowired
//    SeasonsObjectMapper seasonsObjectMapper;
//
//    public List<Integer> findSeasonsForTeam(int teamId) {
//        SeasonsDBMapper response = seasonsRepository.findByTeamId(teamId);
//        if (response != null)
//            return response.getSeasons();
//        return null;
//    }
//
//    public boolean saveSeasons(List<Integer> seasonsList, int teamId) {
//        try {
//            seasonsRepository.save(seasonsObjectMapper.seasonsObjectMapper(seasonsList, teamId));
//            return true;
//        } catch (Exception e) {
//            logger.error("ERROR while storing seasons object in DB");
//            logger.error(e.getMessage());
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
