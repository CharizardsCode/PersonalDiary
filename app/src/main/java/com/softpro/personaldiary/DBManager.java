package com.softpro.personaldiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by User on 15-Jul-16.
 */
public class DBManager {
    DBOpenHelper dbOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public DBManager(Context context) {
        dbOpenHelper = new DBOpenHelper
                (context, DBConstants.DBNAME, null, DBConstants.DB_VERSION);

    }

    private void openDatabase() {
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }

    private void closeDatabase() {
        sqLiteDatabase.close();
    }

    public long saveData(UserBean userBean)

    {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(DBConstants.EMAIL, userBean.getEmail());
        values.put(DBConstants.NAME, "");
        values.put(DBConstants.DOB, "");
        values.put(DBConstants.ADDRESS, "");
        values.put(DBConstants.PHONE, "");
        values.put(DBConstants.BLOODGROUP, "");
        values.put(DBConstants.PAN, "");
        values.put(DBConstants.FAX, "");
        values.put(DBConstants.PASSWORD, userBean.getPassword());
        values.put(DBConstants.BACKGROUND, R.drawable.diary_page);


        long rowId = sqLiteDatabase.insert(DBConstants.TABLE_NAME, null, values);


        closeDatabase();

        return rowId;


    }

    public long updateContent(UserBean userBean, String email, String date)

    {
        openDatabase();

        ContentValues values = new ContentValues();

        values.put(DBConstants.CONTENT, userBean.getContent());


        long rowId = sqLiteDatabase.update(DBConstants.SECOND_TABLE_NAME, values, "email=? and date=?", new String[]{email, date});


        closeDatabase();

        return rowId;


    }

    public long saveContent(UserBean userBean)

    {
        openDatabase();

        ContentValues values = new ContentValues();

        values.put(DBConstants.CONTENT, userBean.getContent());
        values.put(DBConstants.EMAIL, userBean.getEmail());
        values.put(DBConstants.DATE, userBean.getDate());
        long rowId = sqLiteDatabase.insert(DBConstants.SECOND_TABLE_NAME, null, values);


        closeDatabase();

        return rowId;


    }

    public long saveContact(UserBean userBean)

    {
        openDatabase();

        ContentValues values = new ContentValues();

        values.put(DBConstants.CONTACTNAME, userBean.getContactName());
        values.put(DBConstants.EMAIL, userBean.getEmail());
        values.put(DBConstants.CONTACTNUMBER, userBean.getContactNumber());
        long rowId = sqLiteDatabase.insert(DBConstants.THIRD_TABLE_NAME, null, values);


        closeDatabase();

        return rowId;


    }

    public String checkLogin(String email) {
        openDatabase();
        String password = " ";
        Cursor cursor = sqLiteDatabase.query(DBConstants.TABLE_NAME, new String[]{DBConstants.EMAIL, DBConstants.PASSWORD}, "email=?", new String[]{email}, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            password = cursor.getString(cursor.getColumnIndex(DBConstants.PASSWORD));
        }
        closeDatabase();
        return password;
    }

    public String[] fetchValues(String email) {
        openDatabase();
        String[] values = new String[7];
        Cursor cursor = sqLiteDatabase.query(DBConstants.TABLE_NAME, new String[]{DBConstants.NAME, DBConstants.DOB, DBConstants.PHONE, DBConstants.BLOODGROUP, DBConstants.ADDRESS, DBConstants.FAX, DBConstants.PAN}, "email=?", new String[]{email}, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            values[0] = cursor.getString(cursor.getColumnIndex(DBConstants.NAME));
            values[1] = cursor.getString(cursor.getColumnIndex(DBConstants.DOB));
            values[2] = cursor.getString(cursor.getColumnIndex(DBConstants.ADDRESS));
            values[3] = cursor.getString(cursor.getColumnIndex(DBConstants.PHONE));
            values[4] = cursor.getString(cursor.getColumnIndex(DBConstants.BLOODGROUP));
            values[5] = cursor.getString(cursor.getColumnIndex(DBConstants.PAN));
            values[6] = cursor.getString(cursor.getColumnIndex(DBConstants.FAX));
        }
        closeDatabase();
        return values;

    }

    public String showContent(String email, String date) {
        openDatabase();
        String values = " ";
        Cursor cursor = sqLiteDatabase.query(DBConstants.SECOND_TABLE_NAME, new String[]{DBConstants.CONTENT}, "email=? and date=?", new String[]{email, date}, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            values = cursor.getString(cursor.getColumnIndex(DBConstants.CONTENT));
        }
        closeDatabase();
        return values;

    }
    public String[] showDiaryContent(String email) {
        openDatabase();
        int i=0;
        String date="",content="";
        String[] values=new String[10000];
        while (i < 10000) {
            values[i] = "";
            i = i + 1;
        }
        i = 0;
        Cursor cursor = sqLiteDatabase.query(DBConstants.SECOND_TABLE_NAME, new String[]{DBConstants.CONTENT,DBConstants.DATE}, "email=?", new String[]{email}, null, null, null);
        while(cursor != null && cursor.moveToNext()) {
            content= cursor.getString(cursor.getColumnIndex(DBConstants.CONTENT));
            date=cursor.getString(cursor.getColumnIndex(DBConstants.DATE));
            values[i]=date+"     "+content;
            i=i+1;
        }
        closeDatabase();
        return values;

    }

