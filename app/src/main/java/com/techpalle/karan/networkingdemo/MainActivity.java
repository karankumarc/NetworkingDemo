package com.techpalle.karan.networkingdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.techpalle.karan.networkingdemo.adapter.ContactsRecyclerAdapter;
import com.techpalle.karan.networkingdemo.model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_SUCCESSFULLY_LOADED_CONTACTS = 1;

    // Name, Email, Mobile number
    //region Contacts Response
    private String response = "{\n" +
            "    \"contacts\": [\n" +
            "        {\n" +
            "                \"id\": \"c200\",\n" +
            "                \"name\": \"Ravi Tamada\",\n" +
            "                \"email\": \"ravi@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c201\",\n" +
            "                \"name\": \"Johnny Depp\",\n" +
            "                \"email\": \"johnny_depp@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c202\",\n" +
            "                \"name\": \"Leonardo Dicaprio\",\n" +
            "                \"email\": \"leonardo_dicaprio@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c203\",\n" +
            "                \"name\": \"John Wayne\",\n" +
            "                \"email\": \"john_wayne@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c204\",\n" +
            "                \"name\": \"Angelina Jolie\",\n" +
            "                \"email\": \"angelina_jolie@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"female\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c205\",\n" +
            "                \"name\": \"Dido\",\n" +
            "                \"email\": \"dido@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"female\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c206\",\n" +
            "                \"name\": \"Adele\",\n" +
            "                \"email\": \"adele@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"female\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c207\",\n" +
            "                \"name\": \"Hugh Jackman\",\n" +
            "                \"email\": \"hugh_jackman@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c208\",\n" +
            "                \"name\": \"Will Smith\",\n" +
            "                \"email\": \"will_smith@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c209\",\n" +
            "                \"name\": \"Clint Eastwood\",\n" +
            "                \"email\": \"clint_eastwood@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c2010\",\n" +
            "                \"name\": \"Barack Obama\",\n" +
            "                \"email\": \"barack_obama@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c2011\",\n" +
            "                \"name\": \"Kate Winslet\",\n" +
            "                \"email\": \"kate_winslet@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"female\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        },\n" +
            "        {\n" +
            "                \"id\": \"c2012\",\n" +
            "                \"name\": \"Eminem\",\n" +
            "                \"email\": \"eminem@gmail.com\",\n" +
            "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "                \"gender\" : \"male\",\n" +
            "                \"phone\": {\n" +
            "                    \"mobile\": \"+91 0000000000\",\n" +
            "                    \"home\": \"00 000000\",\n" +
            "                    \"office\": \"00 000000\"\n" +
            "                }\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    //endregion

    private ArrayList<Contact> contactArrayList= new ArrayList<>();

    //region List view related code
    //private ListView listViewContacts;

   /* private MyContactsAdapter myContactsAdapter = new MyContactsAdapter();
    class MyContactsAdapter extends BaseAdapter{

        public final String TAG = MyContactsAdapter.class.getSimpleName();

        @Override
        public int getCount() {
            return contactArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return contactArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Log.d(TAG, "getView called at "+position);

            Contact contact = contactArrayList.get(position);
            View view = getLayoutInflater().inflate(R.layout.contacts_row, parent, false);
            //View view1 = getLayoutInflater().inflate(R.layout.contacts_row, null);
            TextView textViewName = (TextView) view.findViewById(R.id.row_name);
            TextView textViewEmail = (TextView) view.findViewById(R.id.row_email);
            TextView textViewMobile = (TextView) view.findViewById(R.id.row_mobile);

            textViewName.setText(contact.getName());
            textViewEmail.setText(contact.getEmail());
            textViewMobile.setText(contact.getPhone());

            return view;
        }
    }*/
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Firebase test
        /*Firebase firebase = new Firebase("https://progressmonitor.firebaseio.com");

        firebase.createUser("karan1234@firebase.com", "testpassword", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(MainActivity.this, ""+firebaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });*/
        //endregion

        //region List View related code
        //listViewContacts = (ListView) findViewById(R.id.list_view_contacts);

        //listViewContacts.setAdapter(myContactsAdapter);

        //myContactsAdapter.notifyDataSetChanged();
        //endregion

        //String response = null;

        String urlString = "http://api.androidhive.info/contacts/";




        //response = retrieveDataFromContactsApi(urlString);

        MyTask task = new MyTask();

        task.execute(urlString, "hjsd", "bhjdsfnsd", "hdsjfhndsl");

        // Building our data source
        //contactArrayList.addAll(parseJsonDataAndReturnResponseInArrayList(response));


        //endregion
    }
    private void setupRecyclerView(){
        //region Declaring, initializing and setting the recycler view properties
        // Declare and initialize recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Declare and initialize adapter
        ContactsRecyclerAdapter recyclerAdapter = new ContactsRecyclerAdapter(this, contactArrayList);

        // Set adapter
        recyclerView.setAdapter(recyclerAdapter);

        // Setup linear layout manager
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Items show default animation even if we do not set this
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private String retrieveDataFromContactsApi(String url) {
        try {
            // Build the URL - STEP 1
            URL urlObject = new URL(url);

            // Open connection with the server - STEP 2
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            // Open an input stream - STEP 3
            InputStream inputStream = connection.getInputStream();

            // Create an input stream reader - STEP 4
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            // Create a buffered reader to read the data more efficiently - STEP 5
            BufferedReader reader = new BufferedReader(inputStreamReader);

            // Read through the buffered reader and create string builder - STEP 6
            String line = reader.readLine();
            StringBuffer stringBuffer = new StringBuffer();

            while (line != null){
                stringBuffer.append(line);
                line = reader.readLine();
            }

            // Disconnect - STEP 7
            connection.disconnect();

            return stringBuffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private ArrayList<Contact> parseJsonDataAndReturnResponseInArrayList(String response) {

        ArrayList<Contact> contactArrayList = new ArrayList<>();


        try {
            JSONObject rootObject = new JSONObject(response);
            JSONArray contactsArray = rootObject.getJSONArray("contacts");

            for (int j = 0; j < 100; j++) {

                for (int i = 0; i < contactsArray.length(); i++) {
                    JSONObject contact = (JSONObject) contactsArray.get(i);
                    String name= contact.getString("name");
                    String email = contact.getString("email");
                    JSONObject phone = contact.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    Contact c = new Contact(mobile, email, name);
                    contactArrayList.add(c);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return contactArrayList;
    }

    class MyTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String url = params[0];
            String second = params[1];

            try {

                Thread.sleep(5000);

                // Build the URL - STEP 1
                URL urlObject = new URL(url);

                // Open connection with the server - STEP 2
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

                // Open an input stream - STEP 3
                InputStream inputStream = connection.getInputStream();

                // Create an input stream reader - STEP 4
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                // Create a buffered reader to read the data more efficiently - STEP 5
                BufferedReader reader = new BufferedReader(inputStreamReader);

                // Read through the buffered reader and create string builder - STEP 6
                String line = reader.readLine();
                StringBuffer stringBuffer = new StringBuffer();

                while (line != null){
                    stringBuffer.append(line);
                    line = reader.readLine();
                }

                // Disconnect - STEP 7
                connection.disconnect();

                return stringBuffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s == null){
                Toast.makeText(MainActivity.this, "Network operation failed", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(MainActivity.this, "Successfully fetched contacts", Toast.LENGTH_SHORT).show();
                createNotification();
                contactArrayList.addAll(parseJsonDataAndReturnResponseInArrayList(s));
                setupRecyclerView();
            }
        }
    }

    private void createNotification(){

        // Build a notification builder object
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Contacts").setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Successfully loaded");

        // Define the action on click of notification
        Intent intent = new Intent(this, MainActivity.class);

        // Wrap the intent inside a pending intent and send it to the notification bar
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Set the pending intent to the builder
        builder.setContentIntent(pendingIntent);

        // Create a notification object
        Notification notification = builder.getNotification();

        // Get an instance of Notification manager
        NotificationManager manager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        // Call the notify method on the manager object to create a notification
        manager.notify(NOTIFICATION_SUCCESSFULLY_LOADED_CONTACTS, notification);
    }

}
