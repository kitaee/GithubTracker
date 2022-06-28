package GithubTracker.tracker;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Member {
    private String githubId;
    private ArrayList<String> onlyFollowings;
    private ArrayList<String> onlyFollowers;
    private ArrayList<String> followings;
    private ArrayList<String> followers;
}
