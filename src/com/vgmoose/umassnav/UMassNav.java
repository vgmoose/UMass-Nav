package com.vgmoose.umassnav;

import java.util.ArrayList;
import java.util.Collections;

import com.google.android.gms.ads.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public final class UMassNav extends Activity
{
	
    static String[] buildings = {"Agricultural Engineering Bldg", "Army ROTC Bldg", "Arnold House", "(Studio) Arts Building", "Auxilary Services Warehouse", "Baker House (offices)", "Bartlett Hall", "Berkshire House", "Berkshire Dining Common", "Blaisdell", "Bowditch Hall", "Bowditch Lodge", "Boyden", "Brett (Offices)", "Campus Center", "Cance (offices)", "Central Heating Plant", "Chancellors House", "Chenoweth Laboratory", "Clark Hall", "(William S.) Clark International Center (Hills)", "Commonwealth Honors College", "Communication Disorders", "Computer Science Bldg", "Condensate Storage Building", "Conte Polymer Center", "Continuing Education", "Curry Hicks", "Dickinson Hall", "Draper Hall", "East Experiment Station", "Engineering Laboratory", "Engineering Laboratory II (E Lab II)", "Faculty Club", "Farley Lodge", "Fernald Hall", "Fine Arts Center (East)", "Fine Arts Center (West)", "Flint Laboratory", "Franklin Dining common ", "French Hall", "Furcolo Hall", "Goessmann Laboratory", "Goodell Bldg", "Goodell Bldg (Graduate School)", "Goodell Bldg (Procurement)", "Gordon Hall", "Gunness Lab", "Hampden Dining Common", "Hampshire Dining Common", "Hampshire House", "Hasbrouck Laboratory", "Hatch Laboratory", "Health Center", "Herter Hall", "Hillel House", "Hills North", "Hills South", "Holdsworth Hall", "Isenberg School of Management", "Integrated Science Building", "John Quincy Adams Tower", "Johnson House (offices)", "Knowles Engineering Bldg", "Lederle Grad Research Ctr (LGRC lowrise)", "Lederle Grad Research Tower (LGRT)", "Life Sciences Laboratories", "Machmer Hall", "Marcus Hall", "Marston Hall", "Mass Ventures", "Mather Building", "Memorial Hall", "Middlesex House", "Montague House", "Morrill 1", "Morrill 2", "Morrill 3", "Morrill 4", "Mullins Center", "Munson Hall", "Nelson House", "Nelson House II", "New Africa House", "Old Chapel", "Paige Laboratory", "Parking Office Trailer", "Parks Marching Band Building", "Photo Center", "Physical Plant", "Police Station", "Recreation Center", "Research Admininstration", "Renaissance Center", "Robsham Visitor's Center", "Shade Trees Laboratory", "Skinner Hall", "Slobody Bldg", "Slobody Bldg", "South College", "Stonewall Center", "Stockbridge Hall", "Student Union", "Thompson Hall", "Thoreau House (offices)", "Tillson House", "Tillson Farm", "Tobin Hall", "Toddler House", "Totman Bldg", "University Bus Garage", "University Press", "University Store", "W.E.B. Du Bois Library", "West Experiment Station", "Whitmore Bldg", "Wilder Hall", "Worcester Dining Commons", "Wysocki House", "Baker Hall", "Birch Hall", "Brett Hall", "Brooks Hall", "Brown Hall", "Butterfield Hall", "Cance Hall", "Cashin Hall", "Chadbourne Hall", "Coolidge Hall", "Crabtree Hall", "Crampton Hall", "Dickinson Hall", "Dwight Hall", "Elm Hall", "Emerson Hall", "Field Hall", "Gorman Hall", "Grayson Hall", "Greenough Hall", "Hamlin Hall", "James Hall", "John Adams Hall", "John Quincy Adams Hall", "Johnson Hall", "Kennedy Hall", "Knowlton Hall", "Leach Hall", "Lewis Hall", "Lincoln Apts", "Linden Hall", "Mackimmie Hall", "Maple Hall", "Mary Lyon Hall", "McNamara Hall", "Melville Hall", "Moore Hall", "North Residence A", "North Residence B", "North Residence C", "North Residence D", "North Village Apts. (family housing)", "Oak Hall", "Patterson Hall", "Pierpont Hall", "Prince Hall", "Sycamore Hall", "Thatcher Hall", "Thoreau Hall", "Van Meter Hall", "Washington Hall", "Webster Hall", "Wheeler Hall"};
    static char dirflag = 'w';
    static String[] addresses = {"250 NATURAL RESOURCES RD", "101 COMMONWEALTH AVE", "715 N PLEASANT", "110 THATCHER RD", "31 COLD STORAGE DR", "160 CLARK HILL RD OFC", "130 HICKS WAY", "121 COUNTY CIR", "121 SOUTHWEST CIR", "113 GRINNELL WAY", "201 NATURAL RESOURCES RD", "31 CLUBHOUSE DR", "131 COMMONWEALTH AVE", "151 INFIRMARY WAY OFC", "1 CAMPUS CENTER WAY", "191 FEARING OFC", "200 MULLINS WAY", "150 CHANCELLORS DR", "100 HOLDSWORTH WAY", "251 STOCKBRIDGE RD", "111 THATCHER RD OFC 3", "157 COMMONWEALTH AVE", "358 N PLEASANT ST", "140 GOVERNORS DR", "30 CAMPUS CENTER SERVICE RD", "120 GOVERNORS DR", "100 VENTURE WAY, SUITE 201, HADLEY MA", "100 HICKS WAY", "155 HICKS WAY", "40 CAMPUS CENTER WAY", "671 N PLEASANT ST", "160 GOVERNORS DR", "101 NORTH SERVICE RD", "243 STOCKBRIDGE RD", "41 CLUBHOUSE DR", "270 STOCKBRIDGE RD", "151 PRESIDENTS DR OFC 1", "151 PRESIDENTS DR OFC 2", "90 CAMPUS CENTER WAY", "260 STOCKBRIDGE RD", "230 STOCKBRIDGE RD", "813 N PLEASANT ST", "686 N PLEASANT ST", "140 HICKS WAY", "140 HICKS WAY OFC 1", "140 HICKS WAY OFC 2", "418 N PLEASANT ST", "121 NATURAL RESOURCES RD", "131 SOUTHWEST CIR ", "141 SOUTHWEST CIR", "131 COUNTY CIR", "666 N PLEASANT ST", "140 HOLDSWORTH WAY", "150 INFIRMARY WAY", "161 PRESIDENTS DR", "388 NO PLEASANT ST #15", "111 THATCHER RD OFC 1", "111 THATCHER RD OFC 2", "160 HOLDSWORTH WAY", "121 PRESIDENTS DR", "661 N PLEASANT ST", "171 FEARING ST OFC", "380 THATCHER RD OFC", "151 HOLDSWORTH WAY", "740 N PLEASANT ST", "710 N PLEASANT ST", "240 THATCHER RD", "240 HICKS WAY", "100 NATURAL RESOURCES RD", "130 NATURAL RESOURCES RD", "100 VENTURE WAY (HADLEY, MA)", "37 MATHER DR", "134 HICKS WAY", "111 COUNTY CIR", "809 N PLEASANT ST", "637 N PLEASANT ST", "627 N PLEASANT ST", "611 N PLEASANT ST", "639 N PLEASANT ST", "200 COMMONWEALTH AVE", "101 HICKS WAY", "513 EAST PLEASANT ST", "505 EAST PLEASANT ST", "180 INFIRMARY WAY", "144 HICKS WAY", "161 HOLDSWORTH WAY", "51 FORESTRY WAY", "110 Grinnell Way", "211 HICKS WAY", "360 CAMPUS CENTER WAY", "585 EAST PLEASANT ST", "161 COMMONWEALTH AVE", "70 BUTTERFIELD TERR", "650 E PLEASANT ST", "300 MASSACHUSETTS AVE", "245 STOCKBRIDGE RD", "651 N PLEASANT ST", "101 UNIVERSITY DR SUITE B", "101 UNIVERSITY DR SUITE C", "150 HICKS WAY", "256 SUNSET AVE OFC", "80 CAMPUS CENTER WAY", "41 CAMPUS CENTER WAY", "200 HICKS WAY", "640 MASSACHUSETTS AVE OFC", "23 TILLSON FARM RD", "151 TILLSON FARM RD", "135 HICKS WAY", "21 CLUBHOUSE DR", "30 EASTMAN LN", "255 GOVERNORS DR", "671 N PLEASANT ST", "1 CAMPUS CENTER WAY OFC", "154 HICKS WAY", "682 N PLEASANT ST", "181 PRESIDENTS DR", "221 STOCKBRIDGE RD", "669 N PLEASANT ST", "911 N PLEASANT ST", "160 CLARK HILL RD", "153 COMMONWEALTH AVE", "151 INFIRMARY WAY", "160 INFIRMARY WAY", "92 EASTMAN LN", "171 CLARK HILL RD", "191 FEARING ST", "112 EASTMAN LN", "110 ORCHARD HILL DR", "630 MASSACHUSETTS AVE", "17 EASTMAN LN", "256 SUNSET AVE", "151 ORCHARD HILL DR", "41 EASTMAN LN", "145 COMMONWEALTH AVE", "151 SOUTHWEST CIR", "171 ORCHARD HILL DR", "90 BUTTERFIELD TER", "161 ORCHARD HILL DR", "120 ORCHARD HILL DR", "739 N PLEASANT ST", "660 MASSACHUSETTS AVE", "161 FEARING ST", "171 FEARING ST", "380 THATCHER RD", "620 MASSACHUSETTS AVE", "691 N PLEASANT ST", "21 EASTMAN LN", "340 THATCHER RD", "345 LINCOLN AVE", "141 COMMONWEALTH AVE", "230 SUNSET AVE", "151 COMMONWEALTH AVE", "43 EASTMAN LN", "102 EASTMAN LN", "650 MASSACHUSETTS AVE", "111 SOUTHWEST CIR", "56 EASTMAN LN", "58 EASTMAN LN", "54 EASTMAN LN", "52 EASTMAN LN", "990 N PLEASANT ST", "143 COMMONWEALTH AVE", "204 SUNSET AVE", "201 FEARING ST", "286 SUNSET AVE", "159 COMMONWEALTH AVE", "300 THATCHER RD", "640 MASSACHUSETTS AVE", "180 CLARK HILL RD", "181 FEARING ST", "141 ORCHARD HILL DR", "171 INFIRMARY WAY"};

	public static final String TAG = "umanav";
	public static final String PREFS_NAME = "umanav";

//	private Button mAddAccountButton;
	private ListView mContactList;
	private static boolean mSortAlts;
//	private CheckBox mShowInvisibleControl;
	private EditText mNarrowDown;
	private UMassNav instance;

	static MenuItem mens1, mens2, mens3, mens4;

	String shortcode;
	AdView adView;


	ArrayList<String> contactslist;
	ArrayList<String> sortedlist;

	/**
	 * Called when the activity is first created. Responsible for initializing the UI.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.v(TAG, "Activity State: onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_umass_nav);

		instance=this;

		SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
		dirflag = settings.getString("dirflag", "w").charAt(0);

		// Obtain handles to UI objects
		//		mAddAccountButton = (Button) findViewById(R.id.addContactButton);
		mContactList = (ListView) findViewById(R.id.list_view);
		//		mShowInvisibleControl = (CheckBox) findViewById(R.id.showInvisible);
		mNarrowDown = (EditText) findViewById(R.id.inputSearch);

		// Fast scroll
		mContactList.setFastScrollEnabled(true); 

		//		mAddAccountButton.setText(mAddAccountButton.getText()+" ("+"*67"+")");

		mNarrowDown.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);


		// Initialize class properties
		//		mShowInvisibleControl.setChecked(mSortAlts);

		mNarrowDown.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) 
			{
				filterText();

			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			public void onTextChanged(CharSequence s, int start, int before, int count){}
		}); 

		// Register handler for UI elements
		//		mens2.setOnMenuItemClickListener(new View.OnClickListener() {
		//			public void onClick(View v) {
		//				Log.d(TAG, "mAddAccountButton clicked");
		//				//				launchContactAdder();
		//			}
		//		});

		mContactList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				callNumber(position);

			}});

		//		mShowInvisibleControl.setOnI can CheckedChangeListener(new OnCheckedChangeListener() {
		//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		//				Log.d(TAG, "mShowInvisibleControl changed: " + isChecked);
		//				mSortAlts = isChecked;
		//				populateContactList();
		//			}
		//		});


		// Admob Code
		// Create the adView

//		try
//		{
		 adView = new AdView(this);
		    adView.setAdUnitId("ca-app-pub-8148658375496745/8944328100");
		    adView.setAdSize(AdSize.BANNER);

		    // Lookup your LinearLayout assuming it's been given
		    // the attribute android:id="@+id/mainLayout".
		    LinearLayout layout = (LinearLayout)findViewById(R.id.adview);

		    // Add the adView to it.
		    layout.addView(adView);

		    // Initiate a generic request.
		    AdRequest adRequest = new AdRequest.Builder().build();

		    // Load the adView with the ad request.
		    adView.loadAd(adRequest);

//		}
//		catch (Exception e)
//		{
//
//		}


		//	    RelativeLayout layout2 = (RelativeLayout)findViewById(R.id.relish);


		// Populate the contact list
		populateContactList();
	}

	public void filterText()
	{
		sortedlist = new ArrayList<String>();

		String searchQuery = mNarrowDown.getText().toString();

		if (!searchQuery.equals(""))
		{

			for (String name : contactslist)
			{

				if (name.toLowerCase().contains(searchQuery.toLowerCase()))
				{
					sortedlist.add(name);
				}

			}
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(instance,android.R.layout.simple_list_item_1, sortedlist);
			mContactList.setAdapter(arrayAdapter);
		}
		else populateContactList();
	}

	public void changeHeader()
	{
		int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		TextView yourTextView = (TextView)findViewById(titleId);
		if (yourTextView==null) return;

		String oldstring = yourTextView.getText().toString();

		String s = "";

		for (char c : oldstring.toCharArray())
		{
			s+=c;
			if (c=='y' || c=='l' || c=='e') break;
		}
		yourTextView.setText(s+" "+shortcode);
	}



	/**
	 * Populate the contact list based on account currently selected in the account spinner.
	 */
	private void populateContactList() {

		contactslist = new ArrayList<String>();

		for (int x=0; x<buildings.length; x++)
		{
			contactslist.add(buildings[x]+"\n"+addresses[x]);
		}

//		Collections.sort(contactslist);

		ArrayAdapter<String> arrayAdapter =      
			new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, contactslist);

		if (!mNarrowDown.getText().toString().equals(""))
		{
			filterText();
			return;
		}

		mContactList.setAdapter(arrayAdapter);
	}

	/**
	 * Launches the ContactAdder activity to add a new contact to the selected accont.
	 */
	//	protected void launchContactAdder() {
	//		Intent i = new Intent(this, ContactAdder.class);
	//		startActivity(i);
	//	}

	public void callNumber(int num)
	{
		Intent i = new Intent(Intent.ACTION_VIEW);
		
		String s = contactslist.get(num);
		if (!mNarrowDown.getText().toString().equals(""))
			s = sortedlist.get(num).toString();

		boolean foundit = false;
		String t = "";

		for (char c : s.toCharArray())
		{
			if (foundit)
				t+=c;
			else
				if (c=='\n')
					foundit=true;
		}
		
		t += " AMHERST, MA";
		
		String url = "http://maps.google.com/maps?saddr=Current+Location&daddr="+t.replaceAll(" ","%20")+"&dirflg="+dirflag;
		
		i.setData(Uri.parse(url));
		startActivity(i);

	}

	public static boolean sortAlt()
	{
		return mSortAlts;
	}

	@Override
	public void onStop()
	{
		super.onStop();

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();

		editor.putString("dirflag", ""+dirflag);

		editor.commit();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		mens1 = menu.add(0, 1, 2, "Walking");
		mens1.setCheckable(true);
		mens1.setChecked(dirflag == 'w');
		mens2 = menu.add(0, 2, 2, "Bus");
		mens2.setCheckable(true);
		mens2.setChecked(dirflag == 'r');
		mens3 = menu.add(0, 3, 2, "Car");
		mens3.setCheckable(true);
		mens3.setChecked(dirflag == 'v');
		mens4 = menu.add(0, 4, 2, "Bike");
		mens4.setCheckable(true);
		mens4.setChecked(dirflag == 'b');
//		changeHeader();
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.v("item id", ""+item.getItemId());
		switch (item.getItemId()) {
	        case 1:
	        	dirflag = 'w';
	        	break;
	        case 2:
	        	dirflag = 'r';
	        	break;
	        case 3:
	        	dirflag = 'v';
	        	break;
	        case 4:
	        	dirflag = 'b';
	        	break;
	        default:
	            return super.onOptionsItemSelected(item);
		}
		
		mens1.setChecked(dirflag == 'w');
		mens2.setChecked(dirflag == 'r');
		mens3.setChecked(dirflag == 'v');
		mens4.setChecked(dirflag == 'b');


		
		return true;
	}
}