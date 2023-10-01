package com.example.passlock;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<ParentItem> parentItemArrayList;
    public Context ctx;

    public MyAdapter(ArrayList<ParentItem> parentItemArrayList,Context context) {
        this.ctx=context;
        this.parentItemArrayList = parentItemArrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ParentItem parentItem = parentItemArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
        holder.nested_rv.setLayoutManager(layoutManager);

        holder.socialmedia.setText(parentItem.socialmedia);
        holder.ivParent.setImageResource(parentItem.imageId);
        ArrayList<ChildItem> arrayList=new ArrayList<>();
        int[] imageId = {R.drawable.ic_baseline_key_24,R.drawable.ic_baseline_delete_24,R.drawable.ic_baseline_person_24,R.drawable.ic_baseline_remove_red_eye_24};


        SharedPreferences sharedPreferences = ctx.getSharedPreferences("Passwords", Context.MODE_PRIVATE);

        if (parentItemArrayList.get(position).getSocialmedia().equals("Instagram")) {
            String instaLoginIdJson = sharedPreferences.getString("Instagramuids", "[]");
            String instaPasswordJson = sharedPreferences.getString("Instagrampass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> instaLoginIdList = gson.fromJson(instaLoginIdJson, type);
            ArrayList<String> instaPasswordList = gson.fromJson(instaPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < instaLoginIdList.size(); i++) {
                String loginId = instaLoginIdList.get(i);
                String password = instaPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }

        else if (parentItemArrayList.get(position).getSocialmedia().equals("Facebook")) {
            String fbLoginIdJson = sharedPreferences.getString("Facebookuids", "[]");
            String fbPasswordJson = sharedPreferences.getString("Facebookpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> fbLoginIdList = gson.fromJson(fbLoginIdJson, type);
            ArrayList<String> fbPasswordList = gson.fromJson(fbPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < fbLoginIdList.size(); i++) {
                String loginId = fbLoginIdList.get(i);
                String password = fbPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Discord")) {
            String discordLoginIdJson = sharedPreferences.getString("Discorduids", "[]");
            String discordPasswordJson = sharedPreferences.getString("Discordpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> discordLoginIdList = gson.fromJson(discordLoginIdJson, type);
            ArrayList<String> discordPasswordList = gson.fromJson(discordPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < discordLoginIdList.size(); i++) {
                String loginId = discordLoginIdList.get(i);
                String password = discordPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        } else if (parentItemArrayList.get(position).getSocialmedia().equals("Linked In")) {
            String linkedInLoginIdJson = sharedPreferences.getString("Linked Inuids", "[]");
            String linkedInPasswordJson = sharedPreferences.getString("Linked Inpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> linkedInLoginIdList = gson.fromJson(linkedInLoginIdJson, type);
            ArrayList<String> linkedInPasswordList = gson.fromJson(linkedInPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < linkedInLoginIdList.size(); i++) {
                String loginId = linkedInLoginIdList.get(i);
                String password = linkedInPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        } else if (parentItemArrayList.get(position).getSocialmedia().equals("Gmail")) {
            String gmailLoginIdJson = sharedPreferences.getString("Gmailuids", "[]");
            String gmailPasswordJson = sharedPreferences.getString("Gmailpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> gmailLoginIdList = gson.fromJson(gmailLoginIdJson, type);
            ArrayList<String> gmailPasswordList = gson.fromJson(gmailPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < gmailLoginIdList.size(); i++) {
                String loginId = gmailLoginIdList.get(i);
                String password = gmailPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        } else if (parentItemArrayList.get(position).getSocialmedia().equals("Netflix")) {
            String netflixLoginIdJson = sharedPreferences.getString("Netflixuids", "[]");
            String netflixPasswordJson = sharedPreferences.getString("Netflixpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> netflixLoginIdList = gson.fromJson(netflixLoginIdJson, type);
            ArrayList<String> netflixPasswordList = gson.fromJson(netflixPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < netflixLoginIdList.size(); i++) {
                String loginId = netflixLoginIdList.get(i);
                String password = netflixPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        } else if (parentItemArrayList.get(position).getSocialmedia().equals("GitHub")) {
            String githubLoginIdJson = sharedPreferences.getString("GitHubuids", "[]");
            String githubPasswordJson = sharedPreferences.getString("GitHubpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> githubLoginIdList = gson.fromJson(githubLoginIdJson, type);
            ArrayList<String> githubPasswordList = gson.fromJson(githubPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < githubLoginIdList.size(); i++) {
                String loginId = githubLoginIdList.get(i);
                String password = githubPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        } else if (parentItemArrayList.get(position).getSocialmedia().equals("Amazon")) {
            String amazonLoginIdJson = sharedPreferences.getString("Amazonuids", "[]");
            String amazonPasswordJson = sharedPreferences.getString("Amazonpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> amazonLoginIdList = gson.fromJson(amazonLoginIdJson, type);
            ArrayList<String> amazonPasswordList = gson.fromJson(amazonPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < amazonLoginIdList.size(); i++) {
                String loginId = amazonLoginIdList.get(i);
                String password = amazonPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        } else if (parentItemArrayList.get(position).getSocialmedia().equals("Microsoft")) {
            String microsoftLoginIdJson = sharedPreferences.getString("Microsoftuids", "[]");
            String microsoftPasswordJson = sharedPreferences.getString("Microsoftpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> microsoftLoginIdList = gson.fromJson(microsoftLoginIdJson, type);
            ArrayList<String> microsoftPasswordList = gson.fromJson(microsoftPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < microsoftLoginIdList.size(); i++) {
                String loginId = microsoftLoginIdList.get(i);
                String password = microsoftPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Apple")) {
            String appleLoginIdJson = sharedPreferences.getString("Appleuids", "[]");
            String applePasswordJson = sharedPreferences.getString("Applepass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> appleLoginIdList = gson.fromJson(appleLoginIdJson, type);
            ArrayList<String> applePasswordList = gson.fromJson(applePasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < appleLoginIdList.size(); i++) {
                String loginId = appleLoginIdList.get(i);
                String password = applePasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Snapchat")) {
            String snapchatLoginIdJson = sharedPreferences.getString("Snapchatuids", "[]");
            String snapchatPasswordJson = sharedPreferences.getString("Snapchatpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> snapchatLoginIdList = gson.fromJson(snapchatLoginIdJson, type);
            ArrayList<String> snapchatPasswordList = gson.fromJson(snapchatPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < snapchatLoginIdList.size(); i++) {
                String loginId = snapchatLoginIdList.get(i);
                String password = snapchatPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Twitter X")) {
            String twitterXLoginIdJson = sharedPreferences.getString("Twitter Xuids", "[]");
            String twitterXPasswordJson = sharedPreferences.getString("Twitter Xpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> twitterXLoginIdList = gson.fromJson(twitterXLoginIdJson, type);
            ArrayList<String> twitterXPasswordList = gson.fromJson(twitterXPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < twitterXLoginIdList.size(); i++) {
                String loginId = twitterXLoginIdList.get(i);
                String password = twitterXPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Reddit")) {
            String redditLoginIdJson = sharedPreferences.getString("Reddituids", "[]");
            String redditPasswordJson = sharedPreferences.getString("Redditpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> redditLoginIdList = gson.fromJson(redditLoginIdJson, type);
            ArrayList<String> redditPasswordList = gson.fromJson(redditPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < redditLoginIdList.size(); i++) {
                String loginId = redditLoginIdList.get(i);
                String password = redditPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Spotify")) {
            String spotifyLoginIdJson = sharedPreferences.getString("Spotifyuids", "[]");
            String spotifyPasswordJson = sharedPreferences.getString("Spotifypass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> spotifyLoginIdList = gson.fromJson(spotifyLoginIdJson, type);
            ArrayList<String> spotifyPasswordList = gson.fromJson(spotifyPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < spotifyLoginIdList.size(); i++) {
                String loginId = spotifyLoginIdList.get(i);
                String password = spotifyPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Pinterest")) {
            String pinterestLoginIdJson = sharedPreferences.getString("Pinterestuids", "[]");
            String pinterestPasswordJson = sharedPreferences.getString("Pinterestpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> pinterestLoginIdList = gson.fromJson(pinterestLoginIdJson, type);
            ArrayList<String> pinterestPasswordList = gson.fromJson(pinterestPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < pinterestLoginIdList.size(); i++) {
                String loginId = pinterestLoginIdList.get(i);
                String password = pinterestPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Disney+ Hotstar")) {
            String hotstarLoginIdJson = sharedPreferences.getString("Disney+ Hotstaruids", "[]");
            String hotstarPasswordJson = sharedPreferences.getString("Disney+ Hotstarpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> hotstarLoginIdList = gson.fromJson(hotstarLoginIdJson, type);
            ArrayList<String> hotstarPasswordList = gson.fromJson(hotstarPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < hotstarLoginIdList.size(); i++) {
                String loginId = hotstarLoginIdList.get(i);
                String password = hotstarPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("Zee5")) {
            String zee5LoginIdJson = sharedPreferences.getString("Zee5uids", "[]");
            String zee5PasswordJson = sharedPreferences.getString("Zee5pass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> zee5LoginIdList = gson.fromJson(zee5LoginIdJson, type);
            ArrayList<String> zee5PasswordList = gson.fromJson(zee5PasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < zee5LoginIdList.size(); i++) {
                String loginId = zee5LoginIdList.get(i);
                String password = zee5PasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else if (parentItemArrayList.get(position).getSocialmedia().equals("SonyLIV")) {
            String sonyLIVLoginIdJson = sharedPreferences.getString("SonyLIVuids", "[]");
            String sonyLIVPasswordJson = sharedPreferences.getString("SonyLIVpass", "[]");

            // Parse the JSON arrays into ArrayLists
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> sonyLIVLoginIdList = gson.fromJson(sonyLIVLoginIdJson, type);
            ArrayList<String> sonyLIVPasswordList = gson.fromJson(sonyLIVPasswordJson, type);

            // Loop through the individual elements and add them to arrayList
            for (int i = 0; i < sonyLIVLoginIdList.size(); i++) {
                String loginId = sonyLIVLoginIdList.get(i);
                String password = sonyLIVPasswordList.get(i);

                arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
            }
        }
        else {
            SharedPreferences sharedPreferences1 = ctx.getSharedPreferences("Service", Context.MODE_PRIVATE);
            String extraServicesJson = sharedPreferences1.getString("ExtraServices", "[]");
            // Convert the JSON array to an ArrayList
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> extraServicesList = gson.fromJson(extraServicesJson, type);

            // Initialize the ArrayList if it's null
            if (extraServicesList == null) {
                extraServicesList = new ArrayList<>();
            }

            // Loop through the ExtraServices list and add their login IDs and passwords
            for (String serviceName : extraServicesList) {
                if(parentItemArrayList.get(position).getSocialmedia().equals(serviceName))
                {
                    String loginIdJson = sharedPreferences.getString(serviceName + "uids", "[]");
                    String passwordJson = sharedPreferences.getString(serviceName + "pass", "[]");

                    // Parse the JSON arrays into ArrayLists
                    Type innerType = new TypeToken<ArrayList<String>>() {}.getType();
                    ArrayList<String> loginIdList = gson.fromJson(loginIdJson, innerType);
                    ArrayList<String> passwordList = gson.fromJson(passwordJson, innerType);

                    // Loop through the individual elements and add them to arrayList
                    for (int i = 0; i < loginIdList.size(); i++) {
                        String loginId = loginIdList.get(i);
                        String password = passwordList.get(i);

                        arrayList.add(new ChildItem(loginId, password, imageId[1], imageId[2], imageId[0], imageId[3]));
                    }
                }
            }
        }

        MemberAdp adapterMember = new MemberAdp(arrayList, parentItemArrayList.get(position).getSocialmedia(), holder.nested_rv.getContext());
        holder.nested_rv.setAdapter(adapterMember);

    }

    @Override
    public int getItemCount() {

        return parentItemArrayList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView socialmedia;
        CircleImageView ivParent;
        RecyclerView nested_rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            socialmedia = itemView.findViewById(R.id.socialmedia);
            socialmedia.setPaintFlags(socialmedia.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            ivParent = itemView.findViewById(R.id.ivparent);
            nested_rv = itemView.findViewById(R.id.nested_rv);

        }
    }

}