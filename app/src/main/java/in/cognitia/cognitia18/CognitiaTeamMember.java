package in.cognitia.cognitia18;

import android.content.Context;
import android.content.res.Resources;

import java.io.Serializable;

/**
 * Created by devansh on 2/10/18.
 */

//Making it serializable so that it can be passed between activities
public class CognitiaTeamMember implements Serializable {

    public static String DEPARTMENTAL;
    public static String DESIGNING;
    public static String ECELL;
    public static String EVENT_MAMANGEMET;
    public static String FUN_EVENTS;
    public static String GAMING;
    public static String HOSPITALITY;
    public static String PHOTOWALK;
    public static String PUBLICITY;
    public static String QUIZ_DEBATE;
    public static String SECRETARIES_MEMBERS;
    public static String SHIMMER_ARPEGGIO;
    public static String STAGE_MANAGEMENT;
    public static String TECHNICAL;
    public static String APP_DEVELOPMENT;
    public static String WEB_DEVELOPMENT;

     private String name;
     private String team;
     private String post;
     private int imageId;
     private String email;

     public static Context context;

     public CognitiaTeamMember(int nameRes, int teamRes, int postRes, int imageRes, int emailRes) {

         Resources res = context.getResources();

         setName(res.getString(nameRes));
         setTeam(res.getString(teamRes));
         setPost(res.getString(postRes));
         setImageId(imageRes);
         setEmail(res.getString(emailRes));

     }

     public void setName(String name) {
         this.name = name;
     }

     public void setTeam(String team) {
         this.team = team;
     }

     public void setPost(String post) {
         this.post = post;
     }

     public void setImageId(int imageId) {
         this.imageId = imageId;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getName() {
         return name;
     }

     public String getTeam() {
         return team;
     }

     public String getPost() {
         return post;
     }

     public int getImageId() {
         return imageId;
     }

     public String getEmail() {
         return email;
     }

    public static void initializeTeamNameStrings() {
        DEPARTMENTAL = context.getResources().getString(R.string.team_departmental);
        DESIGNING = context.getResources().getString(R.string.team_designing);
        ECELL = context.getResources().getString(R.string.team_e_cell);
        EVENT_MAMANGEMET = context.getResources().getString(R.string.team_event_management);
        FUN_EVENTS = context.getResources().getString(R.string.team_fun_events);
        GAMING = context.getResources().getString(R.string.team_gaming);
        HOSPITALITY = context.getResources().getString(R.string.team_hospitality);
        PHOTOWALK = context.getResources().getString(R.string.team_photo_walk);
        PUBLICITY = context.getResources().getString(R.string.team_publicity);
        QUIZ_DEBATE = context.getResources().getString(R.string.team_general_quiz_debate);
        SECRETARIES_MEMBERS = context.getResources().getString(R.string.secretary_and_members);
        SHIMMER_ARPEGGIO = context.getResources().getString(R.string.team_arppegio_and_shimmer);
        STAGE_MANAGEMENT = context.getResources().getString(R.string.team_stage_management_and_printing);
        TECHNICAL = context.getResources().getString(R.string.team_technical);
        WEB_DEVELOPMENT = context.getResources().getString(R.string.team_web_development);
        APP_DEVELOPMENT = context.getResources().getString(R.string.team_app_development);
    }
}
