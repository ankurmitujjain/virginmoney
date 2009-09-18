package com.virginmoneygiving.profanity.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.virginmoneygiving.profanity.dao.ProfaneDAO;
import com.virginmoneygiving.profanity.domain.ProfanePhrase;
import com.virginmoneygiving.profanity.domain.ProfanePhraseType;

/**
 * A mock DAO for getting the profane phrases. 
 * @author nelsons (Stephen Nelson)
 *
 */
public class MockProfaneDAO implements ProfaneDAO {
    
    public List<ProfanePhrase> fetchAllProfanePhrases() {
        List<ProfanePhrase> result = new ArrayList<ProfanePhrase>();
        
        ProfanePhraseType unacceptable = new ProfanePhraseType();
        unacceptable.setCode("UN_ACC");
        
        ProfanePhraseType virginType = new ProfanePhraseType();
        virginType.setCode("SCR_VIR");
        
        ProfanePhraseType nonVirginType = new ProfanePhraseType();
        nonVirginType.setCode("SCR_NONVIR");
        
        //ProfanePhraseType profanePhraseType = new ProfanePhraseType();
        //profanePhraseType.setCode("UN_ACC");
        
        ProfanePhrase idiots = new ProfanePhrase();
        idiots.setProfanePhraseType(unacceptable);
        idiots.setProfanePhrase("idiots");
        result.add(idiots);

        ProfanePhrase damn = new ProfanePhrase();
        damn.setProfanePhraseType(unacceptable);
        damn.setProfanePhrase("damn");
        result.add(damn);

        ProfanePhrase nazi = new ProfanePhrase();
        nazi.setProfanePhraseType(unacceptable);
        nazi.setProfanePhrase("nazi");
        result.add(nazi);

        //ProfanePhraseType profanePhraseType1 = new ProfanePhraseType();
        //profanePhraseType1.setCode("SCR_VIR");
        
        ProfanePhrase virgin = new ProfanePhrase();
        virgin.setProfanePhraseType(virginType);
        virgin.setProfanePhrase("virgin");
        result.add(virgin);

        ProfanePhrase virginMoney = new ProfanePhrase();
        virginMoney.setProfanePhraseType(virginType);
        virginMoney.setProfanePhrase("virgin money");
        result.add(virginMoney);

        ProfanePhrase virginMoneyGiving = new ProfanePhrase();
        virginMoneyGiving.setProfanePhraseType(virginType);
        virginMoneyGiving.setProfanePhrase("virgin money giving");
        result.add(virginMoneyGiving);

        //ProfanePhraseType profanePhraseType2 = new ProfanePhraseType();
        //profanePhraseType2.setCode("SCR_NONVIR");
        
        ProfanePhrase justGiving = new ProfanePhrase();
        justGiving.setProfanePhraseType(nonVirginType);
        justGiving.setProfanePhrase("just giving");
        result.add(justGiving);

        ProfanePhrase beMyCharity = new ProfanePhrase();
        beMyCharity.setProfanePhraseType(nonVirginType);
        beMyCharity.setProfanePhrase("Bemycharity");
        result.add(beMyCharity);

        ProfanePhrase richardBranson = new ProfanePhrase();
        richardBranson.setProfanePhraseType(nonVirginType);
        richardBranson.setProfanePhrase("Richard Branson");
        result.add(richardBranson);
        
        return result;
    }

}
