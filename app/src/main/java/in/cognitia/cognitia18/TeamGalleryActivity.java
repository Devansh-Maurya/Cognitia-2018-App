package in.cognitia.cognitia18;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

public class TeamGalleryActivity extends AppCompatActivity {

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

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_gallery);

        initializeTeamNameStrigs();

        Toolbar toolbar = findViewById(R.id.team_gallery_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        drawerLayout = findViewById(R.id.team_drawer_layout);

        RecyclerView recyclerView = findViewById(R.id.rv_team_images);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration());

        ArrayList<CognitiaTeamMember> teamMembers = TeamMembersArrayInitializer.getTeamMembers();

        TeamGalleryRecyclerViewAdapter adapter = new TeamGalleryRecyclerViewAdapter(teamMembers);
        recyclerView.setAdapter(adapter);

        NavigationView navigationView = findViewById(R.id.team_nav_view);
        navigationView.setCheckedItem(R.id.nav_team);

        NavigationViewHelper navHelper = new NavigationViewHelper(NavigationViewHelper.TEAM_GALLERY_ACTIVITY,
                this, navigationView, drawerLayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public class TeamGalleryRecyclerViewAdapter extends RecyclerView.Adapter<
            TeamGalleryRecyclerViewAdapter.MyTeamMemberImageHolder> {

        private ArrayList<CognitiaTeamMember> teamMembers;
        private boolean[] randomPositionChecker;

        class MyTeamMemberImageHolder extends RecyclerView.ViewHolder{

            ImageView imageView;

            MyTeamMemberImageHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.team_photo);
            }
        }

        TeamGalleryRecyclerViewAdapter(ArrayList<CognitiaTeamMember> teamMembers) {
            this.teamMembers = teamMembers;
            randomPositionChecker = new boolean[teamMembers.size()];
        }

        @NonNull
        @Override
        public MyTeamMemberImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gallery_image_layout, parent, false);

            return new MyTeamMemberImageHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyTeamMemberImageHolder holder, int position) {

            final CognitiaTeamMember member = teamMembers.get(getRandomPosition());
            int imageResId = member.getImageId();
            //Adding to get member details later in the profile activity
            holder.imageView.setContentDescription(member.getName());
            //Loading images using glide
            Glide.with(TeamGalleryActivity.this).load(imageResId).into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TeamGalleryActivity.this, MemberProfileActivity.class);
                    intent.putExtra(MemberProfileActivity.MEMBER_NAME, member.getName());

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            TeamGalleryActivity.this);
                    startActivity(intent, options.toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return teamMembers == null ? 0 : teamMembers.size();
        }

        private int getRandomPosition() {
            Random random = new Random();
            //Returns an integer between 0 to arrayLength - 1

            int position;
            //Check that a unique number is obtained every time
            do {
                position = random.nextInt(randomPositionChecker.length);
            } while (randomPositionChecker[position]);
            randomPositionChecker[position] = true;

            return position;
        }
    }

    //Removing margins between recycler view items
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = 0;
            outRect.top = 0;
            outRect.right = 0;
            outRect.bottom = 0;
        }
    }

    private void initializeTeamNameStrigs() {
        DEPARTMENTAL = getResources().getString(R.string.team_departmental);
        DESIGNING = getResources().getString(R.string.team_designing);
        ECELL = getResources().getString(R.string.team_e_cell);
        EVENT_MAMANGEMET = getResources().getString(R.string.team_event_management);
        FUN_EVENTS = getResources().getString(R.string.team_fun_events);
        GAMING = getResources().getString(R.string.team_gaming);
        HOSPITALITY = getResources().getString(R.string.team_hospitality);
        PHOTOWALK = getResources().getString(R.string.team_photo_walk);
        PUBLICITY = getResources().getString(R.string.team_publicity);
        QUIZ_DEBATE = getResources().getString(R.string.team_general_quiz_debate);
        SECRETARIES_MEMBERS = getResources().getString(R.string.secretary_and_members);
        SHIMMER_ARPEGGIO = getResources().getString(R.string.team_arppegio_and_shimmer);
        STAGE_MANAGEMENT = getResources().getString(R.string.team_stage_management_and_printing);
        TECHNICAL = getResources().getString(R.string.team_technical);
        WEB_DEVELOPMENT = getResources().getString(R.string.team_web_development);
        APP_DEVELOPMENT = getResources().getString(R.string.team_app_development);
    }
}
