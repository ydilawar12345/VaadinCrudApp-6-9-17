package crud.backend;
import crud.backend.User;

import java.text.SimpleDateFormat;
//import java.time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.util.Date;
import java.sql.Date;

/**
 * Created by user on 5/26/2017.
 */
public class Scorer {

    public void scoreHowWellYouKnow (User guess, RankRepository repo){

        Integer score =0;

        if("2488021890".equalsIgnoreCase(guess.getPhoneNumber())){
            score+=1;
        }
        if("5.11".equalsIgnoreCase(guess.getHeight())){
            score+=1;
        }
        if("brown".equalsIgnoreCase(guess.getEyeColor())){
            score+=1;
        }

        User birthdate = repo.findById(1L);

        if((new Date(birthdate.getBirthDay().getTime())).equals(guess.getBirthDay())){
            score+=1;
        }

        guess.setScore(score);
    }
}