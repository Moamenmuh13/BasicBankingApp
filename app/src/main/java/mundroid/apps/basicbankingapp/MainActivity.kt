package mundroid.apps.basicbankingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import mundroid.apps.basicbankingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataSource: ArrayList<Customer>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataSource = DataSource().getLocalData()
        initViews()
    }

    private fun initViews() {
        binding.recyclerView.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.adapter = CustomerAdapter(dataSource, this)
    }
}