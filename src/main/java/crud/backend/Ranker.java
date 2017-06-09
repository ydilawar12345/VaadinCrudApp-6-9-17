package crud.backend;

import java.util.List;

/**
 * Created by user on 5/31/2017.
 */
public class Ranker {

    public List<Rank> theranker(List<Rank> users, Rank currentuser, RankRepository repo) {

        if (currentuser.getId() > 0L) {
            for(int i = 0 ; i <= users.size()-1; i++) {
               if(users.get(i).getId()==(currentuser.getId())){
                   users.set(i,currentuser);
                   break;
               }
            }
        }else {
            users.add(currentuser);
        }

        Rank temp  ;

        for (int i = 0 ; i <= users.size()-1; i++) {
                for (int j = i; j < users.size()-1; j++) {
                    if (users.get(i).getScore() < users.get(j+1).getScore()) {
                        temp = users.get(i);
                        users.set(i, users.get(j + 1));
                        users.set(j + 1, temp);



                }
           }

        }
for(int i = 0 ; i <= users.size()-1; i++){
    users.get(i).setRank(i);
}
    return users;
    }
}