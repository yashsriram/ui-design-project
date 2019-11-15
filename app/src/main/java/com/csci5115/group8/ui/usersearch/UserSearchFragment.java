package com.csci5115.group8.ui.usersearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.csci5115.group8.EditYourProfileActivity;
import com.csci5115.group8.R;
import com.csci5115.group8.UserSRLCustomizationActivity;
import com.csci5115.group8.UserSearchActivity;
import com.csci5115.group8.UserSearchResultsAdapter;

import com.csci5115.group8.data.DataManager;
import com.csci5115.group8.data.user.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class UserSearchFragment extends Fragment {

    private UserSearchViewModel userSearchViewModel;
    private RecyclerView recyclerView;
    private List<User> userSearchResults = DataManager.users;

    private UserSearchResultsAdapter.ItemClickListener itemClickListener = new UserSearchResultsAdapter.ItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            //add user detail page
            Snackbar.make(getView(), "TODO", Snackbar.LENGTH_SHORT).show();
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userSearchViewModel =
                ViewModelProviders.of(this).get(UserSearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user_search, container, false);
        final FloatingActionButton searchUsers = root.findViewById(R.id.search_users);
        searchUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UserSearchActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        recyclerView = root.findViewById(R.id.user_search_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new UserSearchResultsAdapter(getContext(), userSearchResults, itemClickListener));

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            switch (resultCode) {
                case AppCompatActivity.RESULT_OK:
                    String searchText = data.getStringExtra("searchText");
                    String gender = data.getStringExtra("gender");
                    int age = data.getIntExtra("age", -1);
                    int maxBudget = data.getIntExtra("maxBudget", -1);
                    int doesSmoke = data.getIntExtra("doesSmoke", -1);
                    int drugsOkay = data.getIntExtra("drugsOkay", -1);
                    int hasPets = data.getIntExtra("hasPets", -1);
                    int partiesOkay = data.getIntExtra("partiesOkay", -1);
                    int canCook = data.getIntExtra("canCook", -1);
                    int needsPrivateBedroom = data.getIntExtra("needsPrivateBedroom", -1);
                    int hasCar = data.getIntExtra("hasCar", -1);
                    String nativeLanguage = data.getStringExtra("nativeLanguage");


                    userSearchResults = searchUsers(
                            searchText,
                            gender,
                            age,
                            maxBudget,
                            doesSmoke,
                            drugsOkay,
                            hasPets,
                            partiesOkay,
                            canCook,
                            needsPrivateBedroom,
                            hasCar,
                            nativeLanguage
                    );
                    recyclerView.setAdapter(new UserSearchResultsAdapter(getContext(), userSearchResults, null));

                    break;
                case AppCompatActivity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }

    public static List<User> searchUsers(String searchText,
                                         String gender, int age,
                                         int maxBudget,
                                         int doesSmoke,
                                         int drugsOkay,
                                         int hasPets,
                                         int partiesOkay,
                                         int canCook,
                                         int needsPrivateBedroom,
                                         int hasCar,
                                         String nativeLanguage) {
        String regexString = ".*" + searchText + ".*";
        Pattern pattern = Pattern.compile(regexString, Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile(gender + ".*", Pattern.CASE_INSENSITIVE);
        Pattern pattern3 = Pattern.compile(".*" + nativeLanguage + ".*", Pattern.CASE_INSENSITIVE);
        List<User> results = new ArrayList<>();
        for (User user : DataManager.users) {
            // If search matches name or address and all filters match then only add apt to search results
            if ((pattern.matcher(user.name).matches())
                    && filterMatch(doesSmoke, user.preferences.doesSmoke)
                    && filterMatch(drugsOkay, user.preferences.drugsOkay)
                    && filterMatch(hasPets, user.preferences.hasPets)
                    && filterMatch(partiesOkay, user.preferences.partiesOkay)
                    && filterMatch(canCook, user.preferences.canCook)
                    && filterMatch(needsPrivateBedroom, user.preferences.needsPrivateBedroom)
                    && filterMatch(hasCar, user.preferences.hasCar)
                    && (user.maxBudget == maxBudget || maxBudget == -1)
                    && pattern2.matcher(user.gender).matches()
                    && pattern3.matcher(user.nativeLanguage).matches()
                    && (age == user.age || age == -1)
            ) {
                results.add(user);
            }
        }
        return results;
    }

    private static boolean filterMatch(int filter, boolean field) {
        if (filter == 0) {
            return true;
        } else if (filter == 1) {
            return !field;
        } else {
            return field;
        }
    }
}