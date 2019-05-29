package com.example.exe4main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MyCallActivity extends AppCompatActivity {
    EditText editName,editPhone;
    Button btnSave;
    ListView lvContact;

    ArrayList<MyContact> arrContact=new ArrayList<MyContact>();
    ArrayAdapter<MyContact> adapter=null;
    MyContact selectedContact=null;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.call);

        setControl();

        setEvent();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.phonecontextmenu, menu);
        menu.setHeaderTitle("Call- Sms");
        menu.getItem(0).setTitle("Call to "+selectedContact.getPhone());
        menu.getItem(1).setTitle("Send sms to "+selectedContact.getPhone());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.mnuCall:
                doMakeCall();
                break;
            case R.id.mnuSms:
                doMakeSms();
                break;
            case R.id.mnuRemove:
                arrContact.remove(selectedContact);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void doMakeSms() {
        Intent i=new Intent(this, MySMSActivity.class);
        Bundle b=new Bundle();
        b.putSerializable("CONTACT", selectedContact);
        i.putExtra("DATA", b);
        startActivity(i);
    }

    private void doMakeCall() {
        Uri uri=Uri.parse("tel:"+selectedContact.getPhone());
        Intent i=new Intent(Intent.ACTION_CALL, uri);
        startActivity(i);
    }

    private void setEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveContact();
            }
        });
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedContact=arrContact.get(position);
                return false;
            }
        });
    }

    private void doSaveContact() {
        MyContact ct=new MyContact();
        ct.setName(editName.getText()+"");
        ct.setPhone(editPhone.getText()+"");
        arrContact.add(ct);
        adapter.notifyDataSetChanged();
    }

    private void setControl() {
        btnSave=(Button) findViewById(R.id.btnSaveContact);
        editName=(EditText) findViewById(R.id.editName);
        editPhone=(EditText) findViewById(R.id.editPhone);
        lvContact=(ListView) findViewById(R.id.lvContact);
        //tạo đối tượng adapter
        adapter=new ArrayAdapter<MyContact>(this, android.R.layout.simple_list_item_1,arrContact);
        //gán Adapter vào cho ListView
        lvContact.setAdapter(adapter);
        //thiết lập contextmenu cho ListView
        registerForContextMenu(lvContact);
    }
}
