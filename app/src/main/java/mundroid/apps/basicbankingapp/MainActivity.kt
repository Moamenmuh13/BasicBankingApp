package mundroid.apps.basicbankingapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import mundroid.apps.basicbankingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Log.d(TAG, "onCreate: " + getItemList())
        initViews()
        setItemInRecyclerView()
    }

    private fun initViews() {
        binding.recyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)

    }

    private fun setItemInRecyclerView() {
        if (getItemList().size > 0) {
            binding.recyclerView.adapter = CustomerAdapter(getItemList(), this)
        } else
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show()
    }

    private fun getItemList(): ArrayList<Customer> {
        val databaseHandler = DatabaseHandler(this)
        return databaseHandler.getData()
    }


}
