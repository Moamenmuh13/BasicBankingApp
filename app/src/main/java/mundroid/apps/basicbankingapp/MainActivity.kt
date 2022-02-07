package mundroid.apps.basicbankingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import mundroid.apps.basicbankingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataSource: ArrayList<Customer>
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataSource = DatabaseHandler(this).getData()
        Log.d(TAG, "onCreate: $dataSource")
    }

//    private fun initViews() {
//        binding.loadBtn.setOnClickListener(this)
//        binding.recyclerView.layoutManager =
//            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
//        binding.recyclerView.setHasFixedSize(true)
//        binding.recyclerView.adapter = CustomerAdapter(getItemList(), this)
//
//    }
//
//    private fun getItemList(): ArrayList<Customer> {
//        val databaseHandler = DatabaseHandler(this)
//        return databaseHandler.getData()
//    }
//
//    private fun setItemInRecyclerView() {
//        if (getItemList().size > 0) {
//            initViews()
//        } else
//            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onClick(v: View?) {
//        when(v){
//            binding.loadBtn ->{
//                setItemInRecyclerView()
//            }
//        }
//    }


}