package GithubTracker.tracker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class TrackController {

    @GetMapping("/")
    public String viewMainPage(){
        return "main";
    }

    @PostMapping("/")
    public String createMemberForm(MemberForm memberForm, Model model) throws IOException {
        Member member = new Member();
        member.setGithubId(memberForm.getGithubId());

        String followerUrl = "https://github.com/" + member.getGithubId() + "?tab=followers";
        String followingUrl = "https://github.com/" + member.getGithubId() + "?tab=following";

        Document document = Jsoup.connect(followerUrl).get();
        String followers = document.select("span.Link--secondary").text();
        String[] followersList = followers.split(" ");
        ArrayList<String> followerList = new ArrayList<String>(Arrays.asList(followersList));
        member.setFollowers(followerList);

        Document document2 = Jsoup.connect(followingUrl).get();
        String followings = document2.select("span.Link--secondary").text();
        String[] followingLists = followings.split(" ");
        ArrayList<String> followingList = new ArrayList<String>(Arrays.asList(followingLists));
        member.setFollowings(followingList);

        ArrayList<String> onlyFollowers = new ArrayList<>();
        onlyFollowers.addAll(member.getFollowers());
        onlyFollowers.removeAll(member.getFollowings());
        member.setOnlyFollowers(onlyFollowers);

        ArrayList<String> onlyFollowings = new ArrayList<>();
        onlyFollowings.addAll(member.getFollowings());
        onlyFollowings.removeAll(member.getFollowers());
        member.setOnlyFollowings(onlyFollowings);

        model.addAttribute("member",member);
        return "main";
    }
}
