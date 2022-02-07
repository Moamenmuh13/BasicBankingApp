package mundroid.apps.basicbankingapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private const val TAG = "DatabaseHandler"

        private const val DATABASE_NAME = "CustomerDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_CONTACTS = " CustomerTable"

        private const val KEY_ID = "_id"
        private const val KEY_name = "name"
        private const val KEY_email = "email"
        private const val KEY_personalID = "personalID"
        private const val KEY_currentBalance = "currentBalance"
        private const val KEY_cvv = "csv"
        private const val KEY_cardNum = "cardNum"
        private const val KEY_cardType = "cardType"
    }

    //creating table with fields

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE =
            ("Create Table " + TABLE_CONTACTS + " (" + KEY_ID + " INTEGER PRIMARY KEY, "
                    + KEY_name + " TEXT, "
                    + KEY_email + " TEXT, "
                    + KEY_personalID + " TEXT, "
                    + KEY_currentBalance + " TEXT, "
                    + KEY_cvv + " TEXT, "
                    + KEY_cardNum + " TEXT, "
                    + KEY_cardType + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    // add user
    fun addUser(customer: Customer): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_name, customer.name) // inserting name
        contentValues.put(KEY_email, customer.email) // inserting email
        contentValues.put(KEY_personalID, customer.personalID) // inserting personalID
        contentValues.put(
            KEY_currentBalance,
            customer.currentBalance + ""
        ) // inserting currentBalance
        contentValues.put(KEY_cvv, customer.cvv + "") // inserting cvv
        contentValues.put(KEY_cardNum, customer.cardNum + "") // inserting cardNum
        contentValues.put(KEY_cardType, customer.cardType) // inserting cardType


        // inserting row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }


    // add user
//    fun updateUser(customer: Customer): Int {
//        val db = this.writableDatabase
//
//        val contentValues = ContentValues()
//        contentValues.put(KEY_name, customer.name) // inserting name
//        contentValues.put(KEY_email, customer.email) // inserting email
//        contentValues.put(KEY_personalID, customer.personalID) // inserting personalID
//        contentValues.put(KEY_currentBalance, customer.currentBalance) // inserting currentBalance
//        contentValues.put(KEY_cvv, customer.cvv) // inserting cvv
//        contentValues.put(KEY_cardNum, customer.cardNum) // inserting cardNum
//        contentValues.put(KEY_cardType, customer.cardType) // inserting cardType
//
//
//        // inserting row
//        val success = db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=" + customer.id, null)
//        db.close()
//        return success
//    }

    //read data
    @SuppressLint("Range")
    fun getData(): ArrayList<Customer> {
        val customerList: ArrayList<Customer> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            Log.d(TAG, "getData: $e")
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String
        var currentBalance: String
        var personalID: String
        var cvv: String
        var cardNum: String
        var cardType: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_name))
                email = cursor.getString(cursor.getColumnIndex(KEY_email))
                currentBalance = cursor.getString(cursor.getColumnIndex(KEY_currentBalance))
                personalID = cursor.getString(cursor.getColumnIndex(KEY_personalID))
                cvv = cursor.getString(cursor.getColumnIndex(KEY_cvv))
                cardNum = cursor.getString(cursor.getColumnIndex(KEY_cardNum))
                cardType = cursor.getString(cursor.getColumnIndex(KEY_cardType))
                val user = Customer(
                    id,
                    name,
                    email,
                    currentBalance,
                    personalID,
                    cvv,
                    cardNum,
                    cardType
                )
                customerList.add(user)
            } while (cursor.moveToFirst())
        }
        return customerList
    }
}