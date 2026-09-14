package com.marvel.app;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    LinearLayout root, content;
    ArrayList<String[]> customers = new ArrayList<>();

    public void onCreate(Bundle b){super.onCreate(b); showHome();}

    TextView title(String s,int size){
        TextView t=new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(Color.BLACK);
        t.setPadding(20,18,20,12); return t;
    }
    Button btn(String s, View.OnClickListener l){
        Button b=new Button(this); b.setText(s); b.setOnClickListener(l); return b;
    }
    void base(String heading){
        root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.WHITE);
        root.addView(title("MARVEL",28)); root.addView(title(heading,22));
        ScrollView sv=new ScrollView(this); content=new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL);
        sv.addView(content); root.addView(sv,new LinearLayout.LayoutParams(-1,0,1)); setContentView(root);
    }
    void showHome(){
        base("Real Estate");
        content.addView(title("Find your next property",20));
        content.addView(btn("🏠 Properties",v->showProperties()));
        content.addView(btn("➕ Add Property",v->showAddProperty()));
        content.addView(btn("👤 Customer Details",v->showAddCustomer()));
        content.addView(btn("📋 Customers",v->showCustomers()));
    }
    void showProperties(){
        base("Properties");
        String[][] p={{"2 BHK Apartment","₹45 Lakhs","Thanjavur"},{"Independent House","₹68 Lakhs","Trichy"},{"Residential Land","₹25 Lakhs","Kumbakonam"}};
        for(String[] x:p){
            content.addView(title(x[0]+"  •  "+x[1],19)); content.addView(title("📍 "+x[2],16));
            content.addView(btn("View Details",v->showDetails(x[0],x[1],x[2])));
        }
        content.addView(btn("← Home",v->showHome()));
    }
    void showDetails(String n,String price,String loc){
        base("Property Details");
        content.addView(title(n,24)); content.addView(title("Price: "+price,19)); content.addView(title("Location: "+loc,18));
        content.addView(title("Bedrooms: 2   Bathrooms: 2   Area: 1200 sqft",17));
        content.addView(title("Description: Premium property suitable for family living.",17));
        content.addView(btn("📞 Contact",v->{Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:")); startActivity(i);}));
        content.addView(btn("← Properties",v->showProperties()));
    }
    EditText field(String hint){
        EditText e=new EditText(this); e.setHint(hint); e.setPadding(20,12,20,12); content.addView(e); return e;
    }
    void showAddProperty(){
        base("Add Property");
        field("Property title"); field("Price"); field("Property type"); field("Location"); field("Area / sqft"); field("Description"); field("Contact number");
        content.addView(btn("SAVE PROPERTY",v->{Toast.makeText(this,"Property saved",Toast.LENGTH_SHORT).show(); showProperties();}));
        content.addView(btn("← Home",v->showHome()));
    }
    void showAddCustomer(){
        base("Customer Details");
        EditText name=field("Customer name"); EditText phone=field("Mobile number"); field("Email"); field("Requirement: Buy / Rent"); field("Property type"); field("Preferred location"); field("Budget"); EditText notes=field("Notes");
        content.addView(btn("SAVE CUSTOMER",v->{customers.add(new String[]{name.getText().toString(),phone.getText().toString(),notes.getText().toString()}); Toast.makeText(this,"Customer saved",Toast.LENGTH_SHORT).show(); showCustomers();}));
        content.addView(btn("← Home",v->showHome()));
    }
    void showCustomers(){
        base("Customers");
        if(customers.size()==0) content.addView(title("No customers added yet.",18));
        for(String[] c:customers){ content.addView(title("👤 "+c[0],19)); content.addView(title("📱 "+c[1],16)); content.addView(title("📝 "+c[2],15)); content.addView(btn("Call",v->{Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+c[1]));startActivity(i);}));}
        content.addView(btn("➕ Add Customer",v->showAddCustomer())); content.addView(btn("← Home",v->showHome()));
    }
}