    public String[] viewContacts(String email) {
        openDatabase();
        String[] contacts = new String[3000];
        String contactName = "", contactNumber = "";
        int i = 0;
        while (i < 3000) {
            contacts[i] = "";
            i = i + 1;
        }
        i = 0;
        Cursor cursor = sqLiteDatabase.query(DBConstants.THIRD_TABLE_NAME, new String[]{DBConstants.CONTACTNAME, DBConstants.CONTACTNUMBER}, "email=?", new String[]{email}, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            contactName = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACTNAME));
            contactNumber = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACTNUMBER));
            contacts[i] = contactName + "   " + contactNumber;
            i = i + 1;
        }
        closeDatabase();
        return contacts;

    }

    public long updateData(UserBean userBean, String email) {
        openDatabase();


        ContentValues values = new ContentValues();
        values.put(DBConstants.NAME, userBean.getName());
        values.put(DBConstants.DOB, userBean.getDOB());
        values.put(DBConstants.ADDRESS, userBean.getAddress());
        values.put(DBConstants.PHONE, userBean.getPhone());
        values.put(DBConstants.BLOODGROUP, userBean.getBloodGroup());
        values.put(DBConstants.PAN, userBean.getPan());
        values.put(DBConstants.FAX, userBean.getFax());
        long rowId = sqLiteDatabase.update(DBConstants.TABLE_NAME, values, "email=?", new String[]{email});
        closeDatabase();
        return rowId;
    }

    public long updatePassword(UserBean userBean) {
        openDatabase();
        String email;
        email = userBean.getEmail();
        ContentValues values = new ContentValues();
        values.put(DBConstants.PASSWORD, userBean.getPassword());
        long rowId = sqLiteDatabase.update(DBConstants.TABLE_NAME, values, "email=?", new String[]{email});
        closeDatabase();
        return rowId;
    }


    public Boolean checkAuthentication(String name, String dob, String email, String phone, String bloodGroup) {
        openDatabase();
        String dbname = "", dbemail = "", dbphone = "", dbbloodGroup = "", dbdob = "";
        Cursor cursor = sqLiteDatabase.query(DBConstants.TABLE_NAME, new String[]{DBConstants.NAME, DBConstants.DOB, DBConstants.EMAIL, DBConstants.PHONE, DBConstants.BLOODGROUP}, "email=?", new String[]{email}, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            dbname = cursor.getString(cursor.getColumnIndex(DBConstants.NAME));
            dbdob = cursor.getString(cursor.getColumnIndex(DBConstants.DOB));
            dbemail = cursor.getString(cursor.getColumnIndex(DBConstants.EMAIL));
            dbphone = cursor.getString(cursor.getColumnIndex(DBConstants.PHONE));
            dbbloodGroup = cursor.getString(cursor.getColumnIndex(DBConstants.BLOODGROUP));
        }
        closeDatabase();
        if (dbname.equalsIgnoreCase(name) && dbdob.equalsIgnoreCase(dob) && dbemail.equalsIgnoreCase(email) && dbphone.equalsIgnoreCase(phone) && dbbloodGroup.equalsIgnoreCase(bloodGroup))
            return true;
        else
            return false;
    }

    public Boolean checkEmail(String email) {
        openDatabase();
        boolean exist = false;
        Cursor cursor = sqLiteDatabase.query(DBConstants.TABLE_NAME, new String[]{DBConstants.EMAIL}, "email=?", new String[]{email}, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            String dbemail = cursor.getString(cursor.getColumnIndex(DBConstants.EMAIL));
            if (!dbemail.equals("")) {
                exist = true;
            }

        } else
            exist = false;
        closeDatabase();
        return exist;
    }
   /* public Boolean checkContacts(String email) {
        openDatabase();
        boolean exist = false;
        Cursor cursor = sqLiteDatabase.query(DBConstants.THIRD_TABLE_NAME, new String[]{DBConstants.CONTACTNAME}, "email=?", new String[]{email}, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            String dbcontact = cursor.getString(cursor.getColumnIndex(DBConstants.CONTACTNAME));
            if (!dbcontact.equals("")) {
                exist = true;
            }

        } else
            exist = false;
        closeDatabase();
        return exist;
    }*/
}
